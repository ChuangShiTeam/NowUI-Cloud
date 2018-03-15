package com.nowui.cloud.base.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.app.service.AppService;
import com.nowui.cloud.base.app.view.AppView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 应用管理端控制器
 * 
 * @author marcus
 *
 * 2018年3月14日
 */
@Api(value = "应用", description = "应用接口管理")
@RestController
public class AppController extends BaseController {
    
    @Autowired
    private AppService appService;
    
    @ApiOperation(value = "应用列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppView.APP_NAME, value = "应用名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/app/admin/v1/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore AppView appView, @ApiIgnore CommonView commonView) {

        validateRequest(
                appView, 
                AppView.APP_NAME
        );
        
        validateRequest(
                commonView, 
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = appService.countForAdmin(appView.getAppName());
        List<AppView> resultList = appService.listForAdmin(appView.getAppName(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                AppView.APP_ID, 
                AppView.APP_NAME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "应用信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/app/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore AppView appView) {

        validateRequest(
                appView, 
                AppView.APP_ID
        );

        AppView result = appService.find(appView.getAppId());

        validateResponse(
                AppView.APP_ID, 
                AppView.APP_NAME, 
                AppView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "应用新增", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppView.APP_NAME, value = "应用名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/app/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore App app, @ApiIgnore AppView appView, @ApiIgnore CommonView commonView) {
        validateRequest(
                appView, 
                AppView.APP_NAME
        );
        
        validateRequest(
                commonView, 
                CommonView.SYSTEM_REQUEST_USER_ID
        );
        
        //验证应用名称是否重复
        if (appService.checkName(appView.getAppName())) {
            throw new BusinessException("应用名称重复");
        }

        String appId = Util.getRandomUUID();

        App result = appService.save(app, appId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            // 保存应用视图信息
            appView.copy(result);
            
            appService.save(appView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用修改", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppView.APP_NAME, value = "应用名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/app/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore App app, @ApiIgnore AppView appView, @ApiIgnore CommonView commonView) {

        validateRequest(
                appView, 
                AppView.APP_ID, 
                AppView.APP_NAME, 
                AppView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView, 
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        if (appService.checkName(appView.getAppId(), appView.getAppName())) {
            throw new BusinessException("应用名称重复");
        }

        App result = appService.update(app, appView.getAppId(), appView.getAppId(), commonView.getSystemRequestUserId(), appView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 更新应用视图信息
            appView.copy(app);
            
            appService.update(appView, appView.getAppId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用删除", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AppView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AppView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/app/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore AppView appView, @ApiIgnore CommonView commonView) {
        
        validateRequest(
                appView, 
                AppView.APP_ID, 
                AppView.APP_NAME
        );
        
        validateRequest(
                commonView, 
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        App result = appService.delete(appView.getAppId(), appView.getAppId(), commonView.getSystemRequestUserId(), appView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 删除应用视图信息
            appView.copy(result);
            
            appService.delete(appView, appView.getAppId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "应用数据同步", httpMethod = "POST")
    @RequestMapping(value = "/app/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<App> appList = appService.listByMysql();

        for (App app : appList) {
            AppView appView = new AppView();
            appView.copy(app);

            appService.saveOrUpdate(appView, appView.getAppId());
        }

        return renderJson(true);
    }

}