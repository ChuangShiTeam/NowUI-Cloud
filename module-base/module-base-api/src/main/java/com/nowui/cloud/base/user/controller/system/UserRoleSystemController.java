package com.nowui.cloud.base.user.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.entity.UserRole;
import com.nowui.cloud.base.user.rpc.UserRoleRpc;
import com.nowui.cloud.base.user.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户角色系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "用户角色", description = "用户角色系统端接口管理")
@RestController
public class UserRoleSystemController implements UserRoleRpc {

}