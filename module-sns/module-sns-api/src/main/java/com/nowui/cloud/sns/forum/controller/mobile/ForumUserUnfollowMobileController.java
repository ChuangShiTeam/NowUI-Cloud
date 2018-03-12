package com.nowui.cloud.sns.forum.controller.mobile;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
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
	
	@ApiOperation(value = "新增论坛用户取关")
    @RequestMapping(value = "/forum/user/unfollow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
		ForumUserUnfollow body = getEntry(ForumUserUnfollow.class);
        validateRequest(
                body,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.SYSTEM_REQUEST_USER_ID,
                ForumUserUnfollow.FORUM_ID,
                ForumUserUnfollow.MEMBER_ID
        );
        
        String requestUserId = body.getSystemRequestUserId();
        String memberId = body.getMemberId();
        
        String appId = body.getAppId();
        
        ForumUserFollow delResult = new ForumUserFollow();
        
        //先去关注表查询
        ForumUserFollowView followBody = forumUserFollowService.findByMemberIdAndForumId(appId, memberId, body.getForumId());
        //有: 删除
        if (followBody != null) {
        	delResult = forumUserFollowService.delete(followBody.getForumUserFollowId(), requestUserId, followBody.getSystemVersion());
		}
        //没有: 就去取消关注表查询
        ForumUserUnfollowView unfollow = forumUserUnfollowService.findByMemberIdAndForumId(appId, memberId, body.getForumId());
        //有: 返回true
        if (unfollow != null) {
			return renderJson(true);
		}
        
        //没有: 新增取消关注记录
        ForumUserUnfollow result = forumUserUnfollowService.save(body, Util.getRandomUUID(), requestUserId);
        Boolean success = false;

        if (result != null) {
        	
        	//删除关注记录
    		ForumUserFollowView userFollowView = JSON.parseObject(delResult.toJSONString(), ForumUserFollowView.class);
    		forumUserFollowService.delete(userFollowView);
        	
        	//新增取关记录
    		ForumUserUnfollowView userUnfollowView = JSON.parseObject(result.toJSONString(), ForumUserUnfollowView.class);
    		forumUserUnfollowService.delete(userUnfollowView);
            
            success = true;
        }
        
        return renderJson(success);
    }
}