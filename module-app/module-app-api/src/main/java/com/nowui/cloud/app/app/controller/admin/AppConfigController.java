package com.nowui.cloud.app.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.app.app.entity.App;
import com.nowui.cloud.app.app.entity.AppConfig;
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
        validateRequest(body, "appId", "configCategoryId", "configKey", "configIsDisabled", "pageIndex", "pageSize");

        Integer resultTotal = appConfigService.adminCount(body.getAppId(), body.getConfigCategoryId(), body.getConfigKey(), body.getConfigIsDisabled());
        List<AppConfig> resultList = appConfigService.adminList(body.getAppId(), body.getConfigCategoryId(), body.getConfigKey(), body.getConfigIsDisabled(), body.getM(), body.getN());

        validateResponse("appId", "configCategoryName", "configKey", "configValue", "configIsDisabled");

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "应用信息")
    @RequestMapping(value = "/app/config/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody AppConfig body) {
        validateRequest(body, "appId");

        AppConfig result = appConfigService.find(body.getAppId());

        validateResponse("appId", "appName", "systemVersion");

        return renderJson(result);
    }

    @ApiOperation(value = "应用新增")
    @RequestMapping(value = "/app/config/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody AppConfig body) {
        validateRequest(body, "appName");

        Boolean result = appConfigService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "应用修改")
    @RequestMapping(value = "/app/config/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody AppConfig body) {
        validateRequest(body, "appId", "appName", "systemVersion");

        Boolean result = appConfigService.update(body, body.getAppId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "应用删除")
    @RequestMapping(value = "/app/config/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody App body) {
        validateRequest(body, "appId", "systemVersion");

        Boolean result = appConfigService.delete(body.getAppId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}
