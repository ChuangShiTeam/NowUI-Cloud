package com.nowui.cloud.sns.forum.controller.mobile;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.service.TopicForumService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 论坛用户关注移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛用户关注", description = "论坛用户关注移动端接口管理")
@RestController
public class ForumUserFollowMobileController extends BaseController {

	@Autowired
    private ForumUserUnfollowService forumUserUnfollowService;

	@Autowired
	private ForumUserFollowService forumUserFollowService;

	@Autowired
	private ForumService forumService;

	@Autowired
    private FileRpc fileRpc;

	@Autowired
	private TopicForumService topicForumService;

	@ApiOperation(value = "新增用户加入论坛关联")
    @RequestMapping(value = "/forum/user/follow/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody ForumUserFollow body) {
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.SYSTEM_REQUEST_USER_ID,
                ForumUserFollow.FORUM_ID
        );
        
        ForumUserFollow follow = forumUserFollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), body.getForumId());
        if (follow != null) {
			return renderJson(true);
		}
        
        //根据userId,ForumId去取消关注表查找为status的记录
        ForumUserUnfollow forumUserUnfollow = forumUserUnfollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), body.getForumId());
        //有:改变状态,没有:不做操作
        if (forumUserUnfollow != null) {
        	boolean delResult = forumUserUnfollowService.delete(forumUserUnfollow.getForumUserUnfollowMapId(), body.getSystemRequestUserId(), forumUserUnfollow.getSystemVersion());
        }
        
        body.setUserId(body.getSystemRequestUserId());
        //向论坛关注表插入一条记录
        boolean Result = forumUserFollowService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());
        
        return renderJson(Result);
    }

    @ApiOperation(value = "用户关注的论坛列表")
    @RequestMapping(value = "/forum/user/follow/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody ForumUserFollow body) {
        validateRequest(
                body,
                ForumUserFollow.APP_ID,
                ForumUserFollow.USER_ID,
                ForumUserFollow.FORUM_ID,
                ForumUserFollow.PAGE_INDEX,
                ForumUserFollow.PAGE_SIZE
        );

        Integer resultTotal = forumUserFollowService.countForAdmin(body.getAppId() , body.getUserId(), body.getForumId());
        //得到forumId
        List<ForumUserFollow> resultList = forumUserFollowService.listForAdmin(body.getAppId(), body.getUserId(), body.getForumId(), body.getPageIndex(), body.getPageSize());

        ArrayList<Forum> arrayList = new ArrayList<Forum>();

        for (ForumUserFollow forumUserFollow : resultList) {
        //根据forumId去论坛信息表取 论坛头像、论坛名称、论坛简介
        	Forum forum = forumService.find(forumUserFollow.getForumId(), true);
        	String forumName = forum.getForumName();
        	String forumDescription = forum.getForumDescription();
        	forum.setForumName(forumName);
        	forum.setForumDescription(forumDescription);
        	
        	String forumMediaId = forum.getForumMediaId();
        	File file = fileRpc.findV1(forumMediaId);
            file.keep(File.FILE_ID, File.FILE_PATH);
            forum.put(Forum.FORUM_MEDIA_ID, file);

        //根据forumId去论坛话题关联表查询 当日话题最新数量
            Integer count = topicForumService.countForToday(body.getAppId(), forumUserFollow.getForumId(), null);
            forum.put(Forum.FORUM_TODAY_TOPIC_COUNT, count);

        //根据forumId得到版主id，使用 用户接口 得到版主头像
            String forumModeratorId = forum.getForumModerator();
            /**
             * 等用户接口
             */





            arrayList.add(forum);
        }

        validateResponse(
                ForumUserFollow.FORUM_USER_FOLLOW_ID,
                ForumUserFollow.USER_ID,
                ForumUserFollow.FORUM_ID
        );

        return renderJson(resultTotal, resultList);
    }
}