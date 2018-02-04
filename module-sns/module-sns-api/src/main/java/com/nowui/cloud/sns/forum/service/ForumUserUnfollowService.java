package com.nowui.cloud.sns.forum.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.sns.forum.entity.ForumUserUnfollow;
import com.nowui.cloud.sns.forum.view.ForumUserUnfollowView;

import java.util.List;

/**
 * 论坛用户取关业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface ForumUserUnfollowService extends SuperService<ForumUserUnfollow, ForumUserUnfollowView> {

    /**
     * 论坛用户取关关联统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param forumId 论坛编号
     * @return Integer 论坛用户取关关联统计
     */
    Integer countForAdmin(String appId, String userId, String forumId);

    /**
     * 论坛用户取关关联列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param forumId 论坛编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumUserUnfollow> 论坛用户取关关联列表
     */
    List<ForumUserUnfollow> listForAdmin(String appId, String userId, String forumId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据userId和forumId查询ForumUserUnfollow单个记录
     * @param appId 应用编号
     * @param userId 用户编号
     * @param forumId 论坛编号
     * @return ForumUserUnfollow 论坛用户取关记录
     */
    ForumUserUnfollow findByUserIdAndForumId(String appId, String userId, String forumId);
    
    /**
     * 根据forumId删除全部取消关注表的记录
     * 
     * @param appId 应用编号
     * @param forumId 用户编号
     * @param systemUpdateUserId 更新着编号
     * @return boolean 返回删除结果
     */
    boolean deleteByForumId(String appId, String forumId, String systemUpdateUserId);
}
