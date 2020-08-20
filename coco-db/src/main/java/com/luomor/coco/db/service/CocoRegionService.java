package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoRegionMapper;
import com.luomor.coco.db.domain.CocoRegion;
import com.luomor.coco.db.domain.CocoRegionExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CocoRegionService {

    @Resource
    private CocoRegionMapper regionMapper;

    public List<CocoRegion> getAll(){
        CocoRegionExample example = new CocoRegionExample();
        byte b = 4;
        example.or().andTypeNotEqualTo(b);
        return regionMapper.selectByExample(example);
    }

    public List<CocoRegion> queryByPid(Integer parentId) {
        CocoRegionExample example = new CocoRegionExample();
        example.or().andPidEqualTo(parentId);
        return regionMapper.selectByExample(example);
    }

    public CocoRegion findById(Integer id) {
        return regionMapper.selectByPrimaryKey(id);
    }

    public List<CocoRegion> querySelective(String name, Integer code, Integer page, Integer size, String sort, String order) {
        CocoRegionExample example = new CocoRegionExample();
        CocoRegionExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(code)) {
            criteria.andCodeEqualTo(code);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return regionMapper.selectByExample(example);
    }

}
