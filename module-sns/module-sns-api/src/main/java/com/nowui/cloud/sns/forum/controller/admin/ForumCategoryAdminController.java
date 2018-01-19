package com.nowui.cloud.sns.forum.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.sns.forum.entity.ForumCategory;
import com.nowui.cloud.sns.forum.service.ForumCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 论坛分类管理端控制器
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Api(value = "论坛分类", description = "论坛分类管理端接口管理")
@RestController
public class ForumCategoryAdminController extends BaseController {

    @Autowired
    private ForumCategoryService forumCategoryService;

    @ApiOperation(value = "论坛分类列表")
    @RequestMapping(value = "/forum/category/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody ForumCategory body) {
        validateRequest(
                body,
                ForumCategory.APP_ID,
                ForumCategory.FORUM_CATEGORY_NAME,
                ForumCategory.FORUM_CATEGORY_THUMB,
                ForumCategory.FORUM_CATEGORY_SORT,
                ForumCategory.FORUM_CATEGORY_IS_ACTIVE,
                ForumCategory.FORUM_CATEGORY_IS_RECOMMEND,
                ForumCategory.PAGE_INDEX,
                ForumCategory.PAGE_SIZE
        );

        Integer resultTotal = forumCategoryService.countForAdmin(body.getAppId() , body.getForumCategoryName(), body.getForumCategoryThumb(), body.getForumCategorySort(), body.getForumCategoryIsActive(), body.getForumCategoryIsRecommend());
        List<ForumCategory> resultList = forumCategoryService.listForAdmin(body.getAppId(), body.getForumCategoryName(), body.getForumCategoryThumb(), body.getForumCategorySort(), body.getForumCategoryIsActive(), body.getForumCategoryIsRecommend(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                ForumCategory.FORUM_CATEGORY_ID,
                ForumCategory.FORUM_CATEGORY_NAME,
                ForumCategory.FORUM_CATEGORY_THUMB,
                ForumCategory.FORUM_CATEGORY_SORT,
                ForumCategory.FORUM_CATEGORY_IS_ACTIVE,
                ForumCategory.FORUM_CATEGORY_IS_RECOMMEND
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "论坛分类信息")
    @RequestMapping(value = "/forum/category/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody ForumCategory body) {
        validateRequest(
                body,
                ForumCategory.APP_ID,
                ForumCategory.FORUM_CATEGORY_ID
        );

        ForumCategory result = forumCategoryService.find(body.getForumCategoryId());

        validateResponse(
                ForumCategory.FORUM_CATEGORY_ID,
                ForumCategory.FORUM_CATEGORY_NAME,
                ForumCategory.FORUM_CATEGORY_THUMB,
                ForumCategory.FORUM_CATEGORY_SORT,
                ForumCategory.FORUM_CATEGORY_IS_ACTIVE,
                ForumCategory.FORUM_CATEGORY_IS_RECOMMEND
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增论坛分类")
    @RequestMapping(value = "/forum/category/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody ForumCategory body) {
        validateRequest(
                body,
                ForumCategory.APP_ID,
                ForumCategory.FORUM_CATEGORY_NAME,
                ForumCategory.FORUM_CATEGORY_THUMB,
                ForumCategory.FORUM_CATEGORY_SORT,
                ForumCategory.FORUM_CATEGORY_IS_ACTIVE,
                ForumCategory.FORUM_CATEGORY_IS_RECOMMEND
        );

        Boolean result = forumCategoryService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改论坛分类")
    @RequestMapping(value = "/forum/category/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody ForumCategory body) {
        validateRequest(
                body,
                ForumCategory.FORUM_CATEGORY_ID,
                ForumCategory.APP_ID,
                ForumCategory.FORUM_CATEGORY_NAME,
                ForumCategory.FORUM_CATEGORY_THUMB,
                ForumCategory.FORUM_CATEGORY_SORT,
                ForumCategory.FORUM_CATEGORY_IS_ACTIVE,
                ForumCategory.FORUM_CATEGORY_IS_RECOMMEND,
                ForumCategory.SYSTEM_VERSION
        );

        Boolean result = forumCategoryService.update(body, body.getForumCategoryId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除论坛分类")
    @RequestMapping(value = "/forum/category/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody ForumCategory body) {
        validateRequest(
                body,
                ForumCategory.FORUM_CATEGORY_ID,
                ForumCategory.APP_ID,
                ForumCategory.SYSTEM_VERSION
        );

        Boolean result = forumCategoryService.delete(body.getForumCategoryId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}