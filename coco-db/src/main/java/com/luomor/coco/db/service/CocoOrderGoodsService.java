package com.luomor.coco.db.service;

import com.luomor.coco.db.dao.CocoOrderGoodsMapper;
import com.luomor.coco.db.domain.CocoOrderGoods;
import com.luomor.coco.db.domain.CocoOrderGoodsExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoOrderGoodsService {
    @Resource
    private CocoOrderGoodsMapper orderGoodsMapper;

    public int add(CocoOrderGoods orderGoods) {
        orderGoods.setAddTime(LocalDateTime.now());
        orderGoods.setUpdateTime(LocalDateTime.now());
        return orderGoodsMapper.insertSelective(orderGoods);
    }

    public List<CocoOrderGoods> queryByOid(Integer orderId) {
        CocoOrderGoodsExample example = new CocoOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    public List<CocoOrderGoods> findByOidAndGid(Integer orderId, Integer goodsId) {
        CocoOrderGoodsExample example = new CocoOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.selectByExample(example);
    }

    public CocoOrderGoods findById(Integer id) {
        return orderGoodsMapper.selectByPrimaryKey(id);
    }

    public void updateById(CocoOrderGoods orderGoods) {
        orderGoods.setUpdateTime(LocalDateTime.now());
        orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }

    public Short getComments(Integer orderId) {
        CocoOrderGoodsExample example = new CocoOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        long count = orderGoodsMapper.countByExample(example);
        return (short) count;
    }

    public boolean checkExist(Integer goodsId) {
        CocoOrderGoodsExample example = new CocoOrderGoodsExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return orderGoodsMapper.countByExample(example) != 0;
    }

    public void deleteByOrderId(Integer orderId) {
        CocoOrderGoodsExample example = new CocoOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        orderGoodsMapper.logicalDeleteByExample(example);
    }
}
