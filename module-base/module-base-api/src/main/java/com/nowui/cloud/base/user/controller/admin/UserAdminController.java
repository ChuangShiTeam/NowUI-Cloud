package com.nowui.cloud.base.user.controller.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;

/**
 * 用户管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "用户", description = "用户管理端接口管理")
@RestController
public class UserAdminController extends BaseController {
	
	@Autowired
    private AdminService adminService;


}