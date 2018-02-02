package com.nowui.cloud.base.subscription.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.subscription.entity.SubscriptionConfig;
import com.nowui.cloud.base.subscription.service.SubscriptionConfigService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 订阅配置管理端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "订阅配置", description = "订阅配置管理端接口管理")
@RestController
public class SubscriptionConfigAdminController extends BaseController {

    @Autowired
    private SubscriptionConfigService subscriptionConfigService;

    @ApiOperation(value = "订阅配置列表")
    @RequestMapping(value = "/subscription/config/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        SubscriptionConfig subscriptionConfigEntity = getEntry(SubscriptionConfig.class);

        validateRequest(
                subscriptionConfigEntity,
                SubscriptionConfig.APP_ID,
                SubscriptionConfig.SUBSCRIPTION_ACTION,
                SubscriptionConfig.SUBSCRIPTION_USER_ID,
                SubscriptionConfig.PAGE_INDEX,
                SubscriptionConfig.PAGE_SIZE
        );

        Integer resultTotal = subscriptionConfigService.countForAdmin(subscriptionConfigEntity.getAppId(), subscriptionConfigEntity.getSubscriptionAction(), subscriptionConfigEntity.getSubscriptionUserId());
        List<SubscriptionConfig> resultList = subscriptionConfigService.listForAdmin(subscriptionConfigEntity.getAppId(), subscriptionConfigEntity.getSubscriptionAction(), subscriptionConfigEntity.getSubscriptionUserId(), subscriptionConfigEntity.getPageIndex(), subscriptionConfigEntity.getPageSize());

        validateResponse(
                SubscriptionConfig.SUBSCRIPTION_CONFIG_ID,
                SubscriptionConfig.SUBSCRIPTION_ACTION,
                SubscriptionConfig.SUBSCRIPTION_USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "订阅配置信息")
    @RequestMapping(value = "/subscription/config/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        SubscriptionConfig subscriptionConfigEntity = getEntry(SubscriptionConfig.class);

        validateRequest(
                subscriptionConfigEntity,
                SubscriptionConfig.APP_ID,
                SubscriptionConfig.SUBSCRIPTION_CONFIG_ID
        );

        SubscriptionConfig result = subscriptionConfigService.find(subscriptionConfigEntity.getSubscriptionConfigId());

        validateResponse(
                SubscriptionConfig.SUBSCRIPTION_CONFIG_ID,
                SubscriptionConfig.SUBSCRIPTION_ACTION,
                SubscriptionConfig.SUBSCRIPTION_USER_ID,
                SubscriptionConfig.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增订阅配置")
    @RequestMapping(value = "/subscription/config/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        SubscriptionConfig subscriptionConfigEntity = getEntry(SubscriptionConfig.class);

        validateRequest(
                subscriptionConfigEntity,
                SubscriptionConfig.APP_ID,
                SubscriptionConfig.SUBSCRIPTION_ACTION,
                SubscriptionConfig.SUBSCRIPTION_USER_ID
        );

        Boolean result = subscriptionConfigService.save(subscriptionConfigEntity, Util.getRandomUUID(), subscriptionConfigEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改订阅配置")
    @RequestMapping(value = "/subscription/config/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        SubscriptionConfig subscriptionConfigEntity = getEntry(SubscriptionConfig.class);

        validateRequest(
                subscriptionConfigEntity,
                SubscriptionConfig.SUBSCRIPTION_CONFIG_ID,
                SubscriptionConfig.APP_ID,
                SubscriptionConfig.SUBSCRIPTION_ACTION,
                SubscriptionConfig.SUBSCRIPTION_USER_ID,
                SubscriptionConfig.SYSTEM_VERSION
        );

        Boolean result = subscriptionConfigService.update(subscriptionConfigEntity, subscriptionConfigEntity.getSubscriptionConfigId(), subscriptionConfigEntity.getSystemRequestUserId(), subscriptionConfigEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除订阅配置")
    @RequestMapping(value = "/subscription/config/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        SubscriptionConfig subscriptionConfigEntity = getEntry(SubscriptionConfig.class);

        validateRequest(
                subscriptionConfigEntity,
                SubscriptionConfig.SUBSCRIPTION_CONFIG_ID,
                SubscriptionConfig.APP_ID,
                SubscriptionConfig.SYSTEM_VERSION
        );

        Boolean result = subscriptionConfigService.delete(subscriptionConfigEntity.getSubscriptionConfigId(), subscriptionConfigEntity.getSystemRequestUserId(), subscriptionConfigEntity.getSystemVersion());

        return renderJson(result);
    }

}