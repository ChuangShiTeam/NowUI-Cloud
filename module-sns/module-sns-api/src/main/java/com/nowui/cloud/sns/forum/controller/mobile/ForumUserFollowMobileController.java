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

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.router.ForumRouter;
import com.nowui.cloud.sns.forum.router.ForumUserFollowRouter;
import com.nowui.cloud.sns.forum.router.ForumUserUnfollowRouter;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
import com.nowui.cloud.sns.forum.view.ForumUserUnfollowView;
import com.nowui.cloud.sns.forum.view.ForumView;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.util.Util;

import feign.Body;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 论坛用户关注移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户关注", description = "论坛用户关注移动端接口管理")
@RestController
public class ForumUserFollowMobileController extends BaseController {

	@Autowired
	private ForumUserFollowService forumUserFollowService;
	
	@Autowired
    private ForumUserUnfollowService forumUserUnfollowService;

	@Autowired
	private ForumService forumService;

	@Autowired
    private FileRpc fileRpc;
	
	@Autowired
	private MemberRpc memberRpc;

	@Autowired
	private TopicForumService topicForumService;

	@ApiOperation(value = "新增用户论坛关注")
    @RequestMapping(value = "/forum/user/follow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
		ForumUserFollow body = getEntry(ForumUserFollow.class);
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.SYSTEM_REQUEST_USER_ID,
                ForumUserFollow.FORUM_ID
        );
        
        String appId = body.getAppId();
        String userId = body.getSystemRequestUserId();
        String forumId = body.getForumId();
        
        //先查询取消关注表有没有记录
        ForumUserUnfollowView unfollow = forumUserUnfollowService.findByUserIdAndForumId(appId, userId, forumId);
        //有: 删除
        if (!Util.isNullOrEmpty(unfollow)) {
//  TODO 后面处理消息 	Boolean delete = forumUserUnfollowService.delete(unfollow.getForumUserUnfollowId(), appId, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_DELETE, userId, unfollow.getSystemVersion());
        	forumUserUnfollowService.deleteByForumId(appId, forumId, userId);
		}
        //没有: 去关注表看有没有记录
        ForumUserFollowView forumUserFollow = forumUserFollowService.findByUserIdAndForumId(appId, userId, forumId);
        //有: 返回true
        if (!Util.isNullOrEmpty(forumUserFollow)) {
        	return renderJson(true); 
		}
        //没有: 新增关注记录
        ForumUserFollow bean = new ForumUserFollow();
        bean.setAppId(appId);
        bean.setForumId(forumId);
        bean.setUserId(userId);
        bean.setForumUserFollowIsTop(false);
        
//  TODO 后面处理消息    Boolean result = forumUserFollowService.save(bean, appId, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, Util.getRandomUUID(), userId);
        ForumUserFollow result = forumUserFollowService.save(bean, Util.getRandomUUID(), userId);
        Boolean success = false;

        if (result != null) {
        	sendMessage(result, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, appId, userId);
            
            success = true;
        }
        
        return renderJson(success);
    }
	

	@ApiOperation(value = "邀请用户加入论坛")
    @RequestMapping(value = "/forum/user/follow/mobile/v1/invite/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> inviteUserV1() {
		ForumUserFollow body = getEntry(ForumUserFollow.class);
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.SYSTEM_REQUEST_USER_ID,
                ForumUserFollow.USER_ID,
                ForumUserFollow.FORUM_ID
        );
        
        String appId = body.getAppId();
        String requestUserId = body.getSystemRequestUserId();
        String forumId = body.getForumId();
        String beInvitedUserId = body.getUserId();
