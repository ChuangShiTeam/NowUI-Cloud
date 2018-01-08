package com.nowui.cloud.sns.forum.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.service.ForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论坛信息管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛信息", description = "论坛信息管理端接口管理")
@RestController
public class ForumAdminController extends BaseController {

    @Autowired
    private ForumService forumService;

    @ApiOperation(value = "论坛信息列表")
    @RequestMapping(value = "/forum/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_MEDIA_ID,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_FOLLOW,
                Forum.FORUM_IS_RECOMAND,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );

        Integer resultTotal = forumService.adminCount(body.getAppId() , body.getForumMediaId(), body.getForumMediaType(), body.getForumBackgroundMediaId(), body.getForumBackgroundMediaType(), body.getForumName(), body.getForumDescription(), body.getForumModerator(), body.getForumTopicLocation(), body.getForumSort(), body.getForumTop(), body.getForumTopLevel(), body.getForumTopEndTime(), body.getForumIsActive(), body.getForumIsFollow(), body.getForumIsRecomand());
        List<Forum> resultList = forumService.adminList(body.getAppId(), body.getForumMediaId(), body.getForumMediaType(), body.getForumBackgroundMediaId(), body.getForumBackgroundMediaType(), body.getForumName(), body.getForumDescription(), body.getForumModerator(), body.getForumTopicLocation(), body.getForumSort(), body.getForumTop(), body.getForumTopLevel(), body.getForumTopEndTime(), body.getForumIsActive(), body.getForumIsFollow(), body.getForumIsRecomand(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_FOLLOW,
                Forum.FORUM_IS_RECOMAND
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "论坛信息信息")
    @RequestMapping(value = "/forum/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID
        );

        Forum result = forumService.find(body.getForumId());

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_FOLLOW,
                Forum.FORUM_IS_RECOMAND
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增论坛信息")
    @RequestMapping(value = "/forum/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_MEDIA_ID,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_FOLLOW,
                Forum.FORUM_IS_RECOMAND
        );

        Boolean result = forumService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改论坛信息")
    @RequestMapping(value = "/forum/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID,
                Forum.FORUM_MEDIA_ID,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_FOLLOW,
                Forum.FORUM_IS_RECOMAND,
                Forum.SYSTEM_VERSION
        );

        Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除论坛信息")
    @RequestMapping(value = "/forum/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID,
                Forum.SYSTEM_VERSION
        );

        Boolean result = forumService.delete(body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}