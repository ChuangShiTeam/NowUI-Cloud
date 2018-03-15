package com.nowui.cloud.base.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.AppConfig;
import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.service.AppConfigService;
import com.nowui.cloud.base.app.view.AppConfigCategoryView;
import com.nowui.cloud.base.app.view.AppConfigView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 应用配置分类管理端控制器
 * 
 * @author marcus
 *
 * 2018年3月14日
 */
@Api(value = "应用配置分类", description = "应用配置分类接口管理")
@RestController
public class AppConfigCategoryController extends BaseController {

    @Autowired
    private AppConfigCategoryService appConfigCategoryService;
    
    @Autowired
    private AppConfigService appConfigService;

    @ApiOperation(value = "应用配置分类列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_NAME, value = "应用配置分类名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_CODE, value = "应用配置分类编码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/app/config/category/admin/v1/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore AppConfigCategoryView appConfigCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
        		appConfigCategoryView,
                AppConfigCategoryView.APP_ID,
                AppConfigCategoryView.CONFIG_CATEGORY_NAME,
                AppConfigCategoryView.CONFIG_CATEGORY_CODE
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = appConfigCategoryService.countForAdmin(appConfigCategoryView.getAppId(), appConfigCategoryView.getConfigCategoryName(), appConfigCategoryView.getConfigCategoryCode());
        List<AppConfigCategoryView> resultList = appConfigCategoryService.listForAdmin(appConfigCategoryView.getAppId(), appConfigCategoryView.getConfigCategoryName(), appConfigCategoryView.getConfigCategoryCode(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
        		AppConfigCategoryView.CONFIG_CATEGORY_ID,
        		AppConfigCategoryView.CONFIG_CATEGORY_NAME,
        		AppConfigCategoryView.CONFIG_CATEGORY_CODE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "所有应用配置分类列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/app/config/category/admin/v1/all/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> allListV1(@ApiIgnore AppConfigCategoryView appConfigCategoryView) {

        List<AppConfigCategoryView> resultList = appConfigCategoryService.appList(appConfigCategoryView.getAppId());

        validateResponse(
        		AppConfigCategoryView.CONFIG_CATEGORY_ID,
        		AppConfigCategoryView.CONFIG_CATEGORY_NAME
        );

        return renderJson(resultList);
    }

    @ApiOperation(value = "应用配置分类信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_ID, value = "应用配置分类编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/app/config/category/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore AppConfigCategoryView appConfigCategoryView) {

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

    @ApiOperation(value = "应用配置分类新增", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_NAME, value = "应用配置分类名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_CODE, value = "应用配置分类编码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_DESCRIPTION, value = "描述", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/app/config/category/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore AppConfigCategory appConfigCategory, @ApiIgnore AppConfigCategoryView appConfigCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                appConfigCategoryView,
                AppConfigCategoryView.APP_ID,
                AppConfigCategoryView.CONFIG_CATEGORY_NAME,
                AppConfigCategoryView.CONFIG_CATEGORY_CODE,
                AppConfigCategoryView.CONFIG_CATEGORY_DESCRIPTION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String configCategoryId = Util.getRandomUUID();

        AppConfigCategory result = appConfigCategoryService.save(appConfigCategory, configCategoryId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            // 保存应用配置分类视图信息
            appConfigCategoryView.copy(result);
            
            appConfigCategoryService.save(appConfigCategoryView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置分类修改", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigCategoryView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_ID, value = "应用配置分类编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_NAME, value = "应用配置分类名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_CODE, value = "应用配置分类编码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_DESCRIPTION, value = "描述", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/app/config/category/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore AppConfigCategory appConfigCategory, @ApiIgnore AppConfigCategoryView appConfigCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                appConfigCategoryView,
                AppConfigCategoryView.APP_ID,
                AppConfigCategoryView.CONFIG_CATEGORY_ID,
                AppConfigCategoryView.CONFIG_CATEGORY_NAME,
                AppConfigCategoryView.CONFIG_CATEGORY_CODE,
                AppConfigCategoryView.CONFIG_CATEGORY_DESCRIPTION,
                AppConfigCategoryView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );
        

        AppConfigCategory result = appConfigCategoryService.update(appConfigCategory, appConfigCategory.getConfigCategoryId(), appConfigCategory.getAppId(), commonView.getSystemRequestUserId(), appConfigCategory.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 更新分类下应用配置视图
            AppConfigCategoryView oldAppConfigCategoryView = appConfigCategoryService.find(appConfigCategoryView.getConfigCategoryId());
            if (!appConfigCategoryView.getConfigCategoryName().equals(oldAppConfigCategoryView.getConfigCategoryName())) {
                List<AppConfig> appConfigList = appConfigService.listByConfigCategoryId(result.getConfigCategoryId());
                
                if (!Util.isNullOrEmpty(appConfigList)) {
                    for (AppConfig appConfig : appConfigList) {
                        AppConfigView appConfigView = new AppConfigView();
                        appConfigView.copy(appConfig);
                        
                        appConfigView.setConfigCategoryName(result.getConfigCategoryName());
                        appConfigService.save(appConfigView);
                    }
                }
            }
            
            // 更新应用配置分类视图
            appConfigCategoryView.copy(result);
            appConfigCategoryService.update(appConfigCategoryView, appConfigCategoryView.getConfigCategoryId());
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置分类删除", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigCategoryView.CONFIG_CATEGORY_ID, value = "应用配置分类编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigCategoryView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/app/config/category/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore AppConfigCategoryView appConfigCategoryView, @ApiIgnore CommonView commonView) {

        validateRequest(
                appConfigCategoryView,
                AppConfigCategoryView.CONFIG_CATEGORY_ID,
                AppConfigCategoryView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        AppConfigCategory result = appConfigCategoryService.delete(appConfigCategoryView.getConfigCategoryId(), appConfigCategoryView.getAppId(), commonView.getSystemRequestUserId(), appConfigCategoryView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 删除应用配置
            List<AppConfig> appConfigList = appConfigService.listByConfigCategoryId(result.getConfigCategoryId());
            
            if (!Util.isNullOrEmpty(appConfigList)) {
                for (AppConfig appConfig : appConfigList) {
                    AppConfigView appConfigView = new AppConfigView();
                    appConfigView.copy(appConfig);
                    
                    appConfigService.delete(appConfig.getConfigId(), appConfig.getAppId(), commonView.getSystemRequestUserId(), appConfig.getSystemVersion());
                    
                    appConfigService.delete(appConfigView, appConfigView.getConfigId());
                }
            }
            // 删除应用配置分类视图
            appConfigCategoryView.copy(result);
            
            appConfigCategoryService.delete(appConfigCategoryView, appConfigCategoryView.getConfigCategoryId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置分类数据同步", httpMethod = "POST")
    @RequestMapping(value = "/app/config/category/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<AppConfigCategory> appConfigCategoryList = appConfigCategoryService.listByMysql();

        for (AppConfigCategory appConfigCategory : appConfigCategoryList) {
            AppConfigCategoryView appConfigCategoryView = new AppConfigCategoryView();
            appConfigCategoryView.copy(appConfigCategory);

            appConfigCategoryService.saveOrUpdate(appConfigCategoryView, appConfigCategoryView.getConfigCategoryId());
        }

        return renderJson(true);
    }

}
