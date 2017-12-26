package com.nowui.cloud.cms.article.service;

import java.util.List;

import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.rpc.ArticleCategoryRpc;
import com.nowui.cloud.service.BaseService;

/**
 * 文章分类业务接口
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
public interface ArticleCategoryService extends BaseService<ArticleCategory>, ArticleCategoryRpc {
    
    /**
     * 文章分类统计
     * @param appId 应用编号
     * @param articleCategoryParentId 父文章分类编号
     * @param articleCategoryName 文章名称
     * @return Integer 文章数量
     */
    Integer adminCount(String appId, String articleCategoryParentId, String articleCategoryName);

    /**
     * 文章分类列表
     *
     * @param appId 应用编号
     * @param articleCategoryParentId 父文章分类编号
     * @param articleCategoryName 文章名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<ArticleCategory> 文章分类列表
     */
    List<ArticleCategory> adminList(String appId, String articleCategoryParentId, String articleCategoryName, Integer m, Integer n);
}
