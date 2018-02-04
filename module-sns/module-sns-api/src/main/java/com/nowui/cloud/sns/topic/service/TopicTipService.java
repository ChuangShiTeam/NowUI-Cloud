package com.nowui.cloud.sns.topic.service;
import java.util.List;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.sns.topic.entity.TopicTip;
import com.nowui.cloud.sns.topic.view.TopicTipView;

/**
 * 话题提醒业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicTipService extends SuperService<TopicTip, TopicTipView> {

    /**
     * 话题提醒统计
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 发送用户
     * @return Integer 话题提醒统计
     */
    Integer countForAdmin(String appId, String topicId, String userId);

    /**
     * 话题提醒列表
     *
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param userId 发送用户
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicTip> 话题提醒列表
     */
    List<TopicTip> listForAdmin(String appId, String topicId, String userId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据话题编号查询话题提醒列表
     * 
     * @param topicId 话题编号
     * @return List<TopicTip> 话题提醒列表
     */
    List<TopicTipView> listByTopicId(String topicId);
    
    /**
     * 根据话题编号逻辑删除话题提醒信息
     * 
     * @param topicId 话题编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByTopicId(String topicId, String appId, String systemRequestUserId);
    
    /**
     * 批量保存话题提醒
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param topicTipList 话题多提醒列表
     * @param systemRequestUserId 请求用户编号
     */
    void batchSave(String appId, String topicId, List<TopicTip> topicTipList, String systemRequestUserId);
}
