package com.nowui.cloud.sns.topic.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicCommentUserUnlikeService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题的评论用户点赞移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
@Api(value = "话题的评论用户点赞", description = "话题的评论用户点赞移动端接口管理")
@RestController
public class TopicCommentUserLikeMobileController extends BaseController {
	
	@Autowired
    private TopicCommentUserLikeService topicCommentUserLikeService;
	
	@Autowired
    private TopicCommentUserUnlikeService topicCommentUserUnlikeService;
	
	@ApiOperation(value = "新增话题的评论用户点赞")
    @RequestMapping(value = "/topic/comment/user/like/mubile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        TopicCommentUserLike body = getEntry(TopicCommentUserLike.class);

        validateRequest(
        		body,
                TopicCommentUserLike.APP_ID,
                TopicCommentUserLike.COMMENT_ID
        );
        String commentId = body.getCommentId();
        String userId = body.getSystemRequestUserId();
        String appId = body.getAppId();
        
        // 先去评论点赞表查询有没有记录
        TopicCommentUserLike userLike = topicCommentUserLikeService.findTheCommentUserLike(commentId, userId);
        // 有: 就不做操作,并返回提示
        if (userLike != null) {
        	throw new BusinessException("已经点过赞了");
		}
        
        // 没有: 就去评论点赞表插入一条记录,并且删除取消点赞表的记录
        Boolean result = topicCommentUserLikeService.saveWithRedis(appId, commentId, userId, userId);
        if (result) {
        	topicCommentUserUnlikeService.deleteByCommentIdAndUserId(commentId, appId, userId, userId);
		}
        return renderJson(result);
    }
        
}