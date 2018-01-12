package com.nowui.cloud.base.app.controller.system;

import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.rpc.AppConfigCategoryRpc;

import io.swagger.annotations.Api;

/**
 * 应用配置分类系统端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "应用配置分类", description = "应用配置分类系统端接口管理")
@RestController
public class AppConfigCategorySystemController implements AppConfigCategoryRpc {

}
