package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserMessage;
import com.nowui.cloud.base.user.service.UserMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户消息管理端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "用户消息", description = "用户消息管理端接口管理")
@RestController
public class UserMessageAdminController extends BaseController {

    @Autowired
    private UserMessageService userMessageService;

    @ApiOperation(value = "用户消息列表")
    @RequestMapping(value = "/user/message/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody UserMessage body) {
        validateRequest(
                body,
                UserMessage.APP_ID,
                UserMessage.USER_ID,
                UserMessage.MESSAGE_ID,
                UserMessage.USER_MESSAGE_IS_READ,
                UserMessage.PAGE_INDEX,
                UserMessage.PAGE_SIZE
        );

        Integer resultTotal = userMessageService.adminCount(body.getAppId() , body.getUserId(), body.getMessageId(), body.getUserMessageIsRead());
        List<UserMessage> resultList = userMessageService.adminList(body.getAppId(), body.getUserId(), body.getMessageId(), body.getUserMessageIsRead(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserMessage.USER_MESSAGE_ID,
                UserMessage.USER_ID,
                UserMessage.MESSAGE_ID,
                UserMessage.USER_MESSAGE_IS_READ
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户消息信息")
    @RequestMapping(value = "/user/message/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody UserMessage body) {
        validateRequest(
                body,
                UserMessage.APP_ID,
                UserMessage.USER_MESSAGE_ID
        );

        UserMessage result = userMessageService.find(body.getUserMessageId());

        validateResponse(
                UserMessage.USER_MESSAGE_ID,
                UserMessage.USER_ID,
                UserMessage.MESSAGE_ID,
                UserMessage.USER_MESSAGE_IS_READ
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户消息")
    @RequestMapping(value = "/user/message/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody UserMessage body) {
        validateRequest(
                body,
                UserMessage.APP_ID,
                UserMessage.USER_ID,
                UserMessage.MESSAGE_ID,
                UserMessage.USER_MESSAGE_IS_READ
        );

        Boolean result = userMessageService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户消息")
    @RequestMapping(value = "/user/message/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody UserMessage body) {
        validateRequest(
                body,
                UserMessage.USER_MESSAGE_ID,
                UserMessage.APP_ID,
                UserMessage.USER_ID,
                UserMessage.MESSAGE_ID,
                UserMessage.USER_MESSAGE_IS_READ,
                UserMessage.SYSTEM_VERSION
        );

        Boolean result = userMessageService.update(body, body.getUserMessageId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户消息")
    @RequestMapping(value = "/user/message/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody UserMessage body) {
        validateRequest(
                body,
                UserMessage.USER_MESSAGE_ID,
                UserMessage.APP_ID,
                UserMessage.SYSTEM_VERSION
        );

        Boolean result = userMessageService.delete(body.getUserMessageId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}