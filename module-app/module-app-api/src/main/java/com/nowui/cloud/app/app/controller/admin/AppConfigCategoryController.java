package com.nowui.cloud.app.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.app.app.entity.AppConfigCategory;
import com.nowui.cloud.app.app.service.AppConfigCategoryService;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author marcus
 *
 */
@Api(value = "应用配置分类", description = "应用配置分类接口管理")
@RestController
public class AppConfigCategoryController extends BaseController {
    
    @Autowired
    private AppConfigCategoryService appConfigCategoryService;
    
    @ApiOperation(value = "应用配置分类列表")
    @RequestMapping(value = "/app/config/category/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody AppConfigCategory body) {
        validateRequest(body, AppConfigCategory.APP_ID, AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, AppConfigCategory.PAGE_INDEX, AppConfigCategory.PAGE_SIZE);

        Integer resultTotal = appConfigCategoryService.adminCount(body.getAppId(), body.getConfigCategoryName(), body.getConfigCategoryCode());
        List<AppConfigCategory> resultList = appConfigCategoryService.adminList(body.getAppId(), body.getConfigCategoryName(), body.getConfigCategoryCode(), body.getM(), body.getN());

        validateResponse(AppConfigCategory.CONFIG_CATEGORY_ID, AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE);

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "应用配置分类信息")
    @RequestMapping(value = "/app/config/category/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody AppConfigCategory body) {
        validateRequest(body, AppConfigCategory.CONFIG_CATEGORY_ID);

        AppConfigCategory result = appConfigCategoryService.find(body.getConfigCategoryId());

        validateResponse(AppConfigCategory.CONFIG_CATEGORY_ID, AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, AppConfigCategory.SYSTEM_VERSION);

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置分类新增")
    @RequestMapping(value = "/app/config/category/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody AppConfigCategory body) {
        validateRequest(body, AppConfigCategory.APP_ID, AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, AppConfigCategory.CONFIG_CATEGORY_DESCRIPTION);

        Boolean result = appConfigCategoryService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置分类修改")
    @RequestMapping(value = "/app/config/category/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody AppConfigCategory body) {
        validateRequest(body, AppConfigCategory.APP_ID, AppConfigCategory.CONFIG_CATEGORY_ID, AppConfigCategory.CONFIG_CATEGORY_NAME, AppConfigCategory.CONFIG_CATEGORY_CODE, AppConfigCategory.CONFIG_CATEGORY_DESCRIPTION, AppConfigCategory.SYSTEM_VERSION);

        Boolean result = appConfigCategoryService.update(body, body.getConfigCategoryId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "应用配置分类删除")
    @RequestMapping(value = "/app/config/category/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody AppConfigCategory body) {
        validateRequest(body, AppConfigCategory.CONFIG_CATEGORY_ID, AppConfigCategory.SYSTEM_VERSION);

        Boolean result = appConfigCategoryService.delete(body.getConfigCategoryId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}
