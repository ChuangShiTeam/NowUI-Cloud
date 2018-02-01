package com.nowui.cloud.base.subscriptioninfo.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.subscriptioninfo.entity.Subscription;
import com.nowui.cloud.base.subscriptioninfo.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户订阅表管理端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "用户订阅表", description = "用户订阅表管理端接口管理")
@RestController
public class SubscriptionAdminController extends BaseController {

    @Autowired
    private SubscriptionService subscriptionService;

    @ApiOperation(value = "用户订阅表列表")
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

    @ApiOperation(value = "用户订阅表信息")
    @RequestMapping(value = "/subscription/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(){
        Subscription subscriptionEntity = getEntry(Subscription.class);

        validateRequest(
                subscriptionEntity,
                Subscription.APP_ID,
                Subscription.SUBSCRIPTION_ID
        );

        Subscription result = subscriptionService.find(subscriptionEntity.getSubscriptionId());

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

    @ApiOperation(value = "新增用户订阅表")
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

        Boolean result = subscriptionService.save(subscriptionEntity, Util.getRandomUUID(), subscriptionEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户订阅表")
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

        Boolean result = subscriptionService.update(subscriptionEntity, subscriptionEntity.getSubscriptionId(), subscriptionEntity.getSystemRequestUserId(), subscriptionEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户订阅表")
    @RequestMapping(value = "/subscription/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Subscription subscriptionEntity = getEntry(Subscription.class);

        validateRequest(
                subscriptionEntity,
                Subscription.SUBSCRIPTION_ID,
                Subscription.APP_ID,
                Subscription.SYSTEM_VERSION
        );

        Boolean result = subscriptionService.delete(subscriptionEntity.getSubscriptionId(), subscriptionEntity.getSystemRequestUserId(), subscriptionEntity.getSystemVersion());

        return renderJson(result);
    }

}