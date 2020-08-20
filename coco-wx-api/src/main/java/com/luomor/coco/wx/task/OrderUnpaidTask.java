package com.luomor.coco.wx.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.luomor.coco.core.system.SystemConfig;
import com.luomor.coco.core.task.Task;
import com.luomor.coco.core.util.BeanUtil;
import com.luomor.coco.db.domain.CocoOrder;
import com.luomor.coco.db.domain.CocoOrderGoods;
import com.luomor.coco.db.service.CocoGoodsProductService;
import com.luomor.coco.db.service.CocoOrderGoodsService;
import com.luomor.coco.db.service.CocoOrderService;
import com.luomor.coco.db.util.OrderUtil;

import java.time.LocalDateTime;
import java.util.List;

public class OrderUnpaidTask extends Task {
    private final Log logger = LogFactory.getLog(OrderUnpaidTask.class);
    private int orderId = -1;

    public OrderUnpaidTask(Integer orderId, long delayInMilliseconds){
        super("OrderUnpaidTask-" + orderId, delayInMilliseconds);
        this.orderId = orderId;
    }

    public OrderUnpaidTask(Integer orderId){
        super("OrderUnpaidTask-" + orderId, SystemConfig.getOrderUnpaid() * 60 * 1000);
        this.orderId = orderId;
    }

    @Override
    public void run() {
        logger.info("系统开始处理延时任务---订单超时未付款---" + this.orderId);

        CocoOrderService orderService = BeanUtil.getBean(CocoOrderService.class);
        CocoOrderGoodsService orderGoodsService = BeanUtil.getBean(CocoOrderGoodsService.class);
        CocoGoodsProductService productService = BeanUtil.getBean(CocoGoodsProductService.class);

        CocoOrder order = orderService.findById(this.orderId);
        if(order == null){
            return;
        }
        if(!OrderUtil.isCreateStatus(order)){
            return;
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_AUTO_CANCEL);
        order.setEndTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            throw new RuntimeException("更新数据已失效");
        }

        // 商品货品数量增加
        Integer orderId = order.getId();
        List<CocoOrderGoods> orderGoodsList = orderGoodsService.queryByOid(orderId);
        for (CocoOrderGoods orderGoods : orderGoodsList) {
            Integer productId = orderGoods.getProductId();
            Short number = orderGoods.getNumber();
            if (productService.addStock(productId, number) == 0) {
                throw new RuntimeException("商品货品库存增加失败");
            }
        }
        logger.info("系统结束处理延时任务---订单超时未付款---" + this.orderId);
    }
}
