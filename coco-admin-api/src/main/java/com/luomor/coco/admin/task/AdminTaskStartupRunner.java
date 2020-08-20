package com.luomor.coco.admin.task;

import com.luomor.coco.core.task.TaskService;
import com.luomor.coco.db.domain.CocoGrouponRules;
import com.luomor.coco.db.service.CocoGrouponRulesService;
import com.luomor.coco.db.util.GrouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class AdminTaskStartupRunner implements ApplicationRunner {

    @Autowired
    private CocoGrouponRulesService rulesService;
    @Autowired
    private TaskService taskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<CocoGrouponRules> grouponRulesList = rulesService.queryByStatus(GrouponConstant.RULE_STATUS_ON);
        for(CocoGrouponRules grouponRules : grouponRulesList){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime expire =  grouponRules.getExpireTime();
            if(expire.isBefore(now)) {
                // 已经过期，则加入延迟队列
                taskService.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), 0));
            }
            else{
                // 还没过期，则加入延迟队列
                long delay = ChronoUnit.MILLIS.between(now, expire);
                taskService.addTask(new GrouponRuleExpiredTask(grouponRules.getId(), delay));
            }
        }
    }
}