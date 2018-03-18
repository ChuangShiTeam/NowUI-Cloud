package com.nowui.cloud.sns.forum.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.file.file.entity.enums.FileType;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.ForumBackgroundMedia;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.forum.view.ForumBackgroundMediaView;
import com.nowui.cloud.sns.forum.view.ForumView;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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
	 
	 @ApiOperation(value = "论坛列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = ForumView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = ForumView.FORUM_NAME, value = "论坛名称", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/forum/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore ForumView forumView, @ApiIgnore CommonView commonView) {
       validateRequest(
		    forumView,
		    ForumView.APP_ID,
		    ForumView.FORUM_NAME,
		    ForumView.FORUM_IS_ACTIVE,
		    ForumView.FORUM_IS_RECOMMEND,
		    ForumView.FORUM_AUDIT_STATUS,
            CommonView.PAGE_INDEX,
            CommonView.PAGE_SIZE
       );

       Integer resultTotal = forumService.countForAdmin(
    		   forumView.getAppId(), 
    		   forumView.getForumName(), 
    		   forumView.getForumIsActive(), 
    		   forumView.getForumIsRecommend(),
    		   forumView.getForumAuditStatus()
       );
       
       List<Forum> resultList = forumService.listForAdmin(
    		   forumView.getAppId(), 
    		   forumView.getForumName(), 
    		   forumView.getForumIsActive(), 
    		   forumView.getForumIsRecommend(), 
    		   forumView.getForumAuditStatus(),
    		   commonView.getPageIndex(), 
    		   commonView.getPageSize()
	   );
       

       validateResponse(
		    ForumView.FORUM_ID,
		    ForumView.FORUM_MEDIA_FILE_PATH,
		    ForumView.FORUM_MEDIA_TYPE,
		    ForumView.FORUM_BACKGROUND_MEDIA_LIST,
		    ForumView.FORUM_NAME,
		    ForumView.FORUM_DESCRIPTION,
		    ForumView.FORUM_MODERATOR_MEMBER_ID,
		    ForumView.FORUM_LOCATION,
		    ForumView.FORUM_SORT,
		    ForumView.FORUM_IS_TOP,
		    ForumView.FORUM_TOP_LEVEL,
		    ForumView.FORUM_TOP_END_TIME,
		    ForumView.FORUM_IS_ACTIVE,
		    ForumView.FORUM_IS_RECOMMEND,
            Forum.FORUM_AUDIT_STATUS
       );
       
       validateSecondResponse(Forum.FORUM_BACKGROUND_MEDIA_LIST, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_ID, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_FILE_PATH, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_SORT, ForumBackgroundMedia.FORUM_BACKGROUND_MEDIA_TYPE);

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "论坛查询")
    @RequestMapping(value = "/forum/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore ForumView forumView, @ApiIgnore CommonView commonView) {
        validateRequest(
        		forumView,
        		ForumView.APP_ID,
        		ForumView.FORUM_ID
        );

        ForumView result = forumService.find(forumView.getForumId());
        
        validateResponse(
        		ForumView.FORUM_ID,
        		ForumView.FORUM_MEDIA_FILE_PATH,
        		ForumView.FORUM_MEDIA_TYPE,
        		ForumView.FORUM_BACKGROUND_MEDIA_LIST,
        		ForumView.FORUM_NAME,
        		ForumView.FORUM_DESCRIPTION,
        		ForumView.FORUM_MODERATOR_MEMBER_ID,
        		ForumView.FORUM_LOCATION,
        		ForumView.FORUM_SORT,
        		ForumView.FORUM_IS_TOP,
        		ForumView.FORUM_TOP_LEVEL,
        		ForumView.FORUM_TOP_END_TIME,
        		ForumView.FORUM_IS_ACTIVE,
        		ForumView.FORUM_IS_RECOMMEND
        );
        
        validateSecondResponse(ForumView.FORUM_BACKGROUND_MEDIA_LIST, ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_FILE_ID, ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_FILE_PATH, ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_SORT, ForumBackgroundMediaView.FORUM_BACKGROUND_MEDIA_TYPE);

        return renderJson(result);
    }

    @ApiOperation(value = "新增论坛")
    @RequestMapping(value = "/forum/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore Forum forum, @ApiIgnore ForumView forumView, @ApiIgnore CommonView commonView) {
        validateRequest(
        	forumView,
            ForumView.APP_ID,
            ForumView.FORUM_MEDIA_FILE_PATH,
            ForumView.FORUM_MEDIA_TYPE,
            ForumView.FORUM_NAME,
            ForumView.FORUM_DESCRIPTION,
            ForumView.FORUM_SORT,            
            ForumView.FORUM_IS_TOP,             
            ForumView.FORUM_TOP_LEVEL,       
            ForumView.FORUM_TOP_END_TIME,    
            ForumView.FORUM_IS_ACTIVE,       
            ForumView.FORUM_IS_RECOMMEND   
      );

      forumView.setForumMediaType(FileType.IMAGE.getKey());
      forumView.setForumModeratorMemberId(commonView.getSystemRequestUserId());
      forumView.setForumAuditStatus(ForumAuditStatus.WAIT_AUDIT.getKey());
      forumView.setForumAuditContent("");

      String forumId = Util.getRandomUUID();
      String systemRequestUserId = commonView.getSystemRequestUserId();
      Forum result = forumService.save(forum, forumId, systemRequestUserId);
      
      Boolean success = false;

      if (result != null) {
          success = true;
      }

      return renderJson(success);
  }

    @ApiOperation(value = "修改论坛信息")
    @RequestMapping(value = "/forum/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore Forum forum, @ApiIgnore ForumView forumView, @ApiIgnore CommonView commonView) {
        validateRequest(
        		forumView,
                ForumView.FORUM_ID,
                ForumView.APP_ID,
                ForumView.FORUM_MEDIA_FILE_PATH,
                ForumView.FORUM_MEDIA_TYPE,
                ForumView.FORUM_BACKGROUND_MEDIA_LIST,
                ForumView.FORUM_NAME,
                ForumView.FORUM_DESCRIPTION,
                ForumView.FORUM_MODERATOR_MEMBER_ID,
                ForumView.FORUM_LOCATION,
                ForumView.FORUM_SORT,
                ForumView.FORUM_IS_TOP,
                ForumView.FORUM_TOP_LEVEL,
                ForumView.FORUM_TOP_END_TIME,
                ForumView.FORUM_IS_ACTIVE,
                ForumView.FORUM_IS_RECOMMEND,
                ForumView.SYSTEM_VERSION
        );
        String forumId = forumView.getForumId();
        String appId = forumView.getAppId();
        String systemRequestUserId = commonView.getSystemRequestUserId();
        Integer systemVersion = forumView.getSystemVersion();

        Forum result = forumService.update(forum, forumId, appId, systemRequestUserId, systemVersion);
        
        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除论坛信息")
    @RequestMapping(value = "/forum/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore Forum forum, @ApiIgnore ForumView forumView, @ApiIgnore CommonView commonView) {
        validateRequest(
        		forumView,
                ForumView.FORUM_ID,
                ForumView.APP_ID,
                ForumView.SYSTEM_VERSION
        );
        
        String forumId = forumView.getForumId();
        String appId = forumView.getAppId();
        String systemRequestUserId = commonView.getSystemRequestUserId();
        Integer systemVersion = forumView.getSystemVersion();

        Forum result = forumService.delete(forumId, appId, systemRequestUserId, systemVersion);
        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }
    
    @ApiOperation(value = "论坛数据同步")
    @RequestMapping(value = "/forum/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
    	
    	List<Forum> forumlist = forumService.listByMysql();
    	
    	for (Forum forum : forumlist) {
			ForumView forumView = new ForumView();
			forumView.copy(forum);
			
			forumService.saveOrUpdate(forumView, forumView.getForumId());
		}

        return renderJson(true);
    }

}