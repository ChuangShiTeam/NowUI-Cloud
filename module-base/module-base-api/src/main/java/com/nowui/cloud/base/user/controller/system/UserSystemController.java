package com.nowui.cloud.base.user.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;

/**
 * 用户系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "用户", description = "用户系统端接口管理")
@RestController
public class UserSystemController implements UserRpc {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileRpc fileRpc;

	@Override
	public Integer count(String appId, String userType, String userAccount, String userNickName, String userName, String userMobile) {
		return userService.count(appId, userType, userAccount, userNickName, userName, userMobile);
	}

	@Override
	public List<User> list(String appId, String userType, String userAccount, String userNickName, String userName, String userMobile,
			Integer pageIndex, Integer pageSize) {
		return userService.list(appId, userType, userAccount, userNickName, userName, userMobile, pageIndex, pageSize);
	}

    @Override
    public User find(String userId) {
        User user = userService.find(userId);
        
        if (!Util.isNullOrEmpty(user)) {
            File file = fileRpc.find(user.getUserAvatar());
            file.keep(File.FILE_ID, File.FILE_PATH);
            
            user.put(User.USER_AVATAR, file);
        }
        
        return user;
    }
	
	

}