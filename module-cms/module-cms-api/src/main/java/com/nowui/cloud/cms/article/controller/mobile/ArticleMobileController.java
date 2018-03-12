package com.nowui.cloud.cms.article.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.cms.article.view.ArticleView;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文章移动端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "文章", description = "文章接口移动端管理")
@RestController
public class ArticleMobileController extends BaseController {
    
    @Autowired
    private ArticleService articleService;
    
    @ApiOperation(value = "热门文章列表")
    @RequestMapping(value = "/article/mobile/v1/hot/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> hotListV1() {
        ArticleView articleEntity = getEntry(ArticleView.class);

        validateRequest(
                articleEntity,
                ArticleView.APP_ID,
                ArticleView.PAGE_INDEX,
                ArticleView.PAGE_SIZE
        );

        List<ArticleView> resultList = articleService.hotList(articleEntity.getAppId(), articleEntity.getPageIndex(), articleEntity.getPageSize());

        validateResponse(
                ArticleView.ARTICLE_ID,
                ArticleView.ARTICLE_TITLE,
                ArticleView.ARTICLE_MEDIA_PATH,
                ArticleView.ARTICLE_MEDIA_TYPE
        );

        return renderJson(resultList);
    }


}
