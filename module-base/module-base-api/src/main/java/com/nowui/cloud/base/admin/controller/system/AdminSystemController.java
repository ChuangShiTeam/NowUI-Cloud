package com.nowui.cloud.base.admin.controller.system;

import com.nowui.cloud.base.admin.rpc.AdminRpc;
import io.swagger.annotations.Api;
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