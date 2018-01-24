package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题的评论用户点赞管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Api(value = "话题的评论用户点赞", description = "话题的评论用户点赞管理端接口管理")
@RestController
public class TopicCommentUserLikeAdminController extends BaseController {

    @Autowired
    private TopicCommentUserLikeService topicCommentUserLikeService;

    @ApiOperation(value = "话题的评论用户点赞列表")
    @RequestMapping(value = "/topic/comment/user/like/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        TopicCommentUserLike topicCommentUserLikeEntity = getEntry(TopicCommentUserLike.class);

        validateRequest(
                topicCommentUserLikeEntity,
                TopicCommentUserLike.APP_ID,
                TopicCommentUserLike.COMMENT_ID,
                TopicCommentUserLike.USER_ID,
                TopicCommentUserLike.PAGE_INDEX,
                TopicCommentUserLike.PAGE_SIZE
        );

        Integer resultTotal = topicCommentUserLikeService.countForAdmin(topicCommentUserLikeEntity.getAppId() , topicCommentUserLikeEntity.getCommentId(), topicCommentUserLikeEntity.getUserId());
        List<TopicCommentUserLike> resultList = topicCommentUserLikeService.listForAdmin(topicCommentUserLikeEntity.getAppId(), topicCommentUserLikeEntity.getCommentId(), topicCommentUserLikeEntity.getUserId(), topicCommentUserLikeEntity.getPageIndex(), topicCommentUserLikeEntity.getPageSize());

        validateResponse(
                TopicCommentUserLike.COMMENT_USER_LIKE_ID,
                TopicCommentUserLike.COMMENT_ID,
                TopicCommentUserLike.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题的评论用户点赞信息")
    @RequestMapping(value = "/topic/comment/user/like/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        TopicCommentUserLike topicCommentUserLikeEntity = getEntry(TopicCommentUserLike.class);

        validateRequest(
                topicCommentUserLikeEntity,
                TopicCommentUserLike.APP_ID,
                TopicCommentUserLike.COMMENT_USER_LIKE_ID
        );

        TopicCommentUserLike result = topicCommentUserLikeService.find(topicCommentUserLikeEntity.getCommentUserLikeId());

        validateResponse(
                TopicCommentUserLike.COMMENT_USER_LIKE_ID,
                TopicCommentUserLike.COMMENT_ID,
                TopicCommentUserLike.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题的评论用户点赞")
    @RequestMapping(value = "/topic/comment/user/like/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        TopicCommentUserLike topicCommentUserLikeEntity = getEntry(TopicCommentUserLike.class);

        validateRequest(
                topicCommentUserLikeEntity,
                TopicCommentUserLike.APP_ID,
                TopicCommentUserLike.COMMENT_ID,
                TopicCommentUserLike.USER_ID
        );

        Boolean result = topicCommentUserLikeService.save(topicCommentUserLikeEntity, Util.getRandomUUID(), topicCommentUserLikeEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题的评论用户点赞")
    @RequestMapping(value = "/topic/comment/user/like/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        TopicCommentUserLike topicCommentUserLikeEntity = getEntry(TopicCommentUserLike.class);

        validateRequest(
                topicCommentUserLikeEntity,
                TopicCommentUserLike.COMMENT_USER_LIKE_ID,
                TopicCommentUserLike.APP_ID,
                TopicCommentUserLike.COMMENT_ID,
                TopicCommentUserLike.USER_ID,
                TopicCommentUserLike.SYSTEM_VERSION
        );

        Boolean result = topicCommentUserLikeService.update(topicCommentUserLikeEntity, topicCommentUserLikeEntity.getCommentUserLikeId(), topicCommentUserLikeEntity.getSystemRequestUserId(), topicCommentUserLikeEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题的评论用户点赞")
    @RequestMapping(value = "/topic/comment/user/like/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        TopicCommentUserLike topicCommentUserLikeEntity = getEntry(TopicCommentUserLike.class);

        validateRequest(
                topicCommentUserLikeEntity,
                TopicCommentUserLike.COMMENT_USER_LIKE_ID,
                TopicCommentUserLike.APP_ID,
                TopicCommentUserLike.SYSTEM_VERSION
        );

        Boolean result = topicCommentUserLikeService.delete(topicCommentUserLikeEntity.getCommentUserLikeId(), topicCommentUserLikeEntity.getSystemRequestUserId(), topicCommentUserLikeEntity.getSystemVersion());

        return renderJson(result);
    }

}