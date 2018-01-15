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
    Integer countForAdmin(String appId, String userId, String forumId);

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
    List<ForumUserFollow> listForAdmin(String appId, String userId, String forumId, Integer pageIndex, Integer pageSize);

    /**
     * 根据用户编号和论坛编号查询用户关注信息
     * 
     * @param appId 应用编号
     * @param userId 用户Id
     * @param forumId 论坛Id
     * @return ForumUserFollow 论坛用户关注信息
     */
    ForumUserFollow findByUserIdAndForumId(String appId, String userId, String forumId);
    
    /**
     * 查询符合论坛编号的所有用户关注信息
     * 
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @return List<ForumUserFollow> 论坛用户关注列表
     */
    List<ForumUserFollow> findByForumId(String appId, String forumId);
    
    /**
     * 根据forumId删除论坛关注表有forumId的记录
     * 
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @return boolean 返回删除结果
     */
    boolean deleteByForumId(String appId, String forumId, String systemUpdateUserId);
}
