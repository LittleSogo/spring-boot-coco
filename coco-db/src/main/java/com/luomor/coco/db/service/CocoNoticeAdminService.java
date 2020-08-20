package com.luomor.coco.db.service;

import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoNoticeAdminMapper;
import com.luomor.coco.db.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoNoticeAdminService {
    @Resource
    private CocoNoticeAdminMapper noticeAdminMapper;

    public List<CocoNoticeAdmin> querySelective(String title, String type, Integer adminId, Integer page, Integer limit, String sort, String order) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        CocoNoticeAdminExample.Criteria criteria = example.createCriteria();

        if(!StringUtils.isEmpty(title)){
            criteria.andNoticeTitleLike("%" + title + "%");
        }

        if(type.equals("read")){
         criteria.andReadTimeIsNotNull();
        }
        else if(type.equals("unread")){
            criteria.andReadTimeIsNull();
        }
        criteria.andAdminIdEqualTo(adminId);
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return noticeAdminMapper.selectByExample(example);
    }

    public CocoNoticeAdmin find(Integer noticeId, Integer adminId) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andNoticeIdEqualTo(noticeId).andAdminIdEqualTo(adminId).andDeletedEqualTo(false);
        return noticeAdminMapper.selectOneByExample(example);
    }

    public void add(CocoNoticeAdmin noticeAdmin) {
        noticeAdmin.setAddTime(LocalDateTime.now());
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdminMapper.insertSelective(noticeAdmin);
    }

    public void update(CocoNoticeAdmin noticeAdmin) {
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdminMapper.updateByPrimaryKeySelective(noticeAdmin);
    }

    public void markReadByIds(List<Integer> ids, Integer adminId) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andIdIn(ids).andAdminIdEqualTo(adminId).andDeletedEqualTo(false);
        CocoNoticeAdmin noticeAdmin = new CocoNoticeAdmin();
        LocalDateTime now = LocalDateTime.now();
        noticeAdmin.setReadTime(now);
        noticeAdmin.setUpdateTime(now);
        noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
    }

    public void deleteById(Integer id, Integer adminId) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andIdEqualTo(id).andAdminIdEqualTo(adminId).andDeletedEqualTo(false);
        CocoNoticeAdmin noticeAdmin = new CocoNoticeAdmin();
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdmin.setDeleted(true);
        noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
    }

    public void deleteByIds(List<Integer> ids, Integer adminId) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andIdIn(ids).andAdminIdEqualTo(adminId).andDeletedEqualTo(false);
        CocoNoticeAdmin noticeAdmin = new CocoNoticeAdmin();
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdmin.setDeleted(true);
        noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
    }

    public int countUnread(Integer adminId) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andAdminIdEqualTo(adminId).andReadTimeIsNull().andDeletedEqualTo(false);
        return (int)noticeAdminMapper.countByExample(example);
    }

    public List<CocoNoticeAdmin> queryByNoticeId(Integer noticeId) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andNoticeIdEqualTo(noticeId).andDeletedEqualTo(false);
        return noticeAdminMapper.selectByExample(example);
    }

    public void deleteByNoticeId(Integer id) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andNoticeIdEqualTo(id).andDeletedEqualTo(false);
        CocoNoticeAdmin noticeAdmin = new CocoNoticeAdmin();
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdmin.setDeleted(true);
        noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
    }

    public void deleteByNoticeIds(List<Integer> ids) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andNoticeIdIn(ids).andDeletedEqualTo(false);
        CocoNoticeAdmin noticeAdmin = new CocoNoticeAdmin();
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdmin.setDeleted(true);
        noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
    }

    public int countReadByNoticeId(Integer noticeId) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andNoticeIdEqualTo(noticeId).andReadTimeIsNotNull().andDeletedEqualTo(false);
        return (int)noticeAdminMapper.countByExample(example);
    }

    public void updateByNoticeId(CocoNoticeAdmin noticeAdmin, Integer noticeId) {
        CocoNoticeAdminExample example = new CocoNoticeAdminExample();
        example.or().andNoticeIdEqualTo(noticeId).andDeletedEqualTo(false);
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdminMapper.updateByExampleSelective(noticeAdmin, example);
    }
}
