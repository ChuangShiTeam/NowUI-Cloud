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
    @RequestMapping(value = "/topic/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOP_TOP_LEVEL,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );

        Integer resultTotal = topicService.adminCount(body.getAppId() , body.getTopicForumId(), body.getTopicSummary(), body.getUserId(), body.getLatitude(), body.getLongtitude(), body.getTopicLocation(), body.getTopicIsLocation(), body.getTopicIsTop(), body.getTopicIsRecomand(), body.getTopTopLevel());
        List<Topic> resultList = topicService.adminList(body.getAppId(), body.getTopicForumId(), body.getTopicSummary(), body.getUserId(), body.getLatitude(), body.getLongtitude(), body.getTopicLocation(), body.getTopicIsLocation(), body.getTopicIsTop(), body.getTopicIsRecomand(), body.getTopTopLevel(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOP_TOP_LEVEL
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题信息信息")
    @RequestMapping(value = "/topic/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_ID
        );

        Topic result = topicService.find(body.getTopicId());

        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOP_TOP_LEVEL
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题信息")
    @RequestMapping(value = "/topic/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOP_TOP_LEVEL
        );

        Boolean result = topicService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题信息")
    @RequestMapping(value = "/topic/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.TOPIC_ID,
                Topic.APP_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOP_TOP_LEVEL,
                Topic.SYSTEM_VERSION
        );

        Boolean result = topicService.update(body, body.getTopicId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题信息")
    @RequestMapping(value = "/topic/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Topic body) {
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