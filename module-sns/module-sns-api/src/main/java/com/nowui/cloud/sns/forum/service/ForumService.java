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
     * 论坛信息统计 (用于移动端)
     *
     * @param appId 应用编号
     * @param forumMedia 论坛多媒体id
     * @param forumMediaType 论坛多媒体类型
     * @param forumBackgroundMedia 论坛多媒体背景id
     * @param forumBackgroundMediaType 论坛多媒体背景类型
     * @param forumName 论坛名称
     * @param forumDescription 论坛简介
     * @param forumModerator 版主(用户编号)
     * @param forumLocation 位置
     * @param forumSort 论坛排序
     * @param forumTop 论坛是否置顶
     * @param forumTopLevel 论坛置顶级别
     * @param forumTopEndTime 论坛置顶结束时间
     * @param forumIsActive 论坛是否有效
     * @param forumIsRecommend 是否推荐
     * @return Integer 论坛信息统计
     */
    Integer countForMobile(String appId, String forumMedia, String forumMediaType, String forumBackgroundMedia, String forumBackgroundMediaType, String forumName, String forumDescription, String forumModerator, String forumLocation, Integer forumSort, Boolean forumTop, Integer forumTopLevel, Date forumTopEndTime, Boolean forumIsActive, Boolean forumIsRecommend);

    
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
     * 论坛信息列表(用于移动端)
     *
     * @param appId 应用编号
     * @param forumMedia 论坛多媒体id
     * @param forumMediaType 论坛多媒体类型
     * @param forumBackgroundMedia 论坛多媒体背景id
     * @param forumBackgroundMediaType 论坛多媒体背景类型
     * @param forumName 论坛名称
     * @param forumDescription 论坛简介
     * @param forumModerator 版主(用户编号)
     * @param forumLocation 位置
     * @param integer 论坛排序
     * @param boolean1 论坛是否置顶
     * @param integer2 论坛置顶级别
     * @param date 论坛置顶结束时间
     * @param forumIsActive 论坛是否有效
     * @param forumIsRecommend 是否推荐
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Forum> 论坛信息列表
     */
    List<Forum> listForMobile(String appId, String forumMedia, String forumMediaType, String forumBackgroundMedia, String forumBackgroundMediaType, String forumName, String forumDescription, String forumModerator, String forumLocation, Integer forumSort, Boolean forumTop, Integer forumTopLevel, Date forumTopEndTime, Boolean forumIsActive, Boolean forumIsRecommend, Integer pageIndex, Integer pageSize);
    
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
