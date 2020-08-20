package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoKeywordMapper;
import com.luomor.coco.db.domain.CocoKeyword;
import com.luomor.coco.db.domain.CocoKeywordExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoKeywordService {
    @Resource
    private CocoKeywordMapper keywordsMapper;

    public CocoKeyword queryDefault() {
        CocoKeywordExample example = new CocoKeywordExample();
        example.or().andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return keywordsMapper.selectOneByExample(example);
    }

    public List<CocoKeyword> queryHots() {
        CocoKeywordExample example = new CocoKeywordExample();
        example.or().andIsHotEqualTo(true).andDeletedEqualTo(false);
        return keywordsMapper.selectByExample(example);
    }

    public List<CocoKeyword> queryByKeyword(String keyword, Integer page, Integer limit) {
        CocoKeywordExample example = new CocoKeywordExample();
        example.setDistinct(true);
        example.or().andKeywordLike("%" + keyword + "%").andDeletedEqualTo(false);
        PageHelper.startPage(page, limit);
        return keywordsMapper.selectByExampleSelective(example, CocoKeyword.Column.keyword);
    }

    public List<CocoKeyword> querySelective(String keyword, String url, Integer page, Integer limit, String sort, String order) {
        CocoKeywordExample example = new CocoKeywordExample();
        CocoKeywordExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        if (!StringUtils.isEmpty(url)) {
            criteria.andUrlLike("%" + url + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return keywordsMapper.selectByExample(example);
    }

    public void add(CocoKeyword keywords) {
        keywords.setAddTime(LocalDateTime.now());
        keywords.setUpdateTime(LocalDateTime.now());
        keywordsMapper.insertSelective(keywords);
    }

    public CocoKeyword findById(Integer id) {
        return keywordsMapper.selectByPrimaryKey(id);
    }

    public int updateById(CocoKeyword keywords) {
        keywords.setUpdateTime(LocalDateTime.now());
        return keywordsMapper.updateByPrimaryKeySelective(keywords);
    }

    public void deleteById(Integer id) {
        keywordsMapper.logicalDeleteByPrimaryKey(id);
    }
}
