package com.luomor.coco.db.service;

import com.luomor.coco.db.domain.CocoCoupon;
import com.luomor.coco.db.domain.CocoCouponUser;
import com.luomor.coco.db.util.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponAssignService {

    @Autowired
    private CocoCouponUserService couponUserService;
    @Autowired
    private CocoCouponService couponService;

    /**
     * 分发注册优惠券
     *
     * @param userId
     * @return
     */
    public void assignForRegister(Integer userId) {
        List<CocoCoupon> couponList = couponService.queryRegister();
        for(CocoCoupon coupon : couponList){
            Integer couponId = coupon.getId();

            Integer count = couponUserService.countUserAndCoupon(userId, couponId);
            if (count > 0) {
                continue;
            }

            Short limit = coupon.getLimit();
            while(limit > 0){
                CocoCouponUser couponUser = new CocoCouponUser();
                couponUser.setCouponId(couponId);
                couponUser.setUserId(userId);
                Short timeType = coupon.getTimeType();
                if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
                    couponUser.setStartTime(coupon.getStartTime());
                    couponUser.setEndTime(coupon.getEndTime());
                }
                else{
                    LocalDateTime now = LocalDateTime.now();
                    couponUser.setStartTime(now);
                    couponUser.setEndTime(now.plusDays(coupon.getDays()));
                }
                couponUserService.add(couponUser);

                limit--;
            }
        }

    }

}