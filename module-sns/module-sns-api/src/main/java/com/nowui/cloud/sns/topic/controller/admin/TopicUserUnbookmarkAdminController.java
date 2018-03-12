package com.nowui.cloud.sns.topic.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.router.TopicUserUnbookmarkRouter;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicUserUnbookmarkView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 话题用户取消收藏关联管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题用户取消收藏关联", description = "话题用户取消收藏关联管理端接口管理")
@RestController
public class TopicUserUnbookmarkAdminController extends BaseController {

    @Autowired
    private TopicUserUnbookmarkService topicUserUnbookmarkService;

    @ApiOperation(value = "话题用户取消收藏关联列表")
    @RequestMapping(value = "/topic/user/unbookmark/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicUserUnbookmark body) {
        validateRequest(
                body,
                TopicUserUnbookmark.APP_ID,
                TopicUserUnbookmark.TOPIC_ID,
                TopicUserUnbookmark.MEMBER_ID,
                TopicUserUnbookmark.PAGE_INDEX,
                TopicUserUnbookmark.PAGE_SIZE
        );

        Integer resultTotal = topicUserUnbookmarkService.countForAdmin(body.getAppId() , body.getTopicId(), body.getMemberId());
        List<TopicUserUnbookmark> resultList = topicUserUnbookmarkService.listForAdmin(body.getAppId(), body.getTopicId(), body.getMemberId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                TopicUserUnbookmark.TOPIC_USER_UNBOOKMARK_ID,
                TopicUserUnbookmark.TOPIC_ID,
                TopicUserUnbookmark.MEMBER_ID
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "话题用户取消收藏关联信息")
    @RequestMapping(value = "/topic/user/unbookmark/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody TopicUserUnbookmark body) {
        validateRequest(
                body,
                TopicUserUnbookmark.APP_ID,
                TopicUserUnbookmark.TOPIC_USER_UNBOOKMARK_ID
        );

        TopicUserUnbookmarkView result = topicUserUnbookmarkService.find(body.getTopicUserUnbookmarkId());

        validateResponse(
                TopicUserUnbookmark.TOPIC_USER_UNBOOKMARK_ID,
                TopicUserUnbookmark.TOPIC_ID,
                TopicUserUnbookmark.MEMBER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增话题用户取消收藏关联")
    @RequestMapping(value = "/topic/user/unbookmark/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicUserUnbookmark body) {
        validateRequest(
                body,
                TopicUserUnbookmark.APP_ID,
                TopicUserUnbookmark.TOPIC_ID,
                TopicUserUnbookmark.MEMBER_ID
        );

//        Boolean result = topicUserUnbookmarkService.save(body, Util.getRandomUUID(), body.getAppId(), TopicUserUnbookmarkRouter.TOPIC_USER_UNBOOKMARK_V1_SAVE, body.getSystemRequestUserId());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "修改话题用户取消收藏关联")
    @RequestMapping(value = "/topic/user/unbookmark/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody TopicUserUnbookmark body) {
        validateRequest(
                body,
                TopicUserUnbookmark.TOPIC_USER_UNBOOKMARK_ID,
                TopicUserUnbookmark.APP_ID,
                TopicUserUnbookmark.TOPIC_ID,
                TopicUserUnbookmark.MEMBER_ID,
                TopicUserUnbookmark.SYSTEM_VERSION
        );

//        Boolean result = topicUserUnbookmarkService.update(body, body.getTopicUserUnbookmarkId(), body.getAppId(), TopicUserUnbookmarkRouter.TOPIC_USER_UNBOOKMARK_V1_UPDATE, body.getSystemRequestUserId(), body.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }

    @ApiOperation(value = "删除话题用户取消收藏关联")
    @RequestMapping(value = "/topic/user/unbookmark/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicUserUnbookmark body) {
        validateRequest(
                body,
                TopicUserUnbookmark.TOPIC_USER_UNBOOKMARK_ID,
                TopicUserUnbookmark.APP_ID,
                TopicUserUnbookmark.SYSTEM_VERSION
        );

//        Boolean result = topicUserUnbookmarkService.delete(body.getTopicUserUnbookmarkId(), body.getAppId(), TopicUserUnbookmarkRouter.TOPIC_USER_UNBOOKMARK_V1_DELETE, body.getSystemRequestUserId(), body.getSystemVersion());

//        return renderJson(result);
        return renderJson(null);
    }
    
    
    @ApiOperation(value = "动态取消收藏数据同步")
    @RequestMapping(value = "/topic/user/unbookmark/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1(@RequestBody TopicUserUnbookmark body) {
    	List<TopicUserUnbookmark> unbookmarks = topicUserUnbookmarkService.listByMysql();
    	
    	for (TopicUserUnbookmark unbookmark : unbookmarks) {
			TopicUserUnbookmarkView unbookmarkView = new TopicUserUnbookmarkView();
			unbookmarkView.putAll(unbookmark);
			topicUserUnbookmarkService.update(unbookmarkView);
		}

        return renderJson(true);
    }

}