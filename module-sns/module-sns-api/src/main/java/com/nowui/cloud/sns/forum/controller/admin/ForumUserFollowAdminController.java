package com.nowui.cloud.sns.forum.controller.admin;
import com.netflix.infix.lang.infix.antlr.EventFilterParser.null_predicate_return;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.router.ForumUserFollowRouter;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论坛用户关注管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户关注", description = "论坛用户关注管理端接口管理")
@RestController
public class ForumUserFollowAdminController extends BaseController {

    @Autowired
    private ForumUserFollowService forumUserFollowService;

    @ApiOperation(value = "论坛用户关注列表")
    @RequestMapping(value = "/forum/user/follow/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody ForumUserFollow body) {
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.USER_ID,
                ForumUserFollow.FORUM_ID,
                ForumUserFollow.PAGE_INDEX,
                ForumUserFollow.PAGE_SIZE
        );

        Integer resultTotal = forumUserFollowService.countForAdmin(body.getAppId() , body.getUserId(), body.getForumId());
        List<ForumUserFollow> resultList = forumUserFollowService.listForAdmin(body.getAppId(), body.getUserId(), body.getForumId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ForumUserFollow.FORUM_USER_FOLLOW_ID,
                ForumUserFollow.USER_ID,
                ForumUserFollow.FORUM_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "论坛用户关注信息")
    @RequestMapping(value = "/forum/user/follow/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody ForumUserFollow body) {
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.FORUM_USER_FOLLOW_ID
        );

        ForumUserFollowView result = forumUserFollowService.find(body.getForumUserFollowId());

        validateResponse(
                ForumUserFollow.FORUM_USER_FOLLOW_ID,
                ForumUserFollow.USER_ID,
                ForumUserFollow.FORUM_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增论坛用户关注")
    @RequestMapping(value = "/forum/user/follow/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody ForumUserFollow body) {
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.USER_ID,
                ForumUserFollow.FORUM_ID
        );

//  TODO      Boolean result = forumUserFollowService.save(body, Util.getRandomUUID(), body.getAppId(), ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, body.getSystemRequestUserId());

        return renderJson(null);
    }

    @ApiOperation(value = "修改论坛用户关注")
    @RequestMapping(value = "/forum/user/follow/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody ForumUserFollow body) {
        validateRequest(
                body,
                ForumUserFollow.FORUM_USER_FOLLOW_ID,
                ForumUserFollow.APP_ID,
                ForumUserFollow.USER_ID,
                ForumUserFollow.FORUM_ID,
                ForumUserFollow.SYSTEM_VERSION
        );

// TODO       Boolean result = forumUserFollowService.update(body, body.getForumUserFollowId(), body.getAppId(), ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(null);
    }

    @ApiOperation(value = "删除论坛用户关注")
    @RequestMapping(value = "/forum/user/follow/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody ForumUserFollow body) {
        validateRequest(
                body,
                ForumUserFollow.FORUM_USER_FOLLOW_ID,
                ForumUserFollow.APP_ID,
                ForumUserFollow.SYSTEM_VERSION
        );

// TODO       Boolean result = forumUserFollowService.delete(body.getForumUserFollowId(), body.getAppId(), ForumUserFollowRouter.FORUM_USER_FOLLOW_V1_SAVE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(null);
    }

}