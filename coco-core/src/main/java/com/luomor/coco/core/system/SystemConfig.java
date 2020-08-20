package com.luomor.coco.core.system;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统设置
 */
public class SystemConfig {
    // 小程序相关配置
    public final static String COCO_WX_INDEX_NEW = "coco_wx_index_new";
    public final static String COCO_WX_INDEX_HOT = "coco_wx_index_hot";
    public final static String COCO_WX_INDEX_BRAND = "coco_wx_index_brand";
    public final static String COCO_WX_INDEX_TOPIC = "coco_wx_index_topic";
    public final static String COCO_WX_INDEX_CATLOG_LIST = "coco_wx_catlog_list";
    public final static String COCO_WX_INDEX_CATLOG_GOODS = "coco_wx_catlog_goods";
    public final static String COCO_WX_SHARE = "coco_wx_share";
    // 运费相关配置
    public final static String COCO_EXPRESS_FREIGHT_VALUE = "coco_express_freight_value";
    public final static String COCO_EXPRESS_FREIGHT_MIN = "coco_express_freight_min";
    // 订单相关配置
    public final static String COCO_ORDER_UNPAID = "coco_order_unpaid";
    public final static String COCO_ORDER_UNCONFIRM = "coco_order_unconfirm";
    public final static String COCO_ORDER_COMMENT = "coco_order_comment";
    // 商场相关配置
    public final static String COCO_MALL_NAME = "coco_mall_name";
    public final static String COCO_MALL_ADDRESS = "coco_mall_address";
    public final static String COCO_MALL_PHONE = "coco_mall_phone";
    public final static String COCO_MALL_QQ = "coco_mall_qq";
    public final static String COCO_MALL_LONGITUDE = "coco_mall_longitude";
    public final static String COCO_MALL_Latitude = "coco_mall_latitude";

    //所有的配置均保存在该 HashMap 中
    private static Map<String, String> SYSTEM_CONFIGS = new HashMap<>();

    private static String getConfig(String keyName) {
        return SYSTEM_CONFIGS.get(keyName);
    }

    private static Integer getConfigInt(String keyName) {
        return Integer.parseInt(SYSTEM_CONFIGS.get(keyName));
    }

    private static Boolean getConfigBoolean(String keyName) {
        return Boolean.valueOf(SYSTEM_CONFIGS.get(keyName));
    }

    private static BigDecimal getConfigBigDec(String keyName) {
        return new BigDecimal(SYSTEM_CONFIGS.get(keyName));
    }

    public static Integer getNewLimit() {
        return getConfigInt(COCO_WX_INDEX_NEW);
    }

    public static Integer getHotLimit() {
        return getConfigInt(COCO_WX_INDEX_HOT);
    }

    public static Integer getBrandLimit() {
        return getConfigInt(COCO_WX_INDEX_BRAND);
    }

    public static Integer getTopicLimit() {
        return getConfigInt(COCO_WX_INDEX_TOPIC);
    }

    public static Integer getCatlogListLimit() {
        return getConfigInt(COCO_WX_INDEX_CATLOG_LIST);
    }

    public static Integer getCatlogMoreLimit() {
        return getConfigInt(COCO_WX_INDEX_CATLOG_GOODS);
    }

    public static boolean isAutoCreateShareImage() {
        return getConfigBoolean(COCO_WX_SHARE);
    }

    public static BigDecimal getFreight() {
        return getConfigBigDec(COCO_EXPRESS_FREIGHT_VALUE);
    }

    public static BigDecimal getFreightLimit() {
        return getConfigBigDec(COCO_EXPRESS_FREIGHT_MIN);
    }

    public static Integer getOrderUnpaid() {
        return getConfigInt(COCO_ORDER_UNPAID);
    }

    public static Integer getOrderUnconfirm() {
        return getConfigInt(COCO_ORDER_UNCONFIRM);
    }

    public static Integer getOrderComment() {
        return getConfigInt(COCO_ORDER_COMMENT);
    }

    public static String getMallName() {
        return getConfig(COCO_MALL_NAME);
    }

    public static String getMallAddress() {
        return getConfig(COCO_MALL_ADDRESS);
    }

    public static String getMallPhone() {
        return getConfig(COCO_MALL_PHONE);
    }

    public static String getMallQQ() {
        return getConfig(COCO_MALL_QQ);
    }

    public static String getMallLongitude() {
        return getConfig(COCO_MALL_LONGITUDE);
    }

    public static String getMallLatitude() {
        return getConfig(COCO_MALL_Latitude);
    }

    public static void setConfigs(Map<String, String> configs) {
        SYSTEM_CONFIGS = configs;
    }

    public static void updateConfigs(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            SYSTEM_CONFIGS.put(entry.getKey(), entry.getValue());
        }
    }
}