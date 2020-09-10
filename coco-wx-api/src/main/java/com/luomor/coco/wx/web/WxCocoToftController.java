package com.luomor.coco.wx.web;

import com.luomor.coco.wx.service.CocoToftService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Coco服务
 */
@RestController
@RequestMapping("/wx/cocotoft")
@Validated
public class WxCocoToftController {
    private final Log logger = LogFactory.getLog(WxCocoToftController.class);

    @Autowired
    private CocoToftService cocoToftService;

    /**
     * 获取可用服务
     *
     * @return 可用服务数据
     */
    @GetMapping("getAvailableService")
    public Object getAvailableService() {
        return cocoToftService.getAvailableService();
    }
}
