package com.nowui.cloud.base.user.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.user.rpc.UserRpc;
import com.nowui.cloud.controller.BaseController;

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
public class UserSystemController extends BaseController implements UserRpc {
	
	
}