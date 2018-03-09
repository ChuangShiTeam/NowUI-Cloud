package com.nowui.cloud.sns.forum.service;

import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.sns.forum.entity.ForumBackgroundMedia;
import com.nowui.cloud.sns.forum.view.ForumBackgroundMediaView;

import java.util.List;

/**
 * 论坛背景业务接口
 *
 * @author xupengfei
 *
 * 2018-03-09
 */
public interface ForumBackgroundMediaService extends SuperService<ForumBackgroundMedia, ForumBackgroundMediaView> {

    /**
     * 论坛背景统计
     *
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @return Integer 论坛背景统计
     */
    Integer countForAdmin(String appId, String forumId);

    /**
     * 论坛背景列表
     *
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumBackgroundMedia> 论坛背景列表
     */
    List<ForumBackgroundMediaView> listForAdmin(String appId, String forumId, Integer pageIndex, Integer pageSize);

}