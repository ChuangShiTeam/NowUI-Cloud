package com.nowui.cloud.sns.forum.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;

import java.util.List;

/**
 * 论坛用户关注业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface ForumUserFollowService extends BaseService<ForumUserFollow> {

    /**
     * 论坛用户关注统计
     *
     * @param appId 应用编号
     * @param userId 用户Id
     * @param forumId 论坛Id
     * @return Integer 论坛用户关注统计
     */
    Integer adminCount(String appId, String userId, String forumId);

    /**
     * 论坛用户关注列表
     *
     * @param appId 应用编号
     * @param userId 用户Id
     * @param forumId 论坛Id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumUserFollow> 论坛用户关注列表
     */
    List<ForumUserFollow> adminList(String appId, String userId, String forumId, Integer pageIndex, Integer pageSize);
}
