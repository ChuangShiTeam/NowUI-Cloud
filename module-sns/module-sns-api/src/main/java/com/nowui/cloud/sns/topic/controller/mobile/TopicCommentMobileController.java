package com.nowui.cloud.sns.topic.controller.mobile;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.router.TopicCommentRouter;
import com.nowui.cloud.sns.topic.router.TopicTipRouter;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicCommentUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import com.nowui.cloud.sns.topic.view.TopicCommentUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicCommentView;
import com.nowui.cloud.sns.topic.view.TopicTipView;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题评论移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题评论", description = "话题评论移动端接口管理")
@RestController
public class TopicCommentMobileController extends BaseController {
	
	@Autowired
    private TopicCommentService topicCommentService;
	
	@Autowired
	private TopicTipService topicTipService;
	
	@Autowired
    private TopicCommentUserLikeService topicCommentUserLikeService;

	@Autowired
	private MemberRpc memberRpc;

    @ApiOperation(value = "话题详情页评论列表")
    @RequestMapping(value = "/topic/comment/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
    	TopicComment body = getEntry(TopicComment.class);
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.TOPIC_ID,
                TopicComment.PAGE_INDEX,
                TopicComment.PAGE_SIZE,
                TopicComment.SYSTEM_CREATE_TIME
        );

        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String requestMemberId = member.getMemberId();
        
        String appId = body.getAppId();
        String topicId = body.getTopicId();
        
        Integer resultTotal = topicCommentService.countByTopicId(topicId);
        List<TopicCommentView> topicCommentList = topicCommentService.listByTopicId(appId, topicId, (List<String>) body.get(TopicComment.EXCLUDE_COMMENT_ID_LIST), body.getSystemCreateTime(), body.getPageIndex(), body.getPageSize());
        if (Util.isNullOrEmpty(topicCommentList)) {
            return renderJson(resultTotal, topicCommentList);
        }
        
        //处理评论是否自己发的
        for (TopicCommentView topicComment : topicCommentList) {
        	// 验证评论是否是自己的
            topicComment.put(TopicComment.TOPIC_COMMENT_IS_SELF, topicComment.getMemberId().equals(requestMemberId));
            
            // 处理用户是否点赞
            TopicCommentUserLikeView theCommentUserLike = topicCommentUserLikeService.findTheCommentUserLike(appId, topicComment.getTopicCommentId(), requestMemberId);
            if (theCommentUserLike != null) {
            	topicComment.put(TopicComment.TOPIC_COMMENT_IS_LIKE, true);
			}else {
				topicComment.put(TopicComment.TOPIC_COMMENT_IS_LIKE, false);
			}
            
            // 获取评论点赞数
            Integer likeCount = topicCommentUserLikeService.countByCommentIdWithRedis(appId, topicComment.getTopicCommentId());
            topicComment.put(TopicComment.TOPIC_COMMENT_LIKE_COUNT, likeCount);
		}
        
        // TODO 处理用户信息(昵称,头像)
//        String userIds = Util.beanToFieldString(topicCommentList, TopicComment.USER_ID);
//        List<Member> memberList = memberRpc.nickNameAndAvatarListV1(userIds);
//        topicCommentList = Util.beanAddField(
//        		topicCommentList, 
//        		TopicComment.USER_ID, 
//        		User.USER_ID, 
//        		memberList, 
//        		User.USER_ID,
//        		UserAvatar.USER_AVATAR,
//        		UserNickName.USER_NICK_NAME
//        	);
        
        
        // TODO 处理回复用户信息(头像,昵称)
//        String respondUserIds = Util.beanToFieldString(topicCommentList, TopicComment.TOPIC_REPLAY_USER_ID);
//        
//        List<Member> respondMemberList = memberRpc.nickNameAndAvatarListV1(respondUserIds);
//    
//        if (!Util.isNullOrEmpty(respondMemberList)) {
//            for (TopicComment topicComment : topicCommentList) {
//                if (Util.isNullOrEmpty(topicComment.getTopicReplayUserId())) {
//                    continue;
//                }
//                Optional<Member> memberOption = respondMemberList.stream().filter(respondMember -> topicComment.getTopicReplayUserId().equals(respondMember.getUserId())).findFirst();
//                topicComment.put(TopicComment.TOPIC_REPLAY_USER_NICK_NAME, memberOption.isPresent() ? memberOption.get().get(UserNickName.USER_NICK_NAME) : null);
//            }
//        }
        
