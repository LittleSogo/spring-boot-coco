package com.luomor.coco.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoRoleMapper;
import com.luomor.coco.db.domain.CocoRole;
import com.luomor.coco.db.domain.CocoRoleExample;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CocoRoleService {
    @Resource
    private CocoRoleMapper roleMapper;


    public Set<String> queryByIds(Integer[] roleIds) {
        Set<String> roles = new HashSet<String>();
        if(roleIds.length == 0){
            return roles;
        }

        CocoRoleExample example = new CocoRoleExample();
        example.or().andIdIn(Arrays.asList(roleIds)).andEnabledEqualTo(true).andDeletedEqualTo(false);
        List<CocoRole> roleList = roleMapper.selectByExample(example);

        for(CocoRole role : roleList){
            roles.add(role.getName());
        }

        return roles;

    }

    public List<CocoRole> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        CocoRoleExample example = new CocoRoleExample();
        CocoRoleExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return roleMapper.selectByExample(example);
    }

    public CocoRole findById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public void add(CocoRole role) {
        role.setAddTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insertSelective(role);
    }

    public void deleteById(Integer id) {
        roleMapper.logicalDeleteByPrimaryKey(id);
    }

    public void updateById(CocoRole role) {
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    public boolean checkExist(String name) {
        CocoRoleExample example = new CocoRoleExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return roleMapper.countByExample(example) != 0;
    }

    public List<CocoRole> queryAll() {
        CocoRoleExample example = new CocoRoleExample();
        example.or().andDeletedEqualTo(false);
        return roleMapper.selectByExample(example);
    }
}
