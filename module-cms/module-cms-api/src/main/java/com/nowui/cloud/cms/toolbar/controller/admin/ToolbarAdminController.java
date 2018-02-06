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
import com.nowui.cloud.cms.toolbar.router.ToolbarRouter;
import com.nowui.cloud.cms.toolbar.service.ToolbarService;
import com.nowui.cloud.cms.toolbar.view.ToolbarView;
import com.nowui.cloud.controller.BaseController;
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
        Toolbar toolbarEntity = getEntry(Toolbar.class);

        validateRequest(
                toolbarEntity,
                Toolbar.APP_ID,
                Toolbar.TOOLBAR_NAME,
                Toolbar.PAGE_INDEX,
                Toolbar.PAGE_SIZE
        );
        Integer resultTotal = toolbarService.countForAdmin(toolbarEntity.getAppId(), toolbarEntity.getToolbarName());
        List<Toolbar> resultList = toolbarService.listForAdmin(toolbarEntity.getAppId(), toolbarEntity.getToolbarName(), toolbarEntity.getM(), toolbarEntity.getN());
        //处理工具栏未激活图片
        String fileIds = Util.beanToFieldString(resultList, Toolbar.TOOLBAR_IMAGE);
        List<File> fileList = fileRpc.findsV1(fileIds);

        resultList = Util.beanReplaceField(resultList, Toolbar.TOOLBAR_IMAGE, fileList, File.FILE_PATH);

        //处理工具栏激活图片
        String activeFileIds = Util.beanToFieldString(resultList, Toolbar.TOOLBAR_ACTIVE_IMAGE);
        List<File> activeFileList = fileRpc.findsV1(activeFileIds);

        resultList = Util.beanReplaceField(resultList, Toolbar.TOOLBAR_ACTIVE_IMAGE, activeFileList, File.FILE_PATH);

        validateResponse(
                Toolbar.TOOLBAR_ID,
                Toolbar.TOOLBAR_NAME,
                Toolbar.TOOLBAR_IMAGE,
                Toolbar.TOOLBAR_ACTIVE_IMAGE,
                Toolbar.TOOLBAR_URL,
                Toolbar.TOOLBAR_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "根据编号查询工具栏信息")
    @RequestMapping(value = "/toolbar/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Toolbar toolbarEntity = getEntry(Toolbar.class);

        validateRequest(toolbarEntity, Toolbar.TOOLBAR_ID);

        ToolbarView result = toolbarService.find(toolbarEntity.getToolbarId());

//        File file = fileRpc.findV1(result.getToolbarImage());
//        file.keep(File.FILE_ID, File.FILE_PATH);
//        result.put(Toolbar.TOOLBAR_IMAGE, file);

//        File activeFile = fileRpc.findV1(result.getToolbarActiveImage());
//        file.keep(File.FILE_ID, File.FILE_PATH);
//        result.put(Toolbar.TOOLBAR_ACTIVE_IMAGE, activeFile);

        validateResponse(
                Toolbar.TOOLBAR_ID,
                Toolbar.TOOLBAR_NAME,
                Toolbar.TOOLBAR_ACTIVE_IMAGE,
                Toolbar.TOOLBAR_IMAGE,
                Toolbar.TOOLBAR_SORT,
                Toolbar.TOOLBAR_URL,
                Toolbar.SYSTEM_VERSION
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
                Toolbar.TOOLBAR_ACTIVE_IMAGE,
                Toolbar.TOOLBAR_IMAGE,
                Toolbar.TOOLBAR_URL,
                Toolbar.TOOLBAR_SORT
        );

        String toolbarId = Util.getRandomUUID();

        Toolbar result = toolbarService.save(toolbarEntity, toolbarId, toolbarEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ToolbarRouter.TOOLBAR_V1_SAVE, toolbarEntity.getAppId(), toolbarEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "工具栏修改")
    @RequestMapping(value = "/toolbar/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Toolbar toolbarEntity = getEntry(Toolbar.class);
        validateRequest(
                toolbarEntity,
                Toolbar.TOOLBAR_ID,
                Toolbar.TOOLBAR_NAME,
                Toolbar.TOOLBAR_ACTIVE_IMAGE,
                Toolbar.TOOLBAR_IMAGE,
                Toolbar.TOOLBAR_URL,
                Toolbar.TOOLBAR_SORT,
                Toolbar.SYSTEM_VERSION
        );

        Toolbar result = toolbarService.update(toolbarEntity, toolbarEntity.getToolbarId(), toolbarEntity.getSystemRequestUserId(), toolbarEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ToolbarRouter.TOOLBAR_V1_UPDATE, toolbarEntity.getAppId(), toolbarEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
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

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, ToolbarRouter.TOOLBAR_V1_DELETE, toolbarEntity.getAppId(), toolbarEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "工具栏数据同步")
    @RequestMapping(value = "/toolbar/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        List<Toolbar> toolbarList = toolbarService.listByMysql();

        for(Toolbar toolbar : toolbarList) {
            ToolbarView toolbarView = new ToolbarView();
            toolbarView.putAll(toolbar);

            toolbarService.update(toolbarView);
        }

        return renderJson(true);
    }

}
