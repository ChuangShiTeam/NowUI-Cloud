package com.nowui.cloud.cms.article.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文章接口后台端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "文章分类", description = "文章分类后台端接口管理")
@RestController
public class ArticleCategoryAdminController extends BaseController {
    
    @Autowired
    private ArticleCategoryService articleCategoryService;
    
    @ApiOperation(value = "文章分类列表")
    @RequestMapping(value = "/article/category/admin/v1/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        
        ArticleCategoryView articleCategoryView = getEntry(ArticleCategoryView.class);

        validateRequest(
                articleCategoryView,
                ArticleCategoryView.APP_ID,
                ArticleCategoryView.ARTICLE_CATEGORY_NAME,
                ArticleCategoryView.ARTICLE_CATEGORY_CODE,
                ArticleCategoryView.PAGE_INDEX,
                ArticleCategoryView.PAGE_SIZE
        );

        Integer resultTotal = articleCategoryService.countForAdmin(articleCategoryView.getAppId(), articleCategoryView.getArticleCategoryName(), articleCategoryView.getArticleCategoryCode());

        if (Util.isNullOrEmpty(articleCategoryView.getArticleCategoryName()) && Util.isNullOrEmpty(articleCategoryView.getArticleCategoryCode())) {

            List<Map<String, Object>> resultList = articleCategoryService.adminTreeList(articleCategoryView.getAppId(), articleCategoryView.getPageIndex(), articleCategoryView.getPageSize());

            validateResponse(ArticleCategoryView.ARTICLE_CATEGORY_ID, ArticleCategoryView.ARTICLE_CATEGORY_NAME, ArticleCategoryView.ARTICLE_CATEGORY_CODE, ArticleCategoryView.ARTICLE_CATEGORY_SORT, Constant.CHILDREN);

            validateSecondResponse(Constant.CHILDREN, ArticleCategoryView.ARTICLE_CATEGORY_ID, ArticleCategoryView.ARTICLE_CATEGORY_NAME, ArticleCategoryView.ARTICLE_CATEGORY_CODE, ArticleCategoryView.ARTICLE_CATEGORY_SORT, Constant.CHILDREN);

            return renderJson(resultTotal, resultList);

        } else {
            List<ArticleCategoryView> resultList = articleCategoryService.listForAdmin(articleCategoryView.getAppId(), articleCategoryView.getArticleCategoryName(), articleCategoryView.getArticleCategoryCode(), articleCategoryView.getPageIndex(), articleCategoryView.getPageSize());

            validateResponse(ArticleCategoryView.ARTICLE_CATEGORY_ID, ArticleCategoryView.ARTICLE_CATEGORY_NAME, ArticleCategoryView.ARTICLE_CATEGORY_CODE, ArticleCategoryView.ARTICLE_CATEGORY_SORT, Constant.CHILDREN);

            validateSecondResponse(Constant.CHILDREN, ArticleCategoryView.ARTICLE_CATEGORY_NAME, ArticleCategoryView.ARTICLE_CATEGORY_CODE, ArticleCategoryView.ARTICLE_CATEGORY_SORT, Constant.CHILDREN);

            return renderJson(resultTotal, resultList);
        }


    }
    
    @ApiOperation(value = "文章分类树形列表")
    @RequestMapping(value = "/article/category/admin/v1/all/tree/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> allTreeListV1() {
        ArticleCategoryView articleCategoryView = getEntry(ArticleCategoryView.class);

        validateRequest(
                articleCategoryView, 
                ArticleCategoryView.APP_ID
        );

        List<Map<String, Object>> resultList = articleCategoryService.adminAllTreeList(articleCategoryView.getAppId());

        validateResponse(ArticleCategoryView.ARTICLE_CATEGORY_ID, ArticleCategoryView.ARTICLE_CATEGORY_NAME, Constant.CHILDREN);

        validateSecondResponse(Constant.CHILDREN, ArticleCategoryView.ARTICLE_CATEGORY_ID, ArticleCategoryView.ARTICLE_CATEGORY_NAME, Constant.CHILDREN);

        return renderJson(resultList);
        
    }

