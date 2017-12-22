package com.nowui.cloud.app.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.app.app.entity.AppConfig;
import com.nowui.cloud.app.app.entity.AppConfigCategory;
import com.nowui.cloud.app.app.service.AppConfigService;
import com.nowui.cloud.controller.BaseController;

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
    @RequestMapping(value = "/app/config/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody AppConfig body) {
        validateRequest(body, AppConfig.APP_ID, AppConfig.CONFIG_CATEGORY_ID, AppConfig.CONFIG_KEY, AppConfig.CONFIG_IS_DISABLED, AppConfig.PAGE_INDEX, AppConfig.PAGE_SIZE);

        Integer resultTotal = appConfigService.adminCount(body.getAppId(), body.getConfigCategoryId(), body.getConfigKey(), body.getConfigIsDisabled());
        List<AppConfig> resultList = appConfigService.adminList(body.getAppId(), body.getConfigCategoryId(), body.getConfigKey(), body.getConfigIsDisabled(), body.getM(), body.getN());

        validateResponse(AppConfig.APP_ID, AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfig.CONFIG_KEY, AppConfig.CONFIG_VALUE, AppConfig.CONFIG_IS_DISABLED);

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "应用配置信息")
    @RequestMapping(value = "/app/config/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody AppConfig body) {
        validateRequest(body, AppConfig.CONFIG_ID);

        AppConfig result = appConfigService.find(body.getConfigId());

        validateResponse(AppConfig.CONFIG_ID, AppConfig.CONFIG_CATEGORY_ID, AppConfig.CONFIG_KEY, AppConfig.CONFIG_VALUE, AppConfig.CONFIG_IS_DISABLED, AppConfig.CONFIG_DESCRIPTION, AppConfig.SYSTEM_VERSION);

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置新增")
    @RequestMapping(value = "/app/config/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody AppConfig body) {
        validateRequest(body, AppConfig.APP_ID, AppConfig.CONFIG_CATEGORY_ID, AppConfig.CONFIG_KEY, AppConfig.CONFIG_VALUE, AppConfig.CONFIG_IS_DISABLED, AppConfig.CONFIG_DESCRIPTION);

        Boolean result = appConfigService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置修改")
    @RequestMapping(value = "/app/config/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody AppConfig body) {
        validateRequest(body, AppConfig.APP_ID, AppConfig.CONFIG_CATEGORY_ID, AppConfig.CONFIG_KEY, AppConfig.CONFIG_VALUE, AppConfig.CONFIG_IS_DISABLED, AppConfig.CONFIG_DESCRIPTION, AppConfig.SYSTEM_VERSION);

        Boolean result = appConfigService.update(body, body.getConfigId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置删除")
    @RequestMapping(value = "/app/config/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody AppConfig body) {
        validateRequest(body, AppConfig.CONFIG_ID, AppConfig.SYSTEM_VERSION);

        Boolean result = appConfigService.delete(body.getConfigId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}
