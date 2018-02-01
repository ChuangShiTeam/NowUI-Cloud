package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 话题收藏移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题收藏", description = "话题收藏移动端接口管理")
@RestController
public class TopicUserBookmarkMobileController extends BaseController {
	
	@Autowired
    private TopicUserBookmarkService topicUserBookmarkService;

	@Autowired
	private TopicUserUnbookmarkService topicUserUnbookmarkService;
	
	@ApiOperation(value = "新增话题收藏")
    @RequestMapping(value = "/topic/user/bookmark/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicUserBookmark body) {
        validateRequest(
                body,
                TopicUserBookmark.APP_ID,
                TopicUserBookmark.TOPIC_ID
        );
        
        String appId = body.getAppId();
        String topicId = body.getTopicId();
        String userId = body.getSystemRequestUserId();
        
        TopicUserBookmark bookmark = topicUserBookmarkService.findByTopicIdAndUserId(topicId, userId);
        
        if (bookmark != null) {
			throw new BusinessException("已经收藏过了");
		}
        
        Boolean result = topicUserBookmarkService.save(appId, topicId, userId, userId);
        
        if (result) {
            topicUserUnbookmarkService.deleteByTopicIdAndUserId(topicId, userId, userId);
        }

        return renderJson(result);
    }
	
//	@ApiOperation(value = "收藏话题的用户列表")
//    @RequestMapping(value = "/topic/user/bookmark/mobile/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> listV1(@RequestBody TopicUserBookmark body) {
//        validateRequest(
//                body,
//                TopicUserBookmark.APP_ID,
//                TopicUserBookmark.TOPIC_ID
//        );
//
//        //先去收藏表查询收藏话题的用户列表
//        List<TopicUserBookmark> listByTopicId = topicUserBookmarkService.allListByTopicId(body.getAppId(), body.getTopicId());
//        //再查询用户昵称,头像,是否关注
//        /**
//         * 等用户接口
//         */
//        
//        
//
//
//
//        return renderJson(null);
//    }
}