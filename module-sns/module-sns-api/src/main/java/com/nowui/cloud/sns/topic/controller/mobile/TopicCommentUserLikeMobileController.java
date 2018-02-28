package com.nowui.cloud.sns.topic.controller.mobile;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserUnlike;
import com.nowui.cloud.sns.topic.router.TopicCommentUserLikeRouter;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicCommentUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicCommentUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicCommentUserUnlikeView;
import com.nowui.cloud.sns.topic.view.TopicUserUnlikeView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
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
	
	@Autowired
	private MemberRpc memberRpc;
	
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
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String memberId = member.getMemberId();
        
        String appId = body.getAppId();
        
        // 先去评论点赞表查询有没有记录
        TopicCommentUserLikeView userLike = topicCommentUserLikeService.findTheCommentUserLike(appId, commentId, memberId);
        // 有: 就不做操作,并返回提示
        if (userLike != null) {
        	throw new BusinessException("已经点过赞了");
		}
        
        // 没有: 就去评论点赞表插入一条记录
        // TODO 没有处理点赞数
        TopicCommentUserLike result = topicCommentUserLikeService.saveWithRedis(appId, commentId, memberId, requestUserId);
        
        //并且删除取消点赞表的记录
        Boolean success = false;

        if (result != null) {
        	
        	TopicCommentUserUnlike commentUserUnlike = topicCommentUserUnlikeService.deleteByCommentIdAndMemberId(commentId, appId, memberId, requestUserId);
    		
        	/**
        	 * 向MongoDB中保存数据
        	 */
        	if (commentUserUnlike != null) {
				// 删除MongoDB中的取消点赞记录
        		TopicCommentUserUnlikeView userUnlikeView = JSON.parseObject(commentUserUnlike.toJSONString(), TopicCommentUserUnlikeView.class);
        		topicCommentUserUnlikeService.delete(userUnlikeView);
			}
        	
        	// 向MongoDB中新增点赞记录
        	TopicCommentUserLikeView userlikeView = JSON.parseObject(result.toJSONString(), TopicCommentUserLikeView.class);
    		topicCommentUserLikeService.save(userlikeView);
        	
            //sendMessage(result, TopicCommentUserLikeRouter.TOPIC_COMMENT_USER_LIKE_V1_SAVE, appId, userId);

            success = true;
        }

        return renderJson(success);
    }
        
}