package com.nowui.cloud.cms.navigation.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.rpc.NavigationRpc;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 导航栏系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "导航栏", description = "导航栏系统端接口管理")
@RestController
public class NavigationSystemController implements NavigationRpc {

}