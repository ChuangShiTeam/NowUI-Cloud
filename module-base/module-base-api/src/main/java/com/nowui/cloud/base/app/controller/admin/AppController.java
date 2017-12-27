package com.nowui.cloud.base.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.app.service.AppService;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ZhongYongQiang
 */
@Api(value = "应用", description = "应用接口管理")
@RestController
public class AppController extends BaseController {
    
    @Autowired
    private AppService appService;
    
    @ApiOperation(value = "应用列表")
    @RequestMapping(value = "/app/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody App body) {
        validateRequest(body, App.APP_NAME, App.PAGE_INDEX, App.PAGE_SIZE);

        Integer resultTotal = appService.adminCount(body.getAppName());
        List<App> resultList = appService.adminList(body.getAppName(), body.getM(), body.getN());

        validateResponse(App.APP_ID, App.APP_NAME);

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "应用信息")
    @RequestMapping(value = "/app/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody App body) {
        validateRequest(body, App.APP_ID);

        App result = appService.find(body.getAppId());

        validateResponse(App.APP_ID, App.APP_NAME, App.SYSTEM_VERSION);

        return renderJson(result);
    }

    @ApiOperation(value = "应用新增")
    @RequestMapping(value = "/app/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody App body) {
        validateRequest(body, App.APP_NAME);
        
        //验证应用名称是否重复
        if (appService.checkName(body.getAppName())) {
            throw new RuntimeException("应用名称重复");
        }
        
        Boolean result = appService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "应用修改")
    @RequestMapping(value = "/app/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody App body) {
        validateRequest(body, App.APP_ID, App.APP_NAME, App.SYSTEM_VERSION);

        if (appService.checkName(body.getAppId(), body.getAppName())) {
            throw new RuntimeException("应用名称重复");
        }
        
        Boolean result = appService.update(body, body.getAppId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "应用删除")
    @RequestMapping(value = "/app/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody App body) {
        validateRequest(body, App.APP_ID, App.APP_NAME);

        Boolean result = appService.delete(body.getAppId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }


}