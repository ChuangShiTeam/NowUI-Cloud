package com.nowui.cloud.sns.forum.controller.admin;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.entity.enums.FileType;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumAudit;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.service.ForumAuditService;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.service.TopicForumService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论坛信息管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛信息", description = "论坛信息管理端接口管理")
@RestController
public class ForumAdminController extends BaseController {

	@Autowired
	 private ForumService forumService;
	 
	 @Autowired
	 private TopicForumService topicForumService;
	 
	 @Autowired
	 private ForumUserFollowService forumUserFollowService;
	 
	 @Autowired
	 private ForumAuditService forumAuditService;
	 
	 @Autowired
	 private FileRpc fileRpc;
	 
	 @Autowired
	 private MemberRpc memberRpc;
	 
	 @Autowired
	 private ForumUserUnfollowService forumUserUnfollowService;

	 @ApiOperation(value = "新增论坛")
	 @RequestMapping(value = "/forum/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Map<String, Object> saveV1(@RequestBody Forum body) {
	     validateRequest(
           body,
           Forum.APP_ID,
           Forum.FORUM_MEDIA_ID,
//           Forum.FORUM_MEDIA_TYPE,
           Forum.FORUM_NAME,
           Forum.FORUM_DESCRIPTION,
           Forum.FORUM_SORT,            
           Forum.FORUM_TOP,             //是否置顶
           Forum.FORUM_TOP_LEVEL,       //置顶级别
           Forum.FORUM_TOP_END_TIME,    //置顶结束时间
           Forum.FORUM_IS_ACTIVE,       //论坛是否有效
           Forum.FORUM_IS_RECOMAND      //论坛是否推荐
       );

	   body.setForumMediaType(FileType.IMAGE.getKey());
	   body.setForumBackgroundMediaId(body.getForumMediaId());
	   body.setForumBackgroundMediaType(FileType.IMAGE.getKey());
	   body.setForumModerator(body.getSystemRequestUserId());

	   //保存
	   String forumId = Util.getRandomUUID();
	   String systemRequestUserId = body.getSystemRequestUserId();

       Boolean result = forumService.save(body, forumId, systemRequestUserId);
       //提交到审核表
       ForumAudit forumAudit = new ForumAudit();
       forumAudit.setForumAuditStatus(ForumAuditStatus.WAIT_AUDIT.getKey());
       forumAudit.setAppId(body.getAppId());
       forumAudit.setForumId(forumId);
       if (result) {
       	result= forumAuditService.save(forumAudit, Util.getRandomUUID(), systemRequestUserId);
		}

       return renderJson(result);
   }

    @ApiOperation(value = "论坛信息列表")
    @RequestMapping(value = "/forum/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Forum body) {
       validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
       );

       Integer resultTotal = forumService.countForAdmin(body.getAppId() , body.getForumMediaId(), body.getForumMediaType(), body.getForumBackgroundMediaId(), body.getForumBackgroundMediaType(), body.getForumName(), body.getForumDescription(), body.getForumModerator(), body.getForumTopicLocation(), body.getForumSort(), body.getForumTop(), body.getForumTopLevel(), body.getForumTopEndTime(), body.getForumIsActive(), body.getForumIsRecomand());
       List<Forum> resultList = forumService.listForAdmin(
    		   body.getAppId(), 
    		   null, null, null, null, 
    		   body.getForumName(), 
    		   body.getForumDescription(), 
    		   body.getForumModerator(), 
    		   null, null, 
    		   body.getForumTop(), 
    		   body.getForumTopLevel(), 
    		   null, 
    		   body.getForumIsActive(), 
    		   body.getForumIsRecomand(), 
    		   body.getPageIndex(), 
    		   body.getPageSize()
    		   );

       
       String fileIds = Util.beanToFieldString(resultList, Forum.FORUM_MEDIA_ID);
       List<File> fileList = fileRpc.findsV1(fileIds);
       resultList = Util.beanReplaceField(resultList, Forum.FORUM_MEDIA_ID, fileList, File.FILE_PATH);
       
       
       String backFileIds = Util.beanToFieldString(resultList, Forum.FORUM_BACKGROUND_MEDIA_ID);
       List<File> backFileList = fileRpc.findsV1(backFileIds);
       resultList = Util.beanReplaceField(resultList, Forum.FORUM_BACKGROUND_MEDIA_ID, backFileList, File.FILE_PATH);


       validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_RECOMAND
                ,File.FILE_PATH
       );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "查找论坛信息(根据论坛id)")
    @RequestMapping(value = "/forum/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID
        );

        Forum result = forumService.find(body.getForumId());

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_ID,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_FOLLOW,
                Forum.FORUM_IS_RECOMAND
        );

        return renderJson(result);
    }

    

    @ApiOperation(value = "修改论坛信息")
    @RequestMapping(value = "/forum/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID,
                Forum.FORUM_MEDIA_ID,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_ID,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_FOLLOW,
                Forum.FORUM_IS_RECOMAND,
                Forum.SYSTEM_VERSION
        );

        Boolean result = forumService.update(body, body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除论坛信息")
    @RequestMapping(value = "/forum/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID,
                Forum.SYSTEM_VERSION
        );

        Boolean result = forumService.delete(body.getForumId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }
    
    @ApiOperation(value = "论坛重建缓存")
    @RequestMapping(value = "/forum/admin/v1/replace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1(@RequestBody Forum body) {
        validateRequest(body, Forum.FORUM_ID);

        forumService.replace(body.getForumId());

        return renderJson(true);
    }

}