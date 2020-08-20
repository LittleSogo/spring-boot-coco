package com.luomor.coco.admin.dto;

import com.luomor.coco.db.domain.CocoGoods;
import com.luomor.coco.db.domain.CocoGoodsAttribute;
import com.luomor.coco.db.domain.CocoGoodsProduct;
import com.luomor.coco.db.domain.CocoGoodsSpecification;

public class GoodsAllinone {
    CocoGoods goods;
    CocoGoodsSpecification[] specifications;
    CocoGoodsAttribute[] attributes;
    CocoGoodsProduct[] products;

    public CocoGoods getGoods() {
        return goods;
    }

    public void setGoods(CocoGoods goods) {
        this.goods = goods;
    }

    public CocoGoodsProduct[] getProducts() {
        return products;
    }

    public void setProducts(CocoGoodsProduct[] products) {
        this.products = products;
    }

    public CocoGoodsSpecification[] getSpecifications() {
        return specifications;
    }

    public void setSpecifications(CocoGoodsSpecification[] specifications) {
        this.specifications = specifications;
    }

    public CocoGoodsAttribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(CocoGoodsAttribute[] attributes) {
        this.attributes = attributes;
    }

}
