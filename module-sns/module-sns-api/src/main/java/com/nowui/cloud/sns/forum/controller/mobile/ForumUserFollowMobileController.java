package com.nowui.cloud.sns.forum.controller.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.member.member.view.MemberView;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
import com.nowui.cloud.sns.forum.view.ForumUserUnfollowView;
import com.nowui.cloud.sns.forum.view.ForumView;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.util.Util;

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
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String memberId = member.getMemberId();
        
        String forumId = body.getForumId();
        
        //去关注表看有没有记录
        ForumUserFollowView forumUserFollow = forumUserFollowService.findByMemberIdAndForumId(appId, memberId, forumId);
        //有: 返回true
        if (!Util.isNullOrEmpty(forumUserFollow)) {
        	return renderJson(true); 
		}
        //没有: 新增关注记录
        ForumUserFollow bean = new ForumUserFollow();
        bean.setAppId(appId);
        bean.setForumId(forumId);
        bean.setMemberId(memberId);
        bean.setForumUserFollowIsTop(false);
        
//  TODO 后面处理消息    Boolean result = forumUserFollowService.save(bean, appId, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, Util.getRandomUUID(), userId);
        ForumUserFollow result = forumUserFollowService.save(bean, Util.getRandomUUID(), requestUserId);
        Boolean success = false;

        if (result != null) {
        	//查询取消关注表有没有记录
            ForumUserUnfollowView unfollow = forumUserUnfollowService.findByMemberIdAndForumId(appId, memberId, forumId);
            //有: 删除
            if (!Util.isNullOrEmpty(unfollow)) {
//  TODO 后面处理消息 	Boolean delete = forumUserUnfollowService.delete(unfollow.getForumUserUnfollowId(), appId, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_DELETE, userId, unfollow.getSystemVersion());
            	ForumUserUnfollow delResult = forumUserUnfollowService.deleteByForumId(appId, forumId, requestUserId);
            	
            	if (delResult != null) {
            		// 处理删除取消关注记录
            		delResult.setForumId(forumId);
            		ForumUserUnfollowView userUnfollowView = JSON.parseObject(delResult.toJSONString(), ForumUserUnfollowView.class);
            		forumUserUnfollowService.delete(userUnfollowView);
                	
				}
    		}
            
        	ForumUserFollowView forumUserFollowView = JSON.parseObject(result.toJSONString(), ForumUserFollowView.class);
        	forumUserFollowService.save(forumUserFollowView);
        	//sendMessage(result, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, appId, userId);
            
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
                ForumUserFollow.MEMBER_ID,
                ForumUserFollow.FORUM_ID
        );
        
        String appId = body.getAppId();
        String requestUserId = body.getSystemRequestUserId();
        String forumId = body.getForumId();
        String beInvitedMemberId = body.getMemberId();
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
        String appId = body.getAppId();
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String memberId = member.getMemberId();
        
        JSONArray jsonArray = body.getJSONArray(ForumUserFollow.FORUM_ID_LSIT);
        
        if (Util.isNullOrEmpty(jsonArray)) {
            return renderJson(true);
        }
        
        List<String> forumIdList = jsonArray.toJavaList(String.class);
        Boolean success = true;
        
        for (String forumId : forumIdList) {
            ForumUserFollowView forumUserFollow = forumUserFollowService.findByMemberIdAndForumId(appId, memberId, forumId);
            
            if (Util.isNullOrEmpty(forumUserFollow)) {
                ForumUserFollow bean = new ForumUserFollow();
                
                bean.setAppId(appId);
                bean.setForumId(forumId);
                bean.setMemberId(memberId);
                bean.setForumUserFollowIsTop(false);
                
// TODO 后面处理消息       result = forumUserFollowService.save(bean, appId, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, Util.getRandomUUID(), userId);
                
                ForumUserFollow result = forumUserFollowService.save(bean, Util.getRandomUUID(), requestUserId);
                
                if (Util.isNullOrEmpty(result)) {
                	success = false;
                    throw new BusinessException("加入失败");
                }else {
                	// TODO 这里逻辑有问题,回滚是回滚一次的循环的数据,还是全部已经存到数据库的数据
                	ForumUserFollowView forumUserFollowView = JSON.parseObject(result.toJSONString(), ForumUserFollowView.class);
                	forumUserFollowService.save(forumUserFollowView);
				}
            }
        }
        
//		TODO 怎么发送message       
//    	sendMessage(body, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, appId, userId);
        
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
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String requestMemberId = member.getMemberId();

        Integer resultTotal = forumUserFollowService.countByMemberId(appId, requestMemberId);

        List<ForumUserFollowView> forumUserFollowList = forumUserFollowService.listByMemberId(appId, requestMemberId, body.getPageIndex(), body.getPageSize());

        List<ForumView> forumList = new ArrayList<>();

        for (ForumUserFollowView forumUserFollow : forumUserFollowList) {

        	ForumView forum = forumService.find(forumUserFollow.getForumId());

            // 论坛当日话题最新数量
            Integer count = topicForumService.countTodayByForumId(forumUserFollow.getForumId());
            forum.put(Forum.FORUM_TODAY_TOPIC_COUNT, count);
            forum.put(ForumUserFollow.FORUM_USER_FOLLOW_ID, forumUserFollow.getForumUserFollowId());

            forumList.add(forum);
        }

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_TODAY_TOPIC_COUNT,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_MODERATOR_INFO,
                Forum.USER_AVATAR, 
                Forum.USER_NICKNAME, 
                Forum.MEMBER_SIGNATURE,

                ForumUserFollow.FORUM_USER_FOLLOW_ID
        );
        
        validateSecondResponse(Forum.FORUM_MODERATOR_INFO, User.USER_AVATAR, User.USER_NICK_NAME, Member.MEMBER_SIGNATURE);

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
        String requestUserId = body.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String memberId = member.getMemberId();
        
        List<ForumUserFollowView> resultList = forumUserFollowService.listByMemberId(appId, memberId);

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
                ForumUserFollow.APP_ID,
                ForumUserFollow.FORUM_USER_FOLLOW_ID
        );
        
        String requestUserId = forumUserFollowEntry.getSystemRequestUserId();
        MemberView member = memberRpc.findByUserIdV1(requestUserId);
        String memberId = member.getMemberId();
		
		ForumUserFollow result = forumUserFollowService.updateTopForum(forumUserFollowEntry.getAppId(), forumUserFollowEntry.getForumId(), memberId, requestUserId);
        Boolean success = false;
        
        if (result != null) {
        	
        	forumUserFollowEntry.setForumUserFollowIsTop(true);
        	forumUserFollowEntry.setSystemUpdateTime(new Date());
        	ForumUserFollowView forumUserFollowView = JSON.parseObject(forumUserFollowEntry.toJSONString(), ForumUserFollowView.class);
        	forumUserFollowService.update(forumUserFollowView);

//        	sendMessage(forumUserFollowEntry, ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_UPDATE, forumUserFollowEntry.getAppId(), forumUserFollowEntry.getSystemRequestUserId());

            success = true;
		}
        return renderJson(success);
    }
}