package com.nowui.cloud.base.admin.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.rpc.AdminRpc;
import com.nowui.cloud.base.admin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员系统端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "管理员", description = "管理员系统端接口管理")
@RestController
public class AdminSystemController implements AdminRpc {

}