package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.view.ForumView;
import com.nowui.cloud.sns.member.entity.MemberFollow;
import com.nowui.cloud.sns.member.service.MemberFollowService;
import com.nowui.cloud.sns.member.service.MemberHomepageService;
import com.nowui.cloud.sns.member.view.MemberFollowView;
import com.nowui.cloud.sns.member.view.MemberHomepageView;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.view.TopicCommentView;
import com.nowui.cloud.sns.topic.view.TopicForumView;
import com.nowui.cloud.sns.topic.view.TopicMediaView;
import com.nowui.cloud.sns.topic.view.TopicTipView;
import com.nowui.cloud.sns.topic.view.TopicUserBookmarkView;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 话题信息移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题信息", description = "话题信息移动端接口管理")
@RestController
public class TopicMobileController extends BaseController {

    @Autowired
    private TopicService topicService;
    
    @Autowired
    private ForumService forumService;
    
    @Autowired
    private TopicForumService topicForumService;
    
    @Autowired
    private TopicMediaService topicMediaService;
    
    @Autowired
    private TopicTipService topicTipService;
    
    @Autowired
    private MemberHomepageService memberHomepageService;

    @Autowired
    private MemberRpc memberRpc;
    
    @Autowired
    private MemberFollowService memberFollowService;
    
    @Autowired
    private TopicUserLikeService topicUserLikeService;
    
    @Autowired
    private TopicUserBookmarkService topicUserBookmarkService;
    
    @Autowired
    private TopicCommentService topicCommentService;

    @ApiOperation(value = "论坛中的话题信息列表")
    @RequestMapping(value = "/topic/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
    	TopicForum body = getEntry(TopicForum.class);
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.PAGE_INDEX,
                TopicForum.PAGE_SIZE,
                TopicForum.SYSTEM_REQUEST_USER_ID
        );
        // TODO 这个接口好像没有被用到
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String requestMemberId = member.getMemberId();
        String appId = body.getAppId();

        Integer resultTotal = topicForumService.countByForumId(appId, body.getForumId());

        List<TopicView> resultList = topicService.listByForumId(appId, body.getForumId(), requestMemberId, body.getPageIndex(), body.getPageSize());
        
        // 处理话题
        for (TopicView topic : resultList) {
            
            List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);
          
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
            
            //验证话题发布人是否自己
            String theSendMemberId = topic.getMemberId();
            
