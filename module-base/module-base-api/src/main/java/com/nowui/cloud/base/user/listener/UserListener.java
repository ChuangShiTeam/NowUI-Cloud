package com.nowui.cloud.base.user.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nowui.cloud.base.user.service.UserService;

@Component
public class UserListener {
    
    @Autowired
    private UserService userService;
    

}
