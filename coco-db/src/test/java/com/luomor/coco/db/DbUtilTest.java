package com.luomor.coco.db;

import org.junit.Test;
import com.luomor.coco.db.util.DbUtil;

import java.io.File;

public class DbUtilTest {
    @Test
    public void testBackup() {
        File file = new File("test.sql");
        DbUtil.backup(file, "coco", "coco123456", "coco");
    }

//    这个测试用例会重置coco数据库，所以比较危险，请开发者注意
//    @Test
    public void testLoad() {
        File file = new File("test.sql");
        DbUtil.load(file, "coco", "coco123456", "coco");
    }
}
