package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 话题用户取消收藏关联移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题用户取消收藏关联", description = "话题用户取消收藏关联移动端接口管理")
@RestController
public class TopicUserUnbookmarkMobileController extends BaseController {
	
	@Autowired
	private TopicUserUnbookmarkService topicUserUnbookmarkService;
	
	@Autowired
	private TopicUserBookmarkService topicUserBookmarkService;

	@ApiOperation(value = "新增话题用户取消收藏关联")
    @RequestMapping(value = "/topic/user/unbookmark/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicUserUnbookmark body) {
        validateRequest(
                body,
                TopicUserUnbookmark.APP_ID,
                TopicUserUnbookmark.TOPIC_ID,
                TopicUserUnbookmark.SYSTEM_REQUEST_USER_ID
        );
        
        String topicId = body.getTopicId();
        String userId = body.getSystemRequestUserId();

        TopicUserUnbookmark topicUserUnbookmark = topicUserUnbookmarkService.findByTopicIdAndUserId(topicId, userId);
        if (Util.isNullOrEmpty(topicUserUnbookmark)) {
        	throw new RuntimeException("你已经取消收藏过了");
		}

        body.setUserId(userId);
        Boolean result = topicUserUnbookmarkService.save(body, Util.getRandomUUID(), userId);

        if (result) {
            topicUserBookmarkService.deleteByTopicIdAndUserId(topicId, userId, userId);
        }
        return renderJson(result);
    }
}