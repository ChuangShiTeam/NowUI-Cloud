package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.router.TopicCommentRouter;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.view.TopicCommentView;

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
    @RequestMapping(value = "/topic/comment/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_COMMENT_ID,
                TopicComment.PAGE_INDEX,
                TopicComment.PAGE_SIZE
        );

        Integer resultTotal = topicCommentService.countForAdmin(body.getAppId() , body.getUserId(), body.getTopicId(), body.getTopicCommentContent(), body.getTopicReplayUserId(), body.getTopicReplyCommentId());
        List<TopicComment> resultList = topicCommentService.listForAdmin(body.getAppId(), body.getUserId(), body.getTopicId(), body.getTopicCommentContent(), body.getTopicReplayUserId(), body.getTopicReplyCommentId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_COMMENT_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题评论信息")
    @RequestMapping(value = "/topic/comment/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.TOPIC_COMMENT_ID
        );

        TopicCommentView result = topicCommentService.find(body.getTopicCommentId());

        validateResponse(
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_COMMENT_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题评论")
    @RequestMapping(value = "/topic/comment/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_COMMENT_ID
        );

// TODO admin先注释掉       Boolean result = topicCommentService.save(body, Util.getRandomUUID(), body.getAppId(), TopicCommentRouter.TOPIC_COMMENT_V1_SAVE, body.getSystemRequestUserId());

        return renderJson(null);
    }

    @ApiOperation(value = "修改话题评论")
    @RequestMapping(value = "/topic/comment/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.APP_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_COMMENT_ID,
                TopicComment.SYSTEM_VERSION
        );

//  TODO admin先注释掉      Boolean result = topicCommentService.update(body, body.getTopicCommentId(), body.getAppId(), TopicCommentRouter.TOPIC_COMMENT_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(null);
    }

    @ApiOperation(value = "删除话题评论")
    @RequestMapping(value = "/topic/comment/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.APP_ID,
                TopicComment.SYSTEM_VERSION
        );

//  TODO admin先注释掉      Boolean result = topicCommentService.delete(body.getTopicCommentId(), body.getAppId(), TopicCommentRouter.TOPIC_COMMENT_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(null);
    }

}