            topic.put(Topic.TOPIC_IS_SELF, theSendMemberId.equals(requestMemberId));
        }

        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.MEMBER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_MEDIA_LIST,
                User.USER_ID,
                UserAvatar.USER_AVATAR_FILE_PATH,
                UserNickName.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                Topic.TOPIC_IS_SELF
        );

        return renderJson(resultTotal, resultList);
    }
    
    
    @ApiOperation(value = "别人的主页的用户信息(头像,关注数,粉丝数是否关注这个用户等)")
    @RequestMapping(value = "/topic/mobile/v1/home/user/info", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeUserInfoV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.MEMBER_ID
        );
        
        String otherMemberId = body.getMemberId();
        String appId = body.getAppId();
        String requestUserId = body.getSystemRequestUserId();
        
        // 获取用户头像,昵称,签名,背景
        MemberHomepageView otherHomepageView = memberHomepageService.findByMemberId(appId, otherMemberId);
        if (otherHomepageView == null) {
        	throw new BusinessException("对不起,未找到对方信息...");
		}
        
        Boolean memberIsFollow = memberFollowService.checkIsFollow(requestUserId, otherHomepageView.getUserId());
        otherHomepageView.put(MemberFollow.MEMBER_IS_FOLLOW, memberIsFollow);
        
        // TODO 我的宠物
        
        validateResponse(
        		MemberHomepageView.MEMBER_ADDRESS_ADDRESS,
        		MemberHomepageView.MEMBER_ADDRESS_AREA,
        		MemberHomepageView.MEMBER_ADDRESS_CITY,
        		MemberHomepageView.MEMBER_ADDRESS_PROVINCE,
        		MemberHomepageView.MEMBER_BACKGROUND_FILE_PATH,
        		MemberHomepageView.MEMBER_BE_FOLLOW_COUNT,
        		MemberHomepageView.MEMBER_FOLLOW_COUNT,
        		MemberHomepageView.MEMBER_HOMEPAGE_ID,
        		MemberHomepageView.MEMBER_ID,
        		MemberHomepageView.MEMBER_SEND_TOPIC_COUNT,
        		MemberHomepageView.MEMBER_SIGNATURE,
        		MemberHomepageView.PET,
        		MemberHomepageView.USER_AVATAR_FILE_PATH,
        		MemberHomepageView.USER_NICK_NAME,
        		MemberHomepageView.USER_SEX,
        		
        		MemberFollow.MEMBER_IS_FOLLOW
        	);
        
        return renderJson(otherHomepageView);
    }
    
    @ApiOperation(value = "别人的主页的动态列表")
    @RequestMapping(value = "/topic/mobile/v1/home/topic", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeTopicV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.MEMBER_ID,
                Topic.SYSTEM_CREATE_TIME,
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.REQUEST_MEMBER_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );
        String appId = body.getAppId();
        
        Integer commentPageIndex = (Integer) body.get(Topic.COMMENT_PAGE_INDEX);
        Integer commentPageSize =(Integer) body.get(Topic.COMMENT_PAGE_SIZE);
        String otherMemberId = body.getMemberId();
        String requestMemberId = body.getRequestMemberId();
        
        ArrayList<String> memberIdToSearchList = new ArrayList<>();
        memberIdToSearchList.add(otherMemberId);
        Integer countResult = topicService.countByMemberIdList(appId, memberIdToSearchList);
        List<TopicView> resultList = topicService.listByMemberIdList(appId, memberIdToSearchList, (List<String>) body.get(Topic.EXCLUDE_TOPIC_ID_LIST), body.getSystemCreateTime(), body.getPageIndex(), body.getPageSize());
        
        MemberFollowView memberFollowView = memberFollowService.findByUserIdAndBeFollowMemberId(body.getSystemRequestUserId(), otherMemberId);
        boolean isFollow = false;
        if (memberFollowView != null) {
        	isFollow = true;
		}
        for (TopicView topic : resultList) {
        	topic.put(MemberFollow.MEMBER_IS_FOLLOW, isFollow);
        	
            // 取话题前3条评论
            if (Util.isNullOrEmpty(commentPageIndex) || Util.isNullOrEmpty(commentPageSize)) {
            	commentPageIndex = 1;
            	commentPageSize = 3;
			}
            List<TopicCommentView> topicCommentList = topicCommentService.listByTopicId(topic.getTopicId(), commentPageIndex, commentPageSize);
            // 处理发送用户信息(昵称)
            // 处理回复用户信息(昵称)
            topic.put(Topic.TOPIC_COMMENT_LIST, topicCommentList);
            
            // 是否点赞
    		TopicUserLikeView userLikeView = topicUserLikeService.findByTopicIdAndMemberId(topic.getTopicId(), requestMemberId);
    		topic.put(Topic.TOPIC_USER_IS_LIKE, !Util.isNullOrEmpty(userLikeView));
            // 是否收藏
            TopicUserBookmarkView bookmarkView = topicUserBookmarkService.findByTopicIdAndMemberId(topic.getTopicId(), requestMemberId);
            topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, !Util.isNullOrEmpty(bookmarkView));
            
        }
        
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.MEMBER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_FORUM_LIST,
                Topic.TOPIC_MEDIA_LIST,
                Topic.TOPIC_COMMENT_LIST,
                Topic.TOPIC_COUNT_BOOKMARK,
                Topic.TOPIC_COUNT_LIKE,
                Topic.TOPIC_COUNT_COMMENT,
                Topic.TOPIC_USER_IS_BOOKEMARK,
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                
                User.USER_ID,
                UserAvatar.USER_AVATAR_FILE_PATH,
                UserNickName.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                BaseEntity.SYSTEM_CREATE_TIME
            );
        validateSecondResponse(TopicView.TOPIC_MEDIA_LIST, TopicMedia.TOPIC_MEDIA_FILE_PATH, TopicMedia.TOPIC_MEDIA_SORT, TopicMedia.TOPIC_MEDIA_TYPE);
        validateSecondResponse(TopicView.TOPIC_TIP_USER_LIST, Topic.MEMBER_ID);
        validateSecondResponse(TopicView.TOPIC_FORUM_LIST, Forum.FORUM_NAME, Forum.FORUM_ID);
        validateSecondResponse(Topic.TOPIC_COMMENT_LIST, TopicComment.MEMBER_ID, UserNickName.USER_NICK_NAME, TopicComment.TOPIC_REPLY_MEMBER_ID, TopicComment.TOPIC_REPLY_USER_NICKNAME, TopicComment.TOPIC_COMMENT_CONTENT);

        return renderJson(countResult, resultList);
    }
    
    
    @ApiOperation(value = "自己的主页的用户信息(头像,关注数,粉丝数是否关注这个用户等)")
    @RequestMapping(value = "/topic/mobile/v1/home/self/info", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeSelfInfoV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.MEMBER_ID
        );
        
        String requestUserId = body.getSystemRequestUserId();
        String memberId = body.getMemberId();
        String appId = body.getAppId();
        
        // 获取用户头像,昵称,签名,背景
        MemberHomepageView memberHomepageView = memberHomepageService.findByUserId(appId, requestUserId);
        if (memberHomepageView == null) {
        	throw new BusinessException("对不起,未找到您的信息...");
		}
        
        // TODO 我的宠物
        
        validateResponse(
        		MemberHomepageView.MEMBER_ADDRESS_ADDRESS,
        		MemberHomepageView.MEMBER_ADDRESS_AREA,
        		MemberHomepageView.MEMBER_ADDRESS_CITY,
        		MemberHomepageView.MEMBER_ADDRESS_PROVINCE,
        		MemberHomepageView.MEMBER_BACKGROUND_FILE_PATH,
        		MemberHomepageView.MEMBER_BE_FOLLOW_COUNT,
        		MemberHomepageView.MEMBER_FOLLOW_COUNT,
        		MemberHomepageView.MEMBER_HOMEPAGE_ID,
        		MemberHomepageView.MEMBER_ID,
        		MemberHomepageView.MEMBER_SEND_TOPIC_COUNT,
        		MemberHomepageView.MEMBER_SIGNATURE,
        		MemberHomepageView.PET,
        		MemberHomepageView.USER_AVATAR_FILE_PATH,
        		MemberHomepageView.USER_NICK_NAME,
        		MemberHomepageView.USER_SEX
        	);
        return renderJson(memberHomepageView);

    }
    @ApiOperation(value = "自己的主页的动态列表")
    @RequestMapping(value = "/topic/mobile/v1/self/home/topic", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> selfHomeTopicV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.SYSTEM_CREATE_TIME,
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.MEMBER_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );

        String requestUserId = body.getSystemRequestUserId();
        String memberId = body.getMemberId();
        Integer commentPageIndex = (Integer) body.get(Topic.COMMENT_PAGE_INDEX);
        Integer commentPageSize =(Integer) body.get(Topic.COMMENT_PAGE_SIZE);

        ArrayList<String> memberIdToSearchList = new ArrayList<>();
        memberIdToSearchList.add(memberId);
        Integer countResult = topicService.countByMemberIdList(body.getAppId(), memberIdToSearchList);
        List<TopicView> resultList = topicService.listByMemberIdList(body.getAppId(), memberIdToSearchList, (List<String>) body.get(Topic.EXCLUDE_TOPIC_ID_LIST), body.getSystemCreateTime(), body.getPageIndex(), body.getPageSize());
        
        
        for (TopicView topic : resultList) {
            
            //验证话题发布人是否自己
            String theSendMemberId = topic.getMemberId();
            topic.put(Topic.TOPIC_IS_SELF, theSendMemberId.equals(memberId));
            
            //取话题前3条评论
            if (Util.isNullOrEmpty(commentPageIndex) || Util.isNullOrEmpty(commentPageSize)) {
            	commentPageIndex = 1;
            	commentPageSize = 3;
			}
            List<TopicCommentView> topicCommentList = topicCommentService.listByTopicId(topic.getTopicId(), commentPageIndex, commentPageSize);
            
            //TODO 处理发送用户信息(昵称)
            //TODO 处理回复用户信息(昵称)
            
            topic.put(Topic.TOPIC_COMMENT_LIST, topicCommentList);
            
            // 是否点赞
    		TopicUserLikeView userLikeView = topicUserLikeService.findByTopicIdAndMemberId(topic.getTopicId(), memberId);
    		topic.put(Topic.TOPIC_USER_IS_LIKE, !Util.isNullOrEmpty(userLikeView));
            // 是否收藏
            TopicUserBookmarkView bookmarkView = topicUserBookmarkService.findByTopicIdAndMemberId(topic.getTopicId(), memberId);
            topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, !Util.isNullOrEmpty(bookmarkView));
            
        }
        
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.MEMBER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_FORUM_LIST,
                Topic.TOPIC_MEDIA_LIST,
                Topic.TOPIC_COMMENT_LIST,
                Topic.TOPIC_COUNT_BOOKMARK,
                Topic.TOPIC_COUNT_LIKE,
                Topic.TOPIC_COUNT_COMMENT,
                Topic.TOPIC_USER_IS_BOOKEMARK,
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                Topic.TOPIC_IS_SELF,
                User.USER_ID,
                UserAvatar.USER_AVATAR_FILE_PATH,
                UserNickName.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                BaseEntity.SYSTEM_CREATE_TIME
        	);
        validateSecondResponse(TopicView.TOPIC_MEDIA_LIST, TopicMedia.TOPIC_MEDIA_FILE_PATH, TopicMedia.TOPIC_MEDIA_SORT, TopicMedia.TOPIC_MEDIA_TYPE);
        validateSecondResponse(TopicView.TOPIC_TIP_USER_LIST, Topic.MEMBER_ID);
        validateSecondResponse(TopicView.TOPIC_FORUM_LIST, Forum.FORUM_NAME, Forum.FORUM_ID);

        return renderJson(countResult, resultList);
    }
    
    @ApiOperation(value = "话题详情页")
    @RequestMapping(value = "/topic/mobile/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_ID,
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.MEMBER_ID
        );

        String topicId = body.getTopicId();
        String requestUserId = body.getSystemRequestUserId();
        String memberId = body.getMemberId();
        TopicView topic = topicService.find(topicId);
        
        
        if (memberId.equals(topic.getMemberId())) {
            // 验证话题发布者是否自己
            topic.put(Topic.TOPIC_IS_SELF, true);
        }else {
        	//是否关注用户
        	Boolean isFollow = memberFollowService.checkIsFollow(requestUserId, topic.getSystemCreateUserId());
        	topic.put(MemberFollow.MEMBER_IS_FOLLOW, isFollow);
        }
        
        // 查找所有点赞用户(取三条数据,展示到话题详情页)
        List<TopicUserLikeView> userLikeList = topicUserLikeService.listByTopicIdHavePage(topicId, 1, 3);
        
        if (!Util.isNullOrEmpty(userLikeList)) {
            topic.put(Topic.TOPIC_USER_LIKE_LIST, userLikeList);
        }
        
        // 是否点赞
		TopicUserLikeView userLikeView = topicUserLikeService.findByTopicIdAndMemberId(topicId, memberId);
		topic.put(Topic.TOPIC_USER_IS_LIKE, !Util.isNullOrEmpty(userLikeView));
        // 是否收藏
        TopicUserBookmarkView bookmarkView = topicUserBookmarkService.findByTopicIdAndMemberId(topicId, memberId);
        topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, !Util.isNullOrEmpty(bookmarkView));
       
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_MEDIA_LIST,
                Topic.TOPIC_FORUM_LIST,
                Topic.TOPIC_COUNT_BOOKMARK,
                Topic.TOPIC_COUNT_LIKE,
                Topic.TOPIC_COUNT_COMMENT,
                Topic.TOPIC_USER_IS_BOOKEMARK,
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                BaseEntity.SYSTEM_CREATE_TIME,
                Topic.TOPIC_IS_SELF,
                Topic.TOPIC_USER,
                Topic.REQUEST_USER,
                MemberFollow.MEMBER_IS_FOLLOW,
                
                TopicView.MEMBER_ID,
                TopicView.TOPIC_MEDIA_LIST,
                TopicView.TOPIC_FORUM_LIST,
                TopicView.USER_AVATAR_FILE_PATH,
                TopicView.USER_NICKNAME
        );
        validateSecondResponse(TopicView.TOPIC_MEDIA_LIST, TopicMedia.TOPIC_MEDIA_FILE_PATH, TopicMedia.TOPIC_MEDIA_SORT, TopicMedia.TOPIC_MEDIA_TYPE);
        validateSecondResponse(TopicView.TOPIC_FORUM_LIST, Forum.FORUM_NAME, Forum.FORUM_ID);
        validateSecondResponse(Topic.TOPIC_USER_LIKE_LIST, UserAvatar.USER_AVATAR_FILE_PATH, UserNickName.USER_NICK_NAME);

        return renderJson(topic);
    }
    
    @ApiOperation(value = "动态主页列表")
    @RequestMapping(value = "/topic/mobile/v1/home/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> followListV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE,
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.SYSTEM_CREATE_TIME,
                Topic.MEMBER_ID
        );
        String requestUserId = body.getSystemRequestUserId();
        
        // TODO 
        if (Util.isNullOrEmpty(requestUserId)) {
        	return renderJson(0, null);
		}
        String requestMemberId = body.getMemberId();
        if (Util.isNullOrEmpty(requestMemberId)) {
        	return renderJson(0, null);
		}
        
        String appId = body.getAppId();
        
        // 用户关注的人的编号列表  
        List<String> followUserIdList = new ArrayList<>();
        List<MemberFollowView> memberFollowViewList = memberFollowService.listByUserId(requestUserId);
        if (memberFollowViewList != null || memberFollowViewList.size() != 0) {
        	followUserIdList = memberFollowViewList.stream().map(memberFollowView -> memberFollowView.getFollowUserId()).collect(Collectors.toList());
		}
        // 加上本人的用户编号
        followUserIdList.add(requestUserId);
        
        String userIds = JSONArray.toJSONString(followUserIdList);
        List<MemberView> memberViewList = memberRpc.listByUserIdsV1(userIds);
        
        List<String> memberIdList = new ArrayList<>();
        for (MemberView memberView : memberViewList) {
        	memberIdList.add(memberView.getMemberId());
		}
        
        Integer countResult = topicService.countByMemberIdList(appId, memberIdList);
        
        // 这个方法内部查询了点赞数,收藏数,评论数,是否收藏,是否点赞,但是用视图的话就不需要查了
        List<TopicView> resultList = topicService.listByMemberIdList(appId, memberIdList, (List<String>) body.get(Topic.EXCLUDE_TOPIC_ID_LIST), body.getSystemCreateTime(), body.getPageIndex(), body.getPageSize());
        
        if (!Util.isNullOrEmpty(resultList)) {
            // 在controller层调用其他接口处理发布话题者信息(昵称,头像,是否关注)
        	// 取出来的动态中,有发布者的头像,昵称,而且在主页的人,那肯定是关注了
//        	List<String> theFollowUserIdList = memberFollowRpc.followUserIdList(requestUserId);
        	
        	// TODO 下面的应该可以用上面列表
        	List<String> theFollowUserIdList = new ArrayList<>();
            List<MemberFollowView> memberFollowViews = memberFollowService.listByUserId(requestUserId);
            if (memberFollowViews != null || memberFollowViews.size() != 0) {
            	theFollowUserIdList = memberFollowViews.stream().map(memberFollowView -> memberFollowView.getFollowUserId()).collect(Collectors.toList());
    		}
        	String theFollowUserIdJsonList = JSONArray.toJSONString(theFollowUserIdList);
        	for (TopicView topicView : resultList) {
			
        		//话题是否自己发布
        		if (requestUserId.equals(topicView.getSystemCreateUserId())) {
        			topicView.put(Topic.TOPIC_IS_SELF, true);
				}else {
					// TODO 处理是否关注话题发布者,这里应该可以直接赋值为true,
	        		boolean isFollow = theFollowUserIdJsonList.contains(topicView.getSystemCreateUserId());
	        		topicView.put(MemberFollow.MEMBER_IS_FOLLOW, isFollow);
				}
              
        		//评论列表: 取3条
//        		List<TopicCommentView> topicCommentList = topicCommentService.listByTopicId(topicView.getTopicId(), 1, 3);
//        		topicView.put(Topic.TOPIC_COMMENT_LIST, topicCommentList);
        		
        		// 不用处理评论的人的信息,因为直接就取出来了
        		// 处理被回复人的信息
        		
        		// 不用处理各种数量,因为视图里面有了
        		
        		// 是否点赞
        		TopicUserLikeView userLikeView = topicUserLikeService.findByTopicIdAndMemberId(topicView.getTopicId(), requestMemberId);
                topicView.put(Topic.TOPIC_USER_IS_LIKE, !Util.isNullOrEmpty(userLikeView));
                // 是否收藏
                TopicUserBookmarkView bookmarkView = topicUserBookmarkService.findByTopicIdAndMemberId(topicView.getTopicId(), requestMemberId);
                topicView.put(Topic.TOPIC_USER_IS_BOOKEMARK, !Util.isNullOrEmpty(bookmarkView));
        	}
        }
        
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.MEMBER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_FORUM_LIST,
                Topic.TOPIC_MEDIA_LIST,
                Topic.TOPIC_COMMENT_LIST,
                Topic.TOPIC_COUNT_BOOKMARK,
                Topic.TOPIC_COUNT_LIKE,
                Topic.TOPIC_COUNT_COMMENT,
                Topic.TOPIC_USER_IS_BOOKEMARK,
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                Topic.TOPIC_IS_SELF,
                TopicView.USER_AVATAR_FILE_PATH,
                TopicView.USER_NICKNAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                Topic.SYSTEM_CREATE_TIME,
                
                TopicView.TOPIC_MEDIA_LIST,
                TopicView.TOPIC_TIP_USER_LIST,
                TopicView.TOPIC_FORUM_LIST
        );
        validateSecondResponse(TopicView.TOPIC_MEDIA_LIST, TopicMedia.TOPIC_MEDIA_FILE_PATH, TopicMedia.TOPIC_MEDIA_SORT, TopicMedia.TOPIC_MEDIA_TYPE);
        validateSecondResponse(TopicView.TOPIC_TIP_USER_LIST, Topic.MEMBER_ID);
        validateSecondResponse(TopicView.TOPIC_FORUM_LIST, Forum.FORUM_NAME, Forum.FORUM_ID);
        return renderJson(countResult, resultList);
    }
    
    
    
    
    
    
    
    @ApiOperation(value = "热门动态列表")
    @RequestMapping(value = "/topic/mobile/v1/hot/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> hotListV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE,
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.SYSTEM_CREATE_TIME,
                Topic.MEMBER_ID
        );
        String requestUserId = body.getSystemRequestUserId();
        
        // TODO 
        if (Util.isNullOrEmpty(requestUserId)) {
        	return renderJson(0, null);
		}
        String requestMemberId = body.getMemberId();
        if (Util.isNullOrEmpty(requestMemberId)) {
        	return renderJson(0, null);
		}
        
        String appId = body.getAppId();
        
        // 所有人的动态,按评论排序
        Integer countResult = topicService.countAllUserTopic();
        List<TopicView> resultList = topicService.hotTopicList((List<String>) body.get(Topic.EXCLUDE_TOPIC_ID_LIST), body.getSystemCreateTime(), body.getPageIndex(), body.getPageSize());
        if (!Util.isNullOrEmpty(resultList)) {
        	
        	// 获取关注id列表
        	List<String> theFollowUserIdList = new ArrayList<>();
            List<MemberFollowView> memberFollowViews = memberFollowService.listByUserId(requestUserId);
            if (memberFollowViews != null || memberFollowViews.size() != 0) {
            	theFollowUserIdList = memberFollowViews.stream().map(memberFollowView -> memberFollowView.getFollowUserId()).collect(Collectors.toList());
    		}
        	String theFollowUserIdJsonList = JSONArray.toJSONString(theFollowUserIdList);
        	for (TopicView topicView : resultList) {
			
        		//话题是否自己发布
        		if (requestUserId.equals(topicView.getSystemCreateUserId())) {
        			topicView.put(Topic.TOPIC_IS_SELF, true);
				}else {
					// TODO 处理是否关注话题发布者,这里应该有更好的方法
	        		boolean isFollow = theFollowUserIdJsonList.contains(topicView.getSystemCreateUserId());
	        		topicView.put(MemberFollow.MEMBER_IS_FOLLOW, isFollow);
				}
              
        		//评论列表: 取3条
//        		List<TopicCommentView> topicCommentList = topicCommentService.listByTopicId(topicView.getTopicId(), 1, 3);
//        		topicView.put(Topic.TOPIC_COMMENT_LIST, topicCommentList);
        		
        		// 不用处理评论的人的信息,因为直接就取出来了
        		// 处理被回复人的信息
        		
        		// 不用处理各种数量,因为视图里面有了
        		
        		// 是否点赞
        		TopicUserLikeView userLikeView = topicUserLikeService.findByTopicIdAndMemberId(topicView.getTopicId(), requestMemberId);
                topicView.put(Topic.TOPIC_USER_IS_LIKE, !Util.isNullOrEmpty(userLikeView));
                // 是否收藏
                TopicUserBookmarkView bookmarkView = topicUserBookmarkService.findByTopicIdAndMemberId(topicView.getTopicId(), requestMemberId);
                topicView.put(Topic.TOPIC_USER_IS_BOOKEMARK, !Util.isNullOrEmpty(bookmarkView));
        	}
        }
        
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.MEMBER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_FORUM_LIST,
                Topic.TOPIC_MEDIA_LIST,
                Topic.TOPIC_COMMENT_LIST,
                Topic.TOPIC_COUNT_BOOKMARK,
                Topic.TOPIC_COUNT_LIKE,
                Topic.TOPIC_COUNT_COMMENT,
                Topic.TOPIC_USER_IS_BOOKEMARK,
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                Topic.TOPIC_IS_SELF,
                TopicView.USER_AVATAR_FILE_PATH,
                TopicView.USER_NICKNAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                Topic.SYSTEM_CREATE_TIME,
                
                TopicView.TOPIC_MEDIA_LIST,
                TopicView.TOPIC_TIP_USER_LIST,
                TopicView.TOPIC_FORUM_LIST
        );
        validateSecondResponse(TopicView.TOPIC_MEDIA_LIST, TopicMedia.TOPIC_MEDIA_FILE_PATH, TopicMedia.TOPIC_MEDIA_SORT, TopicMedia.TOPIC_MEDIA_TYPE);
        validateSecondResponse(TopicView.TOPIC_TIP_USER_LIST, Topic.MEMBER_ID);
        validateSecondResponse(TopicView.TOPIC_FORUM_LIST, Forum.FORUM_NAME, Forum.FORUM_ID);
        return renderJson(countResult, resultList);
    }
    

    @ApiOperation(value = "新增话题信息")
    @RequestMapping(value = "/topic/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_SUMMARY,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.SYSTEM_REQUEST_USER_ID,
                
                TopicView.MEMBER_ID,
                TopicView.USER_AVATAR_FILE_PATH,
                TopicView.USER_NICKNAME
        );
        
        JSONArray topicMediaJsonArray = body.getJSONArray(Topic.TOPIC_MEDIA_LIST);
        if (Util.isNullOrEmpty(topicMediaJsonArray)) {
            throw new BusinessException("图片不能为空");
        }
        
        JSONArray topicForumJSONArray = body.getJSONArray(Topic.TOPIC_FORUM_LIST);
        JSONArray tipMemberIdJSONArray = body.getJSONArray(Topic.TOPIC_TIP_USER_LIST);
        
        String topicId = Util.getRandomUUID();
        String requestUserId = body.getSystemRequestUserId();
        String appId = body.getAppId();
        
        body.setTopicIsTop(false);
        body.setTopicIsRecommend(false);
        // 保存话题
        Topic result = topicService.save(body, topicId, requestUserId);
        
        boolean success = false;
        
        if (result != null) {
            
            // 保存话题多媒体
            List<TopicMedia> topicMediaList = JSONArray.parseArray(topicMediaJsonArray.toJSONString(), TopicMedia.class);
            topicMediaService.batchSave(appId, topicId, topicMediaList, requestUserId);
            
            List<TopicForum> topicForumList = new ArrayList<>();
            // 保存话题论坛
            if (!Util.isNullOrEmpty(topicForumJSONArray)) {
            	for (Object object : topicForumJSONArray) {
            		JSONObject topicForumJson = (JSONObject)object;
            		TopicForum topicForum = JSONObject.parseObject(topicForumJson.toString(), TopicForum.class);
            		
                	// 如果保存话题时,所同步到的论坛没有找到,那么跳过
                    ForumView forum = forumService.find(topicForum.getForumId());
                    if (Util.isNullOrEmpty(forum)) {
                        continue;
                    }
                    topicForumList.add(topicForum);
                }
                
                topicForumList = topicForumService.batchSave(appId, topicId, topicForumList, requestUserId);
                
            }
            
            // 保存提醒谁看
            List<TopicTip> topicTipList = new ArrayList<>();
            if (!Util.isNullOrEmpty(tipMemberIdJSONArray)) {
            	List<String> tipMemberIdList = tipMemberIdJSONArray.toJavaList(String.class);
                topicTipList = tipMemberIdList.stream().map(tipMemberId -> {
                    TopicTip topicTip = new TopicTip();
                    topicTip.setMemberId(tipMemberId);
                    return topicTip;
                }).collect(Collectors.toList());

                topicTipList = topicTipService.batchSave(appId, topicId, topicTipList, requestUserId);
            }

            // 保存到MongoDB
            
            // 话题,多媒体列表,论坛列表,提醒谁的列表
            // 保存到话题表
            TopicView topicView = JSON.parseObject(result.toJSONString(), TopicView.class);
            topicView.setTopicCountBookmark(0);
            topicView.setTopicCountComment(0);
            topicView.setTopicCountLike(0);
            topicService.save(topicView);
            
            // TODO 这种要不要在消息中处理
            // 更新会员主页视图的动态发布数
            MemberHomepageView memberHomepageView = memberHomepageService.findByUserId(appId, requestUserId);
            Integer memberSendTopicCount = memberHomepageView.getMemberSendTopicCount();
            memberHomepageView.setMemberSendTopicCount(memberSendTopicCount + 1);
            Integer memberHomepageSystemVersion = memberHomepageView.getSystemVersion();
            memberHomepageView.setSystemVersion(memberHomepageSystemVersion + 1);
            memberHomepageService.update(memberHomepageView);
            
            // 在话题论坛表中根据论坛id保存topic
            if (!Util.isNullOrEmpty(topicForumList)) {
            	for (TopicForum topicForum : topicForumList) {
    				// topicForum.setSystemRequestUserId(requestUserId);
    				
    				TopicForumView topicForumView = JSON.parseObject(topicForum.toJSONString(), TopicForumView.class);
    				topicForumService.save(topicForumView);
    			}
			}
            
            // 保存话题提醒表
            if (!Util.isNullOrEmpty(topicTipList)) {
                 for (TopicTip topicTip : topicTipList) {
                     TopicTipView topicTipView = JSON.parseObject(topicTip.toJSONString(), TopicTipView.class);
                     topicTipService.save(topicTipView);
				}
             }
            
            // sendMessage(body, TopicRouter.TOPIC_V1_SAVE, appId, userId);
            success = true;
        }
        return renderJson(success);
    }

    @ApiOperation(value = "删除话题信息")
    @RequestMapping(value = "/topic/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
    	Topic body = getEntry(Topic.class);
        validateRequest(
                body,
                Topic.TOPIC_ID,
                Topic.APP_ID
        );
        String topicId = body.getTopicId();
        String systemRequestUserId = body.getSystemRequestUserId();

        //删除话题信息
        TopicView topic = topicService.find(topicId);

        Topic result = topicService.deleteByTopicId(body.getAppId(), topicId, systemRequestUserId, topic.getSystemVersion());
        boolean success = false;
        
        if (result != null) {
        	
        	//删除话题论坛关联
        	List<TopicForumView> topicForumViews = topicForumService.listByTopicId(topicId);
        	for (TopicForumView topicForumView : topicForumViews) {
        		topicForumService.delete(topicForumView);
			}
        	
            //删除话题多媒体,其实这个按理说也不用删除
        	List<TopicMediaView> topicMediaViews = topicMediaService.listByTopicId(topicId);
        	for (TopicMediaView topicMediaView : topicMediaViews) {
        		topicMediaService.delete(topicMediaView);
			}
            //删除话题评论,其实这个话题删掉了,就不会有评论会显示了,所以就不用删了
            //删除话题收藏,这个也不用删
            //删除话题点赞 ,这个也不用删
            //删除取消收藏 ,这个也不用删
            //删除话题取消点赞,这个也不用删
        	
        	//最后删除主业务,
        	topicService.delete(topic);
        	
        	//sendMessage(result, TopicRouter.TOPIC_V1_DELETE, body.getAppId(), systemRequestUserId);
        	success = true;
		}
        
        return renderJson(success);
    }

}