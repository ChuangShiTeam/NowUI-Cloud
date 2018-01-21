package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.entity.enums.FileType;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.rpc.MemberFollowRpc;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
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
	private TopicCommentService topicCommentService;
	
	@Autowired
	private TopicUserBookmarkService topicUserBookmarkService;
	
	@Autowired
	private TopicUserLikeService topicUserLikeService;
	
	@Autowired
	private TopicUserUnbookmarkService topicUserUnbookmarkService;
	
	@Autowired
	private TopicUserUnlikeService topicUserUnlikeService;
	
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
	            Topic.TOPIC_ID
	    );

	    Topic topic = topicService.findTheTopicDetails(body);

	    //处理用户信息(昵称,头像,是否关注)
	    Member nickNameAndAvatarAndIsFollow = memberRpc.nickNameAndAvatarAndIsFollowFindV1(topic.getUserId(), body.getSystemRequestUserId());
	    topic.put(Topic.USER_ID, nickNameAndAvatarAndIsFollow);

	    
	    //处理图片
	    List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

        String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA_ID);
        List<File> fileList = fileRpc.findsV1(fileIds);
        
        topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA_ID, fileList, File.FILE_PATH);
        //这里本来就是从topic里面取出来的,还用不用再放回去?引用的地址?
        topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
        

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
	            Topic.TOPIC_TOP_LEVEL
	    );

	    return renderJson(topic);
	}


    
    @ApiOperation(value = "关注的人的话题分页列表")
    @RequestMapping(value = "/topic/mobile/v1/follow/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> followListV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );
       
        //先得到用户关注的人的id列表
        List<String> followUserIdList = memberFollowRpc.followUserIdList(body.getSystemRequestUserId());
        
        //得到话题数量和话题列表
        Integer countResult = topicService.countByUserIdList(body.getAppId(), followUserIdList);
        List<Topic> listByUserIdList = topicService.listByUserIdList(body.getAppId(), followUserIdList, body.getPageIndex(), body.getPageSize());
        
        //得到处理后的结果
        List<Topic> topicList = topicService.handleTopic(body, listByUserIdList);
        
        
        //在controller层调用其他接口处理发布话题者信息(昵称,头像,是否关注)
        String userIds = Util.beanToFieldString(topicList, Topic.USER_ID);
        
        List<Member> nickAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());
        
        topicList = Util.beanAddField(
        		topicList, 
        		Topic.USER_ID, 
        		User.USER_ID, 
        		nickAndAvatarAndIsFollowList, 
        		User.USER_ID,
        		UserAvatar.USER_AVATAR,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW
        	);
        
        
        //在controller层调用其他接口处理话题的图片信息
        for (Topic topic : topicList) {
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
                User.USER_ID,
        		UserAvatar.USER_AVATAR,
        		UserNickName.USER_NICK_NAME,
        		MemberFollow.MEMBER_IS_FOLLOW
        );

        return renderJson(countResult, listByUserIdList);
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
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_MEDIA_LIST,    
                Topic.TOPIC_FORUM_LIST,    
                Topic.TOPIC_TIP_USER_LIST   
        );

        String topicId = Util.getRandomUUID();

        JSONArray jsonArray = body.getJSONArray(Topic.TOPIC_MEDIA_LIST);
        if (Util.isNullOrEmpty(jsonArray)) {
            throw new RuntimeException("图片不能为空");
        }
        List<String> mediaIdList = jsonArray.toJavaList(String.class);

        //遍历图片id,存放到话题图片表
        for (String mediaId : mediaIdList) {
        	TopicMedia topicMedia = new TopicMedia();
        	topicMedia.setTopicMedia(mediaId);
        	topicMedia.setTopicId(topicId);
        	topicMedia.setAppId(body.getAppId());
        	topicMedia.setTopicMediaType(FileType.IMAGE.getKey());

        	Boolean mediaSaveResult = topicMediaService.save(new TopicMedia(), Util.getRandomUUID(), body.getSystemRequestUserId());
		}


        //先标记一下,回来再换另一种解析方法
        String forumIds = body.getString(Topic.TOPIC_FORUM_LIST);
        List<String> forumIdList = JSONArray.parseArray(forumIds, String.class);
        //关联到论坛
        for (String forumId : forumIdList) {
			TopicForum topicForum = new TopicForum();
			topicForum.setAppId(body.getAppId());
			topicForum.setForumId(forumId);
			topicForum.setTopicId(topicId);

			Boolean save = topicForumService.save(topicForum, Util.getRandomUUID(), body.getSystemRequestUserId());
		}


        //先标记一下,回来再换另一种解析方法
        String tipUserIds = body.getString(Topic.TOPIC_TIP_USER_LIST);
        List<String> tipUserIdList = JSONArray.parseArray(tipUserIds, String.class);
        //提醒谁看
        for (String tipUserId : tipUserIdList) {
        	TopicTip topicTip = new TopicTip();
            topicTip.setAppId(body.getAppId());
            topicTip.setTopicId(topicId);
            topicTip.setUserId(tipUserId);

            Boolean save = topicTipService.save(new TopicTip(), Util.getRandomUUID(), body.getSystemRequestUserId());
		}

        Boolean result = topicService.save(body, topicId, body.getSystemRequestUserId());
        
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
        Boolean result = topicService.delete(topicId, systemRequestUserId, topic.getSystemVersion());

        //删除话题论坛关联
        List<TopicForum> allTopicForumList = topicForumService.allTopicForumList(body.getAppId(), null, topicId);
        for (TopicForum topicForum : allTopicForumList) {
        	topicForumService.delete(topicForum.getTopicForumId(), systemRequestUserId, topicForum.getSystemVersion());
		}

        //删除话题图片
        List<TopicMedia> listAllMediaByTopicId = topicMediaService.listAllMediaByTopicId(body.getAppId(), topicId, null, null);
        for (TopicMedia topicMedia : listAllMediaByTopicId) {
        	topicMediaService.delete(topicMedia.getTopicMediaId(), systemRequestUserId, topicMedia.getSystemVersion());
		}

        //删除话题评论
        List<TopicComment> allCommentList = topicCommentService.allCommentList(body.getAppId(), null, topicId);
        for (TopicComment topicComment : allCommentList) {
        	topicCommentService.delete(topicComment.getTopicCommentId(), systemRequestUserId, topicComment.getSystemVersion());
		}

        //删除话题收藏
        List<TopicUserBookmark> allListByTopicId = topicUserBookmarkService.allListByTopicId(body.getAppId(), topicId);
        for (TopicUserBookmark topicUserBookmark : allListByTopicId) {
        	topicUserBookmarkService.delete(topicUserBookmark.getTopicUserBookmarkId(), systemRequestUserId, topicUserBookmark.getSystemVersion());
		}

        //删除话题点赞 
        List<TopicUserLike> allLikeListByTopic = topicUserLikeService.allLikeListByTopic(body.getAppId(), topicId);
        for (TopicUserLike topicUserLike : allLikeListByTopic) {
        	topicUserLikeService.delete(topicUserLike.getTopicUserLikeId(), systemRequestUserId, topicUserLike.getSystemVersion());
		}

        //删除取消收藏 
        List<TopicUserUnbookmark> allUnBookMarkListByTopic = topicUserUnbookmarkService.allUnBookMarkListByTopic(body.getAppId(), topicId);
        for (TopicUserUnbookmark topicUserUnbookmark : allUnBookMarkListByTopic) {
        	topicUserUnbookmarkService.delete(topicUserUnbookmark.getTopicUserUnbookmarkId(), systemRequestUserId, topicUserUnbookmark.getSystemVersion());
		}

        //删除话题取消点赞
        List<TopicUserUnlike> unlikeList = topicUserUnlikeService.allUnlikeListByTopicId(body.getAppId(), topicId);
        for (TopicUserUnlike topicUserUnlike : unlikeList) {
        	topicUserUnlikeService.delete(topicUserUnlike.getTopicUserUnlikeId(), systemRequestUserId, topicUserUnlike.getSystemVersion());
		}

        return renderJson(result);
    }

}