package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoAdMapper;
import com.luomor.coco.db.domain.CocoAd;
import com.luomor.coco.db.domain.CocoAdExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoAdService {
    @Resource
    private CocoAdMapper adMapper;

    public List<CocoAd> queryIndex() {
        CocoAdExample example = new CocoAdExample();
        example.or().andPositionEqualTo((byte) 1).andDeletedEqualTo(false).andEnabledEqualTo(true);
        return adMapper.selectByExample(example);
    }

    public List<CocoAd> querySelective(String name, String content, Integer page, Integer limit, String sort, String order) {
        CocoAdExample example = new CocoAdExample();
        CocoAdExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adMapper.selectByExample(example);
    }

    public int updateById(CocoAd ad) {
        ad.setUpdateTime(LocalDateTime.now());
        return adMapper.updateByPrimaryKeySelective(ad);
    }

    public void deleteById(Integer id) {
        adMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoAd ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        adMapper.insertSelective(ad);
    }

    public CocoAd findById(Integer id) {
        return adMapper.selectByPrimaryKey(id);
    }
}
