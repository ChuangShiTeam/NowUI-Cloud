package com.nowui.cloud.base.app.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.app.router.AppRouter;
import com.nowui.cloud.base.app.service.AppService;
import com.nowui.cloud.base.app.view.AppView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.util.Util;

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
    @RequestMapping(value = "/app/admin/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        AppView appView = getEntry(AppView.class);

        validateRequest(appView, AppView.APP_NAME, AppView.PAGE_INDEX, AppView.PAGE_SIZE);

        Integer resultTotal = appService.countForAdmin(appView.getAppName());
        List<AppView> resultList = appService.listForAdmin(appView.getAppName(), appView.getPageIndex(), appView.getPageSize());

        validateResponse(AppView.APP_ID, AppView.APP_NAME);

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "应用信息")
    @RequestMapping(value = "/app/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        App appView = getEntry(App.class);

        validateRequest(appView, App.APP_ID);

        AppView result = appService.find(appView.getAppId());

        validateResponse(AppView.APP_ID, AppView.APP_NAME, AppView.SYSTEM_VERSION);

        return renderJson(result);
    }

    @ApiOperation(value = "应用新增")
    @RequestMapping(value = "/app/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        App appEntity = getEntry(App.class);

        validateRequest(appEntity, App.APP_NAME);
        
        //验证应用名称是否重复
        if (appService.checkName(appEntity.getAppName())) {
            throw new BusinessException("应用名称重复");
        }
        
        appEntity.setAppId(Util.getRandomUUID());
        Boolean result = appService.save(appEntity, Util.getRandomUUID(), appEntity.getAppId(), AppRouter.APP_V1_SAVE, appEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "应用修改")
    @RequestMapping(value = "/app/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        App appEntity = getEntry(App.class);

        validateRequest(appEntity, App.APP_ID, App.APP_NAME, App.SYSTEM_VERSION);

        if (appService.checkName(appEntity.getAppId(), appEntity.getAppName())) {
            throw new BusinessException("应用名称重复");
        }
        
        Boolean result = appService.update(appEntity, appEntity.getAppId(), appEntity.getAppId(), AppRouter.APP_V1_UPDATE, appEntity.getSystemRequestUserId(), appEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "应用删除")
    @RequestMapping(value = "/app/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        App appEntity = getEntry(App.class);

        validateRequest(appEntity, App.APP_ID, App.APP_NAME);

        Boolean result = appService.delete(appEntity.getAppId(), appEntity.getAppId(), AppRouter.APP_V1_DELETE, appEntity.getSystemRequestUserId(), appEntity.getSystemVersion());

        return renderJson(result);
    }


}