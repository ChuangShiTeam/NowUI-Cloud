package com.nowui.cloud.sns.forum.controller.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 论坛信息移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛信息", description = "论坛信息移动端接口管理")
@RestController
public class ForumMobileController extends BaseController {
	
	 @Autowired
	 private ForumService forumService;
	 
	 @Autowired
	 private TopicForumService topicForumService;
	 
	 @Autowired
	 private TopicService topicService;
	 
	 @Autowired
	 private ForumUserFollowService forumUserFollowService;
	 
	 @Autowired
	 private ForumUserUnfollowService forumUserUnfollowService;
	 
	 @Autowired
     private FileRpc fileRpc;
     
     @Autowired
     private MemberRpc memberRpc;

	 @ApiOperation(value = "新增论坛信息")
	 @RequestMapping(value = "/forum/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Map<String, Object> saveV1(@RequestBody Forum body) {
	     validateRequest(
	             body,
	             Forum.APP_ID,
	             Forum.FORUM_MEDIA,
	             Forum.FORUM_MEDIA_TYPE,
	             Forum.FORUM_NAME,
	             Forum.FORUM_DESCRIPTION,
	             Forum.SYSTEM_REQUEST_USER_ID
         );
	     
	     // 验证论坛名称的唯一性
	     Boolean isRepeat = forumService.checkName(body.getAppId(), body.getForumName());
	     if (isRepeat) {
	         throw new RuntimeException("论坛名称已注册");
	     }
	     body.setForumBackgroundMedia(body.getForumMedia());
	     body.setForumBackgroundMediaType(body.getForumMediaType());
	     body.setForumModerator(body.getSystemRequestUserId());
	     body.setForumSort(0);
	     body.setForumIsTop(false);
	     body.setForumIsActive(true);
	     body.setForumIsRecommend(false);
	     body.setForumAuditStatus(ForumAuditStatus.AUDIT_PASS.getKey());
	     body.setForumLocation("");
	     body.setForumAuditContent("");
	     
	     String forumId = Util.getRandomUUID();
	     String userId = body.getSystemRequestUserId();
	     
	     Boolean result = forumService.save(body, forumId, userId);
	     
	     if (result) {

	         // 圈主默认关注论坛
	         ForumUserFollow forumUserFollow = new ForumUserFollow();
	         forumUserFollow.setAppId(body.getAppId());
	         forumUserFollow.setForumId(forumId);
	         forumUserFollow.setUserId(userId);
	         
	         forumUserFollowService.save(forumUserFollow, Util.getRandomUUID(), userId);
	     }
        
	     return renderJson(result);
    }
	 
	 
	 
	@ApiOperation(value = "论坛信息(用于修改论坛信息的页面)")
    @RequestMapping(value = "/forum/mobile/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE,
                Forum.SYSTEM_REQUEST_USER_ID
        );
        // forum包含了论坛简介,论坛名称,论坛头像
        Forum forum = forumService.find(body.getForumId());
        // 处理论坛头像
        File file = fileRpc.findV1(forum.getForumMedia());
    	file.keep(File.FILE_ID, File.FILE_PATH);
    	forum.put(Forum.FORUM_MEDIA, file);
        
        String userId = forum.getForumModerator();
        // 根据forumModerator(userId)查询版主信息:去会员表查找--昵称,个人签名,头像,
        Member moderator = memberRpc.nickNameAndAvatarAndSignatureFind(userId);
        forum.put(Forum.FORUM_MODERATOR, moderator);

        //判断请求用户是否是版主
        if (body.getSystemRequestUserId().equals(userId)) {
        	forum.put(Forum.FORUM_USER_IS_MODERATOR, true);
		}else {
			forum.put(Forum.FORUM_USER_IS_MODERATOR, false);
		}
        
