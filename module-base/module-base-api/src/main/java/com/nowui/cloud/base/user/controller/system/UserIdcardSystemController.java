package com.nowui.cloud.base.user.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.rpc.UserIdcardRpc;
import com.nowui.cloud.base.user.service.UserIdcardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户身份证系统端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户身份证", description = "用户身份证系统端接口管理")
@RestController
public class UserIdcardSystemController implements UserIdcardRpc {

}