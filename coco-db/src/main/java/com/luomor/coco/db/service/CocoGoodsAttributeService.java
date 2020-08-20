package com.luomor.coco.db.service;

import com.luomor.coco.db.dao.CocoGoodsAttributeMapper;
import com.luomor.coco.db.domain.CocoGoodsAttribute;
import com.luomor.coco.db.domain.CocoGoodsAttributeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoGoodsAttributeService {
    @Resource
    private CocoGoodsAttributeMapper goodsAttributeMapper;

    public List<CocoGoodsAttribute> queryByGid(Integer goodsId) {
        CocoGoodsAttributeExample example = new CocoGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return goodsAttributeMapper.selectByExample(example);
    }

    public void add(CocoGoodsAttribute goodsAttribute) {
        goodsAttribute.setAddTime(LocalDateTime.now());
        goodsAttribute.setUpdateTime(LocalDateTime.now());
        goodsAttributeMapper.insertSelective(goodsAttribute);
    }

    public CocoGoodsAttribute findById(Integer id) {
        return goodsAttributeMapper.selectByPrimaryKey(id);
    }

    public void deleteByGid(Integer gid) {
        CocoGoodsAttributeExample example = new CocoGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(gid);
        goodsAttributeMapper.logicalDeleteByExample(example);
    }

    public void deleteById(Integer id) {
        goodsAttributeMapper.logicalDeleteByPrimaryKey(id);
    }

    public void updateById(CocoGoodsAttribute attribute) {
        attribute.setUpdateTime(LocalDateTime.now());
        goodsAttributeMapper.updateByPrimaryKeySelective(attribute);
    }
}
