package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.router.TopicUserLikeRouter;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicUserBookmarkView;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicUserUnlikeView;
import com.nowui.cloud.sns.topic.view.TopicView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 点赞话题关联移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "点赞话题关联", description = "点赞话题关联移动端接口管理")
@RestController
public class TopicUserLikeMobileController extends BaseController {

	@Autowired
    private TopicUserLikeService topicUserLikeService;

	@Autowired
	private TopicUserUnlikeService topicUserUnlikeService;
	
	@Autowired
	private MemberRpc memberRpc;

    @ApiOperation(value = "给话题点赞的用户列表")
    @RequestMapping(value = "/topic/user/like/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
    	TopicUserLike body = getEntry(TopicUserLike.class);
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.TOPIC_ID,
                TopicUserLike.PAGE_INDEX,
                TopicUserLike.PAGE_SIZE,
                TopicUserLike.SYSTEM_REQUEST_USER_ID
        );
        //统计话题点赞数 和 得到话题点赞列表
        Integer resultTotal = topicUserLikeService.countByTopicId(body.getTopicId());
        List<TopicUserLikeView> resultList = topicUserLikeService.listByTopicIdHavePage(body.getTopicId(), body.getPageIndex(), body.getPageSize());
        
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String requestMemberId = member.getMemberId();
       
        //处理列表中用户的头像,昵称,是否关注
//        String userIds = Util.beanToFieldString(resultList, TopicUserLike.USER_ID);
//        
//        List<Member> nickAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());
//        
//        resultList = Util.beanAddField(
//        		resultList,
//        		TopicUserLike.USER_ID,
//        		User.USER_ID,
//        		nickAndAvatarAndIsFollowList,
//        		User.USER_ID,
//        		UserAvatar.USER_AVATAR,
//        		UserNickName.USER_NICK_NAME,
//        		MemberFollow.MEMBER_IS_FOLLOW
//        	);

        for (TopicUserLikeView topicUserLike : resultList) {
        	topicUserLike.put(TopicUserLike.TOPIC_USER_LIKE_IS_SELF, requestMemberId.equals(topicUserLike.getMemberId()));
        }
        
        validateResponse(
                TopicUserLike.TOPIC_USER_LIKE_ID,
                TopicUserLike.MEMBER_ID,
                TopicUserLike.TOPIC_ID,
                User.USER_ID,
        		UserAvatar.USER_AVATAR_FILE_PATH,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW,
        		TopicUserLike.TOPIC_USER_LIKE_IS_SELF
        );

        return renderJson(resultTotal, resultList);
    }


    @ApiOperation(value = "新增点赞话题关联")
    @RequestMapping(value = "/topic/user/like/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
    	TopicUserLike body = getEntry(TopicUserLike.class);
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.TOPIC_ID,
                TopicUserLike.SYSTEM_REQUEST_USER_ID
        );
        
        String appId = body.getAppId();
        String topicId = body.getTopicId();
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String requestMemberId = member.getMemberId();
        
        String userNickName = body.getUserNickName();
        String userAvatar = body.getUserAvatar();

        TopicUserLikeView userLike = topicUserLikeService.findByTopicIdAndMemberId(topicId, requestMemberId);
        
        if (userLike != null) {
            throw new BusinessException("已经点过赞了");
		}

        body.setMemberId(requestMemberId);

        TopicUserLike result = topicUserLikeService.save(appId, topicId, requestMemberId, requestUserId);
        boolean success = false;

        if (result != null) {
            TopicUserUnlike userUnlike = topicUserUnlikeService.deleteByTopicIdAndMemberId(topicId, requestMemberId, appId, requestUserId);
            
            if (userUnlike != null) {
            	//删除取消点赞记录
            	TopicUserUnlikeView userUnlikeView = JSON.parseObject(userUnlike.toJSONString(), TopicUserUnlikeView.class);
            	topicUserUnlikeService.delete(userUnlikeView);
			}
            
            //把点赞记录存到MongoDB中
            /**
             * 用户头像,用户昵称,
             */
            TopicUserLikeView topicView = JSON.parseObject(result.toJSONString(), TopicUserLikeView.class);
            topicView.setUserNickName(userNickName);
            topicView.setUserAvatar(userAvatar);
            topicUserLikeService.save(topicView);
            
            //sendMessage(result, TopicUserLikeRouter.TOPIC_USER_LIKE_V1_SAVE, appId, userId);
            success = true;
        }

        return renderJson(success);
    }
}