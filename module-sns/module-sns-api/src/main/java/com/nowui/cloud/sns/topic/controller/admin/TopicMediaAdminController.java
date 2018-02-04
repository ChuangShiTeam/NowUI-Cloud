package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.router.TopicMediaRouter;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.view.TopicMediaView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题多媒体管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题多媒体", description = "话题多媒体管理端接口管理")
@RestController
public class TopicMediaAdminController extends BaseController {

    @Autowired
    private TopicMediaService topicMediaService;

    @ApiOperation(value = "话题多媒体列表")
    @RequestMapping(value = "/topic/media/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicMedia body) {
        validateRequest(
                body,
                TopicMedia.APP_ID,
                TopicMedia.TOPIC_ID,
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.TOPIC_MEDIA_TYPE,
                TopicMedia.PAGE_INDEX,
                TopicMedia.PAGE_SIZE
        );

        Integer resultTotal = topicMediaService.countForAdmin(body.getAppId() , body.getTopicId(), body.getTopicMedia(), body.getTopicMediaType());
        List<TopicMedia> resultList = topicMediaService.listForAdmin(body.getAppId(), body.getTopicId(), body.getTopicMedia(), body.getTopicMediaType(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.TOPIC_ID,
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.TOPIC_MEDIA_TYPE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题多媒体信息")
    @RequestMapping(value = "/topic/media/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody TopicMedia body) {
        validateRequest(
                body,
                TopicMedia.APP_ID,
                TopicMedia.TOPIC_MEDIA
        );

        TopicMediaView result = topicMediaService.find(body.getTopicMediaId());

        validateResponse(
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.TOPIC_ID,
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.TOPIC_MEDIA_TYPE
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题多媒体")
    @RequestMapping(value = "/topic/media/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicMedia body) {
        validateRequest(
                body,
                TopicMedia.APP_ID,
                TopicMedia.TOPIC_ID,
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.TOPIC_MEDIA_TYPE
        );

        Boolean result = topicMediaService.save(body, Util.getRandomUUID(), body.getAppId(), TopicMediaRouter.TOPIC_MEDIA_V1_SAVE, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改话题多媒体")
    @RequestMapping(value = "/topic/media/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody TopicMedia body) {
        validateRequest(
                body,
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.APP_ID,
                TopicMedia.TOPIC_ID,
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.TOPIC_MEDIA_TYPE,
                TopicMedia.SYSTEM_VERSION
        );

        Boolean result = topicMediaService.update(body, body.getTopicMediaId(), body.getAppId(), TopicMediaRouter.TOPIC_MEDIA_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除话题多媒体")
    @RequestMapping(value = "/topic/media/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicMedia body) {
        validateRequest(
                body,
                TopicMedia.TOPIC_MEDIA,
                TopicMedia.APP_ID,
                TopicMedia.SYSTEM_VERSION
        );

        Boolean result = topicMediaService.delete(body.getTopicMediaId(), body.getAppId(), TopicMediaRouter.TOPIC_MEDIA_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}