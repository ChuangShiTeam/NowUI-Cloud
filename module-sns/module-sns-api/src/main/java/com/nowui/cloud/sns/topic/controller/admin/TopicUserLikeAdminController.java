package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 点赞话题关联管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "点赞话题关联", description = "点赞话题关联管理端接口管理")
@RestController
public class TopicUserLikeAdminController extends BaseController {

    @Autowired
    private TopicUserLikeService topicUserLikeService;

    @ApiOperation(value = "点赞话题关联列表")
    @RequestMapping(value = "/topic/user/like/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID,
                TopicUserLike.PAGE_INDEX,
                TopicUserLike.PAGE_SIZE
        );

        Integer resultTotal = topicUserLikeService.adminCount(body.getAppId() , body.getUserId(), body.getTopicId());
        List<TopicUserLike> resultList = topicUserLikeService.adminList(body.getAppId(), body.getUserId(), body.getTopicId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicUserLike.USER_LIKE_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "点赞话题关联信息")
    @RequestMapping(value = "/topic/user/like/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.USER_LIKE_ID
        );

        TopicUserLike result = topicUserLikeService.find(body.getUserLikeId());

        validateResponse(
                TopicUserLike.USER_LIKE_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增点赞话题关联")
    @RequestMapping(value = "/topic/user/like/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID
        );

        Boolean result = topicUserLikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改点赞话题关联")
    @RequestMapping(value = "/topic/user/like/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.USER_LIKE_ID,
                TopicUserLike.APP_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID,
                TopicUserLike.SYSTEM_VERSION
        );

        Boolean result = topicUserLikeService.update(body, body.getUserLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除点赞话题关联")
    @RequestMapping(value = "/topic/user/like/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.USER_LIKE_ID,
                TopicUserLike.APP_ID,
                TopicUserLike.SYSTEM_VERSION
        );

        Boolean result = topicUserLikeService.delete(body.getUserLikeId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}