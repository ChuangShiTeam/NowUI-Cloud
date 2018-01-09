package com.nowui.cloud.sns.forum.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.forum.entity.TopicForum;

import java.util.List;

/**
 * 话题论坛关联业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicForumService extends BaseService<TopicForum> {

    /**
     * 话题论坛关联统计
     *
     * @param appId 应用编号
     * @param forumId 论坛Id
     * @param topicId 话题Id
     * @return Integer 话题论坛关联统计
     */
    Integer countForAdmin(String appId, String forumId, String topicId);

    /**
     * 话题论坛关联列表
     *
     * @param appId 应用编号
     * @param forumId 论坛Id
     * @param topicId 话题Id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicForum> 话题论坛关联列表
     */
    List<TopicForum> listForAdmin(String appId, String forumId, String topicId, Integer pageIndex, Integer pageSize);

    Boolean deleteByForumId(String appId, String forumId , String systemUpdateUserId, Integer systemVersion);
        
}
