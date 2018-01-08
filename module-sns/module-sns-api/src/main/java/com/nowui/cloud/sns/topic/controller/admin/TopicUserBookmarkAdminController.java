package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题用户收藏关联管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题用户收藏关联", description = "话题用户收藏关联管理端接口管理")
@RestController
public class TopicUserBookmarkAdminController extends BaseController {

    @Autowired
    private TopicUserBookmarkService topicUserBookmarkService;

    @ApiOperation(value = "话题用户收藏关联列表")
    @RequestMapping(value = "/topic/user/bookmark/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody TopicUserBookmark body) {
        validateRequest(
                body,
                TopicUserBookmark.APP_ID,
                TopicUserBookmark.TOPIC_ID,
                TopicUserBookmark.USER_ID,
                TopicUserBookmark.PAGE_INDEX,
                TopicUserBookmark.PAGE_SIZE
        );

        Integer resultTotal = topicUserBookmarkService.adminCount(body.getAppId() , body.getTopicId(), body.getUserId());
        List<TopicUserBookmark> resultList = topicUserBookmarkService.adminList(body.getAppId(), body.getTopicId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicUserBookmark.USER_BOOK_MARKED,
                TopicUserBookmark.TOPIC_ID,
                TopicUserBookmark.USER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题用户收藏关联信息")
    @RequestMapping(value = "/topic/user/bookmark/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody TopicUserBookmark body) {
        validateRequest(
                body,
                TopicUserBookmark.APP_ID,
                TopicUserBookmark.USER_BOOK_MARKED
        );

        TopicUserBookmark result = topicUserBookmarkService.find(body.getUserBookMarked());

        validateResponse(
                TopicUserBookmark.USER_BOOK_MARKED,
                TopicUserBookmark.TOPIC_ID,
                TopicUserBookmark.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题用户收藏关联")
    @RequestMapping(value = "/topic/user/bookmark/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody TopicUserBookmark body) {
        validateRequest(
                body,
                TopicUserBookmark.APP_ID,
                TopicUserBookmark.TOPIC_ID,
                TopicUserBookmark.USER_ID
        );

        Boolean result = topicUserBookmarkService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题用户收藏关联")
    @RequestMapping(value = "/topic/user/bookmark/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody TopicUserBookmark body) {
        validateRequest(
                body,
                TopicUserBookmark.USER_BOOK_MARKED,
                TopicUserBookmark.APP_ID,
                TopicUserBookmark.TOPIC_ID,
                TopicUserBookmark.USER_ID,
                TopicUserBookmark.SYSTEM_VERSION
        );

        Boolean result = topicUserBookmarkService.update(body, body.getUserBookMarked(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题用户收藏关联")
    @RequestMapping(value = "/topic/user/bookmark/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody TopicUserBookmark body) {
        validateRequest(
                body,
                TopicUserBookmark.USER_BOOK_MARKED,
                TopicUserBookmark.APP_ID,
                TopicUserBookmark.SYSTEM_VERSION
        );

        Boolean result = topicUserBookmarkService.delete(body.getUserBookMarked(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}