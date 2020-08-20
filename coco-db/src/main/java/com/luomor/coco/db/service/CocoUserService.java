package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoUserMapper;
import com.luomor.coco.db.domain.CocoUser;
import com.luomor.coco.db.domain.CocoUserExample;
import com.luomor.coco.db.domain.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoUserService {
    @Resource
    private CocoUserMapper userMapper;

    public CocoUser findById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public UserVo findUserVoById(Integer userId) {
        CocoUser user = findById(userId);
        UserVo userVo = new UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setAvatar(user.getAvatar());
        return userVo;
    }

    public CocoUser queryByOid(String openId) {
        CocoUserExample example = new CocoUserExample();
        example.or().andWeixinOpenidEqualTo(openId).andDeletedEqualTo(false);
        return userMapper.selectOneByExample(example);
    }

    public void add(CocoUser user) {
        user.setAddTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertSelective(user);
    }

    public int updateById(CocoUser user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public List<CocoUser> querySelective(String username, String mobile, Integer page, Integer size, String sort, String order) {
        CocoUserExample example = new CocoUserExample();
        CocoUserExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        if (!StringUtils.isEmpty(mobile)) {
            criteria.andMobileEqualTo(mobile);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return userMapper.selectByExample(example);
    }

    public int count() {
        CocoUserExample example = new CocoUserExample();
        example.or().andDeletedEqualTo(false);

        return (int) userMapper.countByExample(example);
    }

    public List<CocoUser> queryByUsername(String username) {
        CocoUserExample example = new CocoUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public boolean checkByUsername(String username) {
        CocoUserExample example = new CocoUserExample();
        example.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return userMapper.countByExample(example) != 0;
    }

    public List<CocoUser> queryByMobile(String mobile) {
        CocoUserExample example = new CocoUserExample();
        example.or().andMobileEqualTo(mobile).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public List<CocoUser> queryByOpenid(String openid) {
        CocoUserExample example = new CocoUserExample();
        example.or().andWeixinOpenidEqualTo(openid).andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        userMapper.logicalDeleteByPrimaryKey(id);
    }
}
