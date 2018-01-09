package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicReplayLike;
import com.nowui.cloud.sns.topic.service.TopicReplayLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题回复管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题回复", description = "话题回复管理端接口管理")
@RestController
public class TopicReplayLikeAdminController extends BaseController {

    @Autowired
    private TopicReplayLikeService topicReplayLikeService;

    @ApiOperation(value = "话题回复列表")
    @RequestMapping(value = "/topic/replay/like/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicReplayLike body) {
        validateRequest(
                body,
                TopicReplayLike.APP_ID,
                TopicReplayLike.TOPIC_COMMENT_ID,
                TopicReplayLike.USER_ID,
                TopicReplayLike.PAGE_INDEX,
                TopicReplayLike.PAGE_SIZE
        );

        Integer resultTotal = topicReplayLikeService.countForAdmin(body.getAppId() , body.getTopicCommentId(), body.getUserId());
        List<TopicReplayLike> resultList = topicReplayLikeService.listForAdmin(body.getAppId(), body.getTopicCommentId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicReplayLike.TOPIC_REPLY_ID,
                TopicReplayLike.TOPIC_COMMENT_ID,
                TopicReplayLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题回复信息")
    @RequestMapping(value = "/topic/replay/like/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody TopicReplayLike body) {
        validateRequest(
                body,
                TopicReplayLike.APP_ID,
                TopicReplayLike.TOPIC_REPLY_ID
        );

        TopicReplayLike result = topicReplayLikeService.find(body.getTopicReplyId());

        validateResponse(
                TopicReplayLike.TOPIC_REPLY_ID,
                TopicReplayLike.TOPIC_COMMENT_ID,
                TopicReplayLike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题回复")
    @RequestMapping(value = "/topic/replay/like/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicReplayLike body) {
        validateRequest(
                body,
                TopicReplayLike.APP_ID,
                TopicReplayLike.TOPIC_COMMENT_ID,
                TopicReplayLike.USER_ID
        );

        Boolean result = topicReplayLikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题回复")
    @RequestMapping(value = "/topic/replay/like/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody TopicReplayLike body) {
        validateRequest(
                body,
                TopicReplayLike.TOPIC_REPLY_ID,
                TopicReplayLike.APP_ID,
                TopicReplayLike.TOPIC_COMMENT_ID,
                TopicReplayLike.USER_ID,
                TopicReplayLike.SYSTEM_VERSION
        );

        Boolean result = topicReplayLikeService.update(body, body.getTopicReplyId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题回复")
    @RequestMapping(value = "/topic/replay/like/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicReplayLike body) {
        validateRequest(
                body,
                TopicReplayLike.TOPIC_REPLY_ID,
                TopicReplayLike.APP_ID,
                TopicReplayLike.SYSTEM_VERSION
        );

        Boolean result = topicReplayLikeService.delete(body.getTopicReplyId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}