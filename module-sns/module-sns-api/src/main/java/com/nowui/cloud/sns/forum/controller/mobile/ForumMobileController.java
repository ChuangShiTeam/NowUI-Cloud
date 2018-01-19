package com.nowui.cloud.sns.forum.controller.mobile;

import java.util.ArrayList;
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
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.service.TopicForumService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 论坛信息移动端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛信息", description = "论坛信息移动端接口管理")
@RestController
public class ForumMobileController extends BaseController {
	
	 @Autowired
	 private ForumService forumService;
	 
	 @Autowired
	 private TopicForumService topicForumService;
	 
	 @Autowired
	 private ForumUserFollowService forumUserFollowService;
	 
	 @Autowired
	 private ForumUserUnfollowService forumUserUnfollowService;
	 
	 @Autowired
     private FileRpc fileRpc;
     
     @Autowired
     private MemberRpc memberRpc;

	 @ApiOperation(value = "新增论坛信息")
	 @RequestMapping(value = "/forum/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Map<String, Object> saveV1(@RequestBody Forum body) {
	     validateRequest(
	             body,
	             Forum.APP_ID,
	             Forum.FORUM_MEDIA,
	             Forum.FORUM_MEDIA_TYPE,
	             Forum.FORUM_NAME,
	             Forum.FORUM_DESCRIPTION,
	             Forum.SYSTEM_REQUEST_USER_ID
         );
	     
	     // TODO 验证论坛名称的唯一性
	    
	     body.setForumBackgroundMedia(body.getForumMedia());
	     body.setForumBackgroundMediaType(body.getForumMediaType());
	     body.setForumModerator(body.getSystemRequestUserId());
	     body.setForumSort(0);
	     body.setForumIsTop(false);
	     body.setForumIsActive(true);
	     body.setForumIsRecommend(false);
	     body.setForumAuditStatus(ForumAuditStatus.AUDIT_PASS.getKey());
	     body.setForumTopicLocation("");
	     body.setForumAuditContent("");
	     
	     String forumId = Util.getRandomUUID();
	     String userId = body.getSystemRequestUserId();
	     
	     Boolean result = forumService.save(body, forumId, userId);
	     
	     if (result) {
	         // 圈主默认关注论坛
	         ForumUserFollow forumUserFollow = new ForumUserFollow();
	         forumUserFollow.setAppId(body.getAppId());
	         forumUserFollow.setForumId(forumId);
	         forumUserFollow.setUserId(userId);
	         
	         forumUserFollowService.save(forumUserFollow, Util.getRandomUUID(), userId);
	     }
        
	     return renderJson(result);
    }
	 
	@ApiOperation(value = "论坛信息")
    @RequestMapping(value = "/forum/mobile/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );
        
        // forum包含了论坛简介,论坛名称,论坛头像
        Forum forum = forumService.find(body.getForumId());
        // 处理论坛头像
        File file = fileRpc.findV1(forum.getForumMedia());
    	file.keep(File.FILE_ID, File.FILE_PATH);
    	forum.put(Forum.FORUM_MEDIA, file);
        
        String userId = forum.getForumModerator();
        // 根据forumModerator(userId)查询版主信息:去会员表查找--昵称,个人签名,头像,
        Member moderator = memberRpc.nickNameAndAvatarAndSignatureFind(userId);
        forum.put(Forum.FORUM_MODERATOR, moderator);

        
        //根据论坛id去forum_user_follow_map表查找此论坛的userId,然后查找用户头像,昵称,只返回前6个的头像和userId.
        List<ForumUserFollow> forumUserFollows = 
        		forumUserFollowService
        		.listForAdmin(body.getAppId()
        				, null
        				, body.getForumId()
        				, body.getPageIndex()
        				, body.getPageSize()
        		);
        
        
        //处理关注论坛列表的昵称,头像
        String userIds = Util.beanToFieldString(forumUserFollows, ForumUserFollow.USER_ID);
        List<Member> nickNameAndAvatarList = memberRpc.nickNameAndAvatarListV1(userIds);
        forum.put(Forum.FORUM_USER_FOLLOW_LIST, nickNameAndAvatarList);
        
        

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_USER_FOLLOW_LIST
        );

