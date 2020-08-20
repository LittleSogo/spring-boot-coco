package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoCartMapper;
import com.luomor.coco.db.domain.CocoCart;
import com.luomor.coco.db.domain.CocoCartExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoCartService {
    @Resource
    private CocoCartMapper cartMapper;

    public CocoCart queryExist(Integer goodsId, Integer productId, Integer userId) {
        CocoCartExample example = new CocoCartExample();
        example.or().andGoodsIdEqualTo(goodsId).andProductIdEqualTo(productId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return cartMapper.selectOneByExample(example);
    }

    public void add(CocoCart cart) {
        cart.setAddTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        cartMapper.insertSelective(cart);
    }

    public int updateById(CocoCart cart) {
        cart.setUpdateTime(LocalDateTime.now());
        return cartMapper.updateByPrimaryKeySelective(cart);
    }

    public List<CocoCart> queryByUid(int userId) {
        CocoCartExample example = new CocoCartExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return cartMapper.selectByExample(example);
    }


    public List<CocoCart> queryByUidAndChecked(Integer userId) {
        CocoCartExample example = new CocoCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true).andDeletedEqualTo(false);
        return cartMapper.selectByExample(example);
    }

    public int delete(List<Integer> productIdList, int userId) {
        CocoCartExample example = new CocoCartExample();
        example.or().andUserIdEqualTo(userId).andProductIdIn(productIdList);
        return cartMapper.logicalDeleteByExample(example);
    }

    public CocoCart findById(Integer id) {
        return cartMapper.selectByPrimaryKey(id);
    }

    public CocoCart findById(Integer userId, Integer id) {
        CocoCartExample example = new CocoCartExample();
        example.or().andUserIdEqualTo(userId).andIdEqualTo(id).andDeletedEqualTo(false);
        return cartMapper.selectOneByExample(example);
    }

    public int updateCheck(Integer userId, List<Integer> idsList, Boolean checked) {
        CocoCartExample example = new CocoCartExample();
        example.or().andUserIdEqualTo(userId).andProductIdIn(idsList).andDeletedEqualTo(false);
        CocoCart cart = new CocoCart();
        cart.setChecked(checked);
        cart.setUpdateTime(LocalDateTime.now());
        return cartMapper.updateByExampleSelective(cart, example);
    }

    public void clearGoods(Integer userId) {
        CocoCartExample example = new CocoCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true);
        CocoCart cart = new CocoCart();
        cart.setDeleted(true);
        cartMapper.updateByExampleSelective(cart, example);
    }

    public List<CocoCart> querySelective(Integer userId, Integer goodsId, Integer page, Integer limit, String sort, String order) {
        CocoCartExample example = new CocoCartExample();
        CocoCartExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(goodsId);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return cartMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        cartMapper.logicalDeleteByPrimaryKey(id);
    }

    public boolean checkExist(Integer goodsId) {
        CocoCartExample example = new CocoCartExample();
        example.or().andGoodsIdEqualTo(goodsId).andCheckedEqualTo(true).andDeletedEqualTo(false);
        return cartMapper.countByExample(example) != 0;
    }

    public void updateProduct(Integer id, String goodsSn, String goodsName, BigDecimal price, String url) {
        CocoCart cart = new CocoCart();
        cart.setPrice(price);
        cart.setPicUrl(url);
        cart.setGoodsSn(goodsSn);
        cart.setGoodsName(goodsName);
        CocoCartExample example = new CocoCartExample();
        example.or().andProductIdEqualTo(id);
        cartMapper.updateByExampleSelective(cart, example);
    }
}
