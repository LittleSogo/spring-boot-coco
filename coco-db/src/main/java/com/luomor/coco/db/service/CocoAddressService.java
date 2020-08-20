package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoAddressMapper;
import com.luomor.coco.db.domain.CocoAddress;
import com.luomor.coco.db.domain.CocoAddressExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoAddressService {
    @Resource
    private CocoAddressMapper addressMapper;

    public List<CocoAddress> queryByUid(Integer uid) {
        CocoAddressExample example = new CocoAddressExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        return addressMapper.selectByExample(example);
    }

    public CocoAddress query(Integer userId, Integer id) {
        CocoAddressExample example = new CocoAddressExample();
        example.or().andIdEqualTo(id).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return addressMapper.selectOneByExample(example);
    }

    public int add(CocoAddress address) {
        address.setAddTime(LocalDateTime.now());
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.insertSelective(address);
    }

    public int update(CocoAddress address) {
        address.setUpdateTime(LocalDateTime.now());
        return addressMapper.updateByPrimaryKeySelective(address);
    }

    public void delete(Integer id) {
        addressMapper.logicalDeleteByPrimaryKey(id);
    }

    public CocoAddress findDefault(Integer userId) {
        CocoAddressExample example = new CocoAddressExample();
        example.or().andUserIdEqualTo(userId).andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return addressMapper.selectOneByExample(example);
    }

    public void resetDefault(Integer userId) {
        CocoAddress address = new CocoAddress();
        address.setIsDefault(false);
        address.setUpdateTime(LocalDateTime.now());
        CocoAddressExample example = new CocoAddressExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        addressMapper.updateByExampleSelective(address, example);
    }

    public List<CocoAddress> querySelective(Integer userId, String name, Integer page, Integer limit, String sort, String order) {
        CocoAddressExample example = new CocoAddressExample();
        CocoAddressExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return addressMapper.selectByExample(example);
    }
}
