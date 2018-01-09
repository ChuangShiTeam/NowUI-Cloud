package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumCategory;
import com.nowui.cloud.sns.forum.mapper.ForumCategoryMapper;
import com.nowui.cloud.sns.forum.service.ForumCategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 论坛分类业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class ForumCategoryServiceImpl extends BaseServiceImpl<ForumCategoryMapper, ForumCategory> implements ForumCategoryService {

    @Override
    public Integer countForAdmin(String appId, String forumCategoryName, String forumCategoryThumb, Integer forumCategorySort, Boolean forumCategoryEnabled, Boolean forumCategoryRecommand) {
        Integer count = count(
                new BaseWrapper<ForumCategory>()
                        .eq(ForumCategory.APP_ID, appId)
                        .likeAllowEmpty(ForumCategory.FORUM_CATEGORY_NAME, forumCategoryName)
                        .likeAllowEmpty(ForumCategory.FORUM_CATEGORY_THUMB, forumCategoryThumb)
                        .eqAllowEmpty(ForumCategory.FORUM_CATEGORY_SORT, forumCategorySort)
                        .eqAllowEmpty(ForumCategory.FORUM_CATEGORY_ENABLED, forumCategoryEnabled)
                        .eqAllowEmpty(ForumCategory.FORUM_CATEGORY_RECOMMAND, forumCategoryRecommand)
                        .eq(ForumCategory.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ForumCategory> listForAdmin(String appId, String forumCategoryName, String forumCategoryThumb, Integer forumCategorySort, Boolean forumCategoryEnabled, Boolean forumCategoryRecommand, Integer pageIndex, Integer pageSize) {
        List<ForumCategory> forumCategoryList = list(
                new BaseWrapper<ForumCategory>()
                        .eq(ForumCategory.APP_ID, appId)
                        .likeAllowEmpty(ForumCategory.FORUM_CATEGORY_NAME, forumCategoryName)
                        .likeAllowEmpty(ForumCategory.FORUM_CATEGORY_THUMB, forumCategoryThumb)
                        .eqAllowEmpty(ForumCategory.FORUM_CATEGORY_SORT, forumCategorySort)
                        .eqAllowEmpty(ForumCategory.FORUM_CATEGORY_ENABLED, forumCategoryEnabled)
                        .eqAllowEmpty(ForumCategory.FORUM_CATEGORY_RECOMMAND, forumCategoryRecommand)
                        .eq(ForumCategory.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumCategory.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumCategoryList;
    }

}