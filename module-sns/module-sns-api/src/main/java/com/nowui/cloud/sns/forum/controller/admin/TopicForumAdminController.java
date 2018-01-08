package com.nowui.cloud.sns.forum.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.forum.entity.TopicForum;
import com.nowui.cloud.sns.forum.service.TopicForumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题论坛关联管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题论坛关联", description = "话题论坛关联管理端接口管理")
@RestController
public class TopicForumAdminController extends BaseController {

    @Autowired
    private TopicForumService topicForumService;

    @ApiOperation(value = "话题论坛关联列表")
    @RequestMapping(value = "/topic/forum/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID,
                TopicForum.PAGE_INDEX,
                TopicForum.PAGE_SIZE
        );

        Integer resultTotal = topicForumService.adminCount(body.getAppId() , body.getForumId(), body.getTopicId());
        List<TopicForum> resultList = topicForumService.adminList(body.getAppId(), body.getForumId(), body.getTopicId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicForum.TOPIC_FORUM_MAP_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题论坛关联信息")
    @RequestMapping(value = "/topic/forum/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.TOPIC_FORUM_MAP_ID
        );

        TopicForum result = topicForumService.find(body.getTopicForumMapId());

        validateResponse(
                TopicForum.TOPIC_FORUM_MAP_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题论坛关联")
    @RequestMapping(value = "/topic/forum/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID
        );

        Boolean result = topicForumService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题论坛关联")
    @RequestMapping(value = "/topic/forum/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.TOPIC_FORUM_MAP_ID,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.TOPIC_ID,
                TopicForum.SYSTEM_VERSION
        );

        Boolean result = topicForumService.update(body, body.getTopicForumMapId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题论坛关联")
    @RequestMapping(value = "/topic/forum/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.TOPIC_FORUM_MAP_ID,
                TopicForum.APP_ID,
                TopicForum.SYSTEM_VERSION
        );

        Boolean result = topicForumService.delete(body.getTopicForumMapId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}