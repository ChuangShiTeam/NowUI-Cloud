package com.nowui.cloud.cms.article.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.mapper.ArticleCategoryMapper;
import com.nowui.cloud.cms.article.repository.ArticleCategoryRepository;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 文章分类业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class ArticleCategoryServiceImpl extends SuperServiceImpl<ArticleCategoryMapper, ArticleCategory, ArticleCategoryRepository, ArticleCategoryView> implements ArticleCategoryService {

    @Override
    public Integer countForAdmin(String appId, String articleCategoryName, String articleCategoryCode) {
        Integer count = 0;
        
        if (Util.isNullOrEmpty(articleCategoryName) && Util.isNullOrEmpty(articleCategoryCode)) {
            count = count(
                    new BaseWrapper<ArticleCategory>()
                            .eq(ArticleCategory.APP_ID, appId)
                            .eq(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, "")
                            .eq(ArticleCategory.SYSTEM_STATUS, true)
                    );
        } else {
            count = count(
                    new BaseWrapper<ArticleCategory>()
                            .eq(ArticleCategory.APP_ID, appId)
                            .likeAllowEmpty(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategoryName)
                            .likeAllowEmpty(ArticleCategory.ARTICLE_CATEGORY_CODE, articleCategoryCode)
                            .eq(ArticleCategory.SYSTEM_STATUS, true)
                    );
        }
        
        return count;
    }
    
    @Override
    public List<ArticleCategory> listForAdmin(String appId, String articleCategoryName, String articleCategoryCode, Integer m, Integer n) {
        List<ArticleCategory> articleCategoryList = list(
                new BaseWrapper<ArticleCategory>()
                        .eq(ArticleCategory.APP_ID, appId)
                        .likeAllowEmpty(ArticleCategory.ARTICLE_CATEGORY_NAME, articleCategoryName)
                        .likeAllowEmpty(ArticleCategory.ARTICLE_CATEGORY_CODE, articleCategoryCode)
                        .eq(ArticleCategory.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(ArticleCategory.ARTICLE_CATEGORY_SORT))
                ,m
                ,n
        );
        return articleCategoryList;
    }
    
    @Override
    public List<Map<String, Object>> adminTreeList(String appId, Integer pageIndex, Integer pageSize) {
        List<ArticleCategory> topList = list(
                new BaseWrapper<ArticleCategory>()
                        .eq(ArticleCategory.APP_ID, appId)
                        .eq(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, "")
                        .eq(ArticleCategory.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(ArticleCategory.ARTICLE_CATEGORY_SORT)),
                pageIndex,
                pageSize
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
            map.put(ArticleCategory.ARTICLE_CATEGORY_CODE, articleCategory.getArticleCategoryCode());
            map.put(ArticleCategory.ARTICLE_CATEGORY_SORT, articleCategory.getArticleCategorySort());
            
            map.put(Constant.CHILDREN, getChildren(childrenList, articleCategory.getArticleCategoryId(), ArticleCategory.ARTICLE_CATEGORY_SORT, ArticleCategory.ARTICLE_CATEGORY_CODE));
            
            list.add(map);
        }
        return list;
    }
    
    /**
     * 递归遍历生成树形结构数据
     * 
     * @param articleCategoryList 文章分类列表
     * @param articleCategoryParentId 文章分类父级编号
     * @param keys 树形中需要保存的Key
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

    @Override
    public List<Map<String, Object>> adminAllTreeList(String appId) {
        List<ArticleCategory> topList = list(
                new BaseWrapper<ArticleCategory>()
                        .eq(ArticleCategory.APP_ID, appId)
                        .eq(ArticleCategory.ARTICLE_CATEGORY_PARENT_ID, "")
                        .eq(ArticleCategory.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(ArticleCategory.ARTICLE_CATEGORY_SORT))
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

    @Override
    public ArticleCategory findByCategoryCode(String appId, String articleCategoryCode) {
        ArticleCategory articleCategory = find(
                new BaseWrapper<ArticleCategory>()
                .eq(ArticleCategory.APP_ID, appId)
                .eq(ArticleCategory.ARTICLE_CATEGORY_CODE, articleCategoryCode)
                .eq(ArticleCategory.SYSTEM_STATUS, true)
        );
        return articleCategory;
    }

}
