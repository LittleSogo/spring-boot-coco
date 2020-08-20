package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoStorageMapper;
import com.luomor.coco.db.domain.CocoStorage;
import com.luomor.coco.db.domain.CocoStorageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoStorageService {
    @Autowired
    private CocoStorageMapper storageMapper;

    public void deleteByKey(String key) {
        CocoStorageExample example = new CocoStorageExample();
        example.or().andKeyEqualTo(key);
        storageMapper.logicalDeleteByExample(example);
    }

    public void add(CocoStorage storageInfo) {
        storageInfo.setAddTime(LocalDateTime.now());
        storageInfo.setUpdateTime(LocalDateTime.now());
        storageMapper.insertSelective(storageInfo);
    }

    public CocoStorage findByKey(String key) {
        CocoStorageExample example = new CocoStorageExample();
        example.or().andKeyEqualTo(key).andDeletedEqualTo(false);
        return storageMapper.selectOneByExample(example);
    }

    public int update(CocoStorage storageInfo) {
        storageInfo.setUpdateTime(LocalDateTime.now());
        return storageMapper.updateByPrimaryKeySelective(storageInfo);
    }

    public CocoStorage findById(Integer id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    public List<CocoStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        CocoStorageExample example = new CocoStorageExample();
        CocoStorageExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(key)) {
            criteria.andKeyEqualTo(key);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return storageMapper.selectByExample(example);
    }
}
