package com.nowui.cloud.sns.topic.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 话题评论移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题评论", description = "话题评论移动端接口管理")
@RestController
public class TopicCommentMobileController extends BaseController {
	
	@Autowired
    private TopicCommentService topicCommentService;
	
	@Autowired
	private TopicTipService topicTipService;

    @ApiOperation(value = "话题详情页评论列表")
    @RequestMapping(value = "/topic/comment/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.TOPIC_ID,
                TopicComment.PAGE_INDEX,
                TopicComment.PAGE_SIZE
        );

        Integer resultTotal = topicCommentService.countForAdmin(body.getAppId() , null, body.getTopicId(), null, null, null);
        List<TopicComment> resultList = topicCommentService.listForAdmin(body.getAppId(), null, body.getTopicId(), null, null, null, body.getPageIndex(), body.getPageSize());
/**
 * TODO 评论要调用 用户接口 查询用户头像
 */
        validateResponse(
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_COMMENT_ID
        );

        return renderJson(resultTotal, resultList);
    }
    
    @ApiOperation(value = "新增话题评论")
    @RequestMapping(value = "/topic/comment/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.APP_ID,
                TopicComment.USER_ID,
                TopicComment.TOPIC_ID,
                TopicComment.TOPIC_COMMENT_CONTENT,
                TopicComment.TOPIC_REPLAY_USER_ID,
                TopicComment.TOPIC_REPLY_COMMENT_ID
        );

        Boolean result = topicCommentService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());
        //提醒被回复人
        if (body.getTopicReplayUserId() != null || "" != body.getTopicReplayUserId() ) {
			//把被回复人添加到回复表
        	TopicTip topicTip = new TopicTip();
        	topicTip.setAppId(body.getAppId());
        	topicTip.setTopicId(body.getTopicId());
        	topicTip.setUserId(body.getTopicReplayUserId());
        	topicTipService.save(topicTip, Util.getRandomUUID(), body.getSystemRequestUserId());
		}

        return renderJson(result);
    }
    
    
    @ApiOperation(value = "删除话题评论")
    @RequestMapping(value = "/topic/comment/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody TopicComment body) {
        validateRequest(
                body,
                TopicComment.TOPIC_COMMENT_ID,
                TopicComment.APP_ID
        );
        TopicComment TopicComment = topicCommentService.find(body.getTopicCommentId());
        Boolean result = topicCommentService.delete(body.getTopicCommentId(), body.getSystemRequestUserId(), TopicComment.getSystemVersion());

        return renderJson(result);
    }
}