    @ApiOperation(value = "根据编号查询文章分类信息")
    @RequestMapping(value = "/article/category/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        ArticleCategoryView articleCategoryView = getEntry(ArticleCategoryView.class);

        validateRequest(articleCategoryView, ArticleCategoryView.ARTICLE_CATEGORY_ID);

        ArticleCategoryView result = articleCategoryService.find(articleCategoryView.getArticleCategoryId());

        validateResponse(
                ArticleCategoryView.ARTICLE_CATEGORY_ID, 
                ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID, 
                ArticleCategoryView.ARTICLE_CATEGORY_NAME, 
                ArticleCategoryView.ARTICLE_CATEGORY_CODE, 
                ArticleCategoryView.ARTICLE_CATEGORY_KEYWORDS, 
                ArticleCategoryView.ARTICLE_CATEGORY_DESCRIPTION, 
                ArticleCategoryView.ARTICLE_CATEGORY_SORT, 
                ArticleCategoryView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "文章分类新增")
    @RequestMapping(value = "/article/category/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        ArticleCategory articleCategoryEntity = getEntry(ArticleCategory.class);

        String articleCategoryId = Util.getRandomUUID();
        String articleCategoryParentPath = "";

        validateRequest(
            articleCategoryEntity, 
            ArticleCategory.APP_ID, 
            ArticleCategory.ARTICLE_CATEGORY_PARENT_ID,
            ArticleCategory.ARTICLE_CATEGORY_NAME,
            ArticleCategory.ARTICLE_CATEGORY_CODE, 
            ArticleCategory.ARTICLE_CATEGORY_KEYWORDS, 
            ArticleCategory.ARTICLE_CATEGORY_DESCRIPTION
        );
        if (Util.isNullOrEmpty(articleCategoryEntity.getArticleCategoryParentId())) {

            JSONArray jsonArray = new JSONArray();

            articleCategoryParentPath = jsonArray.toJSONString();
        } else {
            ArticleCategoryView parent = articleCategoryService.find(articleCategoryEntity.getArticleCategoryParentId());

            JSONArray jsonArray = new JSONArray();;
            if (!Util.isNullOrEmpty(parent.getArticleCategoryParentPath())) {
                jsonArray = JSONArray.parseArray(parent.getArticleCategoryParentPath());
            }
            jsonArray.add(parent.getArticleCategoryId());

            articleCategoryParentPath = jsonArray.toJSONString();
        }

        articleCategoryEntity.setArticleCategoryParentPath(articleCategoryParentPath);

        ArticleCategory result = articleCategoryService.save(articleCategoryEntity, articleCategoryId, articleCategoryEntity.getSystemRequestUserId());

        if (result != null) {
            ArticleCategoryView articleCategoryView = new ArticleCategoryView();
            articleCategoryView.putAll(result);
            
            articleCategoryService.save(articleCategoryView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "文章分类修改")
    @RequestMapping(value = "/article/category/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        ArticleCategory articleCategoryEntity = getEntry(ArticleCategory.class);

        validateRequest(
            articleCategoryEntity, 
            ArticleCategory.ARTICLE_CATEGORY_ID, 
            ArticleCategory.APP_ID, 
            ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, 
            ArticleCategory.ARTICLE_CATEGORY_NAME, 
            ArticleCategory.ARTICLE_CATEGORY_CODE, 
            ArticleCategory.ARTICLE_CATEGORY_KEYWORDS, 
            ArticleCategory.ARTICLE_CATEGORY_DESCRIPTION, 
            ArticleCategory.SYSTEM_VERSION
        );

        ArticleCategory result = articleCategoryService.update(articleCategoryEntity, articleCategoryEntity.getArticleCategoryId(), articleCategoryEntity.getSystemRequestUserId(), articleCategoryEntity.getSystemVersion());

        if (result != null) {
            ArticleCategoryView articleCategoryView = new ArticleCategoryView();
            articleCategoryView.putAll(result);
            
            articleCategoryService.update(articleCategoryView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "文章分类删除")
    @RequestMapping(value = "/article/category/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        ArticleCategory articleCategoryEntity = getEntry(ArticleCategory.class);

        validateRequest(
            articleCategoryEntity, 
            ArticleCategory.ARTICLE_CATEGORY_ID,
            ArticleCategory.SYSTEM_VERSION
        );

        ArticleCategory result = articleCategoryService.delete(articleCategoryEntity.getArticleCategoryId(), articleCategoryEntity.getSystemRequestUserId(), articleCategoryEntity.getSystemVersion());

        if (result != null) {
            // TODO 删除文章分类
        }

        return renderJson(true);
    }
    
    @ApiOperation(value = "文章分类数据同步")
    @RequestMapping(value = "/article/category/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<ArticleCategory> articleCategoryList = articleCategoryService.listByMysql();

        for (ArticleCategory articleCategory : articleCategoryList) {
            ArticleCategoryView articleCategoryView = new ArticleCategoryView();
            articleCategoryView.putAll(articleCategory);

            articleCategoryService.saveOrUpdate(articleCategoryView);
        }

        return renderJson(true);
    }

}
