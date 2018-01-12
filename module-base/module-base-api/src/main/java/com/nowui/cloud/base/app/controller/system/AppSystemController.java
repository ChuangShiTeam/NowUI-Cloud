package com.nowui.cloud.base.app.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.app.rpc.AppRpc;

import io.swagger.annotations.Api;

/**
 * 应用系统端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "应用", description = "应用系统端接口管理")
@RestController
public class AppSystemController implements AppRpc {

    @Override
    public App find(String appId) {
        // TODO Auto-generated method stub
        return null;
    }

}
