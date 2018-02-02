package com.nowui.cloud.sns.topic.controller.mobile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.entity.MemberBackground;
import com.nowui.cloud.member.member.entity.MemberFollow;
import com.nowui.cloud.member.member.entity.MemberSignature;
import com.nowui.cloud.member.member.rpc.MemberFollowRpc;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicTipService;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
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
    private ForumService forumService;
    
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
    
    @Autowired
    private TopicUserLikeService topicUserLikeService;
    
    @Autowired
    private TopicCommentService topicCommentService;

    @ApiOperation(value = "论坛中的话题信息列表")
    @RequestMapping(value = "/topic/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody TopicForum body) {
        validateRequest(
                body,
                TopicForum.APP_ID,
                TopicForum.FORUM_ID,
                TopicForum.PAGE_INDEX,
                TopicForum.PAGE_SIZE,
                TopicForum.SYSTEM_REQUEST_USER_ID
        );
        String requestUserId = body.getSystemRequestUserId();

        Integer resultTotal = topicForumService.countByForumId(body.getForumId());
        
        List<Topic> resultList = topicService.listByForumId(body.getForumId(), body.getSystemRequestUserId(), body.getPageIndex(), body.getPageSize());
        
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
        
        
        // 处理话题
        for (Topic topic : resultList) {
            
            List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);
          
            String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA_ID);
            List<File> fileList = fileRpc.findsV1(fileIds);
            topicMediaList = Util.beanAddField(topicMediaList, TopicMedia.TOPIC_MEDIA_ID, fileList, File.FILE_PATH);
            
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
            
            //验证话题发布人是否自己
            String theSendUserId = topic.getUserId();
            
            topic.put(Topic.TOPIC_IS_SELF, theSendUserId.equals(requestUserId));
            
            
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
                MemberFollow.MEMBER_IS_FOLLOW,
                Topic.TOPIC_IS_SELF
        );

        return renderJson(resultTotal, resultList);
    }
    
    
    @ApiOperation(value = "别人的主页的用户信息(头像,关注数,粉丝数是否关注这个用户等)")
    @RequestMapping(value = "/topic/mobile/v1/home/user/info", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeUserInfoV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.USER_ID
        );
        
        String userId = body.getUserId();
        String requestUserId = body.getSystemRequestUserId();
        
        // 获取用户头像,昵称,签名,背景
        Member memberInfo = memberRpc.nickNameAndAvatarAndBackgroundAndSignatureFind(userId);
        // 获取用户头像,昵称,是否关注
        Boolean memberIsFollow = memberFollowRpc.checkIsFollowV1(requestUserId, userId);
        memberInfo.put(MemberFollow.MEMBER_IS_FOLLOW, memberIsFollow);
        // TODO 我的宠物
        
        
        // 粉丝数
        Integer countBeFollowed = memberFollowRpc.countBeFollowed(userId);
        memberInfo.put(Member.MEMBER_BE_FOLLOW_COUNT, countBeFollowed);
        // 关注数
        Integer countFollow = memberFollowRpc.countFollow(userId);
        memberInfo.put(Member.MEMBER_FOLLOW_COUNT, countFollow);
        // 动态数
        Integer countTopic = topicService.countTopicByUserIdWithRedis(userId);
        memberInfo.put(Member.MEMBER_SEND_TOPIC_COUNT, countTopic);
        
        validateResponse(
                Member.MEMBER_SEND_TOPIC_COUNT,
                Member.MEMBER_FOLLOW_COUNT,
                Member.MEMBER_BE_FOLLOW_COUNT,
                MemberFollow.MEMBER_IS_FOLLOW,
                MemberSignature.MEMBER_SIGNATURE,
                MemberBackground.MEMBER_BACKGROUND,
                UserNickName.USER_NICK_NAME,
                UserAvatar.USER_AVATAR,
                Member.USER_ID
        );
        return renderJson(memberInfo);

    }
    
    @ApiOperation(value = "别人的主页的动态列表")
    @RequestMapping(value = "/topic/mobile/v1/home/topic", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeTopicV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.USER_ID,
                Topic.SYSTEM_CREATE_TIME,
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );
        
        Integer commentPageIndex = (Integer) body.get(Topic.COMMENT_PAGE_INDEX);
        Integer commentPageSize =(Integer) body.get(Topic.COMMENT_PAGE_SIZE);
        

        ArrayList<String> userIdToSearchList = new ArrayList<>();
        userIdToSearchList.add(body.getUserId());
        Integer countResult = topicService.countByUserIdList(body.getAppId(), userIdToSearchList);
        List<Topic> resultList = topicService.listDetailByUserIdList(body.getAppId(), body.getSystemRequestUserId(), userIdToSearchList, (List<String>) body.get(Topic.EXCLUDE_TOPIC_ID_LIST), body.getSystemCreateTime(), body.getPageIndex(), body.getPageSize());
        
        //复制start
        
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

            topicMediaList = Util.beanReplaceField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_ID, File.FILE_PATH);
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
            
          //取话题前3条评论
            if (Util.isNullOrEmpty(commentPageIndex) || Util.isNullOrEmpty(commentPageSize)) {
            	commentPageIndex = 1;
            	commentPageSize = 3;
			}
            List<TopicComment> topicCommentList = topicCommentService.listByTopicId(topic.getTopicId(), commentPageIndex, commentPageSize);
            
            //处理发送用户信息(昵称)
            String sendUserIds = Util.beanToFieldString(topicCommentList, TopicComment.USER_ID);
            List<Member> memberList = memberRpc.nickNameAndAvatarListV1(sendUserIds);
            topicCommentList = Util.beanAddField(
                    topicCommentList, 
                    TopicComment.USER_ID, 
                    User.USER_ID, 
                    memberList, 
                    UserNickName.USER_NICK_NAME
                );
            
            
            // 处理回复用户信息(昵称)
            String respondUserIds = Util.beanToFieldString(topicCommentList, TopicComment.TOPIC_REPLAY_USER_ID);
            
            List<Member> respondMemberList = memberRpc.nickNameAndAvatarListV1(respondUserIds);
        
            for (TopicComment topicComment : topicCommentList) {
                if (Util.isNullOrEmpty(topicComment.getTopicReplayUserId()) || Util.isNullOrEmpty(respondMemberList)) {
                    continue;
                }
                
                Optional<Member> memberOption = respondMemberList.stream().filter(respondMember -> topicComment.getTopicReplayUserId().equals(respondMember.getUserId())).findFirst();
                topicComment.put(TopicComment.TOPIC_REPLAY_USER_NICK_NAME, memberOption.isPresent() ? memberOption.get().get(UserNickName.USER_NICK_NAME) : null);
                topicComment.keep(TopicComment.USER_ID, UserNickName.USER_NICK_NAME, TopicComment.TOPIC_REPLAY_USER_ID, TopicComment.TOPIC_REPLAY_USER_NICK_NAME, TopicComment.TOPIC_COMMENT_CONTENT);
            }
            
            topic.put(Topic.TOPIC_COMMENT_LIST, topicCommentList);
        }
        
        //复制end
        
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
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                
                User.USER_ID,
                UserAvatar.USER_AVATAR,
                UserNickName.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                BaseEntity.SYSTEM_CREATE_TIME
        );

        return renderJson(countResult, resultList);
    }
    
    
    @ApiOperation(value = "自己的主页的用户信息(头像,关注数,粉丝数是否关注这个用户等)")
    @RequestMapping(value = "/topic/mobile/v1/home/self/info", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeSelfInfoV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.SYSTEM_REQUEST_USER_ID
        );
        
        String userId = body.getSystemRequestUserId();
        
        // 获取用户头像,昵称,签名,背景
        Member memberInfo = memberRpc.nickNameAndAvatarAndBackgroundAndSignatureFind(userId);
        
        // TODO 我的宠物
        
        // 粉丝数
        Integer countBeFollowed = memberFollowRpc.countBeFollowed(userId);
        memberInfo.put(Member.MEMBER_BE_FOLLOW_COUNT, countBeFollowed);
        // 关注数
        Integer countFollow = memberFollowRpc.countFollow(userId);
        memberInfo.put(Member.MEMBER_FOLLOW_COUNT, countFollow);
        // 动态数
        Integer countTopic = topicService.countTopicByUserIdWithRedis(userId);
        memberInfo.put(Member.MEMBER_SEND_TOPIC_COUNT, countTopic);
        
        validateResponse(
                Member.MEMBER_SEND_TOPIC_COUNT,
                Member.MEMBER_FOLLOW_COUNT,
                Member.MEMBER_BE_FOLLOW_COUNT,
                MemberSignature.MEMBER_SIGNATURE,
                MemberBackground.MEMBER_BACKGROUND,
                UserNickName.USER_NICK_NAME,
                UserAvatar.USER_AVATAR
        );
        return renderJson(memberInfo);

    }
    @ApiOperation(value = "自己的主页的动态列表")
    @RequestMapping(value = "/topic/mobile/v1/self/home/topic", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> selfHomeTopicV1(@RequestBody Topic body) {
        validateRequest(
                body,
                Topic.APP_ID,
                Topic.SYSTEM_CREATE_TIME,
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE
        );
        
        String requestUserId = body.getSystemRequestUserId();
        Integer commentPageIndex = (Integer) body.get(Topic.COMMENT_PAGE_INDEX);
        Integer commentPageSize =(Integer) body.get(Topic.COMMENT_PAGE_SIZE);
        
        ArrayList<String> userIdToSearchList = new ArrayList<>();
        userIdToSearchList.add(body.getSystemRequestUserId());
        Integer countResult = topicService.countByUserIdList(body.getAppId(), userIdToSearchList);
        List<Topic> resultList = topicService.listDetailByUserIdList(body.getAppId(), body.getSystemRequestUserId(), userIdToSearchList, (List<String>) body.get(Topic.EXCLUDE_TOPIC_ID_LIST), body.getSystemCreateTime(), body.getPageIndex(), body.getPageSize());
        
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

            topicMediaList = Util.beanReplaceField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_ID, File.FILE_PATH);
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
            
            //验证话题发布人是否自己
            String theSendUserId = topic.getUserId();
            
            topic.put(Topic.TOPIC_IS_SELF, theSendUserId.equals(requestUserId));
            
            //取话题前3条评论
            if (Util.isNullOrEmpty(commentPageIndex) || Util.isNullOrEmpty(commentPageSize)) {
            	commentPageIndex = 1;
            	commentPageSize = 3;
			}
            List<TopicComment> topicCommentList = topicCommentService.listByTopicId(topic.getTopicId(), commentPageIndex, commentPageSize);
            
            //处理发送用户信息(昵称)
            String sendUserIds = Util.beanToFieldString(topicCommentList, TopicComment.USER_ID);
            List<Member> memberList = memberRpc.nickNameAndAvatarListV1(sendUserIds);
            topicCommentList = Util.beanAddField(
                    topicCommentList, 
                    TopicComment.USER_ID, 
                    User.USER_ID, 
                    memberList, 
                    UserNickName.USER_NICK_NAME
                );
            
            
            // 处理回复用户信息(昵称)
            String respondUserIds = Util.beanToFieldString(topicCommentList, TopicComment.TOPIC_REPLAY_USER_ID);
            
            List<Member> respondMemberList = memberRpc.nickNameAndAvatarListV1(respondUserIds);
        
            for (TopicComment topicComment : topicCommentList) {
                if (Util.isNullOrEmpty(topicComment.getTopicReplayUserId()) || Util.isNullOrEmpty(respondMemberList)) {
                    continue;
                }
                
                Optional<Member> memberOption = respondMemberList.stream().filter(respondMember -> topicComment.getTopicReplayUserId().equals(respondMember.getUserId())).findFirst();
                topicComment.put(TopicComment.TOPIC_REPLAY_USER_NICK_NAME, memberOption.isPresent() ? memberOption.get().get(UserNickName.USER_NICK_NAME) : null);
                topicComment.keep(TopicComment.USER_ID, UserNickName.USER_NICK_NAME, TopicComment.TOPIC_REPLAY_USER_ID, TopicComment.TOPIC_REPLAY_USER_NICK_NAME, TopicComment.TOPIC_COMMENT_CONTENT);
            }
            
            topic.put(Topic.TOPIC_COMMENT_LIST, topicCommentList);
            
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
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                Topic.TOPIC_IS_SELF,
                User.USER_ID,
                UserAvatar.USER_AVATAR,
                UserNickName.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                BaseEntity.SYSTEM_CREATE_TIME
        );

        return renderJson(countResult, resultList);
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
        topic.put(Topic.TOPIC_USER, nickNameAndAvatarAndIsFollow);
        
        Member nickNameAndAvatar = memberRpc.nickNameAndAvatarFindV1(userId);
        topic.put(Topic.REQUEST_USER, nickNameAndAvatar);
        
        if (userId.equals(topic.getUserId())) {
            // 验证话题发布者是否自己
            topic.put(Topic.TOPIC_IS_SELF, true);
        } 
        
        //处理图片
        List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

        String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA);
        List<File> fileList = fileRpc.findsV1(fileIds);
        
        topicMediaList = Util.beanReplaceField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_ID, File.FILE_PATH);
        topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
        
        // 查找所有点赞用户(取三条数据,展示到话题详情页)
        List<TopicUserLike> userLikeList = topicUserLikeService.listByTopicIdHavePage(topicId, 1, 3);
        
        if (!Util.isNullOrEmpty(userLikeList)) {
            // 处理点赞的用户头像
            String userIds = Util.beanToFieldString(userLikeList, TopicUserLike.USER_ID);
            List<Member> nickAndAvatarList = memberRpc.nickNameAndAvatarListV1(userIds);
            
            userLikeList = Util.beanAddField(
                    userLikeList,
                    TopicUserLike.USER_ID,
                    User.USER_ID,
                    nickAndAvatarList,
                    User.USER_ID,
                    UserAvatar.USER_AVATAR,
                    UserNickName.USER_NICK_NAME
            );
            
            for (TopicUserLike topicUserLike : userLikeList) {
                topicUserLike.keep(UserAvatar.USER_AVATAR, TopicUserLike.USER_ID, MemberFollow.MEMBER_IS_FOLLOW);
            }
            
            topic.put(Topic.TOPIC_USER_LIKE_LIST, userLikeList);
        }
       
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_IS_RECOMAND,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_MEDIA_LIST,
                Topic.TOPIC_FORUM_LIST,
                Topic.TOPIC_COUNT_BOOKMARK,
                Topic.TOPIC_COUNT_LIKE,
                Topic.TOPIC_COUNT_COMMENT,
                Topic.TOPIC_USER_IS_BOOKEMARK,
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                BaseEntity.SYSTEM_CREATE_TIME,
                Topic.TOPIC_IS_SELF,
                Topic.TOPIC_USER,
                Topic.REQUEST_USER
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
                Topic.SYSTEM_REQUEST_USER_ID,
                Topic.SYSTEM_CREATE_TIME
        );
        String requestUserId = body.getSystemRequestUserId();
        
        // 用户关注的人的编号列表
        List<String> followUserIdList = memberFollowRpc.followUserIdList(body.getSystemRequestUserId());
        // 加上本人的用户编号
        followUserIdList.add(body.getSystemRequestUserId());
        
        Integer countResult = topicService.countByUserIdList(body.getAppId(), followUserIdList);
        List<Topic> resultList = topicService.listDetailByUserIdList(body.getAppId(), body.getSystemRequestUserId(), followUserIdList, (List<String>) body.get(Topic.EXCLUDE_TOPIC_ID_LIST), body.getSystemCreateTime(), body.getPageIndex(), body.getPageSize());
        
        if (!Util.isNullOrEmpty(resultList)) {
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
            
            for (Topic topic : resultList) {
                // 话题多媒体
                List<TopicMedia> topicMediaList = (List<TopicMedia>) topic.get(Topic.TOPIC_MEDIA_LIST);

                String fileIds = Util.beanToFieldString(topicMediaList, TopicMedia.TOPIC_MEDIA);
                List<File> fileList = fileRpc.findsV1(fileIds);

                topicMediaList = Util.beanReplaceField(topicMediaList, TopicMedia.TOPIC_MEDIA, fileList, File.FILE_ID, File.FILE_PATH);
                topic.put(Topic.TOPIC_MEDIA_LIST, topicMediaList);
                // 话题是否自己发布
                topic.put(Topic.TOPIC_IS_SELF, requestUserId.equals(topic.getUserId()));
                
                // 评论列表
                List<TopicComment> topicCommentList = topicCommentService.listByTopicId(topic.getTopicId(), 1, 3);
                
                //处理发送用户信息(昵称)
                String sendUserIds = Util.beanToFieldString(topicCommentList, TopicComment.USER_ID);
                List<Member> memberList = memberRpc.nickNameAndAvatarListV1(sendUserIds);
                topicCommentList = Util.beanAddField(
                        topicCommentList, 
                        TopicComment.USER_ID, 
                        User.USER_ID, 
                        memberList, 
                        UserNickName.USER_NICK_NAME
                    );
                
                
                // 处理回复用户信息(昵称)
                String respondUserIds = Util.beanToFieldString(topicCommentList, TopicComment.TOPIC_REPLAY_USER_ID);
                
                List<Member> respondMemberList = memberRpc.nickNameAndAvatarListV1(respondUserIds);
            
                for (TopicComment topicComment : topicCommentList) {
                    if (Util.isNullOrEmpty(topicComment.getTopicReplayUserId()) || Util.isNullOrEmpty(respondMemberList)) {
                        continue;
                    }
                    
                    Optional<Member> memberOption = respondMemberList.stream().filter(respondMember -> topicComment.getTopicReplayUserId().equals(respondMember.getUserId())).findFirst();
                    topicComment.put(TopicComment.TOPIC_REPLAY_USER_NICK_NAME, memberOption.isPresent() ? memberOption.get().get(UserNickName.USER_NICK_NAME) : null);
                    topicComment.keep(TopicComment.USER_ID, UserNickName.USER_NICK_NAME, TopicComment.TOPIC_REPLAY_USER_ID, TopicComment.TOPIC_REPLAY_USER_NICK_NAME, TopicComment.TOPIC_COMMENT_CONTENT);
                }
                
                topic.put(Topic.TOPIC_COMMENT_LIST, topicCommentList);
            }
            
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
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                Topic.TOPIC_IS_SELF,
                UserAvatar.USER_AVATAR,
                UserNickName.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                Topic.SYSTEM_CREATE_TIME
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
            throw new BusinessException("图片不能为空");
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
        Boolean result = topicService.saveWithRedis(body, topicId, userId);
        
        if (result) {
            
            // 保存话题多媒体
            List<TopicMedia> topicMediaList = JSONArray.parseArray(topicMediaJsonArray.toJSONString(), TopicMedia.class);
            topicMediaService.batchSave(appId, topicId, topicMediaList, userId);
            
            
            // 保存话题论坛
            if (!Util.isNullOrEmpty(forumIdJSONArray)) {
                List<String> forumIdList = forumIdJSONArray.toJavaList(String.class);
                
                List<TopicForum> topicForumList = new ArrayList<TopicForum>();
                
                for (String forumId : forumIdList) {
                    Forum forum = forumService.find(forumId);
                    if (Util.isNullOrEmpty(forum)) {
                        continue;
                    }
                    TopicForum topicForum = new TopicForum();
                    topicForum.setForumId(forumId);
                    
                    topicForumList.add(topicForum);
                }
                
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