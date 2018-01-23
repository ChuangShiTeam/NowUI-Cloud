package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.rpc.MemberFollowRpc;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 话题信息移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "话题信息", description = "话题信息移动端接口管理")
@RestController
public class TopicMobileController extends BaseController {

	@Autowired
    private TopicService topicService;
	
	@Autowired
	private TopicForumService topicForumService;
	
	@Autowired
	private TopicMediaService topicMediaService;
	
	@Autowired
	private TopicTipService topicTipService;

	@Autowired
	private FileRpc fileRpc;
	
	@Autowired
	private MemberRpc memberRpc;
	
	@Autowired
	private MemberFollowRpc memberFollowRpc;

    @ApiOperation(value = "论坛中的话题信息列表")
    @RequestMapping(value = "/topic/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.PAGE_INDEX,
                TopicForum.PAGE_SIZE
        );

        Integer resultTotal = topicForumService.countForAdmin(body.getAppId(), body.getForumId(), null);
        
        List<Topic> resultList = topicService.allTopicListByForumId(body);
        
        //处理用户信息
       //在controller层调用其他接口处理发布话题者信息(昵称,头像,是否关注)
        String userIds = Util.beanToFieldString(resultList, Topic.USER_ID);
        
        List<Member> nickAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());
        
        resultList = Util.beanAddField(
        		resultList, 
        		Topic.USER_ID, 
        		User.USER_ID, 
        		nickAndAvatarAndIsFollowList, 
        		User.USER_ID,
        		UserAvatar.USER_AVATAR,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW
        	);
        
        
        //处理话题图片
        for (Topic topic : resultList) {
        	
            List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);
          
            String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA_ID);
            List<File> fileList = fileRpc.findsV1(fileIds);
            
            topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA_ID, fileList, File.FILE_PATH);
            
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
        }
        
        
        
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_MEDIA_LIST,
                User.USER_ID,
        		UserAvatar.USER_AVATAR,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW
        );

        return renderJson(resultTotal, resultList);
    }
    
    @ApiOperation(value = "个人/别人主页动态列表")
    @RequestMapping(value = "/topic/mobile/v1/home/topic", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeTopicV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.USER_ID,//需要前端传过来,那么这个接口可以用于别人的主页和个人的主页
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );

        Integer resultTotal = topicService.countForAdmin(body.getAppId(), null, body.getUserId(), null, null);
        List<Topic> resultList = topicService.allTopicListByUserId(body);

        //处理用户信息(昵称,头像),由其他接口处理返回前端
        
        //处理话题图片
        for (Topic topic : resultList) {
            List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

            String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA_ID);
            List<File> fileList = fileRpc.findsV1(fileIds);

            topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA_ID, fileList, File.FILE_PATH);

            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
        }

        
        
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_MEDIA_LIST
        );

        return renderJson(resultTotal, resultList);
    }
    
    
    
	@ApiOperation(value = "话题详情页")
	@RequestMapping(value = "/topic/mobile/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> findV1(@RequestBody Topic body) {
	    validateRequest(
	            body,
	            Topic.APP_ID,
	            Topic.TOPIC_ID,
	            Topic.SYSTEM_REQUEST_USER_ID
	    );

	    String topicId = body.getTopicId();
	    String userId = body.getSystemRequestUserId();
	    
	    Topic topic = topicService.findDetailByTopicIdAndUserId(topicId, userId);

	    //处理用户信息(昵称,头像,是否关注)
	    Member nickNameAndAvatarAndIsFollow = memberRpc.nickNameAndAvatarAndIsFollowFindV1(topic.getUserId(), userId);
	    topic.put(Topic.USER_ID, nickNameAndAvatarAndIsFollow);

	    
	    //处理图片
	    List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

        String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA);
        List<File> fileList = fileRpc.findsV1(fileIds);
        
       // topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_PATH);
        topicMediaList = Util.beanReplaceField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_ID, File.FILE_PATH);
        //这里本来就是从topic里面取出来的,还用不用再放回去?引用的地址?
        topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
        
        
        //处理点赞的用户头像
        List<TopicUserLike> userLikeList = (List<TopicUserLike>)topic.get(Topic.TOPIC_USER_LIKE_LIST);
        
//        if (userLikeList != null) {
        	String userIds = Util.beanToFieldString(userLikeList, TopicUserLike.USER_ID);
            List<Member> nickAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());
            
            userLikeList = Util.beanAddField(
            		userLikeList,
            		TopicUserLike.USER_ID,
            		User.USER_ID,
            		nickAndAvatarAndIsFollowList,
            		User.USER_ID,
            		UserAvatar.USER_AVATAR,
            		UserNickName.USER_NICK_NAME,
            		MemberFollow.MEMBER_IS_FOLLOW
            	);
            topic.put(Topic.TOPIC_USER_LIKE_LIST, userLikeList);

