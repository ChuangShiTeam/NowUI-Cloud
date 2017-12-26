package com.nowui.cloud.cms.article.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.mapper.ArticleCategoryMapper;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 文章分类业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategoryMapper, ArticleCategory> implements ArticleCategoryService {

    @Override
    public Integer adminCount(String appId, String articleCategoryParentId, String articleCategoryName) {
        Integer count = mapper.selectCount(
                new EntityWrapper<ArticleCategory>()
                        .eq(ArticleCategory.APP_ID, appId)
                        .eq(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, articleCategoryParentId)
                        .like(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategoryName)
                        .eq(ArticleCategory.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ArticleCategory> adminList(String appId, String articleCategoryParentId, String articleCategoryName, Integer m, Integer n) {
        List<ArticleCategory> articleCategoryList = mapper.selectPage(
                new Page<ArticleCategory>(m, n),
                new EntityWrapper<ArticleCategory>()
                        .setSqlSelect(ArticleCategory.ARTICLE_CATEGORY_ID)
                        .eq(ArticleCategory.APP_ID, appId)
                        .eq(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, articleCategoryParentId)
                        .like(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategoryName)
                        .eq(ArticleCategory.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ArticleCategory.SYSTEM_CREATE_TIME))
        );
        
        for (ArticleCategory articleCategory : articleCategoryList) {
            articleCategory.putAll(find(articleCategory.getArticleCategoryId()));
        }
        return articleCategoryList;
    }

}
