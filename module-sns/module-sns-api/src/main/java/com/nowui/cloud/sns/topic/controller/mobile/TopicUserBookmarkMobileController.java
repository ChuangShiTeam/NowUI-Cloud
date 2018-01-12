package com.nowui.cloud.sns.topic.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
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
 * 话题用户收藏关联移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题用户收藏关联", description = "话题用户收藏关联移动端接口管理")
@RestController
public class TopicUserBookmarkMobileController extends BaseController {
	
	@Autowired
    private TopicUserBookmarkService topicUserBookmarkService;

	@Autowired
	private TopicUserUnbookmarkService topicUserUnbookmarkService;
	
	@ApiOperation(value = "新增话题用户收藏关联")
    @RequestMapping(value = "/topic/user/bookmark/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicUserBookmark body) {
        validateRequest(
                body,
                TopicUserBookmark.APP_ID,
                TopicUserBookmark.TOPIC_ID
                //TopicUserBookmark.USER_ID
        );
        
        TopicUserBookmark bookmark = topicUserBookmarkService.findTopicUserBookmark(body.getAppId(), body.getTopicId(), body.getSystemRequestUserId());
        if (bookmark != null) {
			return renderJson(true);
		}
        
        
        //先去取消收藏表查询有没有记录,有:删除,没有:不操作
        TopicUserUnbookmark findUnBookMark = topicUserUnbookmarkService.findUnBookMark(body.getAppId(), body.getTopicId(), body.getSystemRequestUserId());
        
        if (findUnBookMark != null) {
        	topicUserUnbookmarkService.delete(findUnBookMark.getUserUnBookMarkId(), body.getSystemUpdateUserId(), findUnBookMark.getSystemVersion());
		}
        body.setUserId(body.getSystemRequestUserId());

        Boolean result = topicUserBookmarkService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }
}