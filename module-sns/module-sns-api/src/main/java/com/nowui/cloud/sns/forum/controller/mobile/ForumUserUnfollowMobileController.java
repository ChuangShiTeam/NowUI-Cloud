package com.nowui.cloud.sns.forum.controller.mobile;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.router.ForumUserFollowRouter;
import com.nowui.cloud.sns.forum.router.ForumUserUnfollowRouter;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;
import com.nowui.cloud.sns.forum.view.ForumUserUnfollowView;
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
 * 论坛用户取关移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户取关", description = "论坛用户取关移动端接口管理")
@RestController
public class ForumUserUnfollowMobileController extends BaseController {

	@Autowired
    private ForumUserUnfollowService forumUserUnfollowService;
	
	@Autowired
	private ForumUserFollowService forumUserFollowService;
	
	@Autowired
	private MemberRpc memberRpc;
	
	@ApiOperation(value = "新增论坛用户取关")
    @RequestMapping(value = "/forum/user/unfollow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
		ForumUserUnfollow body = getEntry(ForumUserUnfollow.class);
        validateRequest(
                body,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.SYSTEM_REQUEST_USER_ID,
                ForumUserUnfollow.FORUM_ID
        );
        
        String requestUserId = body.getSystemRequestUserId();
        Member member = memberRpc.findByUserIdV1(requestUserId);
        String memberId = member.getMemberId();
        String appId = body.getAppId();
        
        body.setMemberId(memberId);
        
        ForumUserFollow delResult = new ForumUserFollow();
        
        //先去关注表查询
        ForumUserFollowView followBody = forumUserFollowService.findByMemberIdAndForumId(appId, memberId, body.getForumId());
        //有: 删除
        if (followBody != null) {
//  TODO  后面处理消息      	Boolean delResult = forumUserFollowService.delete(followBody.getForumUserFollowId(), body.getAppId(), ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_DELETE, body.getSystemRequestUserId(), followBody.getSystemVersion());
        	delResult = forumUserFollowService.delete(followBody.getForumUserFollowId(), requestUserId, followBody.getSystemVersion());
		}
        //没有: 就去取消关注表查询
        ForumUserUnfollowView unfollow = forumUserUnfollowService.findByMemberIdAndForumId(appId, memberId, body.getForumId());
        //有: 返回true
        if (unfollow != null) {
			return renderJson(true);
		}
        
        //没有: 新增取消关注记录
//      boolean result = forumUserUnfollowService.save(body, Util.getRandomUUID(), body.getAppId(), ForumUserUnfollowRouter.FORUM_USER_UNFOLLOW_V1_SAVE, body.getSystemRequestUserId());
        ForumUserUnfollow result = forumUserUnfollowService.save(body, Util.getRandomUUID(), requestUserId);
        Boolean success = false;

        if (result != null) {
        	
        	//删除关注记录
    		ForumUserFollowView userFollowView = JSON.parseObject(delResult.toJSONString(), ForumUserFollowView.class);
    		forumUserFollowService.delete(userFollowView);
        	
        	//新增取关记录
    		ForumUserUnfollowView userUnfollowView = JSON.parseObject(result.toJSONString(), ForumUserUnfollowView.class);
    		forumUserUnfollowService.delete(userUnfollowView);
        	
        	//sendMessage(result, ForumUserUnfollowRouter.FORUM_USER_UNFOLLOW_V1_SAVE, appId, userId);
            
            success = true;
        }
        
        return renderJson(success);
    }
}