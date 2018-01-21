package com.nowui.cloud.base.message.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.message.entity.Message;
import com.nowui.cloud.base.message.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 消息管理端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "消息", description = "消息管理端接口管理")
@RestController
public class MessageAdminController extends BaseController {

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "消息列表")
    @RequestMapping(value = "/message/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        Message messageEntity = getEntry(Message.class);

        validateRequest(
                messageEntity,
                Message.APP_ID,
                Message.MESSAGE_TITLE,
                Message.MESSAGE_TYPE,
                Message.PAGE_INDEX,
                Message.PAGE_SIZE
        );

        Integer resultTotal = messageService.countForAdmin(messageEntity.getAppId() , messageEntity.getMessageTitle(), messageEntity.getMessageType());
        List<Message> resultList = messageService.listForAdmin(messageEntity.getAppId(), messageEntity.getMessageTitle(), messageEntity.getMessageType(), messageEntity.getPageIndex(), messageEntity.getPageSize());

        validateResponse(
                Message.MESSAGE_ID,
                Message.MESSAGE_TITLE,
                Message.MESSAGE_TYPE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "消息信息")
    @RequestMapping(value = "/message/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Message messageEntity = getEntry(Message.class);

        validateRequest(
                messageEntity,
                Message.APP_ID,
                Message.MESSAGE_ID
        );

        Message result = messageService.find(messageEntity.getMessageId());

        validateResponse(
                Message.MESSAGE_ID,
                Message.OBJECT_ID,
                Message.MESSAGE_TITLE,
                Message.MESSAGE_TYPE,
                Message.MESSAGE_CONTENT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增消息")
    @RequestMapping(value = "/message/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Message messageEntity = getEntry(Message.class);

        validateRequest(
                messageEntity,
                Message.APP_ID,
                Message.OBJECT_ID,
                Message.MESSAGE_TITLE,
                Message.MESSAGE_TYPE,
                Message.MESSAGE_CONTENT
        );

        Boolean result = messageService.save(messageEntity, Util.getRandomUUID(), messageEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改消息")
    @RequestMapping(value = "/message/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Message messageEntity = getEntry(Message.class);

        validateRequest(
                messageEntity,
                Message.MESSAGE_ID,
                Message.APP_ID,
                Message.OBJECT_ID,
                Message.MESSAGE_TITLE,
                Message.MESSAGE_TYPE,
                Message.MESSAGE_CONTENT,
                Message.SYSTEM_VERSION
        );

        Boolean result = messageService.update(messageEntity, messageEntity.getMessageId(), messageEntity.getSystemRequestUserId(), messageEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除消息")
    @RequestMapping(value = "/message/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Message messageEntity = getEntry(Message.class);

        validateRequest(
                messageEntity,
                Message.MESSAGE_ID,
                Message.APP_ID,
                Message.SYSTEM_VERSION
        );

        Boolean result = messageService.delete(messageEntity.getMessageId(), messageEntity.getSystemRequestUserId(), messageEntity.getSystemVersion());

        return renderJson(result);
    }

}