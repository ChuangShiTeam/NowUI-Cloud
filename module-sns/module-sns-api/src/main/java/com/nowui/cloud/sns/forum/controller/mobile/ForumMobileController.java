package com.nowui.cloud.sns.forum.controller.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumBackgroundMedia;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.service.ForumBackgroundMediaService;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.view.ForumBackgroundMediaView;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
import com.nowui.cloud.sns.forum.view.ForumView;
import com.nowui.cloud.sns.member.entity.MemberFollow;
import com.nowui.cloud.sns.member.service.MemberFollowService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.view.TopicForumView;
import com.nowui.cloud.sns.topic.view.TopicUserBookmarkView;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicView;
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
	 private TopicUserLikeService topicUserLikeService;
	 
	 @Autowired
	 private TopicUserBookmarkService topicUserBookmarkService;
	 
	 @Autowired
	 private ForumUserFollowService forumUserFollowService;
	 
	 @Autowired
	 private ForumUserUnfollowService forumUserUnfollowService;
	 
	 @Autowired
	 private MemberFollowService memberFollowService;
	 
	 @Autowired
	 private ForumBackgroundMediaService forumBackgroundMediaService;
	 
     @Autowired
     private MemberRpc memberRpc;
     
	 @ApiOperation(value = "新增论坛信息")
	 @RequestMapping(value = "/forum/mobile/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Map<String, Object> saveV1() {
		 Forum body = getEntry(Forum.class);
	     validateRequest(
	             body,
	             Forum.APP_ID,
	             Forum.FORUM_MEDIA_FILE_PATH,
	             Forum.FORUM_MEDIA_TYPE,
	             Forum.FORUM_NAME,
	             Forum.FORUM_DESCRIPTION,
	             
	             Forum.FORUM_MODERATOR_MEMBER_ID,
	             Forum.SYSTEM_REQUEST_USER_ID,
	             Forum.USER_AVATAR_FILE_PATH,
	             Forum.USER_NICKNAME,
	             
	             Forum.FORUM_BACKGROUND_MEDIA_LIST
         );
	     String appId = body.getAppId();
	     String requestUserId = body.getSystemRequestUserId();
	     String forumModeratormemberId = body.getForumModeratorMemberId();
	     
	     String forumId = Util.getRandomUUID();
	     String forumUserFollowId = Util.getRandomUUID();
	     
	     String forumModeratorUserAvatar = body.getUserAvatarFilePath();
	     String forumModeratorUserNickName = body.getUserNickName();
	     String forumModeratorMemberSignature = body.getMemberSignature();
	     
	     // 验证论坛名称的唯一性
	     Boolean isRepeat = forumService.checkName(appId, body.getForumName());
	     if (isRepeat) {
	         throw new BusinessException("论坛名称已注册");
	     }
	     body.setForumModeratorUserId(requestUserId);
	     body.setForumModeratorMemberId(forumModeratormemberId);
	     body.setForumSort(0);
	     body.setForumIsTop(false);
	     body.setForumIsActive(true);
	     body.setForumTopLevel(0);
	     body.setForumIsRecommend(true);
	     body.setForumAuditStatus(ForumAuditStatus.AUDIT_PASS.getKey());
	     body.setForumLocation("");
	     body.setForumAuditContent("");
	     
	     Forum result = forumService.save(body, forumId, requestUserId);
	     
	     Boolean success = false;
	     
	     if (result != null) {

	         // 圈主默认关注论坛
	         ForumUserFollow forumUserFollow = new ForumUserFollow();
	         forumUserFollow.setAppId(appId);
	         forumUserFollow.setForumId(forumId);
	         forumUserFollow.setMemberId(forumModeratormemberId);
	         forumUserFollow.setForumUserFollowIsTop(false);
	
	         ForumUserFollow forumUserFollowResult = forumUserFollowService.save(forumUserFollow, forumUserFollowId, requestUserId);
	    	
	         // 遍历保存圈子默认背景图片list
	         JSONArray forumBackgorundMediaArray = body.getForumBackgorundMediaList();
	         List<ForumBackgroundMedia> forumBackgorundMediaList = JSONArray.parseArray(forumBackgorundMediaArray.toJSONString(), ForumBackgroundMedia.class);
	         if (!Util.isNullOrEmpty(forumBackgorundMediaList)) {
	        	 for (ForumBackgroundMedia forumBackgroundMedia : forumBackgorundMediaList) {
		        	 forumBackgroundMedia.setAppId(appId);
		        	 forumBackgroundMedia.setForumId(forumId);
		        	 
		        	 forumBackgroundMediaService.save(forumBackgroundMedia, Util.getRandomUUID(), requestUserId);
		         }
			}
	         
	         // mongdb保存论坛
	         ForumView forumView = new ForumView();
	         forumView.putAll(result);
             forumService.save(forumView);
             
             // mongdb保存论坛关注表
             ForumUserFollowView forumUserFollowView= new ForumUserFollowView();
             forumUserFollowView.putAll(forumUserFollowResult);
             
             forumUserFollowView.setForumModerator(forumModeratormemberId);
             forumUserFollowView.setUserAvatarFilePath(forumModeratorUserAvatar);
             forumUserFollowView.setUserNickName(forumModeratorUserNickName);
             forumUserFollowView.setMemberSignature(forumModeratorMemberSignature);
             forumUserFollowView.setForumUserFollowIsTop(false);
             
             forumUserFollowService.save(forumUserFollowView);

        	 success = true;
	     }
        
	     return renderJson(success);
    }
	
	@ApiOperation(value = "查询论坛信息")
    @RequestMapping(value = "/forum/mobile/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
		Forum body = getEntry(Forum.class);
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE,
                Forum.SYSTEM_REQUEST_USER_ID
        );
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String requestMemberId = member.getMemberId();
        
        String appId = body.getAppId();
        
        // forum包含了论坛简介,论坛名称,论坛头像
        ForumView forum = forumService.find(body.getForumId());
        
        String memberId = forum.getForumModeratorMemberId();

        //判断请求用户是否是版主
        if (requestMemberId.equals(memberId)) {
        	forum.put(Forum.FORUM_USER_IS_MODERATOR, true);
		} else {
			forum.put(Forum.FORUM_USER_IS_MODERATOR, false);
		}
        
        //判断请求用户是否加入这个圈子
        List<ForumUserFollowView> requestUserFollows = forumUserFollowService.listByMemberId(requestMemberId);
        //判断请求传来的 论坛id 有没有在 requestUserFollows 中
        if (!Util.isNullOrEmpty(requestUserFollows)) {
        	boolean isSelf = false;
        	for (ForumUserFollowView forumUserFollow : requestUserFollows) {
    			if (body.getForumId().equals(forumUserFollow.getForumId())) {
    				forum.put(Forum.FORUM_REQUEST_USER_IS_FOLLOW, true);
    				break;
    			}else {
    				forum.put(Forum.FORUM_REQUEST_USER_IS_FOLLOW, false);
				}
    			
    			// 判断会员是不是请求本人
    			if (!isSelf) {
    				isSelf = forumUserFollow.getSystemCreateUserId().equals(requestUserId);
        			member.put(Member.MEMBER_IS_SELF, isSelf);
				}
    		}
		}
        
        // 根据论坛编号去forum_user_follow_map表查找此论坛的userId,然后查找用户头像,昵称,只返回前6个的头像和userId.
        List<ForumUserFollowView> forumUserFollows = 
    		forumUserFollowService.listByforumId(appId, body.getForumId(), body.getPageIndex(), body.getPageSize());
        
        forum.put(Forum.FORUM_USER_FOLLOW_LIST, forumUserFollows);
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_FILE_PATH,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_USER_IS_MODERATOR,
                Forum.FORUM_REQUEST_USER_IS_FOLLOW,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR_MEMBER_ID,
                Forum.FORUM_USER_FOLLOW_LIST,
                Member.MEMBER_IS_SELF,
                
                Forum.USER_AVATAR_FILE_PATH,
                Forum.USER_NICKNAME,
                Forum.MEMBER_SIGNATURE,
                Forum.FORUM_BACKGROUND_MEDIA_LIST
        );

        validateSecondResponse(Forum.FORUM_USER_FOLLOW_LIST, ForumUserFollowView.USER_AVATAR_FILE_PATH, ForumUserFollowView.MEMBER_ID);
        validateSecondResponse(Forum.FORUM_BACKGROUND_MEDIA_LIST, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_ID, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_PATH, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_SORT, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_TYPE);
        
        return renderJson(forum);
    }
	
	@ApiOperation(value = "论坛中所有用户信息")
    @RequestMapping(value = "/forum/mobile/v1/findAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findAllV1() {
		Forum body = getEntry(Forum.class);
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );
        
        String appId = body.getAppId();
        String forumId = body.getForumId();
        Integer pageIndex = body.getPageIndex();
        Integer pageSize = body.getPageSize();

        Integer countResult = forumUserFollowService.countByForumId(appId, forumId);
        //得到论坛关注列表
        List<ForumUserFollowView> forumUserFollows = forumUserFollowService.listByforumId(appId, forumId, pageIndex, pageSize);
        
        // TODO 处理关注论坛的用户信息(昵称,头像,是否关注)
