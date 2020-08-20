package com.luomor.coco.wx.service;

import com.github.pagehelper.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.luomor.coco.db.domain.CocoGoods;
import com.luomor.coco.db.domain.CocoGrouponRules;
import com.luomor.coco.db.service.CocoGoodsService;
import com.luomor.coco.db.service.CocoGrouponRulesService;
import com.luomor.coco.db.service.CocoGrouponService;
import com.luomor.coco.wx.vo.GrouponRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxGrouponRuleService {
    private final Log logger = LogFactory.getLog(WxGrouponRuleService.class);

    @Autowired
    private CocoGrouponRulesService grouponRulesService;
    @Autowired
    private CocoGrouponService grouponService;
    @Autowired
    private CocoGoodsService goodsService;


    public List<GrouponRuleVo> queryList(Integer page, Integer size) {
        return queryList(page, size, "add_time", "desc");
    }


    public List<GrouponRuleVo> queryList(Integer page, Integer size, String sort, String order) {
        Page<CocoGrouponRules> grouponRulesList = (Page<CocoGrouponRules>)grouponRulesService.queryList(page, size, sort, order);

        Page<GrouponRuleVo> grouponList = new Page<GrouponRuleVo>();
        grouponList.setPages(grouponRulesList.getPages());
        grouponList.setPageNum(grouponRulesList.getPageNum());
        grouponList.setPageSize(grouponRulesList.getPageSize());
        grouponList.setTotal(grouponRulesList.getTotal());

        for (CocoGrouponRules rule : grouponRulesList) {
            Integer goodsId = rule.getGoodsId();
            CocoGoods goods = goodsService.findById(goodsId);
            if (goods == null)
                continue;

            GrouponRuleVo grouponRuleVo = new GrouponRuleVo();
            grouponRuleVo.setId(goods.getId());
            grouponRuleVo.setName(goods.getName());
            grouponRuleVo.setBrief(goods.getBrief());
            grouponRuleVo.setPicUrl(goods.getPicUrl());
            grouponRuleVo.setCounterPrice(goods.getCounterPrice());
            grouponRuleVo.setRetailPrice(goods.getRetailPrice());
            grouponRuleVo.setGrouponPrice(goods.getRetailPrice().subtract(rule.getDiscount()));
            grouponRuleVo.setGrouponDiscount(rule.getDiscount());
            grouponRuleVo.setGrouponMember(rule.getDiscountMember());
            grouponRuleVo.setExpireTime(rule.getExpireTime());
            grouponList.add(grouponRuleVo);
        }

        return grouponList;
    }
}