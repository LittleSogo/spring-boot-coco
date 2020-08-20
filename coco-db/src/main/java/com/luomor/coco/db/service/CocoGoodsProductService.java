package com.luomor.coco.db.service;

import com.luomor.coco.db.dao.GoodsProductMapper;
import com.luomor.coco.db.dao.CocoGoodsProductMapper;
import com.luomor.coco.db.domain.CocoGoodsProduct;
import com.luomor.coco.db.domain.CocoGoodsProductExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CocoGoodsProductService {
    @Resource
    private CocoGoodsProductMapper cocoGoodsProductMapper;
    @Resource
    private GoodsProductMapper goodsProductMapper;

    public List<CocoGoodsProduct> queryByGid(Integer gid) {
        CocoGoodsProductExample example = new CocoGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid).andDeletedEqualTo(false);
        return cocoGoodsProductMapper.selectByExample(example);
    }

    public CocoGoodsProduct findById(Integer id) {
        return cocoGoodsProductMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        cocoGoodsProductMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(CocoGoodsProduct goodsProduct) {
        goodsProduct.setAddTime(LocalDateTime.now());
        goodsProduct.setUpdateTime(LocalDateTime.now());
        cocoGoodsProductMapper.insertSelective(goodsProduct);
    }

    public int count() {
        CocoGoodsProductExample example = new CocoGoodsProductExample();
        example.or().andDeletedEqualTo(false);
        return (int) cocoGoodsProductMapper.countByExample(example);
    }

    public void deleteByGid(Integer gid) {
        CocoGoodsProductExample example = new CocoGoodsProductExample();
        example.or().andGoodsIdEqualTo(gid);
        cocoGoodsProductMapper.logicalDeleteByExample(example);
    }

    public int addStock(Integer id, Short num){
        return goodsProductMapper.addStock(id, num);
    }

    public int reduceStock(Integer id, Short num){
        return goodsProductMapper.reduceStock(id, num);
    }

    public void updateById(CocoGoodsProduct product) {
        product.setUpdateTime(LocalDateTime.now());
        cocoGoodsProductMapper.updateByPrimaryKeySelective(product);
    }
}