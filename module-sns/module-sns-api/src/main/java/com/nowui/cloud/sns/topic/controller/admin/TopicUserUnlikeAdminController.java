package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题用户取消点赞关联管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题用户取消点赞关联", description = "话题用户取消点赞关联管理端接口管理")
@RestController
public class TopicUserUnlikeAdminController extends BaseController {

    @Autowired
    private TopicUserUnlikeService topicUserUnlikeService;

    @ApiOperation(value = "话题用户取消点赞关联列表")
    @RequestMapping(value = "/topic/user/unlike/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.USER_ID,
                TopicUserUnlike.TOPIC_ID,
                TopicUserUnlike.PAGE_INDEX,
                TopicUserUnlike.PAGE_SIZE
        );

        Integer resultTotal = topicUserUnlikeService.adminCount(body.getAppId() , body.getUserId(), body.getTopicId());
        List<TopicUserUnlike> resultList = topicUserUnlikeService.adminList(body.getAppId(), body.getUserId(), body.getTopicId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicUserUnlike.USER_UN_LIKE_ID,
                TopicUserUnlike.USER_ID,
                TopicUserUnlike.TOPIC_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题用户取消点赞关联信息")
    @RequestMapping(value = "/topic/user/unlike/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.USER_UN_LIKE_ID
        );

        TopicUserUnlike result = topicUserUnlikeService.find(body.getUserUnLikeId());

        validateResponse(
                TopicUserUnlike.USER_UN_LIKE_ID,
                TopicUserUnlike.USER_ID,
                TopicUserUnlike.TOPIC_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题用户取消点赞关联")
    @RequestMapping(value = "/topic/user/unlike/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.USER_ID,
                TopicUserUnlike.TOPIC_ID
        );

        Boolean result = topicUserUnlikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题用户取消点赞关联")
    @RequestMapping(value = "/topic/user/unlike/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.USER_UN_LIKE_ID,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.USER_ID,
                TopicUserUnlike.TOPIC_ID,
                TopicUserUnlike.SYSTEM_VERSION
        );

        Boolean result = topicUserUnlikeService.update(body, body.getUserUnLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题用户取消点赞关联")
    @RequestMapping(value = "/topic/user/unlike/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.USER_UN_LIKE_ID,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.SYSTEM_VERSION
        );

        Boolean result = topicUserUnlikeService.delete(body.getUserUnLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}