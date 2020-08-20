package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoSearchHistoryMapper;
import com.luomor.coco.db.domain.CocoSearchHistory;
import com.luomor.coco.db.domain.CocoSearchHistoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoSearchHistoryService {
    @Resource
    private CocoSearchHistoryMapper searchHistoryMapper;

    public void save(CocoSearchHistory searchHistory) {
        searchHistory.setAddTime(LocalDateTime.now());
        searchHistory.setUpdateTime(LocalDateTime.now());
        searchHistoryMapper.insertSelective(searchHistory);
    }

    public List<CocoSearchHistory> queryByUid(int uid) {
        CocoSearchHistoryExample example = new CocoSearchHistoryExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        example.setDistinct(true);
        return searchHistoryMapper.selectByExampleSelective(example, CocoSearchHistory.Column.keyword);
    }

    public void deleteByUid(int uid) {
        CocoSearchHistoryExample example = new CocoSearchHistoryExample();
        example.or().andUserIdEqualTo(uid);
        searchHistoryMapper.logicalDeleteByExample(example);
    }

    public List<CocoSearchHistory> querySelective(String userId, String keyword, Integer page, Integer size, String sort, String order) {
        CocoSearchHistoryExample example = new CocoSearchHistoryExample();
        CocoSearchHistoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return searchHistoryMapper.selectByExample(example);
    }
}
