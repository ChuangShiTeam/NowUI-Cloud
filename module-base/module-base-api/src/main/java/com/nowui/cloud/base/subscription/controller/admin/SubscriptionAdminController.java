package com.nowui.cloud.base.subscription.controller.admin;

import java.util.List;
import java.util.Map;

import com.nowui.cloud.base.subscription.router.SubscriptionRouter;
import com.nowui.cloud.base.subscription.view.SubscriptionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.subscription.entity.Subscription;
import com.nowui.cloud.base.subscription.service.SubscriptionService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 订阅管理端控制器
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
@Api(value = "订阅", description = "订阅管理端接口管理")
@RestController
public class SubscriptionAdminController extends BaseController {

    @Autowired
    private SubscriptionService subscriptionService;

    @ApiOperation(value = "订阅列表")
    @RequestMapping(value = "/subscription/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        Subscription subscriptionEntity = getEntry(Subscription.class);

        validateRequest(
                subscriptionEntity,
                Subscription.APP_ID,
                Subscription.SUBSCRIPTION_TARGET,
                Subscription.SUBSCRIPTION_TARGET_TYPE,
                Subscription.SUBSCRIPTION_ACTION,
                Subscription.SUBSCRIPTION_USER,
                Subscription.SYSTEM_CREATE_USER_ID,
                Subscription.PAGE_INDEX,
                Subscription.PAGE_SIZE
        );

        Integer resultTotal = subscriptionService.countForAdmin(subscriptionEntity.getAppId(), subscriptionEntity.getSubscriptionTarget(), subscriptionEntity.getSubscriptionTargetType(), subscriptionEntity.getSubscriptionAction(), subscriptionEntity.getSubscriptionUser(), subscriptionEntity.getSystemCreateUserId());
        List<Subscription> resultList = subscriptionService.listForAdmin(subscriptionEntity.getAppId(), subscriptionEntity.getSubscriptionTarget(), subscriptionEntity.getSubscriptionTargetType(), subscriptionEntity.getSubscriptionAction(), subscriptionEntity.getSubscriptionUser(), subscriptionEntity.getSystemCreateUserId(), subscriptionEntity.getPageIndex(), subscriptionEntity.getPageSize());

        validateResponse(
                Subscription.SUBSCRIPTION_ID,
                Subscription.SUBSCRIPTION_TARGET,
                Subscription.SUBSCRIPTION_TARGET_TYPE,
                Subscription.SUBSCRIPTION_ACTION,
                Subscription.SUBSCRIPTION_USER,
                Subscription.SYSTEM_CREATE_USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "订阅信息")
    @RequestMapping(value = "/subscription/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Subscription subscriptionEntity = getEntry(Subscription.class);

        validateRequest(
                subscriptionEntity,
                Subscription.APP_ID,
                Subscription.SUBSCRIPTION_ID
        );

        SubscriptionView result = subscriptionService.find(subscriptionEntity.getSubscriptionId());

        validateResponse(
                Subscription.SUBSCRIPTION_ID,
                Subscription.SUBSCRIPTION_TARGET,
                Subscription.SUBSCRIPTION_TARGET_TYPE,
                Subscription.SUBSCRIPTION_ACTION,
                Subscription.SUBSCRIPTION_USER,
                Subscription.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增订阅")
    @RequestMapping(value = "/subscription/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Subscription subscriptionEntity = getEntry(Subscription.class);

        validateRequest(
                subscriptionEntity,
                Subscription.APP_ID,
                Subscription.SUBSCRIPTION_TARGET,
                Subscription.SUBSCRIPTION_TARGET_TYPE,
                Subscription.SUBSCRIPTION_ACTION,
                Subscription.SUBSCRIPTION_USER
        );

        Subscription result = subscriptionService.save(subscriptionEntity, Util.getRandomUUID(), subscriptionEntity.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            sendMessage(result, SubscriptionRouter.SUBSCRIPTION_V1_SAVE, subscriptionEntity.getAppId(), subscriptionEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改订阅")
    @RequestMapping(value = "/subscription/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Subscription subscriptionEntity = getEntry(Subscription.class);

        validateRequest(
                subscriptionEntity,
                Subscription.SUBSCRIPTION_ID,
                Subscription.APP_ID,
                Subscription.SUBSCRIPTION_TARGET,
                Subscription.SUBSCRIPTION_TARGET_TYPE,
                Subscription.SUBSCRIPTION_ACTION,
                Subscription.SUBSCRIPTION_USER,
                Subscription.SYSTEM_VERSION
        );

        Subscription result = subscriptionService.update(subscriptionEntity, subscriptionEntity.getSubscriptionId(), subscriptionEntity.getSystemUpdateUserId(), subscriptionEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            sendMessage(result, SubscriptionRouter.SUBSCRIPTION_V1_UPDATE, subscriptionEntity.getAppId(), subscriptionEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除订阅")
    @RequestMapping(value = "/subscription/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Subscription subscriptionEntity = getEntry(Subscription.class);

        validateRequest(
                subscriptionEntity,
                Subscription.SUBSCRIPTION_ID,
                Subscription.APP_ID,
                Subscription.SYSTEM_VERSION
        );

        Subscription result = subscriptionService.delete(subscriptionEntity.getSubscriptionId(), subscriptionEntity.getSystemRequestUserId(), subscriptionEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            sendMessage(result, SubscriptionRouter.SUBSCRIPTION_V1_DELETE, subscriptionEntity.getAppId(), subscriptionEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "订阅数据同步")
    @RequestMapping(value = "/subscription/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Subscription> subscriptionList = subscriptionService.listByMysql();

        for(Subscription subscription : subscriptionList) {
            SubscriptionView subscriptionView = new SubscriptionView();
            subscriptionView.putAll(subscription);

            subscriptionService.update(subscriptionView);
        }

        return renderJson(true);
    }

}