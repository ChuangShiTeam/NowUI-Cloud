package com.nowui.cloud.cms.article.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.article.entity.ArticleCategory;
import com.nowui.cloud.cms.article.view.ArticleCategoryView;
import java.util.List;
import java.util.Date;

/**
 * 文章分类业务接口
 *
 * @author ZhongYongQiang
 *
 * 2018-03-15
 */
public interface ArticleCategoryService extends BaseService<ArticleCategory, ArticleCategoryView> {

    /**
     * 文章分类统计
     *
     * @param appId 应用编号
     * @param articleCategoryName 分类名称
     * @param articleCategoryCode 分类编码
     * @return Integer 文章分类统计
     */
    Integer countForAdmin(String appId, String articleCategoryName, String articleCategoryCode);

    /**
     * 文章分类列表
     *
     * @param appId 应用编号
     * @param articleCategoryName 分类名称
     * @param articleCategoryCode 分类编码
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ArticleCategory> 文章分类列表
     */
    List<ArticleCategoryView> listForAdmin(String appId, String articleCategoryName, String articleCategoryCode, Integer pageIndex, Integer pageSize);

}