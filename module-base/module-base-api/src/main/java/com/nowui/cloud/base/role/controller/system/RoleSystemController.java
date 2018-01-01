package com.nowui.cloud.base.role.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.role.entity.Role;
import com.nowui.cloud.base.role.rpc.RoleRpc;
import com.nowui.cloud.base.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "角色", description = "角色系统端接口管理")
@RestController
public class RoleSystemController implements RoleRpc {

}