        return renderJson(forum);
    }
	
	
	
	
	@ApiOperation(value = "论坛中所有用户信息")
    @RequestMapping(value = "/forum/mobile/v1/findAll", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findAllV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );

        Integer countResult = forumUserFollowService.countForAdmin(body.getAppId(), null, body.getForumId());
        //得到论坛关注列表
        List<ForumUserFollow> forumUserFollows = forumUserFollowService
        		.listForAdmin(
        				body.getAppId()
        				, null
        				, body.getForumId()
        				, body.getPageIndex()
        				, body.getPageSize()
        		);
        //处理关注论坛的用户信息(昵称,头像,是否关注)
        String userIds = Util.beanToFieldString(forumUserFollows, ForumUserFollow.USER_ID);
        List<Member> nickNameAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, body.getSystemRequestUserId());
        

        return renderJson(countResult, nickNameAndAvatarAndIsFollowList);
    }
	
	@ApiOperation(value = "论坛推荐信息")
    @RequestMapping(value = "/forum/mobile/v1/findRecomand", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findRecomandV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
        );
        Boolean flag = true;
        int num = 0;
        List<Forum> listRandom = new ArrayList<>();
        while (flag) {
        	
        	if (num == 10) {
            	//如果取了10次还没达到条件,那么就取最新创建的几条
            	listRandom = forumService.listRandom(null, null, null, body.getPageIndex(), body.getPageSize());
            	flag = false;
			}
        	
        	if (flag) {
	            //appId,forumModerator,forumMedia都随机模糊查询一个数字
	            String randomAppId = "" + (1 + Math.random() * (10 - 1 + 1));  //(最小值+Math.random()*(最大值-最小值+1))
	            String randomForumMedia = "" +(1+Math.random()*(10-1+1));
	            String randomForumModerator = "" +(1+Math.random()*(10-1+1));
	            listRandom = forumService.listRandom(randomAppId, randomForumMedia, randomForumModerator, body.getPageIndex(), body.getPageSize());
	            
	            if (listRandom != null && listRandom.size() == body.getPageSize()) {
					flag = false;
				}
	            num++;
        	}
		}

        //处理论坛头像
        for (Forum forum : listRandom) {
        	File file = fileRpc.findV1(forum.getForumMedia());
        	file.keep(File.FILE_ID, File.FILE_PATH);
            forum.put(Forum.FORUM_MEDIA, file);
		}

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION
        );
        
        return renderJson(listRandom);
    }

	@ApiOperation(value = "更新论坛头像")
	@RequestMapping(value = "/forum/mobile/v1/update/media", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateMediaV1(@RequestBody Forum body) {
	    validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_MEDIA
       );
	   //不清楚是否单独写一个更改背景图片的接口
	   body.setForumBackgroundMedia(body.getForumMedia());

       Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());

       return renderJson(result);
    }

	@ApiOperation(value = "更新论坛名称")
	@RequestMapping(value = "/forum/mobile/v1/update/name", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateNameV1(@RequestBody Forum body) {
	    validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_NAME
       );
	    
       Forum forum = forumService.find(body.getForumId());
       Integer systemVersion = forum.getSystemVersion();
       
       Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), systemVersion);

       return renderJson(result);
    }

	@ApiOperation(value = "更新论坛简介")
	@RequestMapping(value = "/forum/mobile/v1/update/description", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> updateDescriptionV1(@RequestBody Forum body) {
	    validateRequest(
          body,
          Forum.APP_ID,
          Forum.FORUM_ID,
          Forum.FORUM_DESCRIPTION
       );

       Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());

       return renderJson(result);
    }

	@ApiOperation(value = "删除论坛")
    @RequestMapping(value = "/forum/mobile/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID,
                Forum.SYSTEM_VERSION
        );
        
        Forum forum = forumService.find(body.getForumId());
        //先从论坛信息表删除
        Boolean result = forumService.delete(body.getForumId(), body.getSystemRequestUserId(), forum.getSystemVersion());
        if (result == false) {
    		return renderJson(result);
		}
        
        
        //再从论坛话题关联表中逻辑删除所有的有论坛id的记录
    	Boolean delResult = topicForumService.deleteByForumId(body.getAppId(), body.getForumId(), body.getSystemRequestUserId());
    	if (delResult == false) {
    		return renderJson(delResult);
		}
    	
    	
    	//从论坛关注表中删除有forumId的记录
    	boolean delUserFollow = forumUserFollowService.deleteByForumId(body.getAppId(), body.getForumId(), body.getSystemRequestUserId());
    	if (delUserFollow == false) {
    		return renderJson(delUserFollow);
		}
    	
    	
    	//从论坛取消关注表删除有forumId的记录
    	boolean delUnFollowResult = forumUserUnfollowService.deleteByForumId(body.getAppId(), body.getForumId(), body.getSystemRequestUserId());
    	if (delUnFollowResult == false) {
    		return renderJson(delUnFollowResult);
		}
    	
    	 
        return renderJson(result);
    }

	@ApiOperation(value = "保存推荐给用户关注论坛")
    @RequestMapping(value = "/forum/mobile/v1/save/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveListV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID_LIST
        );
        //先标记一下,回来再换另一种解析方法
        String forumIds = body.getString(Forum.FORUM_ID_LIST);
        List<String> forumIdList = JSONArray.parseArray(forumIds, String.class);
        Boolean saveResult = true;
        
    	for (String forumId : forumIdList) {
            //根据userId,ForumId去取消关注表查找status为true的记录
            ForumUserUnfollow forumUserUnfollow = forumUserUnfollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), forumId);
            
            //有:改变状态,没有:不做操作
            if (forumUserUnfollow != null) {
                boolean delResult = forumUserUnfollowService.delete(forumUserUnfollow.getForumUserUnfollowMapId(), body.getSystemRequestUserId(), forumUserUnfollow.getSystemVersion());
            }
            	
            //向论坛关注表插入一条记录
        	ForumUserFollow forumUserFollow = new ForumUserFollow();
        	forumUserFollow.setAppId(body.getAppId());
        	forumUserFollow.setForumId(forumId);
        	saveResult = forumUserFollowService.save(forumUserFollow, Util.getRandomUUID(), body.getSystemRequestUserId());
		}

        return renderJson(saveResult);
    }
	

    @ApiOperation(value = "论坛信息搜索列表")
    @RequestMapping(value = "/forum/mobile/v1/search/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> searchListV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_NAME,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE  //前端传值
        );

        Integer resultTotal = forumService.countForMobile(body.getAppId() , null, null, null, null, body.getForumName(), null, null, null, null, null, null, null, null, null);
        List<Forum> resultList = forumService.listForMobile(body.getAppId(), null, null, null, null, body.getForumName(), null, null, null, null, null, null, null, null, null, body.getPageIndex(), body.getPageSize());

      //处理论坛头像
        for (Forum forum : resultList) {
        	File file = fileRpc.findV1(forum.getForumMedia());
        	file.keep(File.FILE_ID, File.FILE_PATH);
            forum.put(Forum.FORUM_MEDIA, file);
		}
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR
        );

        return renderJson(resultTotal, resultList);
    }
    
    @ApiOperation(value = "论坛主页信息")
    @RequestMapping(value = "/forum/mobile/v1/home", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID
        );
        //forum包含了论坛简介,论坛名称,论坛头像,论坛背景图片
        Forum forum = forumService.find(body.getForumId());
        //处理论坛头像
        File file = fileRpc.findV1(forum.getForumMedia());
    	file.keep(File.FILE_ID, File.FILE_PATH);
    	forum.put(Forum.FORUM_MEDIA, file);
    	//处理论坛背景图片
        File backgroundfile = fileRpc.findV1(forum.getForumBackgroundMedia());
        backgroundfile.keep(File.FILE_ID, File.FILE_PATH);
    	forum.put(Forum.FORUM_BACKGROUND_MEDIA, backgroundfile);


        String userId = forum.getForumModerator();
        //根据forumModerator(userId)查询版主信息:去会员表查找--昵称,个性签名,头像
        Member forumModerator = memberRpc.nickNameAndAvatarAndSignatureFind(userId);
        forum.put(Forum.FORUM_MODERATOR, forumModerator);


        //根据systemRequestUserId查询是否加入了这个圈子
        ForumUserFollow forumUserFollow = forumUserFollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), body.getForumId());
        if (forumUserFollow == null) {
        	forum.put(Forum.MEMBER_IS_FOLLOW_FORUM, false);
		}
        forum.put(Forum.MEMBER_IS_FOLLOW_FORUM, true);
        

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.MEMBER_IS_FOLLOW_FORUM
        );

        return renderJson(forum);
    }

}