package com.nowui.cloud.sns.topic.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
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
 * 点赞话题关联移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "点赞话题关联", description = "点赞话题关联移动端接口管理")
@RestController
public class TopicUserLikeMobileController extends BaseController {

	@Autowired
    private TopicUserLikeService topicUserLikeService;

	@Autowired
	private TopicUserUnlikeService topicUserUnlikeService;

    @ApiOperation(value = "点赞话题关联列表")
    @RequestMapping(value = "/topic/user/like/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                TopicUserLike.TOPIC_ID,
                TopicUserLike.PAGE_INDEX,
                TopicUserLike.PAGE_SIZE     //前端传来参数可用于只显示4个头像,和点赞列表
        );
        //统计话题点赞数
        Integer resultTotal = topicUserLikeService.countForAdmin(body.getAppId() , null, body.getTopicId());
        //得到话题点赞列表
        List<TopicUserLike> resultList = topicUserLikeService.listForAdmin(body.getAppId(), null, body.getTopicId(), body.getPageIndex(), body.getPageSize());
        //遍历得到点赞用户的id,然后查询用户的头像,昵称
        for (TopicUserLike topicUserLike : resultList) {
			String userId = topicUserLike.getUserId();
			/**
			 * 等用户接口
			 * 
			 * 得到用户头像,昵称,在TopicUserLike 实体类中添加相应字段常量,然后调整返回参数
			 */
		}



        validateResponse(
                TopicUserLike.USER_LIKE_ID,
                TopicUserLike.USER_ID,
                TopicUserLike.TOPIC_ID
        );

        return renderJson(resultTotal, resultList);
    }


    @ApiOperation(value = "新增点赞话题关联")
    @RequestMapping(value = "/topic/user/like/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody TopicUserLike body) {
        validateRequest(
                body,
                TopicUserLike.APP_ID,
                //TopicUserLike.USER_ID,这个参数好像不传过来?那下面自己设置
                TopicUserLike.TOPIC_ID
        );

        TopicUserLike userLike = topicUserLikeService.findLike(body.getAppId(), body.getSystemRequestUserId(), body.getTopicId());
        if (userLike != null) {
        	//点赞表有记录就返回,不用新增
        	return renderJson(true);
		}


        //先去取消点赞表查询,有:修改,没有:不做操作
        TopicUserUnlike unlike = topicUserUnlikeService.findUnlike(body.getAppId(), body.getSystemRequestUserId(), body.getTopicId());
        if (unlike != null) {
        	Boolean delete = topicUserUnlikeService.delete(unlike.getUserUnLikeId(), body.getSystemUpdateUserId(), unlike.getSystemVersion());
		}


        body.setUserId(body.getSystemRequestUserId());

        Boolean result = topicUserLikeService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }
}