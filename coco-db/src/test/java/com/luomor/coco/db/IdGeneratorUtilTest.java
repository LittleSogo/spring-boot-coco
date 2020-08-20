package com.luomor.coco.db;

import com.luomor.coco.db.util.IdGeneratorUtil;
import org.junit.Test;

public class IdGeneratorUtilTest {
    @Test
    public void test() {
        System.out.println(IdGeneratorUtil.getLongGuid());
    }
}
