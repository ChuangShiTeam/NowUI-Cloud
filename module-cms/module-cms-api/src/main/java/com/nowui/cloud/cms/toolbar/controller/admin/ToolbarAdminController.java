package com.nowui.cloud.cms.toolbar.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.toolbar.entity.Toolbar;
import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.cms.toolbar.view.ToolbarView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 工具栏后台端控制器
 *
 * @author marcus
 * <p>
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
    public Map<String, Object> listV1() {
        ToolbarView toolbarView = getEntry(ToolbarView.class);

        validateRequest(
                toolbarView,
                ToolbarView.APP_ID,
                ToolbarView.TOOLBAR_NAME,
                ToolbarView.PAGE_INDEX,
                ToolbarView.PAGE_SIZE
        );
        Integer resultTotal = toolbarService.countForAdmin(toolbarView.getAppId(), toolbarView.getToolbarName());
        List<ToolbarView> resultList = toolbarService.listForAdmin(toolbarView.getAppId(), toolbarView.getToolbarName(), toolbarView.getM(), toolbarView.getN());

        validateResponse(
                ToolbarView.TOOLBAR_ID,
                ToolbarView.TOOLBAR_NAME,
                ToolbarView.TOOLBAR_IMAGE_FILE_PATH,
                ToolbarView.TOOLBAR_ACTIVE_IMAGE_FILE_PATH,
                ToolbarView.TOOLBAR_URL,
                ToolbarView.TOOLBAR_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询工具栏信息")
    @RequestMapping(value = "/toolbar/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        ToolbarView toolbarView = getEntry(ToolbarView.class);

        validateRequest(
                toolbarView, 
                Toolbar.TOOLBAR_ID
        );

        ToolbarView result = toolbarService.find(toolbarView.getToolbarId());

        validateResponse(
                ToolbarView.TOOLBAR_ID,
                ToolbarView.TOOLBAR_NAME,
                ToolbarView.TOOLBAR_ACTIVE_IMAGE_FILE_ID,
                ToolbarView.TOOLBAR_ACTIVE_IMAGE_FILE_PATH,
                ToolbarView.TOOLBAR_IMAGE_FILE_PATH,
                ToolbarView.TOOLBAR_SORT,
                ToolbarView.TOOLBAR_URL,
                ToolbarView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "工具栏新增")
    @RequestMapping(value = "/toolbar/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Toolbar toolbarEntity = getEntry(Toolbar.class);

        validateRequest(
                toolbarEntity,
                Toolbar.APP_ID,
                Toolbar.TOOLBAR_NAME,
                Toolbar.TOOLBAR_ACTIVE_IMAGE_FILE_ID,
                Toolbar.TOOLBAR_IMAGE_FILE_ID,
                Toolbar.TOOLBAR_URL,
                Toolbar.TOOLBAR_SORT
        );

        String toolbarId = Util.getRandomUUID();

        Toolbar result = toolbarService.save(toolbarEntity, toolbarId, toolbarEntity.getSystemRequestUserId());

        if (result != null) {
            ToolbarView toolbarView = new ToolbarView();
            
            toolbarView.putAll(result);
            
            toolbarService.save(toolbarView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "工具栏修改")
    @RequestMapping(value = "/toolbar/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Toolbar toolbarEntity = getEntry(Toolbar.class);
        validateRequest(
                toolbarEntity,
                Toolbar.TOOLBAR_ID,
                Toolbar.TOOLBAR_NAME,
                Toolbar.TOOLBAR_ACTIVE_IMAGE_FILE_ID,
                Toolbar.TOOLBAR_IMAGE_FILE_ID,
                Toolbar.TOOLBAR_URL,
                Toolbar.TOOLBAR_SORT,
                Toolbar.SYSTEM_VERSION
        );

        Toolbar result = toolbarService.update(toolbarEntity, toolbarEntity.getToolbarId(), toolbarEntity.getSystemRequestUserId(), toolbarEntity.getSystemVersion());

        if (result != null) {
            ToolbarView toolbarView = new ToolbarView();
            
            toolbarView.putAll(result);
            
            toolbarService.update(toolbarView);
            
        }
        
        return renderJson(true);
    }

    @ApiOperation(value = "工具栏删除")
    @RequestMapping(value = "/toolbar/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Toolbar toolbarEntity = getEntry(Toolbar.class);
        validateRequest(
                toolbarEntity,
                Toolbar.TOOLBAR_ID,
                Toolbar.SYSTEM_VERSION
        );

        Toolbar result = toolbarService.delete(toolbarEntity.getToolbarId(), toolbarEntity.getSystemRequestUserId(), toolbarEntity.getSystemVersion());

        if (result != null) {
            ToolbarView toolbarView = new ToolbarView();
            
            toolbarView.putAll(result);
            
            toolbarService.delete(toolbarView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "工具栏数据同步")
    @RequestMapping(value = "/toolbar/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        List<Toolbar> toolbarList = toolbarService.listByMysql();

        for(Toolbar toolbar : toolbarList) {
            ToolbarView toolbarView = new ToolbarView();
            toolbarView.putAll(toolbar);
            
            File toolbarActiveImageFile = fileRpc.findByMysqlV1(toolbarView.getToolbarActiveImageFileId());
            
            File toolbarImageFile = fileRpc.findByMysqlV1(toolbarView.getToolbarActiveImageFileId());
            
            toolbarView.setToolbarActiveImageFilePath(toolbarActiveImageFile.getFilePath());
            
            toolbarView.setToolbarImageFilePath(toolbarImageFile.getFilePath());

            toolbarService.saveOrUpdate(toolbarView);
        }

        return renderJson(true);
    }

}
