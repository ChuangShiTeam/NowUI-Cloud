package com.nowui.cloud.sns.forum.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.file.file.entity.enums.FileType;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumBackgroundMedia;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import com.nowui.cloud.sns.forum.service.ForumUserUnfollowService;
import com.nowui.cloud.sns.forum.view.ForumView;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 论坛管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛", description = "论坛管理端接口管理")
@RestController
public class ForumAdminController extends BaseController {

	@Autowired
	 private ForumService forumService;
	 
	 @Autowired
	 private TopicForumService topicForumService;
	 
	 @Autowired
	 private ForumUserFollowService forumUserFollowService;
	 
	 @Autowired
	 private FileRpc fileRpc;
	 
	 @Autowired
	 private MemberRpc memberRpc;
	 
	 @Autowired
	 private ForumUserUnfollowService forumUserUnfollowService;

    @ApiOperation(value = "论坛列表")
    @RequestMapping(value = "/forum/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
    	Forum body = getEntry(Forum.class);
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
       

       validateResponse(
            Forum.FORUM_ID,
            Forum.FORUM_MEDIA_FILE_PATH,
            Forum.FORUM_MEDIA_TYPE,
            Forum.FORUM_BACKGROUND_MEDIA_LIST,
            Forum.FORUM_NAME,
            Forum.FORUM_DESCRIPTION,
            Forum.FORUM_MODERATOR_MEMBER_ID,
            Forum.FORUM_LOCATION,
            Forum.FORUM_SORT,
            Forum.FORUM_IS_TOP,
            Forum.FORUM_TOP_LEVEL,
            Forum.FORUM_TOP_END_TIME,
            Forum.FORUM_IS_ACTIVE,
            Forum.FORUM_IS_RECOMAND,
            Forum.FORUM_AUDIT_STATUS
       );
       
       validateSecondResponse(Forum.FORUM_BACKGROUND_MEDIA_LIST, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_ID, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_PATH, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_SORT, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_TYPE);

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "论坛查询")
    @RequestMapping(value = "/forum/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
    	Forum body = getEntry(Forum.class);
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID
        );

        ForumView result = forumService.find(body.getForumId());
        
        // 论坛图片
//        File forumMediaFile = fileRpc.findV1(result.getForumMedia());
//        forumMediaFile.keep(File.FILE_ID, File.FILE_PATH);
//        result.put(Forum.FORUM_MEDIA, forumMediaFile);
        
        // 论坛背景
//        File forumBackgroundFile = fileRpc.findV1(result.getForumBackgroundMedia());
//        forumBackgroundFile.keep(File.FILE_ID, File.FILE_PATH);
//        result.put(Forum.FORUM_BACKGROUND_MEDIA, forumBackgroundFile);
        
        // 版主昵称和头像
//        Member forumModerator = memberRpc.nickNameAndAvatarFindV1(result.getForumModerator());
//        if (!Util.isNullOrEmpty(forumModerator)) {
//            
//        }
//        result.put(Forum.FORUM_MODERATOR, forumModerator);

        validateResponse(
                Forum.FORUM_ID,
                Forum.FORUM_MEDIA_FILE_PATH,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_LIST,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR_MEMBER_ID,
                Forum.FORUM_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_IS_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_RECOMAND
        );
        
        validateSecondResponse(Forum.FORUM_BACKGROUND_MEDIA_LIST, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_ID, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_PATH, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_SORT, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_TYPE);

        return renderJson(result);
    }

    @ApiOperation(value = "新增论坛")
    @RequestMapping(value = "/forum/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
    	Forum body = getEntry(Forum.class);
        validateRequest(
            body,
            Forum.APP_ID,
            Forum.FORUM_MEDIA_FILE_PATH,
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
      body.setForumModeratorMemberId(body.getSystemRequestUserId());
      body.setForumAuditStatus(ForumAuditStatus.WAIT_AUDIT.getKey());
      body.setForumAuditContent("");

      String forumId = Util.getRandomUUID();
      String systemRequestUserId = body.getSystemRequestUserId();
      String appId = body.getAppId();

      Forum result = forumService.save(body, forumId, systemRequestUserId);
      
      Boolean success = false;

      if (result != null) {
          success = true;
      }

      return renderJson(success);
  }

    @ApiOperation(value = "修改论坛信息")
    @RequestMapping(value = "/forum/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID,
                Forum.FORUM_MEDIA_FILE_PATH,
                Forum.FORUM_MEDIA_TYPE,
                Forum.FORUM_BACKGROUND_MEDIA_LIST,
                Forum.FORUM_NAME,
                Forum.FORUM_DESCRIPTION,
                Forum.FORUM_MODERATOR_MEMBER_ID,
                Forum.FORUM_LOCATION,
                Forum.FORUM_SORT,
                Forum.FORUM_IS_TOP,
                Forum.FORUM_TOP_LEVEL,
                Forum.FORUM_TOP_END_TIME,
                Forum.FORUM_IS_ACTIVE,
                Forum.FORUM_IS_RECOMAND,
                Forum.SYSTEM_VERSION
        );
        String forumId = body.getForumId();
        String appId = body.getAppId();
        String systemRequestUserId = body.getSystemRequestUserId();
        Integer systemVersion = body.getSystemVersion();

        Forum result = forumService.update(body, forumId, systemRequestUserId, systemVersion);
        
        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除论坛信息")
    @RequestMapping(value = "/forum/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.FORUM_ID,
                Forum.APP_ID,
                Forum.SYSTEM_VERSION
        );
        
        String forumId = body.getForumId();
        String appId = body.getAppId();
        String systemRequestUserId = body.getSystemRequestUserId();
        Integer systemVersion = body.getSystemVersion();

        Forum result = forumService.delete(forumId, systemRequestUserId, systemVersion);
        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }
    
    @ApiOperation(value = "论坛数据同步")
    @RequestMapping(value = "/forum/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1(@RequestBody Forum body) {
    	
    	List<Forum> forumlist = forumService.listByMysql();
    	
    	for (Forum forum : forumlist) {
			ForumView forumView = new ForumView();
			forumView.putAll(forum);
			forumService.update(forumView);
		}

        return renderJson(true);
    }

}