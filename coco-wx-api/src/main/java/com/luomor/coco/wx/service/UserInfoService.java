package com.luomor.coco.wx.service;

import com.luomor.coco.db.domain.CocoUser;
import com.luomor.coco.db.service.CocoUserService;
import com.luomor.coco.wx.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class UserInfoService {
    @Autowired
    private CocoUserService userService;


    public UserInfo getInfo(Integer userId) {
        CocoUser user = userService.findById(userId);
        Assert.state(user != null, "用户不存在");
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(user.getNickname());
        userInfo.setAvatarUrl(user.getAvatar());
        return userInfo;
    }
}
