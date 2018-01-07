package com.nowui.cloud.cms.navigationBar.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.navigationBar.entity.NavigationBar;
import com.nowui.cloud.cms.navigationBar.rpc.NavigationBarRpc;
import com.nowui.cloud.cms.navigationBar.service.NavigationBarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 导航栏系统端控制器
 *
 * @author shawn
 *
 * 2017-12-31
 */
@Api(value = "导航栏", description = "导航栏系统端接口管理")
@RestController
public class NavigationBarSystemController implements NavigationBarRpc {

}