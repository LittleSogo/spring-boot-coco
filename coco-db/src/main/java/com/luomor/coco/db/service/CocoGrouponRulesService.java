package com.luomor.coco.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.luomor.coco.db.dao.CocoGoodsMapper;
import com.luomor.coco.db.dao.CocoGrouponRulesMapper;
import com.luomor.coco.db.domain.CocoGoods;
import com.luomor.coco.db.domain.CocoGrouponRules;
import com.luomor.coco.db.domain.CocoGrouponRulesExample;
import com.luomor.coco.db.util.GrouponConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoGrouponRulesService {
    @Resource
    private CocoGrouponRulesMapper mapper;
    @Resource
    private CocoGoodsMapper goodsMapper;
    private CocoGoods.Column[] goodsColumns = new CocoGoods.Column[]{CocoGoods.Column.id, CocoGoods.Column.name, CocoGoods.Column.brief, CocoGoods.Column.picUrl, CocoGoods.Column.counterPrice, CocoGoods.Column.retailPrice};

    public int createRules(CocoGrouponRules rules) {
        rules.setAddTime(LocalDateTime.now());
        rules.setUpdateTime(LocalDateTime.now());
        return mapper.insertSelective(rules);
    }

    /**
     * 根据ID查找对应团购项
     *
     * @param id
     * @return
     */
    public CocoGrouponRules findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    public List<CocoGrouponRules> queryByGoodsId(Integer goodsId) {
        CocoGrouponRulesExample example = new CocoGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        return mapper.selectByExample(example);
    }

    public int countByGoodsId(Integer goodsId) {
        CocoGrouponRulesExample example = new CocoGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        return (int)mapper.countByExample(example);
    }

    public List<CocoGrouponRules> queryByStatus(Short status) {
        CocoGrouponRulesExample example = new CocoGrouponRulesExample();
        example.or().andStatusEqualTo(status).andDeletedEqualTo(false);
        return mapper.selectByExample(example);
    }

    /**
     * 获取首页团购规则列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<CocoGrouponRules> queryList(Integer page, Integer limit) {
        return queryList(page, limit, "add_time", "desc");
    }

    public List<CocoGrouponRules> queryList(Integer page, Integer limit, String sort, String order) {
        CocoGrouponRulesExample example = new CocoGrouponRulesExample();
        example.or().andStatusEqualTo(GrouponConstant.RULE_STATUS_ON).andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(page, limit);
        return mapper.selectByExample(example);
    }

    /**
     * 判断某个团购规则是否已经过期
     *
     * @return
     */
    public boolean isExpired(CocoGrouponRules rules) {
        return (rules == null || rules.getExpireTime().isBefore(LocalDateTime.now()));
    }

    /**
     * 获取团购规则列表
     *
     * @param goodsId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<CocoGrouponRules> querySelective(String goodsId, Integer page, Integer size, String sort, String order) {
        CocoGrouponRulesExample example = new CocoGrouponRulesExample();
        example.setOrderByClause(sort + " " + order);

        CocoGrouponRulesExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.parseInt(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, size);
        return mapper.selectByExample(example);
    }

    public void delete(Integer id) {
        mapper.logicalDeleteByPrimaryKey(id);
    }

    public int updateById(CocoGrouponRules grouponRules) {
        grouponRules.setUpdateTime(LocalDateTime.now());
        return mapper.updateByPrimaryKeySelective(grouponRules);
    }
}