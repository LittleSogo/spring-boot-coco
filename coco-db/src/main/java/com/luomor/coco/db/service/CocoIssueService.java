package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoIssueMapper;
import com.luomor.coco.db.domain.CocoIssue;
import com.luomor.coco.db.domain.CocoIssueExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoIssueService {
    @Resource
    private CocoIssueMapper issueMapper;

    public void deleteById(Integer id) {
        issueMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoIssue issue) {
        issue.setAddTime(LocalDateTime.now());
        issue.setUpdateTime(LocalDateTime.now());
        issueMapper.insertSelective(issue);
    }

    public List<CocoIssue> querySelective(String question, Integer page, Integer limit, String sort, String order) {
        CocoIssueExample example = new CocoIssueExample();
        CocoIssueExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(question)) {
            criteria.andQuestionLike("%" + question + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return issueMapper.selectByExample(example);
    }

    public int updateById(CocoIssue issue) {
        issue.setUpdateTime(LocalDateTime.now());
        return issueMapper.updateByPrimaryKeySelective(issue);
    }

    public CocoIssue findById(Integer id) {
        return issueMapper.selectByPrimaryKey(id);
    }
}
