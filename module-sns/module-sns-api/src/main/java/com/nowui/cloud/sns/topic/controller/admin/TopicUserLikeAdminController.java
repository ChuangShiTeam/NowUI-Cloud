package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.router.TopicUserLikeRouter;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;

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
    @RequestMapping(value = "/topic/user/like/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID,
                TopicUserLike.PAGE_INDEX,
                TopicUserLike.PAGE_SIZE
        );

        Integer resultTotal = topicUserLikeService.countForAdmin(body.getAppId() , body.getUserId(), body.getTopicId());
        List<TopicUserLike> resultList = topicUserLikeService.listForAdmin(body.getAppId(), body.getUserId(), body.getTopicId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicUserLike.TOPIC_USER_LIKE_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "点赞话题关联信息")
    @RequestMapping(value = "/topic/user/like/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.TOPIC_USER_LIKE_ID
        );

        TopicUserLikeView result = topicUserLikeService.find(body.getTopicUserLikeId());

        validateResponse(
                TopicUserLike.TOPIC_USER_LIKE_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增点赞话题关联")
    @RequestMapping(value = "/topic/user/like/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID
        );

//        Boolean result = topicUserLikeService.save(body, Util.getRandomUUID(), body.getAppId(), TopicUserLikeRouter.TOPIC_USER_LIKE_V1_SAVE, body.getSystemRequestUserId());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "修改点赞话题关联")
    @RequestMapping(value = "/topic/user/like/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.TOPIC_USER_LIKE_ID,
                TopicUserLike.APP_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID,
                TopicUserLike.SYSTEM_VERSION
        );

//        Boolean result = topicUserLikeService.update(body, body.getTopicUserLikeId(), body.getAppId(), TopicUserLikeRouter.TOPIC_USER_LIKE_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "删除点赞话题关联")
    @RequestMapping(value = "/topic/user/like/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.TOPIC_USER_LIKE_ID,
                TopicUserLike.APP_ID,
                TopicUserLike.SYSTEM_VERSION
        );

//        Boolean result = topicUserLikeService.delete(body.getTopicUserLikeId(), body.getAppId(), TopicUserLikeRouter.TOPIC_USER_LIKE_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

}