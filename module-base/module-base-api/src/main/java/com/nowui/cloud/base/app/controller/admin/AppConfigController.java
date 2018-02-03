package com.nowui.cloud.base.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.AppConfig;
import com.nowui.cloud.base.app.router.AppConfigRouter;
import com.nowui.cloud.base.app.service.AppConfigService;
import com.nowui.cloud.base.app.view.AppConfigView;
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
        AppConfigView appConfigView = getEntry(AppConfigView.class);

        validateRequest(
                appConfigView,
                AppConfigView.APP_ID,
                AppConfigView.CONFIG_CATEGORY_ID,
                AppConfigView.CONFIG_KEY,
                AppConfigView.CONFIG_IS_DISABLED,
                AppConfigView.PAGE_INDEX,
                AppConfigView.PAGE_SIZE
        );

        Integer resultTotal = appConfigService.countForAdmin(appConfigView.getAppId(), appConfigView.getConfigCategoryId(), appConfigView.getConfigKey(), appConfigView.getConfigIsDisabled());
        List<AppConfigView> resultList = appConfigService.listForAdmin(appConfigView.getAppId(), appConfigView.getConfigCategoryId(), appConfigView.getConfigKey(), appConfigView.getConfigIsDisabled(), appConfigView.getPageIndex(), appConfigView.getPageSize());

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

    @ApiOperation(value = "应用配置信息")
    @RequestMapping(value = "/app/config/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        AppConfigView appConfigView = getEntry(AppConfigView.class);

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

        Boolean result = appConfigService.save(appConfigEntity, Util.getRandomUUID(), appConfigEntity.getAppId(), AppConfigRouter.APP_CONFIG_V1_SAVE, appConfigEntity.getSystemRequestUserId());

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

        Boolean result = appConfigService.update(appConfigEntity, appConfigEntity.getConfigId(), appConfigEntity.getAppId(), AppConfigRouter.APP_CONFIG_V1_UPDATE, appConfigEntity.getSystemRequestUserId(), appConfigEntity.getSystemVersion());

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

        Boolean result = appConfigService.delete(appConfigEntity.getConfigId(), appConfigEntity.getSystemRequestUserId(), appConfigEntity.getAppId(), AppConfigRouter.APP_CONFIG_V1_DELETE, appConfigEntity.getSystemVersion());

        return renderJson(result);
    }

}
