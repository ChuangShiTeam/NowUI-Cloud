package com.nowui.cloud.base.notifyinfo.controller.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.app.entity.AppConfig;
import com.nowui.cloud.base.app.rpc.AppConfigRpc;
import com.nowui.cloud.base.notifyinfo.entity.Notify;
import com.nowui.cloud.base.notifyinfo.service.NotifyService;
import com.nowui.cloud.base.subscriptioninfo.entity.Subscription;
import com.nowui.cloud.base.subscriptioninfo.service.SubscriptionService;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.usernotifyinfo.entity.UserNotify;
import com.nowui.cloud.base.usernotifyinfo.service.UserNotifyService;
import com.nowui.cloud.controller.BaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.nowui.cloud.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 消息表移动端控制器
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
@Api(value = "消息表", description = "消息表移动端接口管理")
@RestController
public class NotifyMobileController extends BaseController {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private UserNotifyService userNotifyService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private AppConfigRpc appConfigRpc;

    @ApiOperation(value = "新增一条公告记录")
    @RequestMapping(value = "/notify/mobile/v1/create/notify", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> createAnnounceV1(@RequestBody Notify body) {

        validateRequest(
                body,
                Notify.APP_ID,
                Notify.NOTIFY_CONTENT,
                Notify.NOTIFY_SENDER
        );

        String notifyId = Util.getRandomUUID();
        body.setAppId(body.getAppId());
        body.setNotifyContent(body.getNotifyContent());
        body.setNotifySender(body.getNotifySender());

        String userId = body.getSystemCreateUserId();

        Boolean result = notifyService.save(body, notifyId, userId);

        return renderJson(result);
    }

    @ApiOperation(value = "新增一条提醒记录")
    @RequestMapping(value = "/notify/mobile/v1/create/remind", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> createRemindV1(@RequestBody Notify body) {
        validateRequest(
                body,
                Notify.APP_ID,
                Notify.NOTIFY_TARGET,
                Notify.NOTIFY_TARGET_TYPE,
                Notify.NOTIFY_ACTION,
                Notify.NOTIFY_SENDER,
                Notify.NOTIFY_CONTENT
        );

        String notifyId = Util.getRandomUUID();
        body.setAppId(body.getAppId());
        body.setNotifyTarget(body.getNotifyTarget());
        body.setNotifyTargetType(body.getNotifyTargetType());
        body.setNotifyAction(body.getNotifyAction());
        body.setNotifySender(body.getNotifySender());
        body.setNotifyContent(body.getNotifyContent());

        String userId = body.getSystemCreateUserId();
        Boolean result = notifyService.save(body, notifyId, userId);
        return renderJson(result);
    }

    @ApiOperation(value = "拉取公告信息")
    @RequestMapping(value = "/notify/mobile/v1/pull/announce", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> pullAnnounceV1(@RequestBody Notify body) {
        validateRequest(
                body,
                Notify.APP_ID
        );

        //从UserNotify中获取最近的一条公告信息的创建时间: lastTime
        String userId = body.getSystemCreateUserId();
        UserNotify userNotify = userNotifyService.getNewNotify(body.getAppId());

        if (userNotify == null) {
            return renderJson(false);
        }

        // 用lastTime作为过滤条件，查询Notify的公告信息
        List<Notify> notifies = notifyService.getAnnounce(body.getAppId(), userNotify.getSystemCreateTime().toString());

        if (notifies == null) {
            return renderJson(false);
        }

        notifies.forEach((entity) -> {
            UserNotify uNotify = new UserNotify();
            uNotify.setAppId(body.getAppId());
            uNotify.setUserNotifyIsRead(false);
            uNotify.setUserNotifyOwerId(userId);
            uNotify.setNotifyId(entity.getNotifyId());
            userNotifyService.save(uNotify, Util.getRandomUUID(), userId);
        });

        //新建UserNotify并关联查询出来的公告信息
        List<UserNotify> userNotifies = userNotifyService.getUserNotifyByUserId(userId, body.getAppId(), "Announce");

        return renderJson(userNotifies);
    }

    @ApiOperation(value = "拉取提醒信息")
    @RequestMapping(value = "/notify/mobile/v1/pull/remind", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> pullRemindV1(@RequestBody User body) {
        validateRequest(
                body,
                User.APP_ID
        );

        String userId = body.getSystemCreateUserId();

        List<Notify> notifyList = new ArrayList<Notify>();

        // 1、查询用户的订阅表，得到用户的一系列订阅记录
        List<Subscription> subscriptions = subscriptionService.getSubscriptionByUserId(body.getAppId(), userId);

        // 2、通过每一条的订阅记录的target、targetType、action、createdAt去查询Notify表，获取订阅的Notify记录。（注意订阅时间必须早于提醒创建时间）
        subscriptions.forEach((entity) -> {
            List<Notify> notifies = notifyService.getNotifyInfoBySubscription(body.getAppId(), entity.getSubscriptionTarget(), entity.getSubscriptionTargetType(),
                    entity.getSubscriptionAction(), userId, entity.getSystemCreateTime().toString());

            notifies.forEach((notify) -> {
                notifyList.add(notify);
            });
        });

        // TODO:3、查询用户的配置文件SubscriptionConfig，如果没有则使用默认的配置DefaultSubscriptionConfig


        // 4、使用过滤好的Notify作为关联新建UserNotify
        notifyList.forEach((notify) -> {
            UserNotify userNotify = new UserNotify();
            userNotify.setAppId(notify.getAppId());
            userNotify.setUserNotifyIsRead(false);
            userNotify.setUserNotifyOwerId(userId);
            userNotify.setNotifyId(notify.getNotifyId());
            userNotifyService.save(userNotify, Util.getRandomUUID(), userId);
        });

        List<UserNotify> userNotifies = userNotifyService.getUserNotifyByUserId(userId, body.getAppId(), "Remind");

        return renderJson(userNotifies);
    }

    @ApiOperation(value = "订阅消息")
    @RequestMapping(value = "/notify/mobile/v1/subscribe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> subscribeV1(@RequestBody User body) {

        validateRequest(
                body,
                User.APP_ID,
                User.REASON
        );
        Subscription subscription = getEntry(Subscription.class);
        validateRequest(
                subscription,
                Subscription.SUBSCRIPTION_TARGET,
                Subscription.SUBSCRIPTION_TARGET_TYPE
        );

        // 1、通过reason，查询NotifyConfig，获取对应的动作组:actions.
        List<AppConfig> appConfigs = appConfigRpc.findAppConfigsByAppCode(body.getAppId(), "NOWUI_CLOUD");
        if (appConfigs == null){
            throw new BusinessException("系统配置初始化错误!");
        }

        List<AppConfig> list = appConfigs.stream().filter(appConfig -> appConfig.getConfigKey() == "REASONACTION")
                .collect(Collectors.toList());

        if (list == null) {
            throw new BusinessException("系统配置初始化错误!");
        }

        Boolean result = true;

        // 2、遍历动作组，每一个动作新建一则Subscription记录
        list.forEach((item)->{
            // TODO: 遍历JSON数组.
            JSONObject jsonObject = JSONObject.parseObject(item.getConfigValue());
            jsonObject.keySet().stream().forEach(key->jsonObject.get(key));

            // 存储 action 动作.
            Subscription tSubscription = new Subscription();
            tSubscription.setAppId(body.getAppId());
            tSubscription.setSubscriptionTarget(subscription.getSubscriptionTarget());
            tSubscription.setSubscriptionTarget(subscription.getSubscriptionTargetType());

            subscriptionService.save(tSubscription,Util.getRandomUUID(),body.getSystemCreateUserId())
        });

        return renderJson(result);
    }

    @ApiOperation(value = "取消订阅")
    @RequestMapping(value = "/notify/mobile/v1/delete/subscribe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> cancelSubscriptionV1(@RequestBody User body) {
        validateRequest(
                body,
                User.APP_ID
        );
        String userId = body.getSystemCreateUserId();
        Subscription subscription = getEntry(Subscription.class);
        validateRequest(
                subscription,
                Subscription.SUBSCRIPTION_TARGET,
                Subscription.SUBSCRIPTION_TARGET_TYPE
        );
        Boolean result = subscriptionService.delete(subscription.getSubscriptionId(), subscription.getSystemRequestUserId(), subscription.getSystemVersion());
        return renderJson(result);
    }

    @ApiOperation(value = "将消息设置成已读")
    @RequestMapping(value = "/notify/mobile/v1/set/read", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> readV1(@RequestBody UserNotify body) {
        validateRequest(
                body,
                UserNotify.APP_ID
        );

        JSONArray notifyIdsJsonArray = body.getJSONArray(UserNotify.NOTIFYIDS);

        if (Util.isNullOrEmpty(notifyIdsJsonArray)) {
            throw new BusinessException("NOTIFYIDS不能为空!");
        }

        List<String> notifyIds = notifyIdsJsonArray.toJavaList(String.class);
        Boolean result = true;

        notifyIds.forEach((item) -> {
            UserNotify userNotify = new UserNotify();
            userNotify.setAppId(body.getAppId());
            userNotify.setUserNotifyIsRead(true);
            userNotify.setNotifyId(item);
            userNotifyService.update(userNotify, item, userNotify.getSystemUpdateUserId(), userNotify.getSystemVersion());
        });

        return renderJson(result);
    }

}