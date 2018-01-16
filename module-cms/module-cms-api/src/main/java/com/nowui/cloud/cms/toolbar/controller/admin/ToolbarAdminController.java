package com.nowui.cloud.cms.toolbar.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 工具栏后台端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "工具栏", description = "工具栏接口后台端管理")
@RestController
public class ToolbarAdminController extends BaseController {
    
    @Autowired
    private ToolbarService toolbarService;
    
    @Autowired
    private FileRpc fileRpc;
    
    @ApiOperation(value = "工具栏分页列表")
    @RequestMapping(value = "/toolbar/admin/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Toolbar body) {
        validateRequest(
            body, 
            Toolbar.APP_ID, 
            Toolbar.TOOLBAR_NAME, 
            Toolbar.PAGE_INDEX, 
            Toolbar.PAGE_SIZE
        );
        Integer resultTotal = toolbarService.countForAdmin(body.getAppId(), body.getToolbarName());
        List<Toolbar> resultList = toolbarService.listForAdmin(body.getAppId(), body.getToolbarName(), body.getM(), body.getN());

        String fileIds = Util.beanToFieldString(resultList, Toolbar.TOOLBAR_IMAGE);
        List<File> fileList = fileRpc.findsV1(fileIds);
        
        resultList = Util.beanReplaceField(resultList, Toolbar.TOOLBAR_IMAGE, fileList, File.FILE_PATH);
        
        
        String activeFileIds = Util.beanToFieldString(resultList, Toolbar.TOOLBAR_ACTIVE_IMAGE);
        List<File> activeFileList = fileRpc.findsV1(activeFileIds);
        
        resultList = Util.beanReplaceField(resultList, Toolbar.TOOLBAR_ACTIVE_IMAGE, activeFileList, File.FILE_PATH);

        validateResponse(
            Toolbar.TOOLBAR_ID, 
            Toolbar.TOOLBAR_NAME, 
            Toolbar.TOOLBAR_IMAGE,
            Toolbar.TOOLBAR_ACTIVE_IMAGE,
            Toolbar.TOOLBAR_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询工具栏信息")
    @RequestMapping(value = "/toolbar/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Toolbar body) {
        validateRequest(body, Toolbar.TOOLBAR_ID);

        Toolbar result = toolbarService.find(body.getToolbarId());
        
        File file = fileRpc.findV1(result.getToolbarImage());
        file.keep(File.FILE_ID, File.FILE_PATH);
        result.put(Toolbar.TOOLBAR_IMAGE, file);

        File activeFile = fileRpc.findV1(result.getToolbarActiveImage());
        file.keep(File.FILE_ID, File.FILE_PATH);
        result.put(Toolbar.TOOLBAR_ACTIVE_IMAGE, activeFile);
        
        validateResponse(
            Toolbar.TOOLBAR_ID, 
            Toolbar.TOOLBAR_NAME,
            Toolbar.TOOLBAR_ACTIVE_IMAGE,
            Toolbar.TOOLBAR_IMAGE, 
            Toolbar.TOOLBAR_SORT, 
            Toolbar.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "工具栏新增")
    @RequestMapping(value = "/toolbar/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Toolbar body) {
        validateRequest(
            body, 
            Toolbar.APP_ID, 
            Toolbar.TOOLBAR_NAME, 
            Toolbar.TOOLBAR_ACTIVE_IMAGE, 
            Toolbar.TOOLBAR_IMAGE, 
            Toolbar.TOOLBAR_SORT
        );

        Boolean result = toolbarService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "工具栏修改")
    @RequestMapping(value = "/toolbar/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Toolbar body) {
        validateRequest(
            body, 
            Toolbar.TOOLBAR_ID, 
            Toolbar.TOOLBAR_NAME,
            Toolbar.TOOLBAR_ACTIVE_IMAGE, 
            Toolbar.TOOLBAR_IMAGE, 
            Toolbar.TOOLBAR_SORT,
            Toolbar.SYSTEM_VERSION
        );

        Boolean result = toolbarService.update(body, body.getToolbarId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "工具栏删除")
    @RequestMapping(value = "/toolbar/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Toolbar body) {
        validateRequest(
            body, 
            Toolbar.TOOLBAR_ID, 
            Toolbar.SYSTEM_VERSION
        );

        Boolean result = toolbarService.delete(body.getToolbarId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }
    
    @ApiOperation(value = "工具栏重建缓存")
    @RequestMapping(value = "/toolbar/admin/v1/replace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1(@RequestBody Toolbar body) {
        validateRequest(body, Toolbar.TOOLBAR_ID);

        toolbarService.replace(body.getToolbarId());

        return renderJson(true);
    }

}