        //判断请求用户是否加入这个圈子
        List<ForumUserFollow> requestUserFollows = forumUserFollowService.listByUserId(body.getSystemRequestUserId());
        //判断请求传来的 论坛id 有没有在 requestUserFollows 中
        if (!Util.isNullOrEmpty(requestUserFollows)) {
        	for (ForumUserFollow forumUserFollow : requestUserFollows) {
    			if (body.getForumId().equals(forumUserFollow.getForumId())) {
    				forum.put(Forum.FORUM_REQUEST_USER_IS_FOLLOW, true);
    				break;
    			}else {
    				forum.put(Forum.FORUM_REQUEST_USER_IS_FOLLOW, false);
				}
    		}
		}
        
        
        //根据论坛编号去forum_user_follow_map表查找此论坛的userId,然后查找用户头像,昵称,只返回前6个的头像和userId.
        List<ForumUserFollow> forumUserFollows = 
        		forumUserFollowService
        		.listForAdmin(body.getAppId()
        				, null
        				, body.getForumId()
        				, body.getPageIndex()
        				, body.getPageSize()
        		);
        
        
        //处理关注论坛列表的昵称,头像
        String userIds = Util.beanToFieldString(forumUserFollows, ForumUserFollow.USER_ID);
        List<Member> nickNameAndAvatarList = memberRpc.nickNameAndAvatarListV1(userIds);
        forum.put(Forum.FORUM_USER_FOLLOW_LIST, nickNameAndAvatarList);
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_USER_IS_MODERATOR,
                Forum.FORUM_REQUEST_USER_IS_FOLLOW,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_USER_FOLLOW_LIST
        );

        return renderJson(forum);
    }
	
	@ApiOperation(value = "论坛中所有用户信息")
    @RequestMapping(value = "/forum/mobile/v1/findAll", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findAllV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );

        Integer countResult = forumUserFollowService.countForAdmin(body.getAppId(), null, body.getForumId());
        //得到论坛关注列表
        List<ForumUserFollow> forumUserFollows = forumUserFollowService
        		.listForAdmin(
        				body.getAppId()
        				, null
        				, body.getForumId()
        				, body.getPageIndex()
        				, body.getPageSize()
        		);
        //处理关注论坛的用户信息(昵称,头像,是否关注)
        String userIds = Util.beanToFieldString(forumUserFollows, ForumUserFollow.USER_ID);
        List<Member> nickNameAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());
        

        return renderJson(countResult, nickNameAndAvatarAndIsFollowList);
    }
	
	@ApiOperation(value = "论坛推荐列表")
    @RequestMapping(value = "/forum/mobile/v1/recommend/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> recommendListV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.PAGE_SIZE,
                Forum.SYSTEM_REQUEST_USER_ID
        );
        
        // 查询编辑推荐的且用户没有关注过的论坛
        List<Forum> recommendList = forumService.getRandomRecommendAndNotFollowListByUserId(body.getAppId(), body.getSystemRequestUserId(), body.getPageSize());

        if (recommendList.size() < body.getPageSize()) {
            // 推荐的数量不满足则从最新的圈子里面增加推荐
            int pageSize = body.getPageSize() - recommendList.size();
            
            // 查询用户没有关注过的最新论坛
            List<Forum> latestList = forumService.getLatestAndNotFollowListByUserId(body.getAppId(), body.getSystemRequestUserId(), 0, pageSize);
            recommendList.addAll(latestList);
        }
        
        //处理论坛头像
        String fileIds = Util.beanToFieldString(recommendList, Forum.FORUM_MEDIA);
        List<File> fileList = fileRpc.findsV1(fileIds);
        
        recommendList = Util.beanReplaceField(recommendList, Forum.FORUM_MEDIA, fileList, File.FILE_ID, File.FILE_PATH);

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION
        );
        
        return renderJson(recommendList);
    }

	@ApiOperation(value = "更新论坛头像")
	@RequestMapping(value = "/forum/mobile/v1/update/media", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateMediaV1(@RequestBody Forum body) {
	   validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_MEDIA
       );
	    Forum forum = forumService.find(body.getForumId());
	    if (!body.getSystemRequestUserId().equals(forum.getForumModerator())) {
	    return renderJson(false);
		}
	    
	   //不清楚是否单独写一个更改背景图片的接口
	   body.setForumBackgroundMedia(body.getForumMedia());

       Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), forum.getSystemVersion());

       return renderJson(result);
    }

	@ApiOperation(value = "更新论坛名称")
	@RequestMapping(value = "/forum/mobile/v1/update/name", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateNameV1(@RequestBody Forum body) {
	    validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_NAME
       );
	    
       Forum forum = forumService.find(body.getForumId());
       if (!body.getSystemRequestUserId().equals(forum.getForumModerator())) {
       	return renderJson(false);
		}
       
       Integer systemVersion = forum.getSystemVersion();
       
       Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), systemVersion);

       return renderJson(result);
    }

	@ApiOperation(value = "更新论坛简介")
	@RequestMapping(value = "/forum/mobile/v1/update/description", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateDescriptionV1(@RequestBody Forum body) {
	    validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_DESCRIPTION
       );
	    Forum forum = forumService.find(body.getForumId());
        if (!body.getSystemRequestUserId().equals(forum.getForumModerator())) {
        	return renderJson(false);
		}

       Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), forum.getSystemVersion());

       return renderJson(result);
    }

	@ApiOperation(value = "删除论坛")
    @RequestMapping(value = "/forum/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID
        );
        
        Forum forum = forumService.find(body.getForumId());
        if (!body.getSystemRequestUserId().equals(forum.getForumModerator())) {
        	return renderJson(false);
		}
        
        //先从论坛信息表删除
        Boolean result = forumService.delete(body.getForumId(), body.getSystemRequestUserId(), forum.getSystemVersion());
        if (result == false) {
    		return renderJson(result);
		}
        
        
        //再从论坛话题关联表中逻辑删除所有的有论坛编号的记录
    	topicForumService.deleteByForumId(body.getAppId(), body.getForumId(), body.getSystemRequestUserId());
    	
    	//从论坛关注表中删除有forumId的记录
    	boolean delUserFollow = forumUserFollowService.deleteByForumId(body.getAppId(), body.getForumId(), body.getSystemRequestUserId());
    	if (delUserFollow == false) {
    		return renderJson(delUserFollow);
		}
    	
    	
    	//从论坛取消关注表删除有forumId的记录
    	boolean delUnFollowResult = forumUserUnfollowService.deleteByForumId(body.getAppId(), body.getForumId(), body.getSystemRequestUserId());
    	if (delUnFollowResult == false) {
    		return renderJson(delUnFollowResult);
		}
    	
    	 
        return renderJson(result);
    }
	
    @ApiOperation(value = "论坛信息搜索列表")
    @RequestMapping(value = "/forum/mobile/v1/search/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchListV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_NAME,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE  //前端传值
        );

        Integer resultTotal = forumService.countForMobile(body.getAppId() , null, null, null, null, body.getForumName(), null, null, null, null, null, null, null, null, null);
        List<Forum> resultList = forumService.listForMobile(body.getAppId(), null, null, null, null, body.getForumName(), null, null, null, null, null, null, null, null, null, body.getPageIndex(), body.getPageSize());

      //处理论坛头像
        for (Forum forum : resultList) {
        	File file = fileRpc.findV1(forum.getForumMedia());
        	file.keep(File.FILE_ID, File.FILE_PATH);
            forum.put(Forum.FORUM_MEDIA, file);
		}
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR
        );

        return renderJson(resultTotal, resultList);
    }
    
    @ApiOperation(value = "论坛主页信息(论坛主页)")
    @RequestMapping(value = "/forum/mobile/v1/home", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID
        );
        
        Forum forum = forumService.find(body.getForumId());
        
        // 处理论坛图片
        File file = fileRpc.findV1(forum.getForumMedia());
    	file.keep(File.FILE_ID, File.FILE_PATH);
    	forum.put(Forum.FORUM_MEDIA, file);
    	
    	// 处理论坛背景图片
        File backgroundfile = fileRpc.findV1(forum.getForumBackgroundMedia());
        backgroundfile.keep(File.FILE_ID, File.FILE_PATH);
    	forum.put(Forum.FORUM_BACKGROUND_MEDIA, backgroundfile);

    	// 版主昵称、头像、签名
        Member forumModerator = memberRpc.nickNameAndAvatarAndSignatureFind(forum.getForumModerator());
        forum.put(Forum.FORUM_MODERATOR, forumModerator);

        // 根据systemRequestUserId查询是否加入了这个论坛
        ForumUserFollow forumUserFollow = forumUserFollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), body.getForumId());
        forum.put(Forum.MEMBER_IS_FOLLOW_FORUM, forumUserFollow != null);
        
        // 统计加入论坛人数
        Integer forumUserFollowCount = forumUserFollowService.countByForumId(body.getAppId(), body.getForumId());
        forum.put(Forum.FORUM_USER_FOLLOW_COUNT, forumUserFollowCount);
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.MEMBER_IS_FOLLOW_FORUM,
                Forum.FORUM_USER_FOLLOW_COUNT
        );

        return renderJson(forum);
    }
    
    
    @ApiOperation(value = "论坛详情的主页的动态列表")
    @RequestMapping(value = "/forum/mobile/v1/home/topic/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeTopicV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );
        String forumId = body.getForumId();
        Integer pageIndex = body.getPageIndex();
        Integer pageSize = body.getPageSize();
        String userId = body.getSystemRequestUserId();
        
        
        // 1,统计所有topic数量  根据forumId
        Integer countResult = topicForumService.countByForumId(forumId);
        
        // 2,获得topic的分页列表
        List<TopicForum> topicForumList = topicForumService.listByForumId(forumId, pageIndex, pageSize);
        
        // 3,从TopicForum获取topicIdList, 
        List<String> topicIdList = Util.beanToField(topicForumList, TopicForum.TOPIC_ID);
        
        // 4,根据topicIdList查找topicList
//        List<Topic> topicList = topicService.listByTopicIdList(topicIdList, pageIndex, pageSize);
        List<Topic> topicList = topicService.listDetailByTopicIdList(userId, topicIdList, pageIndex, pageSize);
        
        // 在controller层调用其他接口处理发布话题者信息(昵称,头像,是否关注)
        String userIds = Util.beanToFieldString(topicList, Topic.USER_ID);
        List<Member> nickAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());
        
        topicList = Util.beanAddField(
        		topicList, 
        		Topic.USER_ID, 
        		User.USER_ID, 
        		nickAndAvatarAndIsFollowList, 
        		User.USER_ID,
        		UserAvatar.USER_AVATAR,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW
    	);
        
        
        // 图片多媒体
        for (Topic topic : topicList) {
            List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

            String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA);
            List<File> fileList = fileRpc.findsV1(fileIds);

            topicMediaList = Util.beanReplaceField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_ID, File.FILE_PATH);
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
        }
        
        
        validateResponse(
        		Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
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
        		UserAvatar.USER_AVATAR,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW,
        		BaseEntity.SYSTEM_CREATE_TIME
        );

        return renderJson(countResult, topicList);
    }

}