package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoLogMapper;
import com.luomor.coco.db.domain.CocoLog;
import com.luomor.coco.db.domain.CocoLogExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoLogService {
    @Resource
    private CocoLogMapper logMapper;

    public void deleteById(Integer id) {
        logMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoLog log) {
        log.setAddTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        logMapper.insertSelective(log);
    }

    public List<CocoLog> querySelective(String name, Integer page, Integer size, String sort, String order) {
        CocoLogExample example = new CocoLogExample();
        CocoLogExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andAdminLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return logMapper.selectByExample(example);
    }
}
