package com.nowui.cloud.sns.forum.controller.admin;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.forum.entity.ForumBackgroundMedia;
import com.nowui.cloud.sns.forum.view.ForumBackgroundMediaView;
import com.nowui.cloud.sns.forum.service.ForumBackgroundMediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论坛背景管理端控制器
 *
 * @author xupengfei
 *
 * 2018-03-09
 */
@Api(value = "论坛背景", description = "论坛背景管理端接口管理")
@RestController
public class ForumBackgroundMediaAdminController extends BaseController {

    @Autowired
    private ForumBackgroundMediaService forumBackgroundMediaService;

    @ApiOperation(value = "论坛背景列表")
    @RequestMapping(value = "/forum/background/media/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        ForumBackgroundMediaView forumBackgroundMediaView = getEntry(ForumBackgroundMediaView.class);

        validateRequest(
                forumBackgroundMediaView,
                ForumBackgroundMediaView.APP_ID,
                ForumBackgroundMediaView.FORUM_ID,
                ForumBackgroundMediaView.PAGE_INDEX,
                ForumBackgroundMediaView.PAGE_SIZE
        );

        Integer resultTotal = forumBackgroundMediaService.countForAdmin(forumBackgroundMediaView.getAppId(), forumBackgroundMediaView.getForumId());
        List<ForumBackgroundMediaView> resultList = forumBackgroundMediaService.listForAdmin(forumBackgroundMediaView.getAppId(), forumBackgroundMediaView.getForumId(), forumBackgroundMediaView.getPageIndex(), forumBackgroundMediaView.getPageSize());

        validateResponse(
                ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_ID,
                ForumBackgroundMediaView.FORUM_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "论坛背景信息")
    @RequestMapping(value = "/forum/background/media/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        ForumBackgroundMediaView forumBackgroundMediaView = getEntry(ForumBackgroundMediaView.class);

        validateRequest(
                forumBackgroundMediaView,
                ForumBackgroundMediaView.APP_ID,
                ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_ID
        );

        ForumBackgroundMediaView result = forumBackgroundMediaService.find(forumBackgroundMediaView.getForumBackgroundMediaId());

        validateResponse(
                ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_ID,
            	ForumBackgroundMediaView.FORUM_ID,
            	ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_FILE_ID,
            	ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_TYPE,
            	ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_SORT,
                ForumBackgroundMediaView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "论坛背景新增")
    @RequestMapping(value = "/forum/background/media/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        ForumBackgroundMedia forumBackgroundMediaEntity = getEntry(ForumBackgroundMedia.class);

        String forumBackgroundMediaId = Util.getRandomUUID();

        validateRequest(
                forumBackgroundMediaEntity,
                ForumBackgroundMedia.APP_ID,
                ForumBackgroundMedia.FORUM_ID,
                ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_ID,
                ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_TYPE,
                ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_SORT
        );

        ForumBackgroundMedia result = forumBackgroundMediaService.save(forumBackgroundMediaEntity, forumBackgroundMediaId, forumBackgroundMediaEntity.getSystemRequestUserId());

        if (result != null) {
            ForumBackgroundMediaView forumBackgroundMediaView = new ForumBackgroundMediaView();
            forumBackgroundMediaView.putAll(result);

            forumBackgroundMediaService.save(forumBackgroundMediaView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "论坛背景修改")
    @RequestMapping(value = "/forum/background/media/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        ForumBackgroundMedia forumBackgroundMediaEntity = getEntry(ForumBackgroundMedia.class);

        validateRequest(
                forumBackgroundMediaEntity,
                ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_ID,
                ForumBackgroundMedia.APP_ID,
                ForumBackgroundMedia.FORUM_ID,
                ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_ID,
                ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_TYPE,
                ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_SORT,
                ForumBackgroundMedia.SYSTEM_VERSION
        );

        ForumBackgroundMedia result = forumBackgroundMediaService.update(forumBackgroundMediaEntity, forumBackgroundMediaEntity.getForumBackgroundMediaId(), forumBackgroundMediaEntity.getSystemRequestUserId(), forumBackgroundMediaEntity.getSystemVersion());

        if (result != null) {
            ForumBackgroundMediaView forumBackgroundMediaView = new ForumBackgroundMediaView();
            forumBackgroundMediaView.putAll(result);

            forumBackgroundMediaService.update(forumBackgroundMediaView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "论坛背景删除")
    @RequestMapping(value = "/forum/background/media/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        ForumBackgroundMedia forumBackgroundMediaEntity = getEntry(ForumBackgroundMedia.class);

        validateRequest(
                forumBackgroundMediaEntity,
                ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_ID,
                ForumBackgroundMedia.APP_ID,
                ForumBackgroundMedia.SYSTEM_VERSION
        );

        ForumBackgroundMedia result = forumBackgroundMediaService.delete(forumBackgroundMediaEntity.getForumBackgroundMediaId(), forumBackgroundMediaEntity.getSystemRequestUserId(), forumBackgroundMediaEntity.getSystemVersion());

        if (result != null) {
            ForumBackgroundMediaView forumBackgroundMediaView = new ForumBackgroundMediaView();
            forumBackgroundMediaView.putAll(result);

            forumBackgroundMediaService.update(forumBackgroundMediaView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "论坛背景数据同步")
    @RequestMapping(value = "/forum/background/media/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<ForumBackgroundMedia> forumBackgroundMediaList = forumBackgroundMediaService.listByMysql();

        for (ForumBackgroundMedia forumBackgroundMedia : forumBackgroundMediaList) {
            ForumBackgroundMediaView forumBackgroundMediaView = new ForumBackgroundMediaView();
            forumBackgroundMediaView.putAll(forumBackgroundMedia);

            forumBackgroundMediaService.saveOrUpdate(forumBackgroundMediaView);
        }

        return renderJson(true);
    }

}