        validateResponse(
            TopicComment.TOPIC_COMMENT_ID,
            TopicComment.MEMBER_ID,
            TopicComment.TOPIC_ID,
            TopicComment.TOPIC_COMMENT_CONTENT,
            TopicComment.TOPIC_REPLAY_MEMBER_ID,
            TopicComment.TOPIC_REPLY_COMMENT_ID,
            TopicComment.TOPIC_REPLAY_USER_NICK_NAME,
            User.USER_ID,
    		UserAvatar.USER_AVATAR,
    		UserNickName.USER_NICK_NAME,
    		TopicComment.SYSTEM_CREATE_TIME,
    		TopicComment.TOPIC_COMMENT_IS_SELF,
    		TopicComment.TOPIC_COMMENT_IS_LIKE,
    		TopicComment.TOPIC_COMMENT_LIKE_COUNT
        );

        return renderJson(resultTotal, topicCommentList);
    }

    @ApiOperation(value = "新增话题评论")
    @RequestMapping(value = "/topic/comment/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
    	TopicComment body = getEntry(TopicComment.class);
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.SYSTEM_REQUEST_USER_ID,
                TopicComment.TOPIC_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_MEMBER_ID,
                TopicComment.TOPIC_REPLY_COMMENT_ID
        );
        String systemRequestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(systemRequestUserId);
        String memberId = member.getMemberId();
        body.setMemberId(memberId);
        
        String topicReplayMemberId = body.getTopicReplayMemberId();
        String appId = body.getAppId();
        
        TopicComment result = topicCommentService.save(body, Util.getRandomUUID(), systemRequestUserId);
        
        Boolean success = false;

        if (result != null) {
        	
        	//提醒被回复人
        	TopicTip topicTip = new TopicTip();
            if (!Util.isNullOrEmpty(topicReplayMemberId)) {
    			//把被回复人添加到回复表
            	
            	topicTip.setAppId(body.getAppId());
            	topicTip.setTopicId(body.getTopicId());
            	topicTip.setMemberId(topicReplayMemberId);
            	
            	topicTip = topicTipService.save(topicTip, Util.getRandomUUID(), systemRequestUserId);
    		}
            
            
            /**
             * 向MongoDB中保存
             * 动态评论(1:发评论的用户头像, 2:发评论的用户Id(这个不用), 3:发评论的用户昵称, 4:被回复的评论的id(这个前端自动带过来了), 5:被回复的用户的头像(这个不用), 6:被回复的用户的id(前端带过来) 7:被回复的用户昵称)
             * 提醒谁看
             */
            // 保存动态评论
            TopicCommentView topicCommentView = JSON.parseObject(result.toJSONString(), TopicCommentView.class);
            topicCommentService.save(topicCommentView);
            
            // 保存提醒谁看
            TopicTipView topicTipView = JSON.parseObject(topicTip.toJSONString(), TopicTipView.class);
            topicTipService.save(topicTipView);
            
            
            //sendMessage(result, TopicCommentRouter.TOPIC_COMMENT_V1_SAVE, appId, systemRequestUserId);

            success = true;
        }

        return renderJson(success);
    }
    
    
    @ApiOperation(value = "删除话题评论")
    @RequestMapping(value = "/topic/comment/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
    	TopicComment body = getEntry(TopicComment.class);
        validateRequest(
                body,
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.APP_ID
        );
     //TODO 删除话题评论的接口,没有这功能,也没完善接口  
//        Boolean result = topicCommentService.delete(topicCommentId, body.getAppId(), TopicCommentRouter.TOPIC_COMMENT_V1_DELETE, body.getSystemRequestUserId(), TopicComment.getSystemVersion());
        
        TopicComment result = topicCommentService.delete(body.getTopicCommentId(), body.getSystemRequestUserId(), body.getSystemVersion());
        
        Boolean success = false;

        if (result != null) {
            //sendMessage(result, TopicCommentRouter.TOPIC_COMMENT_V1_DELETE, result.getAppId(), result.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }
}