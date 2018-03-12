package com.nowui.cloud.base.notify.controller.mobile;

import java.util.Map;

import com.nowui.cloud.member.member.rpc.MemberRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.rpc.AppConfigRpc;
import com.nowui.cloud.base.notify.entity.Notify;
import com.nowui.cloud.base.notify.service.NotifyService;
import com.nowui.cloud.base.subscription.service.SubscriptionService;
import com.nowui.cloud.base.user.service.UserNotifyService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 消息移动端控制器
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
@Api(value = "消息", description = "消息移动端接口管理")
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
    @Autowired
    private MemberRpc memberRpc;

    @ApiOperation(value = "新增一条公告记录")
    @RequestMapping(value = "/notify/mobile/v1/create/notify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> createAnnounceV1() {
        Notify notifyEntity = getEntry(Notify.class);

        validateRequest(
                notifyEntity,
                Notify.APP_ID,
                Notify.NOTIFY_CONTENT,
                Notify.NOTIFY_SENDER
        );
        String notifyId = Util.getRandomUUID();

        Notify result = notifyService.save(notifyEntity, notifyId, notifyEntity.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "新增一条提醒记录")
    @RequestMapping(value = "/notify/mobile/v1/create/remind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> createRemindV1() {
        Notify notifyEntity = getEntry(Notify.class);

        validateRequest(
                notifyEntity,
                Notify.APP_ID,
                Notify.NOTIFY_TARGET,
                Notify.NOTIFY_TARGET_TYPE,
                Notify.NOTIFY_ACTION,
                Notify.NOTIFY_SENDER,
                Notify.NOTIFY_CONTENT
        );

        String notifyId = Util.getRandomUUID();

        Notify result = notifyService.save(notifyEntity, notifyId, notifyEntity.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

//    @ApiOperation(value = "拉取公告信息")
//    @RequestMapping(value = "/notify/mobile/v1/pull/announce", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> pullAnnounceV1(@RequestBody Notify body) {
//        validateRequest(
//                body,
//                Notify.APP_ID
//        );
//
//        //从UserNotify中获取最近的一条公告信息的创建时间: lastTime
//        String userId = body.getSystemCreateUserId();
//        UserNotify userNotify = userNotifyService.getNewNotify(body.getAppId());
//
//        if (userNotify == null) {
//            return renderJson(false);
//        }
//
//        // 用lastTime作为过滤条件，查询Notify的公告信息
//        List<Notify> notifies = notifyService.getAnnounce(body.getAppId(), userNotify.getSystemCreateTime().toString());
//
//        if (notifies == null) {
//            return renderJson(false);
//        }
//
//        notifies.forEach((entity) -> {
//            UserNotify uNotify = new UserNotify();
//            uNotify.setAppId(body.getAppId());
//            uNotify.setUserNotifyIsRead(false);
//            uNotify.setUserNotifyOwerId(userId);
//            uNotify.setNotifyId(entity.getNotifyId());
//            userNotifyService.save(uNotify, Util.getRandomUUID(), body.getAppId(), UserNotifyRouter.USER_NOTIFY_V1_SAVE, body.getSystemCreateUserId());
////            userNotifyService.save(uNotify, Util.getRandomUUID(), userId);
//        });
//
//        List<UserNotify> userNotifies = userNotifyService.getUserNotifyByUserId(userId, body.getAppId(), "Remind");
//
//        userNotifies.forEach((entity) -> {
////            Notify notify = notifyService.find(userNotify.getNotifyId());
//            NotifyView notify = notifyService.find(userNotify.getNotifyId());
//            Member member = memberRpc.nickNameAndAvatarFindV1(notify.getNotifySender());
////            entity.setHeader(User.USER_AVATAR);
//            entity.setUserName(User.USER_NICK_NAME);
////            entity.setAction(notify.getNotifyAction());
////            entity.setTargetType(notify.getNotifyTargetType());
////            entity.setTargetContent(notify.getNotifyContent());
////            entity.setTargetId(notify.getNotifyTarget());
//            entity.setSystemCreateTime(notify.getSystemCreateTime());
//        });
//
//        // 构建JSON
//        validateResponse(UserNotify.HEADER, UserNotify.USERNAME, UserNotify.ACTION,
//                UserNotify.TARGETTYPE, UserNotify.TARGETID, UserNotify.TARGETCONTENT,
//                UserNotify.MESSAGE);
//
//        return renderJson(userNotifies);
//
//        //新建UserNotify并关联查询出来的公告信息
////        List<UserNotify> userNotifies = userNotifyService.getUserNotifyByUserId(userId, body.getAppId(), "Announce");
////
////        return renderJson(userNotifies);
//    }
//
//    @ApiOperation(value = "拉取提醒信息")
//    @RequestMapping(value = "/notify/mobile/v1/pull/remind", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> pullRemindV1(@RequestBody User body) {
//        validateRequest(
//                body,
//                User.APP_ID
//        );
//
//        String userId = body.getSystemCreateUserId();
//        List<Notify> notifyList = new ArrayList<Notify>();
//        // 1、查询用户的订阅表，得到用户的一系列订阅记录
//        List<Subscription> subscriptions = subscriptionService.getSubscriptionByUserId(body.getAppId(), userId);
//        // 2、通过每一条的订阅记录的target、targetType、action、createdAt去查询Notify表，获取订阅的Notify记录。（注意订阅时间必须早于提醒创建时间）
//        subscriptions.forEach((entity) -> {
//            List<Notify> notifies = notifyService.getNotifyInfoBySubscription(body.getAppId(), entity.getSubscriptionTarget(), entity.getSubscriptionTargetType(),
//                    entity.getSubscriptionAction(), userId, entity.getSystemCreateTime().toString());
//            notifies.forEach((notify) -> {
//                notifyList.add(notify);
//            });
//        });
//
//        // TODO:3、查询用户的配置文件SubscriptionConfig，如果没有则使用默认的配置DefaultSubscriptionConfig
//
//        // 4、使用过滤好的Notify作为关联新建UserNotify
//        notifyList.forEach((notify) -> {
//            UserNotifyView aUserNotify = userNotifyService.find(notify.getNotifyId());
//            if (aUserNotify == null) {
//                UserNotify userNotify = new UserNotify();
//                userNotify.setAppId(notify.getAppId());
//                userNotify.setUserNotifyIsRead(false);
//                userNotify.setUserNotifyOwerId(userId);
//                userNotify.setNotifyId(notify.getNotifyId());
//                userNotifyService.save(userNotify, Util.getRandomUUID(), body.getAppId(), UserNotifyRouter.USER_NOTIFY_V1_SAVE, body.getSystemCreateUserId());
//
////                userNotifyService.save(userNotify, Util.getRandomUUID(), userId);
//            }
//        });
//
//        List<UserNotify> userNotifies = userNotifyService.getUserNotifyByUserId(userId, body.getAppId(), "Remind");
//
//
//        // 构建JSON
//
//
//        return renderJson(userNotifies);
//    }
//
//    @ApiOperation(value = "订阅消息")
//    @RequestMapping(value = "/notify/mobile/v1/subscribe/message", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> subscribeV1(@RequestBody User body) {
//        validateRequest(
//                body,
//                User.APP_ID,
//                User.REASON
//        );
//        Subscription subscription = getEntry(Subscription.class);
//        validateRequest(
//                subscription,
//                Subscription.SUBSCRIPTION_TARGET,
//                Subscription.SUBSCRIPTION_TARGET_TYPE
//        );
//        // 1、通过reason，查询NotifyConfig，获取对应的动作组:actions.
//        List<AppConfig> appConfigs = appConfigRpc.findAppConfigsByAppCode(body.getAppId(), "NOWUI_CLOUD");
//        if (appConfigs == null) {
//            throw new BusinessException("系统配置初始化错误!");
//        }
//        List<AppConfig> list = appConfigs.stream().filter(appConfig -> "REASONACTION".equals(appConfig.getConfigKey()))
//                .collect(Collectors.toList());
//        if (list == null) {
//            throw new BusinessException("系统配置初始化错误!");
//        }
//        Boolean result = true;
//        // 2、遍历动作组，每一个动作新建一则Subscription记录
//        list.forEach((AppConfig item) -> {
//            JSONObject jsonObject = JSONObject.parseObject(item.getConfigValue());
//            JSONArray json = JSONArray.parseArray(jsonObject.get(body.getReason()).toString());
//            if (json.size() > 0) {
//                for (int i = 0; i < json.size(); i++) {
//                    // 存储 action 动作.
//                    Subscription tSubscription = new Subscription();
//                    tSubscription.setAppId(body.getAppId());
//                    tSubscription.setSubscriptionAction(json.get(i).toString());
//                    tSubscription.setSubscriptionTarget(subscription.getSubscriptionTarget());
//                    tSubscription.setSubscriptionTargetType(subscription.getSubscriptionTargetType());
//                    subscriptionService.save(tSubscription, Util.getRandomUUID(), body.getAppId(), body.getSystemCreateUserId());
////                    subscriptionService.save(tSubscription, Util.getRandomUUID(), body.getSystemCreateUserId());
//
//                }
//            }
//        });
//        return renderJson(result);
//    }
//
//    @ApiOperation(value = "取消订阅")
//    @RequestMapping(value = "/notify/mobile/v1/delete/subscribe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> cancelSubscriptionV1(@RequestBody User body) {
//        validateRequest(
//                body,
//                User.APP_ID
//        );
//        String userId = body.getSystemCreateUserId();
//        Subscription subscription = getEntry(Subscription.class);
//        validateRequest(
//                subscription,
//                Subscription.SUBSCRIPTION_TARGET,
//                Subscription.SUBSCRIPTION_TARGET_TYPE
//        );
//        Boolean result = subscriptionService.delete(subscription.getSubscriptionId(), body.getAppId(), body.getSystemUpdateUserId(), body.getSystemVersion());
////        Boolean result = subscriptionService.delete(subscription.getSubscriptionId(), subscription.getSystemRequestUserId(), subscription.getSystemVersion());
//        return renderJson(result);
//    }
//
//    @ApiOperation(value = "将消息设置成已读")
//    @RequestMapping(value = "/notify/mobile/v1/set/read", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> readV1(@RequestBody UserNotify body) {
//        validateRequest(
//                body,
//                UserNotify.APP_ID
//        );
//        JSONArray notifyIdsJsonArray = body.getJSONArray(UserNotify.NOTIFYIDS);
//        if (Util.isNullOrEmpty(notifyIdsJsonArray)) {
//            throw new BusinessException("NOTIFYIDS不能为空!");
//        }
//        List<String> notifyIds = notifyIdsJsonArray.toJavaList(String.class);
//        Boolean result = true;
//        notifyIds.forEach((item) -> {
//            UserNotify userNotify = new UserNotify();
//            userNotify.setAppId(body.getAppId());
//            userNotify.setUserNotifyIsRead(true);
//            userNotify.setNotifyId(item);
//            userNotifyService.update(userNotify, item, body.getAppId(), UserNotifyRouter.USER_NOTIFY_V1_UPDATE, body.getSystemUpdateUserId(), body.getSystemVersion());
////            userNotifyService.update(userNotify, item, userNotify.getSystemUpdateUserId(), userNotify.getSystemVersion());
//
//        });
//        return renderJson(result);
//    }

}