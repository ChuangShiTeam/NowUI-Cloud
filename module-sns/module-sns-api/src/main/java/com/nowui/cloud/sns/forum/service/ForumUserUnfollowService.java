package com.nowui.cloud.sns.forum.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;

import java.util.List;

/**
 * 论坛用户取关关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface ForumUserUnfollowService extends BaseService<ForumUserUnfollow> {

    /**
     * 论坛用户取关关联统计
     *
     * @param appId 应用编号
     * @param userId 用户ID
     * @param forumId 论坛Id
     * @return Integer 论坛用户取关关联统计
     */
    Integer adminCount(String appId, String userId, String forumId);

    /**
     * 论坛用户取关关联列表
     *
     * @param appId 应用编号
     * @param userId 用户ID
     * @param forumId 论坛Id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumUserUnfollow> 论坛用户取关关联列表
     */
    List<ForumUserUnfollow> adminList(String appId, String userId, String forumId, Integer pageIndex, Integer pageSize);
}
