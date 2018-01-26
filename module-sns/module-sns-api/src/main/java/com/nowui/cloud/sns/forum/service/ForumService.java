package com.nowui.cloud.sns.forum.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.forum.entity.Forum;

import java.util.Date;
import java.util.List;

/**
 * 论坛信息业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface ForumService extends BaseService<Forum> {

    /**
     * 论坛信息统计(用于Admin)
     *
     * @param appId 应用编号
     * @param forumName 论坛名称
     * @param forumIsActive 论坛是否有效
     * @param forumIsRecommend 是否推荐
     * @param forumAuditStatus 论坛审核状态
     * @return Integer 论坛信息统计
     */
    Integer countForAdmin(String appId, String forumName, Boolean forumIsActive, Boolean forumIsRecommend, String forumAuditStatus);

    /**
     * 论坛信息统计 (用于移动端搜索)
     *
     * @param appId 应用编号
     * @param forumName 论坛名称
     * @return Integer 论坛信息统计
     */
    Integer countSearchForMobile(String appId, String forumName);

    
    /**
     * 论坛信息列表(用于Admin)
     *
     * @param appId 应用编号
     * @param forumName 论坛名称
     * @param forumIsActive 论坛是否有效
     * @param forumIsRecommend 是否推荐
     * @param forumAuditStatus 论坛审核状态
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Forum> 论坛信息列表
     */
    List<Forum> listForAdmin(String appId, String forumName, Boolean forumIsActive, Boolean forumIsRecommend, String forumAuditStatus, Integer pageIndex, Integer pageSize);
    
    /**
     * 搜索论坛信息列表(用于移动端)
     *
     * @param appId 应用编号
     * @param forumName 论坛名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Forum> 论坛信息列表
     */
    List<Forum> searchForMobile(String appId, String forumName, Integer pageIndex, Integer pageSize);
    
    /**
     * 获取随机个数推荐的且用户没有关注的论坛列表
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param n 个数
     * @return List<Forum> 论坛列表
     */
    List<Forum> getRandomRecommendAndNotFollowListByUserId(String appId, String userId, int n);
    
    /**
     * 获取用户没有关注的最新论坛列表
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param m 开始数
     * @param n 结束数
     * @return List<Forum> 论坛列表
     */
    List<Forum> getLatestAndNotFollowListByUserId(String appId, String userId, int m, int n);
    
    /**
     * 验证论坛名称唯一性
     * @param appId 应用编号
     * @param forumName 论坛名称
     * @return true 重复 false 不重复
     */
    Boolean checkName(String appId, String forumName);
    
}
