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
     * 论坛信息统计
     *
     * @param appId 应用编号
     * @param forumMediaId 论坛多媒体id
     * @param forumMediaType 论坛多媒体类型
     * @param forumBackgroundMediaId 论坛多媒体背景id
     * @param forumBackgroundMediaType 论坛多媒体背景类型
     * @param forumName 论坛名称
     * @param forumDescription 论坛简介
     * @param forumModerator 版主(用户id)
     * @param forumTopicLocation 位置
     * @param forumTop 论坛排序
     * @param forumTopLevel 论坛是否置顶
     * @param forumTopEndTime 论坛置顶级别
     * @param date 论坛置顶结束时间
     * @param boolean2 论坛是否有效
     * @param boolean3 是否关注
     * @param boolean4 是否推荐
     * @return Integer 论坛信息统计
     */
    Integer countForAdmin(String appId, String forumMediaId, String forumMediaType, String forumBackgroundMediaId, String forumBackgroundMediaType, String forumName, String forumDescription, String forumModerator, String forumTopicLocation, Integer forumSort, Boolean forumTop, Integer forumTopLevel, Date forumTopEndTime, Boolean forumIsActive, Boolean forumIsFollow, Boolean forumIsRecomand);

    /**
     * 论坛信息列表
     *
     * @param appId 应用编号
     * @param forumMediaId 论坛多媒体id
     * @param forumMediaType 论坛多媒体类型
     * @param forumBackgroundMediaId 论坛多媒体背景id
     * @param forumBackgroundMediaType 论坛多媒体背景类型
     * @param forumName 论坛名称
     * @param forumDescription 论坛简介
     * @param forumModerator 版主(用户id)
     * @param forumTopicLocation 位置
     * @param integer 论坛排序
     * @param boolean1 论坛是否置顶
     * @param integer2 论坛置顶级别
     * @param date 论坛置顶结束时间
     * @param boolean2 论坛是否有效
     * @param boolean3 是否关注
     * @param boolean4 是否推荐
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Forum> 论坛信息列表
     */
    List<Forum> listForAdmin(String appId, String forumMediaId, String forumMediaType, String forumBackgroundMediaId, String forumBackgroundMediaType, String forumName, String forumDescription, String forumModerator, String forumTopicLocation, Integer forumSort, Boolean forumTop, Integer forumTopLevel, Date forumTopEndTimete, Boolean forumIsActive, Boolean forumIsFollow, Boolean forumIsRecomand, Integer pageIndex, Integer pageSize);
}
