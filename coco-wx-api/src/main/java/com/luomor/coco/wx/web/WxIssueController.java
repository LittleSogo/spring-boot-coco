package com.luomor.coco.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.luomor.coco.core.util.ResponseUtil;
import com.luomor.coco.core.validator.Order;
import com.luomor.coco.core.validator.Sort;
import com.luomor.coco.db.domain.CocoIssue;
import com.luomor.coco.db.service.CocoIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wx/issue")
@Validated
public class WxIssueController {
    private final Log logger = LogFactory.getLog(WxIssueController.class);

    @Autowired
    private CocoIssueService issueService;

    /**
     * 帮助中心
     */
    @RequestMapping("/list")
    public Object list(String question,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<CocoIssue> issueList = issueService.querySelective(question, page, size, sort, order);
        return ResponseUtil.okList(issueList);
    }

}
