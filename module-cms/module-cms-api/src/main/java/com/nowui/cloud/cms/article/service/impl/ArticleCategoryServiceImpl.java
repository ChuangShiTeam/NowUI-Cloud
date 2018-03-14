package com.nowui.cloud.cms.article.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.mapper.ArticleCategoryMapper;
import com.nowui.cloud.cms.article.repository.ArticleCategoryRepository;
import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 文章分类业务接口实现
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Service
public class ArticleCategoryServiceImpl extends BaseServiceImpl<ArticleCategoryMapper, ArticleCategory, ArticleCategoryRepository, ArticleCategoryView> implements ArticleCategoryService {

    @Override
    public Integer countForAdmin(String appId, String articleCategoryName, String articleCategoryCode) {
        Integer count = 0;
        
        if (Util.isNullOrEmpty(articleCategoryName) && Util.isNullOrEmpty(articleCategoryCode)) {
            Criteria criteria = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                    .and(ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID).is("")
                    .and(ArticleCategoryView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            count = count(query);
        } else {
            Criteria criteria = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                    .and(ArticleCategoryView.ARTICLE_CATEGORY_NAME).regex(".*?" + articleCategoryName + ".*")
                    .and(ArticleCategoryView.ARTICLE_CATEGORY_CODE).regex(".*?" + articleCategoryCode + ".*")
                    .and(ArticleCategoryView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            count = count(query);
        }
        
        return count;
    }
    
    @Override
    public List<ArticleCategoryView> listForAdmin(String appId, String articleCategoryName, String articleCategoryCode, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                .and(ArticleCategoryView.ARTICLE_CATEGORY_NAME).regex(".*?" + articleCategoryName + ".*")
                .and(ArticleCategoryView.ARTICLE_CATEGORY_CODE).regex(".*?" + articleCategoryCode + ".*")
                .and(ArticleCategoryView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, ArticleCategoryView.ARTICLE_CATEGORY_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);
        
        return list(query, sort, pageIndex, pageSize);
    }
    
    @Override
    public List<Map<String, Object>> adminTreeList(String appId, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                .and(ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID).is("")
                .and(ArticleCategoryView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, ArticleCategoryView.ARTICLE_CATEGORY_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);
        
        List<ArticleCategoryView> topList = list(query, sort, pageIndex, pageSize);
        
        Criteria criteria1 = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                .and(ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID).ne("")
                .and(ArticleCategoryView.SYSTEM_STATUS).is(true);


        Query query1 = new Query(criteria1);
        query1.with(sort);
        
        List<ArticleCategoryView> childrenList = list(query1, sort);
        List<Map<String, Object>> list = new ArrayList<>();
        
        for (ArticleCategoryView articleCategoryView : topList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ArticleCategoryView.ARTICLE_CATEGORY_ID, articleCategoryView.getArticleCategoryId());
            map.put(ArticleCategoryView.ARTICLE_CATEGORY_NAME, articleCategoryView.getArticleCategoryName());
            map.put(ArticleCategoryView.ARTICLE_CATEGORY_CODE, articleCategoryView.getArticleCategoryCode());
            map.put(ArticleCategoryView.ARTICLE_CATEGORY_SORT, articleCategoryView.getArticleCategorySort());
            
            map.put(Constant.CHILDREN, getChildren(childrenList, articleCategoryView.getArticleCategoryId(), ArticleCategoryView.ARTICLE_CATEGORY_SORT, ArticleCategoryView.ARTICLE_CATEGORY_CODE));
            
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
    private List<Map<String, Object>> getChildren(List<ArticleCategoryView> articleCategoryViewList, String articleCategoryParentId, String... keys) {
        List<Map<String, Object>> list = new ArrayList<>();
        
        if (articleCategoryViewList != null && articleCategoryViewList.size() > 0) {
            for (ArticleCategoryView articleCategoryView : articleCategoryViewList) {
                if (articleCategoryParentId.equals(articleCategoryView.getArticleCategoryParentId())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put(ArticleCategoryView.ARTICLE_CATEGORY_ID, articleCategoryView.getArticleCategoryId());
                    map.put(ArticleCategoryView.ARTICLE_CATEGORY_NAME, articleCategoryView.getArticleCategoryName());
                    
                    for (String key : keys) {
                        map.put(key, articleCategoryView.get(key)); 
                    }
                    
                    List<Map<String, Object>> childrenList = getChildren(articleCategoryViewList, articleCategoryView.getArticleCategoryId(), keys);
                    
                    map.put(Constant.CHILDREN, childrenList);
                    
                    list.add(map);
                }
            }
        }
        
        return list;
    }

    @Override
    public List<Map<String, Object>> adminAllTreeList(String appId) {
        Criteria criteria = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                .and(ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID).is("")
                .and(ArticleCategoryView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.ASC, ArticleCategoryView.ARTICLE_CATEGORY_SORT));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);
        
        List<ArticleCategoryView> topList = list(query, sort);
        
        Criteria criteria1 = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                .and(ArticleCategoryView.ARTICLE_CATEGORY_PARENT_ID).ne("")
                .and(ArticleCategoryView.SYSTEM_STATUS).is(true);


        Query query1 = new Query(criteria1);
        query1.with(sort);
        
        List<ArticleCategoryView> childrenList = list(query1, sort);
        List<Map<String, Object>> list = new ArrayList<>();
        
        for (ArticleCategoryView articleCategoryView : topList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(ArticleCategoryView.ARTICLE_CATEGORY_ID, articleCategoryView.getArticleCategoryId());
            map.put(ArticleCategoryView.ARTICLE_CATEGORY_NAME, articleCategoryView.getArticleCategoryName());
            map.put(ArticleCategoryView.ARTICLE_CATEGORY_SORT, articleCategoryView.getArticleCategorySort());
            
            map.put(Constant.CHILDREN, getChildren(childrenList, articleCategoryView.getArticleCategoryId(), ArticleCategoryView.ARTICLE_CATEGORY_SORT));
            
            list.add(map);
        }
        
        return list;
    }

    @Override
    public ArticleCategoryView findByCategoryCode(String appId, String articleCategoryCode) {
        Criteria criteria = Criteria.where(ArticleCategoryView.APP_ID).is(appId)
                .and(ArticleCategoryView.ARTICLE_CATEGORY_CODE).is(articleCategoryCode)
                .and(ArticleCategoryView.SYSTEM_STATUS).is(true);
        Query query = new Query(criteria);
        
        return find(query);
    }

}
