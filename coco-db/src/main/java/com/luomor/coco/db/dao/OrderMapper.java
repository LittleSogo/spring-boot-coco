package com.luomor.coco.db.dao;

import org.apache.ibatis.annotations.Param;
import com.luomor.coco.db.domain.CocoOrder;

import java.time.LocalDateTime;

public interface OrderMapper {
    int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime, @Param("order") CocoOrder order);
}