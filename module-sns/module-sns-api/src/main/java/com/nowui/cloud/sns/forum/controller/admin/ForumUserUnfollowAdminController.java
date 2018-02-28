package com.nowui.cloud.sns.forum.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.view.ForumUserUnfollowView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论坛用户取关关联管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户取关关联", description = "论坛用户取关关联管理端接口管理")
@RestController
public class ForumUserUnfollowAdminController extends BaseController {

    @Autowired
    private ForumUserUnfollowService forumUserUnfollowService;

    @ApiOperation(value = "论坛用户取关关联列表")
    @RequestMapping(value = "/forum/user/unfollow/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody ForumUserUnfollow body) {
        validateRequest(
                body,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.MEMBER_ID,
                ForumUserUnfollow.FORUM_ID,
                ForumUserUnfollow.PAGE_INDEX,
                ForumUserUnfollow.PAGE_SIZE
        );

        Integer resultTotal = forumUserUnfollowService.countForAdmin(body.getAppId() , body.getMemberId(), body.getForumId());
        List<ForumUserUnfollow> resultList = forumUserUnfollowService.listForAdmin(body.getAppId(), body.getMemberId(), body.getForumId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ForumUserUnfollow.FORUM_USER_UNFOLLOW_ID,
                ForumUserUnfollow.MEMBER_ID,
                ForumUserUnfollow.FORUM_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "论坛用户取关关联信息")
    @RequestMapping(value = "/forum/user/unfollow/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody ForumUserUnfollow body) {
        validateRequest(
                body,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.FORUM_USER_UNFOLLOW_ID
        );

        ForumUserUnfollowView result = forumUserUnfollowService.find(body.getForumUserUnfollowId());

        validateResponse(
                ForumUserUnfollow.FORUM_USER_UNFOLLOW_ID,
                ForumUserUnfollow.MEMBER_ID,
                ForumUserUnfollow.FORUM_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增论坛用户取关关联")
    @RequestMapping(value = "/forum/user/unfollow/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody ForumUserUnfollow body) {
        validateRequest(
                body,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.MEMBER_ID,
                ForumUserUnfollow.FORUM_ID
        );

//   TODO admin中的,先注释     Boolean result = forumUserUnfollowService.save(body, Util.getRandomUUID(), body.getAppId(), body.getSystemRequestUserId());

        return renderJson(null);
    }

    @ApiOperation(value = "修改论坛用户取关关联")
    @RequestMapping(value = "/forum/user/unfollow/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody ForumUserUnfollow body) {
        validateRequest(
                body,
                ForumUserUnfollow.FORUM_USER_UNFOLLOW_ID,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.MEMBER_ID,
                ForumUserUnfollow.FORUM_ID,
                ForumUserUnfollow.SYSTEM_VERSION
        );

//  admin中的,先注释      Boolean result = forumUserUnfollowService.update(body, body.getForumUserUnfollowId(), body.getAppId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(null);
    }

    @ApiOperation(value = "删除论坛用户取关关联")
    @RequestMapping(value = "/forum/user/unfollow/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody ForumUserUnfollow body) {
        validateRequest(
                body,
                ForumUserUnfollow.FORUM_USER_UNFOLLOW_ID,
                ForumUserUnfollow.APP_ID,
                ForumUserUnfollow.SYSTEM_VERSION
        );

//  admin中的,先注释      Boolean result = forumUserUnfollowService.delete(body.getForumUserUnfollowId(), body.getAppId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(null);
    }
    
    @ApiOperation(value = "论坛用户取消关注数据同步")
    @RequestMapping(value = "/forum/user/unfollow/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1(@RequestBody ForumUserUnfollow body) {
    	
    	List<ForumUserUnfollow> forumUserUnfollows= forumUserUnfollowService.listByMysql();
    	
    	for (ForumUserUnfollow forumUserUnfollow : forumUserUnfollows) {
			ForumUserUnfollowView forumUserUnfollowView = new ForumUserUnfollowView();
			forumUserUnfollowView.putAll(forumUserUnfollow);
			forumUserUnfollowService.update(forumUserUnfollowView);
		}

        return renderJson(true);
    }

}