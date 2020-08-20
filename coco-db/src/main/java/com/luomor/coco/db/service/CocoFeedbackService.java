package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoFeedbackMapper;
import com.luomor.coco.db.domain.CocoFeedback;
import com.luomor.coco.db.domain.CocoFeedbackExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Peter
 * @date 2018/8/27 11:39
 */
@Service
public class CocoFeedbackService {
    @Autowired
    private CocoFeedbackMapper feedbackMapper;

    public Integer add(CocoFeedback feedback) {
        feedback.setAddTime(LocalDateTime.now());
        feedback.setUpdateTime(LocalDateTime.now());
        return feedbackMapper.insertSelective(feedback);
    }

    public List<CocoFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order) {
        CocoFeedbackExample example = new CocoFeedbackExample();
        CocoFeedbackExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return feedbackMapper.selectByExample(example);
    }
}
