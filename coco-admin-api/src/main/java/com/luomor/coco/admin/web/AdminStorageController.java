package com.luomor.coco.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.luomor.coco.admin.annotation.RequiresPermissionsDesc;
import com.luomor.coco.core.storage.StorageService;
import com.luomor.coco.core.util.ResponseUtil;
import com.luomor.coco.core.validator.Order;
import com.luomor.coco.core.validator.Sort;
import com.luomor.coco.db.domain.CocoStorage;
import com.luomor.coco.db.service.CocoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/storage")
@Validated
public class AdminStorageController {
    private final Log logger = LogFactory.getLog(AdminStorageController.class);

    @Autowired
    private StorageService storageService;
    @Autowired
    private CocoStorageService cocoStorageService;

    @RequiresPermissions("admin:storage:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "查询")
    @GetMapping("/list")
    public Object list(String key, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<CocoStorage> storageList = cocoStorageService.querySelective(key, name, page, limit, sort, order);
        return ResponseUtil.okList(storageList);
    }

    @RequiresPermissions("admin:storage:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "上传")
    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        CocoStorage cocoStorage = storageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return ResponseUtil.ok(cocoStorage);
    }

    @RequiresPermissions("admin:storage:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "详情")
    @PostMapping("/read")
    public Object read(@NotNull Integer id) {
        CocoStorage storageInfo = cocoStorageService.findById(id);
        if (storageInfo == null) {
            return ResponseUtil.badArgumentValue();
        }
        return ResponseUtil.ok(storageInfo);
    }

    @RequiresPermissions("admin:storage:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody CocoStorage cocoStorage) {
        if (cocoStorageService.update(cocoStorage) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(cocoStorage);
    }

    @RequiresPermissions("admin:storage:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody CocoStorage cocoStorage) {
        String key = cocoStorage.getKey();
        if (StringUtils.isEmpty(key)) {
            return ResponseUtil.badArgument();
        }
        cocoStorageService.deleteByKey(key);
        storageService.delete(key);
        return ResponseUtil.ok();
    }
}