//		}

        validateResponse(
	            Topic.TOPIC_ID,
	            Topic.TOPIC_SUMMARY,
	            Topic.USER_ID,
	            Topic.LATITUDE,
	            Topic.LONGTITUDE,
	            Topic.TOPIC_LOCATION,
	            Topic.TOPIC_IS_LOCATION,
	            Topic.TOPIC_IS_TOP,
	            Topic.TOPIC_IS_RECOMAND,
	            Topic.TOPIC_TOP_LEVEL,
	            Topic.TOPIC_MEDIA_LIST,
	            Topic.TOPIC_FORUM_LIST,
	            Topic.TOPIC_COMMENT_LIST,
	            Topic.TOPIC_COUNT_BOOKMARK,
	            Topic.TOPIC_COUNT_LIKE,
	            Topic.TOPIC_COUNT_COMMENT,
	            Topic.TOPIC_USER_IS_BOOKEMARK,
	            Topic.TOPIC_USER_IS_LIKE,
	            Topic.TOPIC_USER_LIKE_LIST,
	            BaseEntity.SYSTEM_CREATE_TIME
	    );

	    return renderJson(topic);
	}
    
    @ApiOperation(value = "动态主页列表")
    @RequestMapping(value = "/topic/mobile/v1/home/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> followListV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE,
                Topic.SYSTEM_REQUEST_USER_ID
        );
       
        // 用户关注的人的编号列表
        List<String> followUserIdList = memberFollowRpc.followUserIdList(body.getSystemRequestUserId());
        // 加上本人的用户编号
        followUserIdList.add(body.getSystemRequestUserId());
        
        Integer countResult = topicService.countByUserIdList(body.getAppId(), followUserIdList);
        List<Topic> resultList = topicService.listDetailByUserIdList(body.getAppId(), body.getSystemRequestUserId(), followUserIdList, body.getPageIndex(), body.getPageSize());
        
        // 在controller层调用其他接口处理发布话题者信息(昵称,头像,是否关注)
        String userIds = Util.beanToFieldString(resultList, Topic.USER_ID);
        List<Member> nickAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());
        
        resultList = Util.beanAddField(
                resultList, 
        		Topic.USER_ID, 
        		User.USER_ID, 
        		nickAndAvatarAndIsFollowList, 
        		User.USER_ID,
        		UserAvatar.USER_AVATAR,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW
    	);
        
        
        // 图片多媒体
        for (Topic topic : resultList) {
            List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

            String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA);
            List<File> fileList = fileRpc.findsV1(fileIds);

//            topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_PATH);
            topicMediaList = Util.beanReplaceField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_ID, File.FILE_PATH);
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
            
            
            
            
        }
        
      
        
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_FORUM_LIST,
                Topic.TOPIC_MEDIA_LIST,
                Topic.TOPIC_COMMENT_LIST,
	            Topic.TOPIC_COUNT_BOOKMARK,
	            Topic.TOPIC_COUNT_LIKE,
	            Topic.TOPIC_COUNT_COMMENT,
	            Topic.TOPIC_USER_IS_BOOKEMARK,
	            Topic.TOPIC_USER_LIKE_LIST,
	            
                User.USER_ID,
        		UserAvatar.USER_AVATAR,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW,
        		BaseEntity.SYSTEM_CREATE_TIME
        );

        return renderJson(countResult, resultList);
    }

    
    
    @ApiOperation(value = "新增话题信息")
    @RequestMapping(value = "/topic/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_SUMMARY,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION
        );
        
        JSONArray topicMediaJsonArray = body.getJSONArray(Topic.TOPIC_MEDIA_LIST);
        if (Util.isNullOrEmpty(topicMediaJsonArray)) {
            throw new RuntimeException("图片不能为空");
        }
        
        JSONArray forumIdJSONArray = body.getJSONArray(Topic.TOPIC_FORUM_LIST);
        JSONArray tipUserIdJSONArray = body.getJSONArray(Topic.TOPIC_TIP_USER_LIST);
        
        String topicId = Util.getRandomUUID();
        String userId = body.getSystemRequestUserId();
        String appId = body.getAppId();
        
        body.setUserId(userId);
        body.setTopicIsTop(false);
        body.setTopicIsRecommend(false);
        // 保存话题
        Boolean result = topicService.save(body, topicId, userId);
        
        if (result) {
        	
            // 保存话题多媒体
            List<TopicMedia> topicMediaList = JSONArray.parseArray(topicMediaJsonArray.toJSONString(), TopicMedia.class);
            topicMediaService.batchSave(appId, topicId, topicMediaList, userId);

            // 保存话题论坛
            if (!Util.isNullOrEmpty(forumIdJSONArray)) {
                List<String> forumIdList = forumIdJSONArray.toJavaList(String.class);
                
                List<TopicForum> topicForumList = forumIdList.stream().map(forumId -> {
                    TopicForum topicForum = new TopicForum();
                    topicForum.setForumId(forumId);
                    return topicForum;
                }).collect(Collectors.toList());
                
                topicForumService.batchSave(appId, topicId, topicForumList, userId);
            }
            
            // 保存提醒谁看
            if (!Util.isNullOrEmpty(tipUserIdJSONArray)) {
                List<String> tipUserIdList = tipUserIdJSONArray.toJavaList(String.class);
                
                List<TopicTip> topicTipList = tipUserIdList.stream().map(tipUserId -> {
                    TopicTip topicTip = new TopicTip();
                    topicTip.setUserId(tipUserId);
                    return topicTip;
                }).collect(Collectors.toList());

                topicTipService.batchSave(appId, topicId, topicTipList, userId);
            }
		}
        return renderJson(result);
    }

    @ApiOperation(value = "删除话题信息")
    @RequestMapping(value = "/topic/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.TOPIC_ID,
                Topic.APP_ID
        );
        String topicId = body.getTopicId();
        String systemRequestUserId = body.getSystemRequestUserId();

        //删除话题信息
        Topic topic = topicService.find(topicId);

        Boolean result = topicService.deleteByTopicId(body.getAppId(), topicId, systemRequestUserId, topic.getSystemVersion());
        
        return renderJson(result);
    }

}