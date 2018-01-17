package com.nowui.cloud.cms.toolbar.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.cms.toolbar.rpc.ToolbarRpc;
import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;

/**
 * 工具栏系统端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "工具栏", description = "工具栏接口系统端管理")
@RestController
public class ToolbarSystemController implements ToolbarRpc {
    
    @Autowired
    private ToolbarService toolbarService;
    
    @Autowired
    private FileRpc fileRpc;

    @Override
    public List<Toolbar> list(String appId) {

        List<Toolbar> toolbarList = toolbarService.mobileList(appId);

        if (Util.isNullOrEmpty(toolbarList)) {
            return new ArrayList<>();
        }

        String fileIds = Util.beanToFieldString(toolbarList, Toolbar.TOOLBAR_IMAGE);
        List<File> fileList = fileRpc.findsV1(fileIds);
        
        toolbarList = Util.beanReplaceField(toolbarList, Toolbar.TOOLBAR_IMAGE, fileList, File.FILE_PATH);
        

        String activeFileIds = Util.beanToFieldString(toolbarList, Toolbar.TOOLBAR_ACTIVE_IMAGE);
        List<File> activeFileList = fileRpc.findsV1(activeFileIds);

        toolbarList = Util.beanReplaceField(toolbarList, Toolbar.TOOLBAR_ACTIVE_IMAGE, activeFileList, File.FILE_PATH);


        return toolbarList;
    }

}