//        TODO 暂时不做这个接口
//        
//        //TODO 不管传来的用户标识是什么,都会使用userId进行save操作
//        
//        //先查询取消关注表有没有记录
//        ForumUserUnfollowView unfollow = forumUserUnfollowService.findByUserIdAndForumId(appId, beInvitedUserId, forumId);
//        //有: 删除
//        if (!Util.isNullOrEmpty(unfollow)) {
////TODO 后面处理消息        	Boolean delete = forumUserUnfollowService.delete(unfollow.getForumUserUnfollowId(), appId, ForumUserUnfollowRouter.FORUM_USER_UNFOLLOW_V1_DELETE, beInvitedUserId, unfollow.getSystemVersion());
//        	forumUserUnfollowService.delete(unfollow.getForumUserUnfollowId(), requestUserId, unfollow.getSystemVersion());
//		}
//        //没有: 去关注表看有没有记录
//        ForumUserFollow forumUserFollow = forumUserFollowService.findByUserIdAndForumId(appId, beInvitedUserId, forumId);
//        //有: 返回true
//        if (!Util.isNullOrEmpty(forumUserFollow)) {
//        	return renderJson(true); 
//		}
//        //没有: 新增取消关注记录
//        ForumUserFollow bean = new ForumUserFollow();
//        bean.setAppId(appId);
//        bean.setForumId(forumId);
//        bean.setUserId(beInvitedUserId);
//        bean.setForumUserFollowIsTop(false);
//        
//        //这里的createUserId使用邀请人的
//        Boolean result = forumUserFollowService.save(bean, appId, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, Util.getRandomUUID(), requestUserId);
        
        return renderJson(null);
        
    }
	
	@ApiOperation(value = "批量新增用户论坛关注")
    @RequestMapping(value = "/forum/user/follow/mobile/v1/batch/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> batchSaveV1() {
		ForumUserFollow body = getEntry(ForumUserFollow.class);
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.SYSTEM_REQUEST_USER_ID
        );
        
        JSONArray jsonArray = body.getJSONArray(ForumUserFollow.FORUM_ID_LSIT);
        
        if (Util.isNullOrEmpty(jsonArray)) {
            return renderJson(true);
        }
        
        List<String> forumIdList = jsonArray.toJavaList(String.class);
        Boolean success = true;
        String appId = body.getAppId();
        String userId = body.getSystemRequestUserId();
        
        for (String forumId : forumIdList) {
            ForumUserFollowView forumUserFollow = forumUserFollowService.findByUserIdAndForumId(appId, userId, forumId);
            
            if (Util.isNullOrEmpty(forumUserFollow)) {
                ForumUserFollow bean = new ForumUserFollow();
                
                bean.setAppId(appId);
                bean.setForumId(forumId);
                bean.setUserId(userId);
                bean.setForumUserFollowIsTop(false);
                
// TODO 后面处理消息       result = forumUserFollowService.save(bean, appId, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, Util.getRandomUUID(), userId);
                
                ForumUserFollow result = forumUserFollowService.save(bean, Util.getRandomUUID(), userId);
                
                if (Util.isNullOrEmpty(result)) {
                	success = false;
                    throw new BusinessException("加入失败");
                }
            }
        }
        
        	// TODO 怎么发送message       
        	sendMessage(body, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, appId, userId);
            
            success = true;
            
            return renderJson(success);
        }
        

    @ApiOperation(value = "用户关注论坛列表")
    @RequestMapping(value = "/forum/user/follow/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
    	ForumUserFollow body = getEntry(ForumUserFollow.class);
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.PAGE_INDEX,
                ForumUserFollow.PAGE_SIZE,
                ForumUserFollow.SYSTEM_REQUEST_USER_ID
        );

        String appId = body.getAppId();
        String userId = body.getSystemRequestUserId();

        Integer resultTotal = forumUserFollowService.countByUserId(body.getAppId(), body.getSystemRequestUserId());

        List<ForumUserFollowView> forumUserFollowList = forumUserFollowService.listByUserId(appId, userId, body.getPageIndex(), body.getPageSize());

        List<ForumView> forumList = new ArrayList<>();

        for (ForumUserFollowView forumUserFollow : forumUserFollowList) {

        	ForumView forum = forumService.find(forumUserFollow.getForumId());

            // 论坛当日话题最新数量
            Integer count = topicForumService.countTodayByForumId(forumUserFollow.getForumId());
            forum.put(Forum.FORUM_TODAY_TOPIC_COUNT, count);

            forumList.add(forum);
        }

        // TODO 处理论坛多媒体
//        String fileIds = Util.beanToFieldString(forumList, Forum.FORUM_MEDIA);
//        List<File> fileList = fileRpc.findsV1(fileIds);
//        forumList = Util.beanReplaceField(forumList, Forum.FORUM_MEDIA, fileList, File.FILE_PATH);
//        
//        String userIds = Util.beanToFieldString(forumList, Forum.FORUM_MODERATOR);
//        List<Member> memberList = memberRpc.nickNameAndAvatarListV1(userIds);
//        forumList = Util.beanReplaceField(forumList, Forum.FORUM_MODERATOR, Member.USER_ID, memberList, UserNickName.USER_NICK_NAME, UserAvatar.USER_AVATAR);
        
        
        
        
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_TODAY_TOPIC_COUNT,
                Forum.FORUM_MODERATOR
        );

        return renderJson(resultTotal, forumList);
    }
    
    @ApiOperation(value = "用户所有关注论坛名称列表")
    @RequestMapping(value = "/forum/user/follow/mobile/v1/name/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> nameListV1() {
    	ForumUserFollow body = getEntry(ForumUserFollow.class);
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.SYSTEM_REQUEST_USER_ID
        );
        
        String appId = body.getAppId();
        String userId = body.getSystemRequestUserId();
        
        List<ForumUserFollowView> resultList = forumUserFollowService.listByUserId(appId, userId);

        List<ForumView> forumList = new ArrayList<>();

        for (ForumUserFollowView forumUserFollow : resultList) {
            ForumView forum = forumService.find(forumUserFollow.getForumId());
            forumList.add(forum);
        }
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_NAME
        );

        return renderJson(forumList);
    }
    
    @ApiOperation(value = "置顶用户关注论坛")
    @RequestMapping(value = "/forum/user/follow/mobile/v1/top", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> topV1() {
    	ForumUserFollow forumUserFollowEntry = getEntry(ForumUserFollow.class);
        validateRequest(
        		forumUserFollowEntry,
                ForumUserFollow.FORUM_ID,
                ForumUserFollow.APP_ID
        );
		
		ForumUserFollow result = forumUserFollowService.updateTopForum(forumUserFollowEntry.getAppId(), forumUserFollowEntry.getForumId(), forumUserFollowEntry.getSystemRequestUserId());
        Boolean success = false;
        
        if (result != null) {
        	forumUserFollowEntry.setForumUserFollowIsTop(true);
        	sendMessage(forumUserFollowEntry, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_UPDATE, forumUserFollowEntry.getAppId(), forumUserFollowEntry.getSystemRequestUserId());

            success = true;
		}
        return renderJson(success);
    }
}