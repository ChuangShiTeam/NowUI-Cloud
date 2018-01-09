package com.nowui.cloud.sns.forum.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.forum.entity.ForumCategory;

import java.util.List;

/**
 * 论坛分类业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface ForumCategoryService extends BaseService<ForumCategory> {

    /**
     * 论坛分类统计
     *
     * @param appId 应用编号
     * @param forumCategoryName 论坛分类名称
     * @param forumCategoryThumb 论坛分类缩略图
     * @param forumCategorySort 论坛分类排序
     * @param forumCategoryEnabled 论坛分类是否启用
     * @param ForumCategoryRecommand 是否推荐
     * @return Integer 论坛分类统计
     */
    Integer countForAdmin(String appId, String forumCategoryName, String forumCategoryThumb, Integer forumCategorySort, Boolean forumCategoryEnabled, Boolean ForumCategoryRecommand);

    /**
     * 论坛分类列表
     *
     * @param appId 应用编号
     * @param forumCategoryName 论坛分类名称
     * @param forumCategoryThumb 论坛分类缩略图
     * @param integer 论坛分类排序
     * @param boolean1 论坛分类是否启用
     * @param boolean2 是否推荐
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumCategory> 论坛分类列表
     */
    List<ForumCategory> listForAdmin(String appId, String forumCategoryName, String forumCategoryThumb, Integer forumCategorySort, Boolean forumCategoryEnabled, Boolean ForumCategoryRecommand, Integer pageIndex, Integer pageSize);
}
