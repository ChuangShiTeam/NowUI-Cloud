package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.forum.entity.TopicForum;
import com.nowui.cloud.sns.forum.service.TopicForumService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
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



    @ApiOperation(value = "论坛中的话题信息列表")
    @RequestMapping(value = "/topic/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.FORUM_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );


        Integer resultTotal = topicForumService.countForAdmin(body.getAppId(), body.getString(Topic.FORUM_ID), null);
        
        List<Topic> resultList = topicService.allTopicListByForumId(body);
        
        
        for (Topic topic : resultList) {
        	//处理话题图片
            List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);
          
            String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA_ID);
            List<File> fileList = fileRpc.findsV1(fileIds);
            
            topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA_ID, fileList, File.FILE_PATH);
            
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
            
            
            //处理话题评论图片
            List<TopicComment> topicCommentList = (List<TopicComment>) topic.get(Topic.TOPIC_COMMENT_LIST);
            /**
             * 给每个评论设置一个用户头像常量
             * 
             * 然后调用接口处理
             */
            // 处理用户信息
            String userIds = Util.beanToFieldString(topicCommentList, TopicComment.USER_ID);
            
            List<Member> memberList = memberRpc.nickNameAndAvatarListV1(userIds);
            
            topicCommentList = Util.beanAddField(
            		topicCommentList, 
            		TopicComment.USER_ID, 
            		User.USER_ID, 
            		memberList, 
            		User.USER_ID,
            		UserAvatar.USER_AVATAR,
            		UserNickName.USER_NICK_NAME);
            
            // 处理回复用户信息
            String respondUserIds = Util.beanToFieldString(topicCommentList, TopicComment.TOPIC_REPLAY_USER_ID);
            
            List<Member> respondMemberList = memberRpc.nickNameAndAvatarListV1(respondUserIds);
        
            Stream<Member> respondMemberStream = respondMemberList.stream();
            for (TopicComment topicComment : topicCommentList) {
            	if (Util.isNullOrEmpty(topicComment.getTopicReplayUserId())) {
            		continue;
            	}
            	Optional<Member> memberOption = respondMemberStream.filter(respondMember -> topicComment.getTopicReplayUserId().equals(respondMember.getUserId())).findFirst();
            	topicComment.put(TopicComment.TOPIC_REPLAY_USER_NICK_NAME, memberOption.isPresent() ? memberOption.get().get(UserNickName.USER_NICK_NAME) : null);
            }
        }
        
        /**
         * 需要再调整一下返回参数
         */
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOP_TOP_LEVEL,
                Topic.TOPIC_COMMENT_LIST
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

        Integer resultTotal = topicService.countForAdmin(body.getAppId(), null, null, body.getUserId(), null, null);
        List<Topic> resultList = topicService.allTopicListByUserId(body);

        for (Topic topic : resultList) {
            List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

            String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA_ID);
            List<File> fileList = fileRpc.findsV1(fileIds);

            topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA_ID, fileList, File.FILE_PATH);

            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
        }

        /**
         * 需要再调整一下返回参数
         */
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOP_TOP_LEVEL
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

	    //处理图片
	    //得到图片列表
	    List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

        String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA_ID);
        List<File> fileList = fileRpc.findsV1(fileIds);
        
        topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA_ID, fileList, File.FILE_PATH);
        //这里本来就是从topic里面取出来的,还用不用再放回去?引用的地址?
        topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
	    
	    /**
	     * 再调整一下返回参数
	     */
	    validateResponse(
	            Topic.TOPIC_ID,
	            Topic.TOPIC_FORUM_ID,
	            Topic.TOPIC_SUMMARY,
	            Topic.USER_ID,
	            Topic.LATITUDE,
	            Topic.LONGTITUDE,
	            Topic.TOPIC_LOCATION,
	            Topic.TOPIC_IS_LOCATION,
	            Topic.TOPIC_IS_TOP,
	            Topic.TOPIC_IS_RECOMAND,
	            Topic.TOP_TOP_LEVEL
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
        
        //先得到用户关注的人的数量,和列表
        
        //然后根据用户列表去topic表 查询 得到话题列表
        /**
         * 等用户接口好了,再写
         */
        
        
        
        
        /**
         * 需要再调整一下返回参数
         */
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOP_TOP_LEVEL
        );

        return renderJson(0, null);
    }

    @ApiOperation(value = "新增话题信息")
    @RequestMapping(value = "/topic/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.TOPIC_FORUM_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_MEDIA_LIST,    //图片id列表
                Topic.TOPIC_FORUM_LIST,    //发布到的论坛列表
                Topic.TOPIC_TIP_USER_LIST    //提醒谁看用户列表 
        );

        String topicId = Util.getRandomUUID();
        
        //先标记一下,回来再换另一种解析方法
        String mediaIds = body.getString(Topic.TOPIC_MEDIA_LIST);
        List<String> mediaIdList = JSONArray.parseArray(mediaIds, String.class);
        

        //遍历图片id,存放到话题图片表
        for (String mediaId : mediaIdList) {
        	TopicMedia topicMedia = new TopicMedia();
        	topicMedia.setTopicMediaId(mediaId);
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
        	topicForumService.delete(topicForum.getTopicForumMapId(), systemRequestUserId, topicForum.getSystemVersion());
		}

        //删除话题图片
        List<TopicMedia> listAllMediaByTopicId = topicMediaService.listAllMediaByTopicId(body.getAppId(), topicId, null, null);
        for (TopicMedia topicMedia : listAllMediaByTopicId) {
        	topicMediaService.delete(topicMedia.getTopicMediaMapId(), systemRequestUserId, topicMedia.getSystemVersion());
		}

        //删除话题评论
        List<TopicComment> allCommentList = topicCommentService.allCommentList(body.getAppId(), null, topicId);
        for (TopicComment topicComment : allCommentList) {
        	topicCommentService.delete(topicComment.getTopicCommentId(), systemRequestUserId, topicComment.getSystemVersion());
		}

        //删除话题收藏
        List<TopicUserBookmark> allListByTopicId = topicUserBookmarkService.allListByTopicId(body.getAppId(), topicId);
        for (TopicUserBookmark topicUserBookmark : allListByTopicId) {
        	topicUserBookmarkService.delete(topicUserBookmark.getUserBookMarkId(), systemRequestUserId, topicUserBookmark.getSystemVersion());
		}

        //删除话题点赞 
        List<TopicUserLike> allLikeListByTopic = topicUserLikeService.allLikeListByTopic(body.getAppId(), topicId);
        for (TopicUserLike topicUserLike : allLikeListByTopic) {
        	topicUserLikeService.delete(topicUserLike.getUserLikeId(), systemRequestUserId, topicUserLike.getSystemVersion());
		}

        //删除取消收藏 
        List<TopicUserUnbookmark> allUnBookMarkListByTopic = topicUserUnbookmarkService.allUnBookMarkListByTopic(body.getAppId(), topicId);
        for (TopicUserUnbookmark topicUserUnbookmark : allUnBookMarkListByTopic) {
        	topicUserUnbookmarkService.delete(topicUserUnbookmark.getUserUnBookMarkId(), systemRequestUserId, topicUserUnbookmark.getSystemVersion());
		}

        //删除话题取消点赞
        List<TopicUserUnlike> unlikeList = topicUserUnlikeService.allUnlikeListByTopicId(body.getAppId(), topicId);
        for (TopicUserUnlike topicUserUnlike : unlikeList) {
        	topicUserUnlikeService.delete(topicUserUnlike.getUserUnLikeId(), systemRequestUserId, topicUserUnlike.getSystemVersion());
		}

        return renderJson(result);
    }

}