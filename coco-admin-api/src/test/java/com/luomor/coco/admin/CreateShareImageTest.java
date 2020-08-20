package com.luomor.coco.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.luomor.coco.core.qcode.QCodeService;
import com.luomor.coco.db.domain.CocoGoods;
import com.luomor.coco.db.service.CocoGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CreateShareImageTest {
    @Autowired
    QCodeService qCodeService;
    @Autowired
    CocoGoodsService cocoGoodsService;

    @Test
    public void test() {
        CocoGoods good = cocoGoodsService.findById(1181010);
        qCodeService.createGoodShareImage(good.getId().toString(), good.getPicUrl(), good.getName());
    }
}
