package com.nowui.cloud.sns.forum.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.entity.enums.FileType;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.entity.Member;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumAudit;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.service.ForumAuditService;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.service.TopicForumService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

    @ApiOperation(value = "论坛信息列表")
    @RequestMapping(value = "/forum/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Forum body) {
       validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_NAME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_RECOMAND,
                Forum.FORUM_AUDIT_STATUS,
                Forum.PAGE_INDEX,
                Forum.PAGE_SIZE
       );

       Integer resultTotal = forumService.countForAdmin(
               body.getAppId(), 
               body.getForumName(), 
               body.getForumIsActive(), 
               body.getForumIsRecommend(),
               body.getForumAuditStatus()
       );
       
       List<Forum> resultList = forumService.listForAdmin(
    		   body.getAppId(), 
    		   body.getForumName(), 
    		   body.getForumIsActive(), 
    		   body.getForumIsRecommend(), 
    		   body.getForumAuditStatus(),
    		   body.getPageIndex(), 
    		   body.getPageSize()
	   );
       
       // 论坛头像
       String fileIds = Util.beanToFieldString(resultList, Forum.FORUM_MEDIA);
       List<File> fileList = fileRpc.findsV1(fileIds);
       resultList = Util.beanReplaceField(resultList, Forum.FORUM_MEDIA, fileList, File.FILE_PATH);
       
       // 论坛背景
       String backFileIds = Util.beanToFieldString(resultList, Forum.FORUM_BACKGROUND_MEDIA);
       List<File> backFileList = fileRpc.findsV1(backFileIds);
       resultList = Util.beanReplaceField(resultList, Forum.FORUM_BACKGROUND_MEDIA, backFileList, File.FILE_PATH);
       
       // 版主昵称和头像
       String userIds = Util.beanToFieldString(resultList, Forum.FORUM_MODERATOR);
       List<Member> moderatorList = memberRpc.nickNameAndAvatarListV1(userIds);
       resultList = Util.beanReplaceField(resultList, Forum.FORUM_MODERATOR, Member.USER_ID, moderatorList, UserAvatar.USER_AVATAR, UserNickName.USER_NICK_NAME);

       validateResponse(
            Forum.FORUM_ID,
            Forum.FORUM_MEDIA,
            Forum.FORUM_MEDIA_TYPE,
            Forum.FORUM_BACKGROUND_MEDIA,
            Forum.FORUM_BACKGROUND_MEDIA_TYPE,
            Forum.FORUM_NAME,
            Forum.FORUM_DESCRIPTION,
            Forum.FORUM_MODERATOR,
            Forum.FORUM_TOPIC_LOCATION,
            Forum.FORUM_SORT,
            Forum.FORUM_IS_TOP,
            Forum.FORUM_TOP_LEVEL,
            Forum.FORUM_TOP_END_TIME,
            Forum.FORUM_IS_ACTIVE,
            Forum.FORUM_IS_RECOMAND,
            Forum.FORUM_AUDIT_STATUS
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
                Forum.FORUM_MEDIA,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_IS_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_RECOMAND
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增论坛")
    @RequestMapping(value = "/forum/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Forum body) {
        validateRequest(
            body,
            Forum.APP_ID,
            Forum.FORUM_MEDIA,
            Forum.FORUM_MEDIA_TYPE,
            Forum.FORUM_NAME,
            Forum.FORUM_DESCRIPTION,
            Forum.FORUM_SORT,            
            Forum.FORUM_IS_TOP,             
            Forum.FORUM_TOP_LEVEL,       
            Forum.FORUM_TOP_END_TIME,    
            Forum.FORUM_IS_ACTIVE,       
            Forum.FORUM_IS_RECOMAND      
      );

      body.setForumMediaType(FileType.IMAGE.getKey());
      body.setForumBackgroundMedia(body.getForumMedia());
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

    @ApiOperation(value = "修改论坛信息")
    @RequestMapping(value = "/forum/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID,
                Forum.FORUM_MEDIA,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA,
                Forum.FORUM_BACKGROUND_MEDIA_TYPE,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR,
                Forum.FORUM_TOPIC_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_IS_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
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