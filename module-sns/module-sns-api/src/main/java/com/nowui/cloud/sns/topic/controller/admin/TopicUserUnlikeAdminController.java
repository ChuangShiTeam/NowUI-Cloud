package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.router.TopicUserUnlikeRouter;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicUserUnlikeView;

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
    @RequestMapping(value = "/topic/user/unlike/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.MEMBER_ID,
                TopicUserUnlike.TOPIC_ID,
                TopicUserUnlike.PAGE_INDEX,
                TopicUserUnlike.PAGE_SIZE
        );

        Integer resultTotal = topicUserUnlikeService.countForAdmin(body.getAppId() , body.getMemberId(), body.getTopicId());
        List<TopicUserUnlike> resultList = topicUserUnlikeService.listForAdmin(body.getAppId(), body.getMemberId(), body.getTopicId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicUserUnlike.TOPIC_USER_UNLIKE_ID,
                TopicUserUnlike.MEMBER_ID,
                TopicUserUnlike.TOPIC_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题用户取消点赞关联信息")
    @RequestMapping(value = "/topic/user/unlike/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.TOPIC_USER_UNLIKE_ID
        );

        TopicUserUnlikeView result = topicUserUnlikeService.find(body.getTopicUserUnlikeId());

        validateResponse(
                TopicUserUnlike.TOPIC_USER_UNLIKE_ID,
                TopicUserUnlike.MEMBER_ID,
                TopicUserUnlike.TOPIC_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题用户取消点赞关联")
    @RequestMapping(value = "/topic/user/unlike/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.MEMBER_ID,
                TopicUserUnlike.TOPIC_ID
        );

//        Boolean result = topicUserUnlikeService.save(body, Util.getRandomUUID(), body.getAppId(), TopicUserUnlikeRouter.TOPIC_USER_UNLIKE_V1_SAVE, body.getSystemRequestUserId());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "修改话题用户取消点赞关联")
    @RequestMapping(value = "/topic/user/unlike/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.TOPIC_USER_UNLIKE_ID,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.MEMBER_ID,
                TopicUserUnlike.TOPIC_ID,
                TopicUserUnlike.SYSTEM_VERSION
        );

//        Boolean result = topicUserUnlikeService.update(body, body.getTopicUserUnlikeId(), body.getAppId(), TopicUserUnlikeRouter.TOPIC_USER_UNLIKE_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "删除话题用户取消点赞关联")
    @RequestMapping(value = "/topic/user/unlike/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.TOPIC_USER_UNLIKE_ID,
                TopicUserUnlike.APP_ID,
                TopicUserUnlike.SYSTEM_VERSION
        );

//        Boolean result = topicUserUnlikeService.delete(body.getTopicUserUnlikeId(), body.getAppId(), TopicUserUnlikeRouter.TOPIC_USER_UNLIKE_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

}