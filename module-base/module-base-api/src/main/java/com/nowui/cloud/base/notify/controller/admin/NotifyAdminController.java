package com.nowui.cloud.base.notify.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.notify.entity.Notify;
import com.nowui.cloud.base.notify.service.NotifyService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 消息管理端控制器
 *
 * @author shawn
 *
 * 2018-01-28
 */
@Api(value = "消息", description = "消息管理端接口管理")
@RestController
public class NotifyAdminController extends BaseController {

    @Autowired
    private NotifyService notifyService;

    @ApiOperation(value = "消息列表")
    @RequestMapping(value = "/notify/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        Notify notifyEntity = getEntry(Notify.class);

        validateRequest(
                notifyEntity,
                Notify.APP_ID,
                Notify.NOTIFY_CONTENT,
                Notify.NOTIFY_TARGET,
                Notify.NOTIFY_TARGET_TYPE,
                Notify.NOTIFY_ACTION,
                Notify.PAGE_INDEX,
                Notify.PAGE_SIZE
        );

        Integer resultTotal = notifyService.countForAdmin(notifyEntity.getAppId(), notifyEntity.getNotifyContent(), notifyEntity.getNotifyTarget(), notifyEntity.getNotifyTargetType(), notifyEntity.getNotifyAction());
        List<Notify> resultList = notifyService.listForAdmin(notifyEntity.getAppId(), notifyEntity.getNotifyContent(), notifyEntity.getNotifyTarget(), notifyEntity.getNotifyTargetType(), notifyEntity.getNotifyAction(), notifyEntity.getPageIndex(), notifyEntity.getPageSize());

        validateResponse(
                Notify.NOTIFY_ID,
                Notify.NOTIFY_CONTENT,
                Notify.NOTIFY_TARGET,
                Notify.NOTIFY_TARGET_TYPE,
                Notify.NOTIFY_ACTION
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "消息信息")
    @RequestMapping(value = "/notify/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Notify notifyEntity = getEntry(Notify.class);

        validateRequest(
                notifyEntity,
                Notify.APP_ID,
                Notify.NOTIFY_ID
        );

        Notify result = notifyService.find(notifyEntity.getNotifyId());

        validateResponse(
                Notify.NOTIFY_ID,
                Notify.NOTIFY_CONTENT,
                Notify.NOTIFY_TARGET,
                Notify.NOTIFY_TARGET_TYPE,
                Notify.NOTIFY_ACTION,
                Notify.NOTIFY_SENDER,
                Notify.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增消息")
    @RequestMapping(value = "/notify/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Notify notifyEntity = getEntry(Notify.class);

        validateRequest(
                notifyEntity,
                Notify.APP_ID,
                Notify.NOTIFY_CONTENT,
                Notify.NOTIFY_TARGET,
                Notify.NOTIFY_TARGET_TYPE,
                Notify.NOTIFY_ACTION,
                Notify.NOTIFY_SENDER
        );

        Boolean result = notifyService.save(notifyEntity, Util.getRandomUUID(), notifyEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改消息")
    @RequestMapping(value = "/notify/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Notify notifyEntity = getEntry(Notify.class);

        validateRequest(
                notifyEntity,
                Notify.NOTIFY_ID,
                Notify.APP_ID,
                Notify.NOTIFY_CONTENT,
                Notify.NOTIFY_TARGET,
                Notify.NOTIFY_TARGET_TYPE,
                Notify.NOTIFY_ACTION,
                Notify.NOTIFY_SENDER,
                Notify.SYSTEM_VERSION
        );

        Boolean result = notifyService.update(notifyEntity, notifyEntity.getNotifyId(), notifyEntity.getSystemRequestUserId(), notifyEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除消息")
    @RequestMapping(value = "/notify/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Notify notifyEntity = getEntry(Notify.class);

        validateRequest(
                notifyEntity,
                Notify.NOTIFY_ID,
                Notify.APP_ID,
                Notify.SYSTEM_VERSION
        );

        Boolean result = notifyService.delete(notifyEntity.getNotifyId(), notifyEntity.getSystemRequestUserId(), notifyEntity.getSystemVersion());

        return renderJson(result);
    }

}