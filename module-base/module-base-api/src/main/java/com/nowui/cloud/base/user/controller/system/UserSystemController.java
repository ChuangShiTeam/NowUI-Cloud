package com.nowui.cloud.base.user.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.base.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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

	@Override
	public Integer count(String appId, String userType, String userAccount, String userNickName, String userName, String userMobile) {
		return userService.count(appId, userType, userAccount, userNickName, userName, userMobile);
	}

	@Override
	public List<User> list(String appId, String userType, String userAccount, String userNickName, String userName, String userMobile,
			Integer pageIndex, Integer pageSize) {
		return userService.list(appId, userType, userAccount, userNickName, userName, userMobile, pageIndex, pageSize);
	}
	
	

}