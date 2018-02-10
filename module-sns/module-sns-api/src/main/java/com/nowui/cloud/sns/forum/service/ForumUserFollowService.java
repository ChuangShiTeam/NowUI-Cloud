package com.nowui.cloud.sns.forum.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.view.ForumUserFollowView;

import java.util.List;

/**
 * 论坛用户关注业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface ForumUserFollowService extends SuperService<ForumUserFollow, ForumUserFollowView> {

    /**
     * 论坛用户关注统计
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param forumId 论坛编号
     * @return Integer 论坛用户关注统计
     */
    Integer countForAdmin(String appId, String memberId, String forumId);
    

    /**
     * 论坛用户关注列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param forumId 论坛编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumUserFollow> 论坛用户关注列表
     */
    List<ForumUserFollow> listForAdmin(String appId, String memberId, String forumId, Integer pageIndex, Integer pageSize);

    /**
     * 论坛用户关注列表
     *
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumUserFollow> 论坛用户关注列表
     */
    List<ForumUserFollowView> listByforumId(String appId, String forumId, Integer pageIndex, Integer pageSize);

    
    /**
     * 根据论坛编号统计关注论坛用户数
     *
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @return Integer 论坛用户关注统计
     */
    Integer countByForumId(String appId, String forumId);
    
    /**
     * 根据用户编号统计用户关注论坛数
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @return Integer 论坛用户关注统计
     */
    Integer countByMemberId(String appId, String memberId);
    
    /**
     * 论坛用户关注分页列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumUserFollow> 论坛用户关注列表
     */
    List<ForumUserFollowView> listByMemberId(String appId, String memberId, Integer pageIndex, Integer pageSize);
    
    /**
     * 论坛用户关注列表
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @return List<ForumUserFollow> 论坛用户关注列表
     */
    List<ForumUserFollowView> listByMemberId(String appId, String memberId);
    
    /**
     * 论坛用户关注列表
     * 
     * @param memberId 会员编号
     * @return List<ForumUserFollow> 论坛用户关注列表
     */
    List<ForumUserFollowView> listByMemberId(String memberId);
    
    /**
     * 根据用户编号和论坛编号查询用户关注信息
     * 
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param forumId 论坛编号
     * @return ForumUserFollow 论坛用户关注信息
     */
    ForumUserFollowView findByMemberIdAndForumId(String appId, String memberId, String forumId);
    
    /**
     * 根据用户编号和论坛编号查询用户关注信息(没用appId)
     * 
     * @param 会员编号 用户编号
     * @param forumId 论坛编号
     * @return ForumUserFollow 论坛用户关注信息
     */
    ForumUserFollowView findByMemberIdAndForumId(String memberId, String forumId);
    
    /**
     * 查询符合论坛编号的所有用户关注信息
     * 
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @return List<ForumUserFollow> 论坛用户关注列表
     */
    List<ForumUserFollow> findByForumId(String appId, String forumId);
    
    /**
     * 根据论坛编号逻辑删除用户关注论坛记录
     * 
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @param systemRequestUserId 操作者Id
     * @return boolean 返回删除结果
     */
    ForumUserFollow updateTopForum(String appId, String forumId, String memberId, String systemRequestUserId);
    
    /**
     * 根据论坛编号逻辑删除用户关注论坛记录
     * 
     * @param appId 应用编号
     * @param forumId 论坛编号
     * @param systemUpdateUserId 操作者Id
     * @return boolean 返回删除结果
     */
    ForumUserFollow deleteByForumId(String appId, String forumId, String systemUpdateUserId);
}
