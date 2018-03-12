package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.sns.topic.view.TopicUserBookmarkView;
import com.nowui.cloud.sns.topic.view.TopicUserUnbookmarkView;
import com.nowui.cloud.sns.topic.view.TopicView;
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
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private MemberRpc memberRpc;

	@ApiOperation(value = "新增话题用户取消收藏关联")
    @RequestMapping(value = "/topic/user/unbookmark/mobile/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
		TopicUserUnbookmark body = getEntry(TopicUserUnbookmark.class);
        validateRequest(
                body,
                TopicUserUnbookmark.APP_ID,
                TopicUserUnbookmark.TOPIC_ID,
                TopicUserUnbookmark.SYSTEM_REQUEST_USER_ID,
                TopicUserUnbookmark.MEMBER_ID
        );
        
        String topicId = body.getTopicId();
        String requestUserId = body.getSystemRequestUserId();
        String memberId = body.getMemberId();


        TopicUserUnbookmarkView topicUserUnbookmark = topicUserUnbookmarkService.findByTopicIdAndMemberId(topicId, memberId);
        if (!Util.isNullOrEmpty(topicUserUnbookmark)) {
        	throw new BusinessException("你已经取消收藏过了");
		}

        body.setMemberId(memberId);
        TopicUserUnbookmark result = topicUserUnbookmarkService.save(body, Util.getRandomUUID(), requestUserId);
        
        boolean success = false;
        
        if (result != null) {
        	
            TopicUserBookmark userBookmark = topicUserBookmarkService.deleteByTopicIdAndMemberId(topicId, memberId, body.getAppId(), requestUserId);
            
            if (userBookmark != null) {
            	// 删除收藏记录
                TopicUserBookmarkView bookmarkView = JSON.parseObject(userBookmark.toJSONString(), TopicUserBookmarkView.class);
                topicUserBookmarkService.delete(bookmarkView);
			}
            
            // 新增取消收藏记录
            TopicUserUnbookmarkView unBookmarkView = JSON.parseObject(result.toJSONString(), TopicUserUnbookmarkView.class);
            topicUserUnbookmarkService.save(unBookmarkView);
            
            // TODO 更新话题视图中的收藏数量: 减1
            TopicView topicView = topicService.find(topicId);
            Integer topicCountBookmark = topicView.getTopicCountBookmark();
            topicView.setTopicCountBookmark(topicCountBookmark - 1);
            topicService.update(topicView);
            
            //sendMessage(result, TopicUserUnbookmarkRouter.TOPIC_USER_UNBOOKMARK_V1_SAVE, appId, userId);
            
            success = true;
        }
        return renderJson(success);
    }
}