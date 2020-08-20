package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoAdminMapper;
import com.luomor.coco.db.domain.CocoAdmin;
import com.luomor.coco.db.domain.CocoAdmin.Column;
import com.luomor.coco.db.domain.CocoAdminExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoAdminService {
    private final Column[] result = new Column[]{Column.id, Column.username, Column.avatar, Column.roleIds};
    @Resource
    private CocoAdminMapper adminMapper;

    public List<CocoAdmin> findAdmin(String username) {
        CocoAdminExample example = new CocoAdminExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }

    public CocoAdmin findAdmin(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    public List<CocoAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        CocoAdminExample example = new CocoAdminExample();
        CocoAdminExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return adminMapper.selectByExampleSelective(example, result);
    }

    public int updateById(CocoAdmin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id) {
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoAdmin admin) {
        admin.setAddTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.insertSelective(admin);
    }

    public CocoAdmin findById(Integer id) {
        return adminMapper.selectByPrimaryKeySelective(id, result);
    }

    public List<CocoAdmin> all() {
        CocoAdminExample example = new CocoAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
