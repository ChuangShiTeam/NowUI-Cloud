package com.nowui.cloud.sns.topic.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
                TopicUserUnbookmark.TOPIC_ID
                //TopicUserUnbookmark.USER_ID
        );

        TopicUserUnbookmark UnBookMark = topicUserUnbookmarkService.findUnBookMark(body.getAppId(), body.getTopicId(), body.getSystemRequestUserId());
        if (UnBookMark != null) {
        	//取消点赞表有记录就返回true,不再执行
        	return renderJson(true);
		}

        //先查找取消表有没有收藏记录,有:删除,没有:不操作
        TopicUserBookmark bookmark = topicUserBookmarkService.findByTopicIdAndUserId(body.getTopicId(), body.getSystemRequestUserId());
        if (bookmark != null) {
        	topicUserBookmarkService.delete(bookmark.getTopicUserBookmarkId(), body.getSystemUpdateUserId(), bookmark.getSystemVersion());
		}

        body.setUserId(body.getSystemRequestUserId());
        Boolean result = topicUserUnbookmarkService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }
}