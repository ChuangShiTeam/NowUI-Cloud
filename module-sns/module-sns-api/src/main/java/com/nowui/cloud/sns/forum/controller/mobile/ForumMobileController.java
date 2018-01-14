package com.nowui.cloud.sns.forum.controller.mobile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.entity.enums.FileType;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumAudit;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditType;
import com.nowui.cloud.sns.forum.service.ForumAuditService;
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
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	 private TopicForumService topicForumServicepic;
	 
	 @Autowired
	 private ForumUserFollowService forumUserFollowService;
	 
	 @Autowired
	 private ForumAuditService forumAuditService;
	 
	 @Autowired
	 private FileRpc fileRpc;
	 
	 @Autowired
	 private ForumUserUnfollowService forumUserUnfollowService;

	 @ApiOperation(value = "新增论坛信息")
	 @RequestMapping(value = "/forum/mobile/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Map<String, Object> saveV1(@RequestBody Forum body) {
	     validateRequest(
            body,
            Forum.APP_ID,
            Forum.FORUM_MEDIA_ID,
//            Forum.FORUM_MEDIA_TYPE,
            Forum.FORUM_NAME,
            Forum.FORUM_DESCRIPTION,
            Forum.FORUM_MODERATOR,
            Forum.FORUM_TOPIC_LOCATION
        );
	    
	    body.setForumMediaType(FileType.IMAGE.getKey());
	    body.setForumBackgroundMediaId(body.getForumMediaId());
	    body.setForumBackgroundMediaType(FileType.IMAGE.getKey());
	    body.setForumModerator(body.getSystemRequestUserId());
	    body.setForumSort(0);
	    body.setForumTop(false);
	    body.setForumTopLevel(0);
	    body.setForumTopEndTime(null);
	    body.setForumIsActive(true);
	    body.setForumIsRecomand(false);
	    //保存
	    String forumId = Util.getRandomUUID();
        Boolean result = forumService.save(body, forumId, body.getSystemRequestUserId());
        //提交到审核表
        ForumAudit forumAudit = new ForumAudit();
        forumAudit.setForumAuditStatus(ForumAuditType.WAITAUDIT.getKey());
        forumAudit.setAppId(body.getAppId());
        forumAudit.setForumId(forumId);
        if (result) {
        	//保存
        	result= forumAuditService.save(forumAudit, Util.getRandomUUID(), body.getSystemRequestUserId());
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
        
        //result包含了简介,名称,头像
        Forum result = forumService.find(body.getForumId());
        //处理头像
        File file = fileRpc.findV1(result.getForumMediaId());
    	file.keep(File.FILE_ID, File.FILE_PATH);
    	result.put(Forum.FORUM_MEDIA_ID, file);
        
        String userId = result.getForumModerator();
        //根据forumModerator(userId)查询版主信息:去会员表查找--昵称,个人简介,头像,
        /**
         * 等用户接口
         */


        //根据论坛id去forum_user_follow_map表查找此论坛的userId,然后遍历查找用户头像,只返回前6个的头像和userId.
        List<ForumUserFollow> forumUserFollows = forumUserFollowService.listForAdmin(body.getAppId(), null, body.getForumId(), body.getPageIndex(), body.getPageSize());
        
        //遍历forumUserFollows的userId去查找用户还是会员表的头像,并返回前6个的头像和userId
        /**
         * 等用户接口
         */

        validateResponse(
//                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
//                Forum.FORUM_MEDIA_TYPE,
//                Forum.FORUM_BACKGROUND_MEDIA_ID,
//                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR
//                Forum.FORUM_TOPIC_LOCATION,
//                Forum.FORUM_SORT,
//                Forum.FORUM_TOP,
//                Forum.FORUM_TOP_LEVEL,
//                Forum.FORUM_TOP_END_TIME,
//                Forum.FORUM_IS_ACTIVE,
//                Forum.FORUM_IS_FOLLOW,
//                Forum.FORUM_IS_RECOMAND
        );

        return renderJson(result);
    }
	
	
	
	
	@ApiOperation(value = "论坛中所有用户信息")
    @RequestMapping(value = "/forum/mobile/v1/findAll", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findAllV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID
        );

        //根据论坛id去forum_user_follow_map表查找此论坛的userId,然后遍历查找用户头像,返回全部的的头像和userId.
        List<ForumUserFollow> forumUserFollows = forumUserFollowService.findByForumId(body.getAppId(), body.getForumId());
        /**
         * 等用户接口
         */


        validateResponse(
//                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
//                Forum.FORUM_MEDIA_TYPE,
//                Forum.FORUM_BACKGROUND_MEDIA_ID,
//                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR
//                Forum.FORUM_TOPIC_LOCATION,
//                Forum.FORUM_SORT,
//                Forum.FORUM_TOP,
//                Forum.FORUM_TOP_LEVEL,
//                Forum.FORUM_TOP_END_TIME,
//                Forum.FORUM_IS_ACTIVE,
//                Forum.FORUM_IS_FOLLOW,
//                Forum.FORUM_IS_RECOMAND
        );

        return renderJson(null);
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
            //appId,forumModerator,forumMediaId都随机模糊查询一个数字
            String randomAppId = "" + (1+Math.random()*(10-1+1));  //(最小值+Math.random()*(最大值-最小值+1))
            String randomForumMediaId = "" +(1+Math.random()*(10-1+1));
            String randomForumModerator = "" +(1+Math.random()*(10-1+1));
            listRandom = forumService.listRandom(randomAppId, randomForumMediaId, randomForumModerator, body.getPageIndex(), body.getPageSize());

            if (listRandom != null || listRandom.size() == body.getPageSize()) {
				flag = false;
			}
            num++;
            if (num == 10) {
            	//如果取了10次还没达到条件,那么就取最新创建的几条
            	listRandom = forumService.listRandom(null, null, null, body.getPageIndex(), body.getPageSize());
            	flag = false;
			}
		}

        //处理论坛头像
        for (Forum forum : listRandom) {
        	File file = fileRpc.findV1(forum.getForumMediaId());
        	file.keep(File.FILE_ID, File.FILE_PATH);
            forum.put(Forum.FORUM_MEDIA_ID, file);
		}

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
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
          Forum.FORUM_MEDIA_ID
       );
	   //不清楚是否单独写一个更改背景图片的接口
	   body.setForumBackgroundMediaId(body.getForumMediaId());

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

       Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());

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
        //先从论坛信息表删除
        Boolean result = forumService.delete(body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());
        //再从论坛动态表中逻辑删除所有的有论坛id的字段
    	if (result) {
    		Boolean delResult = topicForumServicepic.deleteByForumId(body.getAppId(), body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());
    		result = delResult;
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
        
        Boolean delResult = null;
    	for (String forumId : forumIdList) {
            //根据userId,ForumId去取消关注表查找status为true的记录
            ForumUserUnfollow forumUserUnfollow = forumUserUnfollowService.findByUserIdAndForumId(body.getAppId(), body.getSystemRequestUserId(), forumId);
            delResult = null;
            //有:改变状态,没有:不做操作
            if (forumUserUnfollow != null || forumUserUnfollow.size() != 0) {
                delResult = forumUserUnfollowService.delete(forumUserUnfollow.getForumUserUnfollowMapId(), body.getSystemRequestUserId(), forumUserUnfollow.getSystemVersion());
            }
            if (delResult) {
            	//向论坛关注表插入一条记录
            	ForumUserFollow forumUserFollow = new ForumUserFollow();
            	forumUserFollow.setAppId(body.getAppId());
            	forumUserFollow.setForumId(forumId);
            	delResult = forumUserFollowService.save(forumUserFollow, Util.getRandomUUID(), body.getSystemRequestUserId());
    		}
            return renderJson(delResult);
		}

        return renderJson(delResult);
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

        Integer resultTotal = forumService.countForAdmin(body.getAppId() , null, null, null, null, body.getForumName(), null, null, null, null, null, null, null, null, null, null);
        List<Forum> resultList = forumService.listForAdmin(body.getAppId(), null, null, null, null, body.getForumName(), null, null, null, null, null, null, null, null, null, null, body.getPageIndex(), body.getPageSize());

      //处理论坛头像
        for (Forum forum : resultList) {
        	File file = fileRpc.findV1(forum.getForumMediaId());
        	file.keep(File.FILE_ID, File.FILE_PATH);
            forum.put(Forum.FORUM_MEDIA_ID, file);
		}
        
        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
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
        //result包含了简介,名称,头像,背景图片
        Forum result = forumService.find(body.getForumId());
        //处理头像
        File file = fileRpc.findV1(result.getForumMediaId());
    	file.keep(File.FILE_ID, File.FILE_PATH);
    	result.put(Forum.FORUM_MEDIA_ID, file);
    	//处理背景图片
        File backgroundfile = fileRpc.findV1(result.getForumBackgroundMediaId());
        backgroundfile.keep(File.FILE_ID, File.FILE_PATH);
    	result.put(Forum.FORUM_BACKGROUND_MEDIA_ID, backgroundfile);
        
        
        String userId = result.getForumModerator();
        //根据forumModerator(userId)查询版主信息:去会员表查找--昵称,个人简介,头像,
        /**
         * 等用户接口
         */



        validateResponse(
//                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
//                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
//                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR
//                Forum.FORUM_TOPIC_LOCATION,
//                Forum.FORUM_IS_ACTIVE,
        );

        return renderJson(result);
    }
    
    

}