package com.nowui.cloud.cms.toolbar.controller.desktop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;

/**
 * 工具栏桌面端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "工具栏", description = "工具栏接口桌面端管理")
@RestController
public class ToolbarDesktopController extends BaseController {
    
    @Autowired
    private ToolbarService toolbarService;

}
