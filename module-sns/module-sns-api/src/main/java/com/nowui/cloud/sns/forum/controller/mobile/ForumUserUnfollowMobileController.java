package com.nowui.cloud.sns.forum.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.apache.commons.math.analysis.interpolation.NevilleInterpolator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 论坛用户取关关联移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户取关关联", description = "论坛用户取关关联移动端接口管理")
@RestController
public class ForumUserUnfollowMobileController extends BaseController {

	@Autowired
    private ForumUserUnfollowService forumUserUnfollowService;
	
	@Autowired
	private ForumUserFollowService forumUserFollowService;
	
	@ApiOperation(value = "新增论坛用户取关关联")
    @RequestMapping(value = "/forum/user/unfollow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody ForumUserUnfollow body) {
        validateRequest(
                body,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.SYSTEM_REQUEST_USER_ID,
                ForumUserUnfollow.FORUM_ID,
                ForumUserFollow.SYSTEM_VERSION
        );
        
        ForumUserUnfollow unfollow = forumUserUnfollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), body.getForumId());
        if (unfollow != null) {
			return renderJson(true);
		}
        
        //先找到关注表id
        ForumUserFollow followBody = forumUserFollowService.findByUserIdAndForumId(body.getAppId(), body.getUserId(), body.getForumId());
        //如果有就删除,没有:不操作
        if (followBody != null || followBody.size() != 0) {
        	//根据UserFollowId删除记录
        	Boolean delResult = forumUserFollowService.delete(followBody.getForumUserFollowId(), body.getSystemRequestUserId(), body.getSystemVersion());
		}
        //向取消关注表插入一条数据
        boolean result = forumUserUnfollowService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());
        return renderJson(result);
    }
}