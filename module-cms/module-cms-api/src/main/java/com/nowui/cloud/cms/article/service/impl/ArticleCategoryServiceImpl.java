package com.nowui.cloud.cms.article.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.mapper.ArticleCategoryMapper;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.mybatisplus.BaseWrapper;
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
    public Integer adminCount(String appId, String articleCategoryName) {
        Integer count = count(
                new BaseWrapper<ArticleCategory>()
                        .eq(ArticleCategory.APP_ID, appId)
                        .eq(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, "")
                        .like(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategoryName)
                        .eq(ArticleCategory.SYSTEM_STATUS, true)
        );
        return count;
    }
    
    @Override
    public List<Map<String, Object>> adminList(String appId, String articleCategoryName, Integer m, Integer n) {
        List<ArticleCategory> topList = list(
                new BaseWrapper<ArticleCategory>()
                        .eq(ArticleCategory.APP_ID, appId)
                        .eq(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, "")
                        .like(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategoryName)
                        .eq(ArticleCategory.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(ArticleCategory.ARTICLE_CATEGORY_SORT))
                ,m
                ,n
        );
        List<ArticleCategory> childrenList = list(
                new BaseWrapper<ArticleCategory>()
                .eq(ArticleCategory.APP_ID, appId)
                .ne(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, "")
                .eq(ArticleCategory.SYSTEM_STATUS, true)
                .orderAsc(Arrays.asList(ArticleCategory.ARTICLE_CATEGORY_SORT))
        );
        List<Map<String, Object>> list = new ArrayList<>();
        
        for (ArticleCategory articleCategory : topList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ArticleCategory.ARTICLE_CATEGORY_ID, articleCategory.getArticleCategoryId());
            map.put(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategory.getArticleCategoryName());
            map.put(ArticleCategory.ARTICLE_CATEGORY_SORT, articleCategory.getArticleCategorySort());
            
            map.put(Constant.CHILDREN, getChildren(childrenList, articleCategory.getArticleCategoryId(), ArticleCategory.ARTICLE_CATEGORY_SORT));
            
            list.add(map);
        }
        return list;
    }
    
    /**
     * 递归遍历生成树形结构数据
     * @param articleCategoryList
     * @param articleCategoryParentId
     * @param keys
     * @return
     */
    private List<Map<String, Object>> getChildren(List<ArticleCategory> articleCategoryList, String articleCategoryParentId, String... keys) {
        List<Map<String, Object>> list = new ArrayList<>();
        
        if (articleCategoryList != null && articleCategoryList.size() > 0) {
            for (ArticleCategory articleCategory : articleCategoryList) {
                if (articleCategoryParentId.equals(articleCategory.getArticleCategoryParentId())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(ArticleCategory.ARTICLE_CATEGORY_ID, articleCategory.getArticleCategoryId());
                    map.put(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategory.getArticleCategoryName());
                    
                    for (String key : keys) {
                        map.put(key, articleCategory.get(key)); 
                    }
                    
                    List<Map<String, Object>> childrenList = getChildren(articleCategoryList, articleCategory.getArticleCategoryId(), keys);
                    
                    map.put(Constant.CHILDREN, childrenList);
                    
                    list.add(map);
                }
            }
        }
        
        return list;
    }

}
