package com.nowui.cloud.sns.forum.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.router.ForumUserFollowRouter;
import com.nowui.cloud.sns.forum.router.ForumUserUnfollowRouter;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
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
	
	@ApiOperation(value = "新增论坛用户取关")
    @RequestMapping(value = "/forum/user/unfollow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
		ForumUserUnfollow body = getEntry(ForumUserUnfollow.class);
        validateRequest(
                body,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.SYSTEM_REQUEST_USER_ID,
                ForumUserUnfollow.FORUM_ID
                // ForumUserFollow.SYSTEM_VERSION
        );
        
        body.setUserId(body.getSystemRequestUserId());
        
        //先去关注表查询
        ForumUserFollow followBody = forumUserFollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), body.getForumId());
        //有: 删除
        if (followBody != null) {
        	Boolean delResult = forumUserFollowService.delete(followBody.getForumUserFollowId(), body.getAppId(), ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_DELETE, body.getSystemRequestUserId(), followBody.getSystemVersion());
		}
        //没有: 就去取消关注表查询
        ForumUserUnfollow unfollow = forumUserUnfollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), body.getForumId());
        //有: 返回true
        if (unfollow != null) {
			return renderJson(true);
		}
        //没有: 新增关注记录
        boolean result = forumUserUnfollowService.save(body, Util.getRandomUUID(), body.getAppId(), ForumUserUnfollowRouter.FORUM_USER_UNFOLLOW_V1_SAVE, body.getSystemRequestUserId());
        
        return renderJson(result);
    }
}