//        String userIds = Util.viewBeanToFieldString(forumUserFollows, ForumUserFollow.USER_ID);
//        List<Member> nickNameAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());

//        return renderJson(countResult, nickNameAndAvatarAndIsFollowList);
        return renderJson(countResult, forumUserFollows);
    }
	
	@ApiOperation(value = "论坛推荐列表")
    @RequestMapping(value = "/forum/mobile/v1/recommend/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> recommendListV1() {
		Forum body = getEntry(Forum.class);
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.PAGE_SIZE,
                Forum.SYSTEM_REQUEST_USER_ID,
                Forum.REQUEST_MEMBER_ID
        );
        
        String requestUserId = body.getSystemRequestUserId();
        String memberId = body.getRequestMemberId();
        
        // TODO 这个查的mysql 查询编辑推荐的且用户没有关注过的论坛
        List<ForumView> recommendList = forumService.getRandomRecommendAndNotFollowListByMemberId(body.getAppId(), memberId, body.getPageSize());

        if (recommendList.size() < body.getPageSize()) {
            // 推荐的数量不满足则从最新的圈子里面增加推荐
            int pageSize = body.getPageSize() - recommendList.size();
            
            // 查询用户没有关注过的最新论坛
            List<ForumView> latestList = forumService.getLatestAndNotFollowListByMemberId(body.getAppId(), memberId, 0, pageSize);
            recommendList.addAll(latestList);
        }

        validateResponse(
                ForumView.FORUM_ID,
                ForumView.FORUM_MEDIA_FILE_PATH,
                ForumView.FORUM_NAME,
                ForumView.FORUM_DESCRIPTION
        );
        
        return renderJson(recommendList);
    }

	@ApiOperation(value = "更新论坛头像")
	@RequestMapping(value = "/forum/mobile/v1/update/media", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateMediaV1() {
	   Forum body = getEntry(Forum.class);
	   validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_MEDIA_FILE_PATH
       );
	   String requestUserId = body.getSystemRequestUserId();
	   MemberView member = memberRpc.findByUserIdV1(requestUserId);
	   String memberId = member.getMemberId();
	   
	    ForumView forum = forumService.find(body.getForumId());
	    if (!forum.getForumModeratorMemberId().equals(memberId)) {
	    	return renderJson(false);
		}
	    
	   //不清楚是否单独写一个更改背景图片的接口
//	   body.setForumBackgroundMediaFilePath(body.getForumMediaFilePath());

	   Forum result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), forum.getSystemVersion());
	   
	   Boolean success = false;

       if (result != null) {
    	   
    	   ForumView forumView = JSON.parseObject(result.toJSONString(), ForumView.class);
    	   
    	   forumService.update(forumView);

           success = true;
       }

       return renderJson(success);
    }

	@ApiOperation(value = "更新论坛名称")
	@RequestMapping(value = "/forum/mobile/v1/update/name", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateNameV1() {
		Forum body = getEntry(Forum.class);
	    validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_NAME
       );
	   
	   String requestUserId = body.getSystemRequestUserId();
	   MemberView member = memberRpc.findByUserIdV1(requestUserId);
	   String memberId = member.getMemberId();
	   String appId = body.getAppId();
	   String forumId = body.getForumId();
       ForumView forum = forumService.find(forumId);
       String forumName = forum.getForumName();
       
       
       if (!memberId.equals(forum.getForumModeratorMemberId())) {
       		return renderJson(false);
		}
       
       Integer systemVersion = forum.getSystemVersion();
       
       Forum result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), systemVersion);
       Boolean success = false;

       if (result != null) {
    	   
    	   // TODO 在topic表中的topicforumlist中用到了forumName
    	   // 有两个修改方案:
    	   // 1,查询topic时,遍历topicforumlist,得到forumName,并返回(可以对论坛信息表使用缓存)
    	   // 2,使用消息,查找话题论坛表,找到关联到的话题列表,遍历修改论坛名称(可以对话题论坛表使用缓存)
       
    	   // TODO 这里的逻辑是不是应该放到监听消息那里
    	   List<TopicForumView> topicForumViews = topicForumService.listByForumId(appId, forumId);
           for (TopicForumView topicForumView : topicForumViews) {
				JSONObject topicInfo = topicForumView.getTopicInfo();
				// 从topicInfo中取出topicMediaList 数组
				List<JSONObject> topicForumList = (List<JSONObject>) topicInfo.get(Topic.TOPIC_FORUM_LIST);
				for (JSONObject theTopicForum : topicForumList) {
					// 取到每个对象的论坛名称
					String theForumName = (String) theTopicForum.get(Forum.FORUM_NAME);
					if (forumName.equals(theForumName)) {
						theTopicForum.put(Forum.FORUM_NAME, forumName);
						break;
					}
				}
	            topicForumService.update(topicForumView);
			}
    	   
    	   ForumView forumView = JSON.parseObject(result.toJSONString(), ForumView.class);
    	   forumService.update(forumView);

           success = true;
       }

       return renderJson(success);
    }

	@ApiOperation(value = "更新论坛简介")
	@RequestMapping(value = "/forum/mobile/v1/update/description", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateDescriptionV1() {
		Forum body = getEntry(Forum.class);
	    validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_DESCRIPTION
       );
	    
	   String requestUserId = body.getSystemRequestUserId();
	   MemberView member = memberRpc.findByUserIdV1(requestUserId);
	   String memberId = member.getMemberId();
	    
       ForumView forum = forumService.find(body.getForumId());
       if (!memberId.equals(forum.getForumModeratorMemberId())) {
       		return renderJson(false);
		}
        
        Forum result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), forum.getSystemVersion());
        Boolean success = false;

        if (result != null) {
            
            ForumView forumView = JSON.parseObject(result.toJSONString(), ForumView.class);
     	    forumService.update(forumView);

            success = true;
        }

        return renderJson(success);
    }
	

	@ApiOperation(value = "删除论坛")
    @RequestMapping(value = "/forum/mobile/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
		Forum body = getEntry(Forum.class);
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID
        );
        
        String requestUserId = body.getSystemRequestUserId();
 	    MemberView member = memberRpc.findByUserIdV1(requestUserId);
 	    String memberId = member.getMemberId();
 	    String appId = body.getAppId();
 	    String forumId = body.getForumId();
 	    
        ForumView forum = forumService.find(forumId);
        String forumName = forum.getForumName();
        if (!memberId.equals(forum.getForumModeratorMemberId())) {
        		return renderJson(false);
        }
        
        //先从论坛信息表删除
        Forum result = forumService.delete(forumId, requestUserId, forum.getSystemVersion());
        
        Boolean success = false;
        
        if (result != null) {
        	//再从论坛话题关联表中逻辑删除所有的有论坛编号的记录
        	topicForumService.deleteByForumId(appId, forumId, requestUserId);
        	
        	//从论坛关注表中删除有forumId的记录
        	forumUserFollowService.deleteByForumId(appId, forumId, requestUserId);
        	
        	//从论坛取消关注表删除有forumId的记录
        	forumUserUnfollowService.deleteByForumId(appId, forumId, requestUserId);
        	
        	
        	// TODO 操作MongoDB,去看mongodb文档里有哪些存有论坛信息的,在这里删除掉
        	
        	// TODO 在topic表中的topicforumlist中用到了forumName
      	    // 有两个删除方案:
      	    // 1,查询topic时,遍历topicforumlist,没有在话题论坛表中找到论坛就删除(可以对论坛信息表使用缓存)
      	    // 2,使用消息(或者不用),查找话题论坛表,找到关联到的话题列表(这个要放到主业务删除逻辑的前面,是有原因的),遍历删除同步到的论坛(可以对话题论坛表使用缓存)
        	
        	// 用第2个
            List<TopicForumView> topicForumViews = topicForumService.listByForumId(appId, forumId);
            for (TopicForumView topicForumView : topicForumViews) {
				JSONObject topicInfo = topicForumView.getTopicInfo();
				// 从topicInfo中取出topicMediaList 数组
				List<JSONObject> topicForumList = (List<JSONObject>) topicInfo.get(Topic.TOPIC_FORUM_LIST);
				for (JSONObject theTopicForum : topicForumList) {
					// 取到每个对象的论坛名称
					String theForumName = (String) theTopicForum.get(Forum.FORUM_NAME);
					if (forumName.equals(theForumName)) {
						topicForumList.remove(theTopicForum);
						break;
					}
				}
	            topicForumService.update(topicForumView);
			}
        	
        	//TODO 这里逻辑有问题
        	ForumView forumView = JSON.parseObject(result.toJSONString(), ForumView.class);
      	    //TODO 这里要有删除MongoDB主业务的删除方法,Mark
        	forumService.delete(forumView);

        	success = true;
		}
    	 
        return renderJson(success);
    }
	
    @ApiOperation(value = "论坛信息搜索列表")
    @RequestMapping(value = "/forum/mobile/v1/search/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchListV1() {
    	Forum body = getEntry(Forum.class);
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_NAME,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );
        // TODO 这个接口好像没用??
        Integer resultTotal = forumService.countSearchForMobile(body.getAppId(), body.getForumName());
        List<Forum> resultList = forumService.searchForMobile(body.getAppId(), body.getForumName(), body.getPageIndex(), body.getPageSize());

        if (!Util.isNullOrEmpty(resultList)) {
//            String fileIds = Util.beanToFieldString(resultList, Forum.FORUM_MEDIA);
//            List<File> fileList = fileRpc.findsV1(fileIds);
//            resultList = Util.beanReplaceField(resultList, Forum.FORUM_MEDIA, fileList, File.FILE_PATH);
//            
//            String userIds = Util.beanToFieldString(resultList, Forum.FORUM_MODERATOR);
//            List<Member> memberList = memberRpc.nickNameAndAvatarListV1(userIds);
//            resultList = Util.beanReplaceField(resultList, Forum.FORUM_MODERATOR, Member.USER_ID, memberList, Member.USER_ID, UserNickName.USER_NICK_NAME, UserAvatar.USER_AVATAR);

            
            for (Forum forum : resultList) {
                // 论坛当日话题最新数量
                Integer count = topicForumService.countTodayByForumId(forum.getForumId());
                forum.put(Forum.FORUM_TODAY_TOPIC_COUNT, count);
            }
        }
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_FILE_PATH,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR_MEMBER_ID,
                Forum.FORUM_TODAY_TOPIC_COUNT
        );

        return renderJson(resultTotal, resultList);
    }
    
    @ApiOperation(value = "论坛主页信息(论坛主页)")
    @RequestMapping(value = "/forum/mobile/v1/home", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeV1() {
    	Forum body = getEntry(Forum.class);
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.REQUEST_MEMBER_ID
        );
        String requestUserId = body.getSystemRequestUserId();
        String requestMemberId = body.getRequestMemberId();
        
        ForumView forum = forumService.find(body.getForumId());

        // 根据systemRequestUserId查询是否加入了这个论坛
        ForumUserFollowView forumUserFollow = forumUserFollowService.findByMemberIdAndForumId(body.getAppId(), requestMemberId, body.getForumId());
        forum.put(Forum.MEMBER_IS_FOLLOW_FORUM, forumUserFollow != null);
        
        // 统计加入论坛人数
        Integer forumUserFollowCount = forumUserFollowService.countByForumId(body.getAppId(), body.getForumId());
        forum.put(Forum.FORUM_USER_FOLLOW_COUNT, forumUserFollowCount);
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_FILE_PATH,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_LIST,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR_MEMBER_ID,
                Forum.MEMBER_IS_FOLLOW_FORUM,
                Forum.FORUM_USER_FOLLOW_COUNT,
                Forum.USER_AVATAR_FILE_PATH,
                Forum.USER_NICKNAME,
                Forum.MEMBER_SIGNATURE
        );
        
        validateSecondResponse(Forum.FORUM_BACKGROUND_MEDIA_LIST, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_ID, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_PATH, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_SORT, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_TYPE);
        
        return renderJson(forum);
    }


    @ApiOperation(value = "论坛详情的主页的动态列表")
    @RequestMapping(value = "/forum/mobile/v1/home/topic/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeTopicV1() {
    	Forum body = getEntry(Forum.class);
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.REQUEST_MEMBER_ID,
                Topic.SYSTEM_CREATE_TIME,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );
        String forumId = body.getForumId();
        Integer pageIndex = body.getPageIndex();
        Integer pageSize = body.getPageSize();
        String requestUserId = body.getSystemRequestUserId();
        String requestMemberId = body.getRequestMemberId();
        String appId = body.getAppId();
        
        
        // 1,统计所有topic数量  根据forumId
        Integer countResult = topicForumService.countByForumId(appId, forumId);
        
        // 2,获得topic的分页列表
        List<TopicForumView> topicForumList = topicForumService.listByForumId(forumId, (List<String>) body.get(Topic.EXCLUDE_TOPIC_ID_LIST), body.getSystemCreateTime(), pageIndex, pageSize);

        // 处理论坛信息(暂时留在这里,视图里好像已经解决了)
       
        // 3,从TopicForum获取topicIdList, 
        List<String> topicIdList = Util.viewBeanToField(topicForumList, TopicForum.TOPIC_ID);

        List<TopicView> topicList = new ArrayList<>();
        // 4,根据topicIdList查找topicList
        if (!Util.isNullOrEmpty(topicIdList)) {
        	topicList = topicService.listByTopicIdList(topicIdList);

        	for (TopicView topic : topicList) {
	            
	            // 处理话题是否自己发布
	            if (requestMemberId.equals(topic.getMemberId())) {
	            	topic.put(Topic.TOPIC_IS_SELF, true);
				}else {
					Boolean isFollow = memberFollowService.checkIsFollow(requestUserId, topic.getSystemCreateUserId());
					topic.put(MemberFollow.MEMBER_IS_FOLLOW, isFollow);
				}
	            
	            // 是否点赞
	    		TopicUserLikeView userLikeView = topicUserLikeService.findByTopicIdAndMemberId(topic.getTopicId(), requestMemberId);
	    		topic.put(Topic.TOPIC_USER_IS_LIKE, !Util.isNullOrEmpty(userLikeView));
	            // 是否收藏
	            TopicUserBookmarkView bookmarkView = topicUserBookmarkService.findByTopicIdAndMemberId(topic.getTopicId(), requestMemberId);
	            topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, !Util.isNullOrEmpty(bookmarkView));
	            
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
	            MemberFollow.MEMBER_IS_FOLLOW,
	            
                User.USER_ID,
        		UserAvatar.USER_AVATAR_FILE_PATH,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW,
        		BaseEntity.SYSTEM_CREATE_TIME,
        		
        		TopicView.TOPIC_MEDIA_LIST,
                TopicView.TOPIC_TIP_USER_LIST,
                TopicView.TOPIC_FORUM_LIST
        );
        
        validateSecondResponse(TopicView.TOPIC_MEDIA_LIST, TopicMedia.TOPIC_MEDIA_FILE_PATH, TopicMedia.TOPIC_MEDIA_SORT, TopicMedia.TOPIC_MEDIA_TYPE);
        validateSecondResponse(TopicView.TOPIC_TIP_USER_LIST, Topic.MEMBER_ID);
        validateSecondResponse(TopicView.TOPIC_FORUM_LIST, Forum.FORUM_NAME, Forum.FORUM_ID);

        return renderJson(countResult, topicList);
    }

}