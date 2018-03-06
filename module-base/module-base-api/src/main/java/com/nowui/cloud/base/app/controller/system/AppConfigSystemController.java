package com.nowui.cloud.base.app.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.app.entity.AppConfig;
import com.nowui.cloud.base.app.entity.AppConfigCategory;
import com.nowui.cloud.base.app.rpc.AppConfigRpc;
import com.nowui.cloud.base.app.service.AppConfigCategoryService;
import com.nowui.cloud.base.app.service.AppConfigService;
import com.nowui.cloud.base.app.view.AppConfigCategoryView;
import com.nowui.cloud.base.app.view.AppConfigView;

import io.swagger.annotations.Api;

/**
 * 应用配置系统端控制器
 *
 * @author marcus
 * <p>
 * 2018-01-01
 */
@Api(value = "应用配置", description = "应用配置系统端接口管理")
@RestController
public class AppConfigSystemController implements AppConfigRpc {

    @Autowired
    private AppConfigCategoryService appConfigCategoryService;

    @Autowired
    private AppConfigService appConfigService;

    @Override
    public JSONObject findByCategoryCode(String appId, String appConfigCategoryCode) {
        AppConfigCategoryView appConfigCategoryView = appConfigCategoryService.findByConfigCategoryCode(appId, appConfigCategoryCode);
        if (appConfigCategoryView == null) {
            return null;
        }
        List<AppConfigView> appConfigViewList = appConfigService.abledListByConfigCategoryId(appId, appConfigCategoryView.getConfigCategoryId());
        if (appConfigViewList == null || appConfigViewList.size() == 0) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        for (AppConfigView appConfigView : appConfigViewList) {
            jsonObject.put(appConfigView.getConfigKey(), appConfigView.getConfigValue());
        }
        return jsonObject;
    }

    @Override
    public List<AppConfig> findAppConfigsByAppCode(String appId, String appConfigCategoryCode) {
        // TODO: 暂时无法判断错误原因.
        return null;
//        AppConfigCategory appConfigCategory = appConfigCategoryService.findByConfigCategoryCode(appId, appConfigCategoryCode);
//        if (appConfigCategory == null) {
//            return null;
//        }
//        List<AppConfig> appConfigList = appConfigService.abledListByConfigCategoryId(appId, appConfigCategory.getConfigCategoryId());
//        if (appConfigList == null || appConfigList.size() == 0) {
//            return null;
//        }
//        return appConfigList;
    }
}
