package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoCommentMapper;
import com.luomor.coco.db.domain.CocoComment;
import com.luomor.coco.db.domain.CocoCommentExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoCommentService {
    @Resource
    private CocoCommentMapper commentMapper;

    public List<CocoComment> queryGoodsByGid(Integer id, int offset, int limit) {
        CocoCommentExample example = new CocoCommentExample();
        example.setOrderByClause(CocoComment.Column.addTime.desc());
        example.or().andValueIdEqualTo(id).andTypeEqualTo((byte) 0).andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return commentMapper.selectByExample(example);
    }

    public List<CocoComment> query(Byte type, Integer valueId, Integer showType, Integer offset, Integer limit) {
        CocoCommentExample example = new CocoCommentExample();
        example.setOrderByClause(CocoComment.Column.addTime.desc());
        if (showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andHasPictureEqualTo(true).andDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        PageHelper.startPage(offset, limit);
        return commentMapper.selectByExample(example);
    }

    public int count(Byte type, Integer valueId, Integer showType) {
        CocoCommentExample example = new CocoCommentExample();
        if (showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andHasPictureEqualTo(true).andDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        return (int) commentMapper.countByExample(example);
    }

    public int save(CocoComment comment) {
        comment.setAddTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        return commentMapper.insertSelective(comment);
    }

    public List<CocoComment> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        CocoCommentExample example = new CocoCommentExample();
        CocoCommentExample.Criteria criteria = example.createCriteria();

        // type=2 是订单商品回复，这里过滤
        criteria.andTypeNotEqualTo((byte) 2);

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            criteria.andValueIdEqualTo(Integer.valueOf(valueId)).andTypeEqualTo((byte) 0);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return commentMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        commentMapper.logicalDeleteByPrimaryKey(id);
    }

    public CocoComment findById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    public int updateById(CocoComment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment);
    }
}
