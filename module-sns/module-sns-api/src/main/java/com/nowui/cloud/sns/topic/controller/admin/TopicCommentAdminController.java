package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题评论管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题评论", description = "话题评论管理端接口管理")
@RestController
public class TopicCommentAdminController extends BaseController {

    @Autowired
    private TopicCommentService topicCommentService;

    @ApiOperation(value = "话题评论列表")
    @RequestMapping(value = "/topic/comment/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_CONTENT,
                TopicComment.PAGE_INDEX,
                TopicComment.PAGE_SIZE
        );

        Integer resultTotal = topicCommentService.adminCount(body.getAppId() , body.getUserId(), body.getTopicCommentContent(), body.getTopicReplayUserId(), body.getTopicReplyContent());
        List<TopicComment> resultList = topicCommentService.adminList(body.getAppId(), body.getUserId(), body.getTopicCommentContent(), body.getTopicReplayUserId(), body.getTopicReplyContent(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_CONTENT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题评论信息")
    @RequestMapping(value = "/topic/comment/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.TOPIC_COMMENT_ID
        );

        TopicComment result = topicCommentService.find(body.getTopicCommentId());

        validateResponse(
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_CONTENT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题评论")
    @RequestMapping(value = "/topic/comment/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_CONTENT
        );

        Boolean result = topicCommentService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题评论")
    @RequestMapping(value = "/topic/comment/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.APP_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_CONTENT,
                TopicComment.SYSTEM_VERSION
        );

        Boolean result = topicCommentService.update(body, body.getTopicCommentId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题评论")
    @RequestMapping(value = "/topic/comment/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.APP_ID,
                TopicComment.SYSTEM_VERSION
        );

        Boolean result = topicCommentService.delete(body.getTopicCommentId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}