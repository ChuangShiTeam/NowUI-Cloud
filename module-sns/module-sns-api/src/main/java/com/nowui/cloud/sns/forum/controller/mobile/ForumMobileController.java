package com.nowui.cloud.sns.forum.controller.mobile;

import com.nowui.cloud.base.file.entity.enums.FileType;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

	 @ApiOperation(value = "新增论坛信息")
	 @RequestMapping(value = "/forum/mobile/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	 public Map<String, Object> save(@RequestBody Forum body) {
	     validateRequest(
            body,
            Forum.APP_ID,
            Forum.FORUM_MEDIA_ID,
//            Forum.FORUM_MEDIA_TYPE,
//            Forum.FORUM_BACKGROUND_MEDIA_ID,
//            Forum.FORUM_BACKGROUND_MEDIA_TYPE,
            Forum.FORUM_NAME,
            Forum.FORUM_DESCRIPTION,
//            Forum.FORUM_MODERATOR,
            Forum.FORUM_TOPIC_LOCATION
//            Forum.FORUM_SORT,
//            Forum.FORUM_TOP,
//            Forum.FORUM_TOP_LEVEL,
//            Forum.FORUM_TOP_END_TIME,
//            Forum.FORUM_IS_ACTIVE,
//            Forum.FORUM_IS_RECOMAND
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

        Boolean result = forumService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }
	 
	@ApiOperation(value = "论坛信息")
    @RequestMapping(value = "/forum/mobile/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Forum body) {
        validateRequest(
                body,
                Forum.APP_ID,
                Forum.FORUM_ID
        );

        Forum result = forumService.find(body.getForumId());
        //根据forumModerator查询版主信息:去会员表查找--昵称,个人简介,头像封装成json
        
        //根据论坛id去forum_user_follow_map表查找此论坛的userId,然后遍历查找用户头像
        
        
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
	 
	


}