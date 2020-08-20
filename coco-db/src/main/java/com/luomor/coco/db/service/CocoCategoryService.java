package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoCategoryMapper;
import com.luomor.coco.db.domain.CocoCategory;
import com.luomor.coco.db.domain.CocoCategoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoCategoryService {
    @Resource
    private CocoCategoryMapper categoryMapper;
    private CocoCategory.Column[] CHANNEL = {CocoCategory.Column.id, CocoCategory.Column.name, CocoCategory.Column.iconUrl};

    public List<CocoCategory> queryL1WithoutRecommend(int offset, int limit) {
        CocoCategoryExample example = new CocoCategoryExample();
        example.or().andLevelEqualTo("L1").andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return categoryMapper.selectByExample(example);
    }

    public List<CocoCategory> queryL1(int offset, int limit) {
        CocoCategoryExample example = new CocoCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return categoryMapper.selectByExample(example);
    }

    public List<CocoCategory> queryL1() {
        CocoCategoryExample example = new CocoCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public List<CocoCategory> queryByPid(Integer pid) {
        CocoCategoryExample example = new CocoCategoryExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public List<CocoCategory> queryL2ByIds(List<Integer> ids) {
        CocoCategoryExample example = new CocoCategoryExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public CocoCategory findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    public List<CocoCategory> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        CocoCategoryExample example = new CocoCategoryExample();
        CocoCategoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return categoryMapper.selectByExample(example);
    }

    public int updateById(CocoCategory category) {
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    public void deleteById(Integer id) {
        categoryMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoCategory category) {
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertSelective(category);
    }

    public List<CocoCategory> queryChannel() {
        CocoCategoryExample example = new CocoCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExampleSelective(example, CHANNEL);
    }
}
