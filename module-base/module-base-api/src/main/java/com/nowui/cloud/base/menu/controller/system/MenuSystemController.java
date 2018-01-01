package com.nowui.cloud.base.menu.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.menu.entity.Menu;
import com.nowui.cloud.base.menu.rpc.MenuRpc;
import com.nowui.cloud.base.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单系统端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "菜单", description = "菜单系统端接口管理")
@RestController
public class MenuSystemController implements MenuRpc {

}