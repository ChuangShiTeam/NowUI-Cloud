package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题提醒管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题提醒", description = "话题提醒管理端接口管理")
@RestController
public class TopicTipAdminController extends BaseController {

    @Autowired
    private TopicTipService topicTipService;

    @ApiOperation(value = "话题提醒列表")
    @RequestMapping(value = "/topic/tip/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicTip body) {
        validateRequest(
                body,
                TopicTip.APP_ID,
                TopicTip.TOPIC_ID,
                TopicTip.USER_ID,
                TopicTip.PAGE_INDEX,
                TopicTip.PAGE_SIZE
        );

        Integer resultTotal = topicTipService.countForAdmin(body.getAppId() , body.getTopicId(), body.getUserId());
        List<TopicTip> resultList = topicTipService.listForAdmin(body.getAppId(), body.getTopicId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicTip.TOPIC_TIP_ID,
                TopicTip.TOPIC_ID,
                TopicTip.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题提醒信息")
    @RequestMapping(value = "/topic/tip/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody TopicTip body) {
        validateRequest(
                body,
                TopicTip.APP_ID,
                TopicTip.TOPIC_TIP_ID
        );

        TopicTip result = topicTipService.find(body.getTopicTipId());

        validateResponse(
                TopicTip.TOPIC_TIP_ID,
                TopicTip.TOPIC_ID,
                TopicTip.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题提醒")
    @RequestMapping(value = "/topic/tip/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicTip body) {
        validateRequest(
                body,
                TopicTip.APP_ID,
                TopicTip.TOPIC_ID,
                TopicTip.USER_ID
        );

        Boolean result = topicTipService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题提醒")
    @RequestMapping(value = "/topic/tip/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody TopicTip body) {
        validateRequest(
                body,
                TopicTip.TOPIC_TIP_ID,
                TopicTip.APP_ID,
                TopicTip.TOPIC_ID,
                TopicTip.USER_ID,
                TopicTip.SYSTEM_VERSION
        );

        Boolean result = topicTipService.update(body, body.getTopicTipId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题提醒")
    @RequestMapping(value = "/topic/tip/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicTip body) {
        validateRequest(
                body,
                TopicTip.TOPIC_TIP_ID,
                TopicTip.APP_ID,
                TopicTip.SYSTEM_VERSION
        );

        Boolean result = topicTipService.delete(body.getTopicTipId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}