package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoFootprintMapper;
import com.luomor.coco.db.domain.CocoFootprint;
import com.luomor.coco.db.domain.CocoFootprintExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoFootprintService {
    @Resource
    private CocoFootprintMapper footprintMapper;

    public List<CocoFootprint> queryByAddTime(Integer userId, Integer page, Integer size) {
        CocoFootprintExample example = new CocoFootprintExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        example.setOrderByClause(CocoFootprint.Column.addTime.desc());
        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);
    }

    public CocoFootprint findById(Integer id) {
        return footprintMapper.selectByPrimaryKey(id);
    }

    public CocoFootprint findById(Integer userId, Integer id) {
        CocoFootprintExample example = new CocoFootprintExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return footprintMapper.selectOneByExample(example);
    }

    public void deleteById(Integer id) {
        footprintMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoFootprint footprint) {
        footprint.setAddTime(LocalDateTime.now());
        footprint.setUpdateTime(LocalDateTime.now());
        footprintMapper.insertSelective(footprint);
    }

    public List<CocoFootprint> querySelective(String userId, String goodsId, Integer page, Integer size, String sort, String order) {
        CocoFootprintExample example = new CocoFootprintExample();
        CocoFootprintExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.valueOf(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return footprintMapper.selectByExample(example);
    }
}
