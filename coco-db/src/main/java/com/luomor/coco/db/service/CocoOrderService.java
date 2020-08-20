package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoOrderMapper;
import com.luomor.coco.db.dao.OrderMapper;
import com.luomor.coco.db.domain.CocoOrder;
import com.luomor.coco.db.domain.CocoOrderExample;
import com.luomor.coco.db.util.OrderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class CocoOrderService {
    @Resource
    private CocoOrderMapper cocoOrderMapper;
    @Resource
    private OrderMapper orderMapper;

    public int add(CocoOrder order) {
        order.setAddTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        return cocoOrderMapper.insertSelective(order);
    }

    public int count(Integer userId) {
        CocoOrderExample example = new CocoOrderExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return (int) cocoOrderMapper.countByExample(example);
    }

    public CocoOrder findById(Integer orderId) {
        return cocoOrderMapper.selectByPrimaryKey(orderId);
    }

    public CocoOrder findById(Integer userId, Integer orderId) {
        CocoOrderExample example = new CocoOrderExample();
        example.or().andIdEqualTo(orderId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return cocoOrderMapper.selectOneByExample(example);
    }

    private String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public int countByOrderSn(Integer userId, String orderSn) {
        CocoOrderExample example = new CocoOrderExample();
        example.or().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return (int) cocoOrderMapper.countByExample(example);
    }

    // TODO 这里应该产生一个唯一的订单，但是实际上这里仍然存在两个订单相同的可能性
    public String generateOrderSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String orderSn = now + getRandomNum(6);
        while (countByOrderSn(userId, orderSn) != 0) {
            orderSn = now + getRandomNum(6);
        }
        return orderSn;
    }

    public List<CocoOrder> queryByOrderStatus(Integer userId, List<Short> orderStatus, Integer page, Integer limit, String sort, String order) {
        CocoOrderExample example = new CocoOrderExample();
        example.setOrderByClause(CocoOrder.Column.addTime.desc());
        CocoOrderExample.Criteria criteria = example.or();
        criteria.andUserIdEqualTo(userId);
        if (orderStatus != null) {
            criteria.andOrderStatusIn(orderStatus);
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return cocoOrderMapper.selectByExample(example);
    }

    public List<CocoOrder> querySelective(Integer userId, String orderSn, LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        CocoOrderExample example = new CocoOrderExample();
        CocoOrderExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(orderSn)) {
            criteria.andOrderSnEqualTo(orderSn);
        }
        if(start != null){
            criteria.andAddTimeGreaterThanOrEqualTo(start);
        }
        if(end != null){
            criteria.andAddTimeLessThanOrEqualTo(end);
        }
        if (orderStatusArray != null && orderStatusArray.size() != 0) {
            criteria.andOrderStatusIn(orderStatusArray);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return cocoOrderMapper.selectByExample(example);
    }

    public int updateWithOptimisticLocker(CocoOrder order) {
        LocalDateTime preUpdateTime = order.getUpdateTime();
        order.setUpdateTime(LocalDateTime.now());
        return orderMapper.updateWithOptimisticLocker(preUpdateTime, order);
    }

    public void deleteById(Integer id) {
        cocoOrderMapper.logicalDeleteByPrimaryKey(id);
    }

    public int count() {
        CocoOrderExample example = new CocoOrderExample();
        example.or().andDeletedEqualTo(false);
        return (int) cocoOrderMapper.countByExample(example);
    }

    public List<CocoOrder> queryUnpaid(int minutes) {
        CocoOrderExample example = new CocoOrderExample();
        example.or().andOrderStatusEqualTo(OrderUtil.STATUS_CREATE).andDeletedEqualTo(false);
        return cocoOrderMapper.selectByExample(example);
    }

    public List<CocoOrder> queryUnconfirm(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        CocoOrderExample example = new CocoOrderExample();
        example.or().andOrderStatusEqualTo(OrderUtil.STATUS_SHIP).andShipTimeLessThan(expired).andDeletedEqualTo(false);
        return cocoOrderMapper.selectByExample(example);
    }

    public CocoOrder findBySn(String orderSn) {
        CocoOrderExample example = new CocoOrderExample();
        example.or().andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return cocoOrderMapper.selectOneByExample(example);
    }

    public Map<Object, Object> orderInfo(Integer userId) {
        CocoOrderExample example = new CocoOrderExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        List<CocoOrder> orders = cocoOrderMapper.selectByExampleSelective(example, CocoOrder.Column.orderStatus, CocoOrder.Column.comments);

        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        for (CocoOrder order : orders) {
            if (OrderUtil.isCreateStatus(order)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(order)) {
                unship++;
            } else if (OrderUtil.isShipStatus(order)) {
                unrecv++;
            } else if (OrderUtil.isConfirmStatus(order) || OrderUtil.isAutoConfirmStatus(order)) {
                uncomment += order.getComments();
            } else {
                // do nothing
            }
        }

        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        return orderInfo;

    }

    public List<CocoOrder> queryComment(int days) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expired = now.minusDays(days);
        CocoOrderExample example = new CocoOrderExample();
        example.or().andCommentsGreaterThan((short) 0).andConfirmTimeLessThan(expired).andDeletedEqualTo(false);
        return cocoOrderMapper.selectByExample(example);
    }

    public void updateAftersaleStatus(Integer orderId, Short statusReject) {
        CocoOrder order = new CocoOrder();
        order.setId(orderId);
        order.setAftersaleStatus(statusReject);
        order.setUpdateTime(LocalDateTime.now());
        cocoOrderMapper.updateByPrimaryKeySelective(order);
    }
}
