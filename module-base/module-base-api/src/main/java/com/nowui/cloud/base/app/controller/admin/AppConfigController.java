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
 * 应用配置管理端控制器
 * 
 * @author marcus
 *
 * 2018年3月14日
 */
@Api(value = "应用配置", description = "应用配置接口管理")
@RestController
public class AppConfigController extends BaseController {
    
    @Autowired
    private AppConfigService appConfigService;
    
    @Autowired
    private AppConfigCategoryService appConfigCategoryService;
    
    @ApiOperation(value = "应用配置列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_CATEGORY_ID, value = "应用配置分类编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_KEY, value = "键", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_IS_DISABLED, value = "是否禁用", required = true, paramType = "query", dataType = "boolean"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/app/config/admin/v1/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore AppConfigView appConfigView, @ApiIgnore CommonView commonView) {

        validateRequest(
                appConfigView,
                AppConfigView.APP_ID,
                AppConfigView.CONFIG_CATEGORY_ID,
                AppConfigView.CONFIG_KEY,
                AppConfigView.CONFIG_IS_DISABLED
        );
        
        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = appConfigService.countForAdmin(appConfigView.getAppId(), appConfigView.getConfigCategoryId(), appConfigView.getConfigKey(), appConfigView.getConfigIsDisabled());
        List<AppConfigView> resultList = appConfigService.listForAdmin(appConfigView.getAppId(), appConfigView.getConfigCategoryId(), appConfigView.getConfigKey(), appConfigView.getConfigIsDisabled(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                AppConfigView.APP_ID,
                AppConfigView.CONFIG_ID,
                AppConfigView.CONFIG_CATEGORY_NAME,
                AppConfigView.CONFIG_KEY,
                AppConfigView.CONFIG_VALUE,
                AppConfigView.CONFIG_IS_DISABLED
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "应用配置信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigView.CONFIG_ID, value = "配置编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/app/config/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore AppConfigView appConfigView) {
        
        validateRequest(
                appConfigView,
                AppConfigView.CONFIG_ID
        );

        AppConfigView result = appConfigService.find(appConfigView.getConfigId());

        validateResponse(
                AppConfigView.CONFIG_ID,
                AppConfigView.CONFIG_CATEGORY_ID,
                AppConfigView.CONFIG_KEY,
                AppConfigView.CONFIG_VALUE,
                AppConfigView.CONFIG_IS_DISABLED,
                AppConfigView.CONFIG_DESCRIPTION,
                AppConfigView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置新增", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_CATEGORY_ID, value = "应用配置分类编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_CATEGORY_NAME, value = "应用配置分类名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_KEY, value = "键", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_VALUE, value = "值", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_IS_DISABLED, value = "是否禁用", required = true, paramType = "query", dataType = "boolean"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_DESCRIPTION, value = "描述", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/app/config/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore AppConfig appConfig, @ApiIgnore AppConfigView appConfigView, @ApiIgnore CommonView commonView) {
        validateRequest(
                appConfigView,
                AppConfigView.APP_ID,
                AppConfigView.CONFIG_CATEGORY_ID,
                AppConfigView.CONFIG_CATEGORY_NAME,
                AppConfigView.CONFIG_KEY,
                AppConfigView.CONFIG_VALUE,
                AppConfigView.CONFIG_IS_DISABLED,
                AppConfigView.CONFIG_DESCRIPTION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );
        
        String configId = Util.getRandomUUID();

        AppConfig result = appConfigService.save(appConfig, configId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            // 保存应用配置视图信息
            appConfigView.putEntry(result);
            
            appConfigService.save(appConfigView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置修改", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_ID, value = "应用配置编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_CATEGORY_ID, value = "应用配置分类编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_CATEGORY_NAME, value = "应用配置分类名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_KEY, value = "键", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_VALUE, value = "值", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_IS_DISABLED, value = "是否禁用", required = true, paramType = "query", dataType = "boolean"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_DESCRIPTION, value = "描述", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/app/config/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore AppConfig appConfig, @ApiIgnore AppConfigView appConfigView, @ApiIgnore CommonView commonView) {

        validateRequest(
                appConfigView,
                AppConfigView.APP_ID,
                AppConfigView.CONFIG_ID,
                AppConfigView.CONFIG_CATEGORY_ID,
                AppConfigView.CONFIG_KEY,
                AppConfigView.CONFIG_VALUE,
                AppConfigView.CONFIG_IS_DISABLED,
                AppConfigView.CONFIG_DESCRIPTION,
                AppConfig.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );
        
        AppConfig result = appConfigService.update(appConfig, appConfig.getConfigId(), appConfig.getAppId(), commonView.getSystemRequestUserId(), appConfig.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 更新应用配置信息
            appConfigView.putEntry(result);
            
            appConfigService.update(appConfigView, appConfigView.getConfigId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置删除", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppConfigView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.CONFIG_ID, value = "应用配置编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppConfigView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/app/config/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore AppConfigView appConfigView, @ApiIgnore CommonView commonView) {

        validateRequest(
                appConfigView,
                AppConfigView.APP_ID,
                AppConfigView.CONFIG_ID,
                AppConfigView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        AppConfig result = appConfigService.delete(appConfigView.getConfigId(), appConfigView.getAppId(), commonView.getSystemRequestUserId(), appConfigView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 删除应用配置
            appConfigView.putEntry(result);
            
            appConfigService.delete(appConfigView, appConfigView.getConfigId());
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用配置数据同步", httpMethod = "POST")
    @RequestMapping(value = "/app/config/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<AppConfig> appConfigList = appConfigService.listByMysql();

        for (AppConfig appConfig : appConfigList) {
            AppConfigView appConfigView = new AppConfigView();
            appConfigView.putEntry(appConfig);
            
            AppConfigCategory appConfigCategory = appConfigCategoryService.findByMysql(appConfigView.getConfigCategoryId());
            appConfigView.setConfigCategoryName(appConfigCategory.getConfigCategoryName());
            
            appConfigService.saveOrUpdate(appConfigView, appConfigView.getConfigId());
        }

        return renderJson(true);
    }

}
