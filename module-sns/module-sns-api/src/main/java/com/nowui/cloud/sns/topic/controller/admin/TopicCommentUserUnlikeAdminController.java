package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserUnlike;
import com.nowui.cloud.sns.topic.router.TopicCommentUserUnlikeRouter;
import com.nowui.cloud.sns.topic.service.TopicCommentUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicCommentUserUnlikeView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题评论的取消点赞管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Api(value = "话题评论的取消点赞", description = "话题评论的取消点赞管理端接口管理")
@RestController
public class TopicCommentUserUnlikeAdminController extends BaseController {

    @Autowired
    private TopicCommentUserUnlikeService topicCommentUserUnlikeService;

    @ApiOperation(value = "话题评论的取消点赞列表")
    @RequestMapping(value = "/topic/comment/user/unlike/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        TopicCommentUserUnlike topicCommentUserUnlikeEntity = getEntry(TopicCommentUserUnlike.class);

        validateRequest(
                topicCommentUserUnlikeEntity,
                TopicCommentUserUnlike.APP_ID,
                TopicCommentUserUnlike.COMMENT_ID,
                TopicCommentUserUnlike.MEMBER_ID,
                TopicCommentUserUnlike.PAGE_INDEX,
                TopicCommentUserUnlike.PAGE_SIZE
        );

        Integer resultTotal = topicCommentUserUnlikeService.countForAdmin(topicCommentUserUnlikeEntity.getAppId() , topicCommentUserUnlikeEntity.getCommentId(), topicCommentUserUnlikeEntity.getMemberId());
        List<TopicCommentUserUnlike> resultList = topicCommentUserUnlikeService.listForAdmin(topicCommentUserUnlikeEntity.getAppId(), topicCommentUserUnlikeEntity.getCommentId(), topicCommentUserUnlikeEntity.getMemberId(), topicCommentUserUnlikeEntity.getPageIndex(), topicCommentUserUnlikeEntity.getPageSize());

        validateResponse(
                TopicCommentUserUnlike.COMMENT_USER_UNLIKE_ID,
                TopicCommentUserUnlike.COMMENT_ID,
                TopicCommentUserUnlike.MEMBER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题评论的取消点赞信息")
    @RequestMapping(value = "/topic/comment/user/unlike/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        TopicCommentUserUnlike topicCommentUserUnlikeEntity = getEntry(TopicCommentUserUnlike.class);

        validateRequest(
                topicCommentUserUnlikeEntity,
                TopicCommentUserUnlike.APP_ID,
                TopicCommentUserUnlike.COMMENT_USER_UNLIKE_ID
        );

        TopicCommentUserUnlikeView result = topicCommentUserUnlikeService.find(topicCommentUserUnlikeEntity.getCommentUserUnlikeId());

        validateResponse(
                TopicCommentUserUnlike.COMMENT_USER_UNLIKE_ID,
                TopicCommentUserUnlike.COMMENT_ID,
                TopicCommentUserUnlike.MEMBER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题评论的取消点赞")
    @RequestMapping(value = "/topic/comment/user/unlike/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        TopicCommentUserUnlike topicCommentUserUnlikeEntity = getEntry(TopicCommentUserUnlike.class);

        validateRequest(
                topicCommentUserUnlikeEntity,
                TopicCommentUserUnlike.APP_ID,
                TopicCommentUserUnlike.COMMENT_ID,
                TopicCommentUserUnlike.MEMBER_ID
        );

//        Boolean result = topicCommentUserUnlikeService.save(topicCommentUserUnlikeEntity, Util.getRandomUUID(), topicCommentUserUnlikeEntity.getAppId(), TopicCommentUserUnlikeRouter.TOPIC_COMMENT_USER_UNLIKE_V1_SAVE, topicCommentUserUnlikeEntity.getSystemRequestUserId());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "修改话题评论的取消点赞")
    @RequestMapping(value = "/topic/comment/user/unlike/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        TopicCommentUserUnlike topicCommentUserUnlikeEntity = getEntry(TopicCommentUserUnlike.class);

        validateRequest(
                topicCommentUserUnlikeEntity,
                TopicCommentUserUnlike.COMMENT_USER_UNLIKE_ID,
                TopicCommentUserUnlike.APP_ID,
                TopicCommentUserUnlike.COMMENT_ID,
                TopicCommentUserUnlike.MEMBER_ID,
                TopicCommentUserUnlike.SYSTEM_VERSION
        );

//        Boolean result = topicCommentUserUnlikeService.update(topicCommentUserUnlikeEntity, topicCommentUserUnlikeEntity.getCommentUserUnlikeId(), topicCommentUserUnlikeEntity.getAppId(), TopicCommentUserUnlikeRouter.TOPIC_COMMENT_USER_UNLIKE_V1_UPDATE, topicCommentUserUnlikeEntity.getSystemRequestUserId(), topicCommentUserUnlikeEntity.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "删除话题评论的取消点赞")
    @RequestMapping(value = "/topic/comment/user/unlike/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        TopicCommentUserUnlike topicCommentUserUnlikeEntity = getEntry(TopicCommentUserUnlike.class);

        validateRequest(
                topicCommentUserUnlikeEntity,
                TopicCommentUserUnlike.COMMENT_USER_UNLIKE_ID,
                TopicCommentUserUnlike.APP_ID,
                TopicCommentUserUnlike.SYSTEM_VERSION
        );

//        Boolean result = topicCommentUserUnlikeService.delete(topicCommentUserUnlikeEntity.getCommentUserUnlikeId(), topicCommentUserUnlikeEntity.getAppId(), TopicCommentUserUnlikeRouter.TOPIC_COMMENT_USER_UNLIKE_V1_DELETE, topicCommentUserUnlikeEntity.getSystemRequestUserId(), topicCommentUserUnlikeEntity.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

}