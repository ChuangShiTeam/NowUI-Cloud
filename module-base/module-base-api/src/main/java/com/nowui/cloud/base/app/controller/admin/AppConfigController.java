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
import com.nowui.cloud.base.app.service.AppConfigService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author marcus
 *
 */
@Api(value = "应用配置", description = "应用配置接口管理")
@RestController
public class AppConfigController extends BaseController {
    
    @Autowired
    private AppConfigService appConfigService;
    
    @ApiOperation(value = "应用配置列表")
    @RequestMapping(value = "/app/config/admin/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        AppConfig appConfigEntity = getEntry(AppConfig.class);

        validateRequest(
                appConfigEntity,
                AppConfig.APP_ID,
                AppConfig.CONFIG_CATEGORY_ID,
                AppConfig.CONFIG_KEY,
                AppConfig.CONFIG_IS_DISABLED,
                AppConfig.PAGE_INDEX,
                AppConfig.PAGE_SIZE
        );

        Integer resultTotal = appConfigService.countForAdmin(appConfigEntity.getAppId(), appConfigEntity.getConfigCategoryId(), appConfigEntity.getConfigKey(), appConfigEntity.getConfigIsDisabled());
        List<AppConfig> resultList = appConfigService.listForAdmin(appConfigEntity.getAppId(), appConfigEntity.getConfigCategoryId(), appConfigEntity.getConfigKey(), appConfigEntity.getConfigIsDisabled(), appConfigEntity.getPageIndex(), appConfigEntity.getPageSize());

        validateResponse(
                AppConfig.APP_ID,
                AppConfig.CONFIG_ID,
                AppConfigCategory.CONFIG_CATEGORY_NAME,
                AppConfig.CONFIG_KEY,
                AppConfig.CONFIG_VALUE,
                AppConfig.CONFIG_IS_DISABLED
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "应用配置信息")
    @RequestMapping(value = "/app/config/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        AppConfig appConfigEntity = getEntry(AppConfig.class);

        validateRequest(
                appConfigEntity,
                AppConfig.CONFIG_ID
        );

        AppConfig result = appConfigService.find(appConfigEntity.getConfigId());

        validateResponse(
                AppConfig.CONFIG_ID,
                AppConfig.CONFIG_CATEGORY_ID,
                AppConfig.CONFIG_KEY,
                AppConfig.CONFIG_VALUE,
                AppConfig.CONFIG_IS_DISABLED,
                AppConfig.CONFIG_DESCRIPTION,
                AppConfig.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置新增")
    @RequestMapping(value = "/app/config/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        AppConfig appConfigEntity = getEntry(AppConfig.class);

        validateRequest(
                appConfigEntity,
                AppConfig.APP_ID,
                AppConfig.CONFIG_CATEGORY_ID,
                AppConfig.CONFIG_KEY,
                AppConfig.CONFIG_VALUE,
                AppConfig.CONFIG_IS_DISABLED,
                AppConfig.CONFIG_DESCRIPTION
        );

        Boolean result = appConfigService.save(appConfigEntity, Util.getRandomUUID(), appConfigEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置修改")
    @RequestMapping(value = "/app/config/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        AppConfig appConfigEntity = getEntry(AppConfig.class);

        validateRequest(
                appConfigEntity,
                AppConfig.APP_ID,
                AppConfig.CONFIG_CATEGORY_ID,
                AppConfig.CONFIG_KEY,
                AppConfig.CONFIG_VALUE,
                AppConfig.CONFIG_IS_DISABLED,
                AppConfig.CONFIG_DESCRIPTION,
                AppConfig.SYSTEM_VERSION
        );

        Boolean result = appConfigService.update(appConfigEntity, appConfigEntity.getConfigId(), appConfigEntity.getSystemRequestUserId(), appConfigEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置删除")
    @RequestMapping(value = "/app/config/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        AppConfig appConfigEntity = getEntry(AppConfig.class);

        validateRequest(
                appConfigEntity,
                AppConfig.CONFIG_ID,
                AppConfig.SYSTEM_VERSION
        );

        Boolean result = appConfigService.delete(appConfigEntity.getConfigId(), appConfigEntity.getSystemRequestUserId(), appConfigEntity.getSystemVersion());

        return renderJson(result);
    }

}
