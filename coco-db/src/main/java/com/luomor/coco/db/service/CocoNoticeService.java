package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoNoticeMapper;
import com.luomor.coco.db.domain.CocoNotice;
import com.luomor.coco.db.domain.CocoNoticeExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoNoticeService {
    @Resource
    private CocoNoticeMapper noticeMapper;


    public List<CocoNotice> querySelective(String title, String content, Integer page, Integer limit, String sort, String order) {
        CocoNoticeExample example = new CocoNoticeExample();
        CocoNoticeExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return noticeMapper.selectByExample(example);
    }

    public int updateById(CocoNotice notice) {
        notice.setUpdateTime(LocalDateTime.now());
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }

    public void deleteById(Integer id) {
        noticeMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoNotice notice) {
        notice.setAddTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        noticeMapper.insertSelective(notice);
    }

    public CocoNotice findById(Integer id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    public void deleteByIds(List<Integer> ids) {
        CocoNoticeExample example = new CocoNoticeExample();
        example.or().andIdIn(ids).andDeletedEqualTo(false);
        CocoNotice notice = new CocoNotice();
        notice.setUpdateTime(LocalDateTime.now());
        notice.setDeleted(true);
        noticeMapper.updateByExampleSelective(notice, example);
    }
}
