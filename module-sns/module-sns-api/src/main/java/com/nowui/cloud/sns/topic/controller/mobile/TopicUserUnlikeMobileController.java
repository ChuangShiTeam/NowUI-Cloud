package com.nowui.cloud.sns.topic.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import javax.swing.plaf.BorderUIResource.BevelBorderUIResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题用户取消点赞关联移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题用户取消点赞关联", description = "话题用户取消点赞关联移动端接口管理")
@RestController
public class TopicUserUnlikeMobileController extends BaseController {

	@Autowired
    private TopicUserUnlikeService topicUserUnlikeService;
	
	@Autowired
	private TopicUserLikeService topicUserLikeService;
	
	@ApiOperation(value = "新增话题用户取消点赞关联")
    @RequestMapping(value = "/topic/user/unlike/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicUserUnlike body) {
        validateRequest(
                body,
                TopicUserUnlike.APP_ID,
                //TopicUserUnlike.USER_ID,
                TopicUserUnlike.TOPIC_ID
        );
        
        TopicUserUnlike unlike = topicUserUnlikeService.findUnlike(body.getAppId(), body.getSystemRequestUserId(), body.getTopicId());
        if (unlike != null) {
        	return renderJson(true);
		}
        
        //先去点赞表查询,是否有记录
        TopicUserLike like = topicUserLikeService.findByTopicIdAndUserId(body.getTopicId(), body.getSystemRequestUserId());
        if (like != null) {
        	Boolean delete = topicUserLikeService.delete(like.getTopicUserLikeId(), body.getSystemUpdateUserId(), like.getSystemVersion());
		}
        
        body.setUserId(body.getSystemRequestUserId());
        Boolean result = topicUserUnlikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }
}