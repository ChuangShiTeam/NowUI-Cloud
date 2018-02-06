package com.nowui.cloud.sns.topic.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.router.TopicForumRouter;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.view.TopicForumView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 话题论坛关联管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题论坛关联", description = "话题论坛关联管理端接口管理")
@RestController
public class TopicForumAdminController extends BaseController {

    @Autowired
    private TopicForumService topicForumService;

    @ApiOperation(value = "话题论坛关联列表")
    @RequestMapping(value = "/topic/forum/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID,
                TopicForum.PAGE_INDEX,
                TopicForum.PAGE_SIZE
        );

        Integer resultTotal = topicForumService.countForAdmin(body.getAppId() , body.getForumId(), body.getTopicId());
        List<TopicForum> resultList = topicForumService.listForAdmin(body.getAppId(), body.getForumId(), body.getTopicId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicForum.TOPIC_FORUM_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题论坛关联信息")
    @RequestMapping(value = "/topic/forum/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.TOPIC_FORUM_ID
        );

        TopicForumView result = topicForumService.find(body.getTopicForumId());

        validateResponse(
                TopicForum.TOPIC_FORUM_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题论坛关联")
    @RequestMapping(value = "/topic/forum/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID
        );

//        Boolean result = topicForumService.save(body, Util.getRandomUUID(), body.getAppId(), TopicForumRouter.TOPIC_FORUM_V1_SAVE, body.getSystemRequestUserId());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "修改话题论坛关联")
    @RequestMapping(value = "/topic/forum/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.TOPIC_FORUM_ID,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID,
                TopicForum.SYSTEM_VERSION
        );

//        Boolean result = topicForumService.update(body, body.getTopicForumId(), body.getAppId(), TopicForumRouter.TOPIC_FORUM_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "删除话题论坛关联")
    @RequestMapping(value = "/topic/forum/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.TOPIC_FORUM_ID,
                TopicForum.APP_ID,
                TopicForum.SYSTEM_VERSION
        );

//        Boolean result = topicForumService.delete(body.getTopicForumId(), body.getAppId(), TopicForumRouter.TOPIC_FORUM_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

}