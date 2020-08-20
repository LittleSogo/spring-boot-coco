package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoTopicMapper;
import com.luomor.coco.db.domain.CocoGroupon;
import com.luomor.coco.db.domain.CocoTopic;
import com.luomor.coco.db.domain.CocoTopic.Column;
import com.luomor.coco.db.domain.CocoTopicExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoTopicService {
    @Resource
    private CocoTopicMapper topicMapper;
    private Column[] columns = new Column[]{Column.id, Column.title, Column.subtitle, Column.price, Column.picUrl, Column.readCount};

    public List<CocoTopic> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    public List<CocoTopic> queryList(int offset, int limit, String sort, String order) {
        CocoTopicExample example = new CocoTopicExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return topicMapper.selectByExampleSelective(example, columns);
    }

    public int queryTotal() {
        CocoTopicExample example = new CocoTopicExample();
        example.or().andDeletedEqualTo(false);
        return (int) topicMapper.countByExample(example);
    }

    public CocoTopic findById(Integer id) {
        CocoTopicExample example = new CocoTopicExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return topicMapper.selectOneByExampleWithBLOBs(example);
    }

    public List<CocoTopic> queryRelatedList(Integer id, int offset, int limit) {
        CocoTopicExample example = new CocoTopicExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        List<CocoTopic> topics = topicMapper.selectByExample(example);
        if (topics.size() == 0) {
            return queryList(offset, limit, "add_time", "desc");
        }
        CocoTopic topic = topics.get(0);

        example = new CocoTopicExample();
        example.or().andIdNotEqualTo(topic.getId()).andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        List<CocoTopic> relateds = topicMapper.selectByExampleWithBLOBs(example);
        if (relateds.size() != 0) {
            return relateds;
        }

        return queryList(offset, limit, "add_time", "desc");
    }

    public List<CocoTopic> querySelective(String title, String subtitle, Integer page, Integer limit, String sort, String order) {
        CocoTopicExample example = new CocoTopicExample();
        CocoTopicExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (!StringUtils.isEmpty(subtitle)) {
            criteria.andSubtitleLike("%" + subtitle + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return topicMapper.selectByExampleWithBLOBs(example);
    }

    public int updateById(CocoTopic topic) {
        topic.setUpdateTime(LocalDateTime.now());
        CocoTopicExample example = new CocoTopicExample();
        example.or().andIdEqualTo(topic.getId());
        return topicMapper.updateByExampleSelective(topic, example);
    }

    public void deleteById(Integer id) {
        topicMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoTopic topic) {
        topic.setAddTime(LocalDateTime.now());
        topic.setUpdateTime(LocalDateTime.now());
        topicMapper.insertSelective(topic);
    }


    public void deleteByIds(List<Integer> ids) {
        CocoTopicExample example = new CocoTopicExample();
        example.or().andIdIn(ids).andDeletedEqualTo(false);
        CocoTopic topic = new CocoTopic();
        topic.setUpdateTime(LocalDateTime.now());
        topic.setDeleted(true);
        topicMapper.updateByExampleSelective(topic, example);
    }
}
