package com.nowui.cloud.base.user.controller.admin;
import java.util.List;
import java.util.Map;

import com.nowui.cloud.base.user.router.UserNotifyRouter;
import com.nowui.cloud.base.user.view.UserNotifyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.user.entity.UserNotify;
import com.nowui.cloud.base.user.service.UserNotifyService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户消息队列表管理端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "用户消息队列表", description = "用户消息队列表管理端接口管理")
@RestController
public class UserNotifyAdminController extends BaseController {

    @Autowired
    private UserNotifyService userNotifyService;

    @ApiOperation(value = "用户消息队列表列表")
    @RequestMapping(value = "/user/notify/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        UserNotify userNotifyEntity = getEntry(UserNotify.class);

        validateRequest(
                userNotifyEntity,
                UserNotify.APP_ID,
                UserNotify.USER_NOTIFY_IS_READ,
                UserNotify.USER_NOTIFY_OWER_ID,
                UserNotify.PAGE_INDEX,
                UserNotify.PAGE_SIZE
        );

        Integer resultTotal = userNotifyService.countForAdmin(userNotifyEntity.getAppId(), userNotifyEntity.getUserNotifyIsRead(), userNotifyEntity.getUserNotifyOwerId());
        List<UserNotify> resultList = userNotifyService.listForAdmin(userNotifyEntity.getAppId(), userNotifyEntity.getUserNotifyIsRead(), userNotifyEntity.getUserNotifyOwerId(), userNotifyEntity.getPageIndex(), userNotifyEntity.getPageSize());

        validateResponse(
                UserNotify.USER_NOTIFY_ID,
                UserNotify.USER_NOTIFY_IS_READ,
                UserNotify.USER_NOTIFY_OWER_ID,
                UserNotify.NOTIFY_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户消息队列表信息")
    @RequestMapping(value = "/user/notify/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(){
        UserNotify userNotifyEntity = getEntry(UserNotify.class);

        validateRequest(
                userNotifyEntity,
                UserNotify.APP_ID,
                UserNotify.USER_NOTIFY_ID
        );

        UserNotifyView result = userNotifyService.find(userNotifyEntity.getUserNotifyId());

        validateResponse(
                UserNotify.USER_NOTIFY_ID,
                UserNotify.USER_NOTIFY_IS_READ,
                UserNotify.USER_NOTIFY_OWER_ID,
                UserNotify.NOTIFY_ID,
                UserNotify.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户消息队列表")
    @RequestMapping(value = "/user/notify/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        UserNotify userNotifyEntity = getEntry(UserNotify.class);

        validateRequest(
                userNotifyEntity,
                UserNotify.APP_ID,
                UserNotify.USER_NOTIFY_IS_READ,
                UserNotify.USER_NOTIFY_OWER_ID,
                UserNotify.NOTIFY_ID
        );

        String userNotifyId = Util.getRandomUUID();

        UserNotify result = userNotifyService.save(userNotifyEntity, userNotifyId, userNotifyEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, UserNotifyRouter.USER_NOTIFY_V1_SAVE, userNotifyEntity.getAppId(), userNotifyEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改用户消息队列表")
    @RequestMapping(value = "/user/notify/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        UserNotify userNotifyEntity = getEntry(UserNotify.class);

        validateRequest(
                userNotifyEntity,
                UserNotify.USER_NOTIFY_ID,
                UserNotify.APP_ID,
                UserNotify.USER_NOTIFY_IS_READ,
                UserNotify.USER_NOTIFY_OWER_ID,
                UserNotify.NOTIFY_ID,
                UserNotify.SYSTEM_VERSION
        );

        UserNotify result = userNotifyService.update(userNotifyEntity, userNotifyEntity.getUserNotifyId(), userNotifyEntity.getSystemRequestUserId(), userNotifyEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, UserNotifyRouter.USER_NOTIFY_V1_UPDATE, userNotifyEntity.getAppId(), userNotifyEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除用户消息队列表")
    @RequestMapping(value = "/user/notify/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        UserNotify userNotifyEntity = getEntry(UserNotify.class);

        validateRequest(
                userNotifyEntity,
                UserNotify.USER_NOTIFY_ID,
                UserNotify.APP_ID,
                UserNotify.SYSTEM_VERSION
        );

        UserNotify result = userNotifyService.delete(userNotifyEntity.getUserNotifyId(), userNotifyEntity.getSystemRequestUserId(), userNotifyEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, UserNotifyRouter.USER_NOTIFY_V1_DELETE, userNotifyEntity.getAppId(), userNotifyEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "管理员数据同步")
    @RequestMapping(value = "/user/notify/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<UserNotify> userNotifyList = userNotifyService.listByMysql();

        for (UserNotify userNotify : userNotifyList) {
            UserNotifyView userNotifyView = new UserNotifyView();
            userNotifyView.putAll(userNotify);

            userNotifyService.update(userNotifyView);
        }

        return renderJson(true);
    }

}