package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题信息管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题信息", description = "话题信息管理端接口管理")
@RestController
public class TopicAdminController extends BaseController {

    @Autowired
    private TopicService topicService;

    @ApiOperation(value = "话题信息列表")
    @RequestMapping(value = "/topic/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOPIC_TOP_LEVEL,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );

        Integer resultTotal = topicService.countForAdmin(body.getAppId(), body.getTopicSummary(), body.getUserId(), body.getLatitude(), body.getTopicIsLocation());
        List<Topic> resultList = topicService.listForAdmin(body.getAppId(), body.getTopicSummary(), body.getUserId(), body.getLatitude(), body.getLongtitude(), body.getTopicLocation(), body.getTopicIsLocation(), body.getTopicIsTop(), body.getTopicIsRecommend(), body.getTopicTopLevel(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOPIC_TOP_LEVEL
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题信息信息")
    @RequestMapping(value = "/topic/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_ID
        );

        Topic result = topicService.find(body.getTopicId());

        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOPIC_TOP_LEVEL
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题信息")
    @RequestMapping(value = "/topic/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOPIC_TOP_LEVEL
        );

        Boolean result = topicService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题信息")
    @RequestMapping(value = "/topic/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.TOPIC_ID,
                Topic.APP_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOPIC_TOP_LEVEL,
                Topic.SYSTEM_VERSION
        );

        Boolean result = topicService.update(body, body.getTopicId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题信息")
    @RequestMapping(value = "/topic/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.TOPIC_ID,
                Topic.APP_ID,
                Topic.SYSTEM_VERSION
        );

        Boolean result = topicService.delete(body.getTopicId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}