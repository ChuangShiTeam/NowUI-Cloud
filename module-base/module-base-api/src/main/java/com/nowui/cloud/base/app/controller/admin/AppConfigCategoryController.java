package com.nowui.cloud.base.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.router.AppConfigCategoryRouter;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.view.AppConfigCategoryView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author marcus
 */
@Api(value = "应用配置分类", description = "应用配置分类接口管理")
@RestController
public class AppConfigCategoryController extends BaseController {

    @Autowired
    private AppConfigCategoryService appConfigCategoryService;

    @ApiOperation(value = "应用配置分类列表")
    @RequestMapping(value = "/app/config/category/admin/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        AppConfigCategoryView appConfigCategoryView = getEntry(AppConfigCategoryView.class);

        validateRequest(
        		appConfigCategoryView,
                AppConfigCategoryView.APP_ID,
                AppConfigCategoryView.CONFIG_CATEGORY_NAME,
                AppConfigCategoryView.CONFIG_CATEGORY_CODE,
                AppConfigCategoryView.PAGE_INDEX,
                AppConfigCategoryView.PAGE_SIZE
        );

        Integer resultTotal = appConfigCategoryService.countForAdmin(appConfigCategoryView.getAppId(), appConfigCategoryView.getConfigCategoryName(), appConfigCategoryView.getConfigCategoryCode());
        List<AppConfigCategoryView> resultList = appConfigCategoryService.listForAdmin(appConfigCategoryView.getAppId(), appConfigCategoryView.getConfigCategoryName(), appConfigCategoryView.getConfigCategoryCode(), appConfigCategoryView.getPageIndex(), appConfigCategoryView.getPageSize());

        validateResponse(
        		AppConfigCategoryView.CONFIG_CATEGORY_ID,
        		AppConfigCategoryView.CONFIG_CATEGORY_NAME,
        		AppConfigCategoryView.CONFIG_CATEGORY_CODE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "所有应用配置分类列表")
    @RequestMapping(value = "/app/config/category/admin/v1/all/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> allListV1() {
    	AppConfigCategoryView appConfigCategoryView = getEntry(AppConfigCategoryView.class);


        List<AppConfigCategoryView> resultList = appConfigCategoryService.appList(appConfigCategoryView.getAppId());

        validateResponse(
        		AppConfigCategoryView.CONFIG_CATEGORY_ID,
        		AppConfigCategoryView.CONFIG_CATEGORY_NAME
        );

        return renderJson(resultList);
    }

    @ApiOperation(value = "应用配置分类信息")
    @RequestMapping(value = "/app/config/category/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        AppConfigCategoryView appConfigCategoryView = getEntry(AppConfigCategoryView.class);

        validateRequest(
                appConfigCategoryView,
                AppConfigCategoryView.CONFIG_CATEGORY_ID
        );

        AppConfigCategoryView result = appConfigCategoryService.find(appConfigCategoryView.getConfigCategoryId());

        validateResponse(
                AppConfigCategoryView.CONFIG_CATEGORY_ID,
                AppConfigCategoryView.CONFIG_CATEGORY_NAME,
                AppConfigCategoryView.CONFIG_CATEGORY_CODE,
                AppConfigCategoryView.CONFIG_CATEGORY_DESCRIPTION,
                AppConfigCategoryView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置分类新增")
    @RequestMapping(value = "/app/config/category/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        AppConfigCategory appConfigCategoryEntity = getEntry(AppConfigCategory.class);

        validateRequest(
                appConfigCategoryEntity,
                AppConfigCategory.APP_ID,
                AppConfigCategory.CONFIG_CATEGORY_NAME,
                AppConfigCategory.CONFIG_CATEGORY_CODE,
                AppConfigCategory.CONFIG_CATEGORY_DESCRIPTION
        );

        String configCategoryId = Util.getRandomUUID();

        AppConfigCategory result = appConfigCategoryService.save(appConfigCategoryEntity, configCategoryId, appConfigCategoryEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, AppConfigCategoryRouter.APP_CONFIG_CATEGORY_V1_SAVE, appConfigCategoryEntity.getAppId(), appConfigCategoryEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置分类修改")
    @RequestMapping(value = "/app/config/category/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        AppConfigCategory appConfigCategoryEntity = getEntry(AppConfigCategory.class);

        validateRequest(
                appConfigCategoryEntity,
                AppConfigCategory.APP_ID,
                AppConfigCategory.CONFIG_CATEGORY_ID,
                AppConfigCategory.CONFIG_CATEGORY_NAME,
                AppConfigCategory.CONFIG_CATEGORY_CODE,
                AppConfigCategory.CONFIG_CATEGORY_DESCRIPTION,
                AppConfigCategory.SYSTEM_VERSION
        );

        AppConfigCategory result = appConfigCategoryService.update(appConfigCategoryEntity, appConfigCategoryEntity.getConfigCategoryId(), appConfigCategoryEntity.getSystemRequestUserId(), appConfigCategoryEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, AppConfigCategoryRouter.APP_CONFIG_CATEGORY_V1_UPDATE, appConfigCategoryEntity.getAppId(), appConfigCategoryEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置分类删除")
    @RequestMapping(value = "/app/config/category/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        AppConfigCategory appConfigCategoryEntity = getEntry(AppConfigCategory.class);

        validateRequest(
                appConfigCategoryEntity,
                AppConfigCategory.CONFIG_CATEGORY_ID,
                AppConfigCategory.SYSTEM_VERSION
        );

        AppConfigCategory result = appConfigCategoryService.delete(appConfigCategoryEntity.getConfigCategoryId(), appConfigCategoryEntity.getSystemRequestUserId(), appConfigCategoryEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, AppConfigCategoryRouter.APP_CONFIG_CATEGORY_V1_DELETE, appConfigCategoryEntity.getAppId(), appConfigCategoryEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置分类数据同步")
    @RequestMapping(value = "/app/config/category/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<AppConfigCategory> appConfigCategoryList = appConfigCategoryService.listByMysql();

        for (AppConfigCategory appConfigCategory : appConfigCategoryList) {
            AppConfigCategoryView appConfigCategoryView = new AppConfigCategoryView();
            appConfigCategoryView.putAll(appConfigCategory);

            appConfigCategoryService.update(appConfigCategoryView);
        }

        return renderJson(true);
    }

}
