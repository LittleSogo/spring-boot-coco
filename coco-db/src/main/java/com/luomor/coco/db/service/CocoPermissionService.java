package com.luomor.coco.db.service;

import com.luomor.coco.db.dao.CocoPermissionMapper;
import com.luomor.coco.db.domain.CocoPermission;
import com.luomor.coco.db.domain.CocoPermissionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CocoPermissionService {
    @Resource
    private CocoPermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds.length == 0){
            return permissions;
        }

        CocoPermissionExample example = new CocoPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<CocoPermission> permissionList = permissionMapper.selectByExample(example);

        for(CocoPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }


    public Set<String> queryByRoleId(Integer roleId) {
        Set<String> permissions = new HashSet<String>();
        if(roleId == null){
            return permissions;
        }

        CocoPermissionExample example = new CocoPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<CocoPermission> permissionList = permissionMapper.selectByExample(example);

        for(CocoPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId) {
        if(roleId == null){
            return false;
        }

        CocoPermissionExample example = new CocoPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId) {
        CocoPermissionExample example = new CocoPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(CocoPermission cocoPermission) {
        cocoPermission.setAddTime(LocalDateTime.now());
        cocoPermission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(cocoPermission);
    }
}
