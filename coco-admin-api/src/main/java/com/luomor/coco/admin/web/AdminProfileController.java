package com.luomor.coco.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import com.luomor.coco.core.util.JacksonUtil;
import com.luomor.coco.core.util.ResponseUtil;
import com.luomor.coco.core.util.bcrypt.BCryptPasswordEncoder;
import com.luomor.coco.core.validator.Order;
import com.luomor.coco.core.validator.Sort;
import com.luomor.coco.db.domain.CocoAdmin;
import com.luomor.coco.db.domain.CocoIssue;
import com.luomor.coco.db.domain.CocoNotice;
import com.luomor.coco.db.domain.CocoNoticeAdmin;
import com.luomor.coco.db.service.CocoAdminService;
import com.luomor.coco.db.service.CocoNoticeAdminService;
import com.luomor.coco.db.service.CocoNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.luomor.coco.admin.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

@RestController
@RequestMapping("/admin/profile")
@Validated
public class AdminProfileController {
    private final Log logger = LogFactory.getLog(AdminProfileController.class);

    @Autowired
    private CocoAdminService adminService;
    @Autowired
    private CocoNoticeService noticeService;
    @Autowired
    private CocoNoticeAdminService noticeAdminService;

    @RequiresAuthentication
    @PostMapping("/password")
    public Object create(@RequestBody String body) {
        String oldPassword = JacksonUtil.parseString(body, "oldPassword");
        String newPassword = JacksonUtil.parseString(body, "newPassword");
        if (StringUtils.isEmpty(oldPassword)) {
            return ResponseUtil.badArgument();
        }
        if (StringUtils.isEmpty(newPassword)) {
            return ResponseUtil.badArgument();
        }

        Subject currentUser = SecurityUtils.getSubject();
        CocoAdmin admin = (CocoAdmin) currentUser.getPrincipal();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(oldPassword, admin.getPassword())) {
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "账号密码不对");
        }

        String encodedNewPassword = encoder.encode(newPassword);
        admin.setPassword(encodedNewPassword);

        adminService.updateById(admin);
        return ResponseUtil.ok();
    }

    private Integer getAdminId(){
        Subject currentUser = SecurityUtils.getSubject();
        CocoAdmin admin = (CocoAdmin) currentUser.getPrincipal();
        return admin.getId();
    }

    @RequiresAuthentication
    @GetMapping("/nnotice")
    public Object nNotice() {
        int count = noticeAdminService.countUnread(getAdminId());
        return ResponseUtil.ok(count);
    }

    @RequiresAuthentication
    @GetMapping("/lsnotice")
    public Object lsNotice(String title, String type,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit,
                            @Sort @RequestParam(defaultValue = "add_time") String sort,
                            @Order @RequestParam(defaultValue = "desc") String order) {
        List<CocoNoticeAdmin> noticeList = noticeAdminService.querySelective(title, type, getAdminId(), page, limit, sort, order);
        return ResponseUtil.okList(noticeList);
    }

    @RequiresAuthentication
    @PostMapping("/catnotice")
    public Object catNotice(@RequestBody String body) {
        Integer noticeId = JacksonUtil.parseInteger(body, "noticeId");
        if(noticeId == null){
            return ResponseUtil.badArgument();
        }

        CocoNoticeAdmin noticeAdmin = noticeAdminService.find(noticeId, getAdminId());
        if(noticeAdmin == null){
           return ResponseUtil.badArgumentValue();
        }
        // 更新通知记录中的时间
        noticeAdmin.setReadTime(LocalDateTime.now());
        noticeAdminService.update(noticeAdmin);

        // 返回通知的相关信息
        Map<String, Object> data = new HashMap<>();
        CocoNotice notice = noticeService.findById(noticeId);
        data.put("title", notice.getTitle());
        data.put("content", notice.getContent());
        data.put("time", notice.getUpdateTime());
        Integer adminId = notice.getAdminId();
        if(adminId.equals(0)){
            data.put("admin", "系统");
        }
        else{
            CocoAdmin admin = adminService.findById(notice.getAdminId());
            data.put("admin", admin.getUsername());
            data.put("avatar", admin.getAvatar());
        }
        return ResponseUtil.ok(data);
    }

    @RequiresAuthentication
    @PostMapping("/bcatnotice")
    public Object bcatNotice(@RequestBody String body) {
        List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
        noticeAdminService.markReadByIds(ids, getAdminId());
        return ResponseUtil.ok();
    }

    @RequiresAuthentication
    @PostMapping("/rmnotice")
    public Object rmNotice(@RequestBody String body) {
        Integer id = JacksonUtil.parseInteger(body, "id");
        if(id == null){
            return ResponseUtil.badArgument();
        }
        noticeAdminService.deleteById(id, getAdminId());
        return ResponseUtil.ok();
    }

    @RequiresAuthentication
    @PostMapping("/brmnotice")
    public Object brmNotice(@RequestBody String body) {
        List<Integer> ids = JacksonUtil.parseIntegerList(body, "ids");
        noticeAdminService.deleteByIds(ids, getAdminId());
        return ResponseUtil.ok();
    }

}
