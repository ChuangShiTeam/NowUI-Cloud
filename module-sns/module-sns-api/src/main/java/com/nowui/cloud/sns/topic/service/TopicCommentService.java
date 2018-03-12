package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.view.TopicCommentView;

import java.util.Date;
import java.util.List;

/**
 * 话题评论业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface TopicCommentService extends BaseService<TopicComment, TopicCommentView> {

    /**
     * 话题评论统计
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param topicCommentContent 话题内容
     * @param topicReplayUserId 回复人
     * @param topicReplyCommentId 被回复评论id
     * @return Integer 话题评论统计
     */
    Integer countForAdmin(String appId, String memberId, String topicId, String topicCommentContent, String topicReplayMemberId, String topicReplyCommentId);

    /**
     * 话题评论列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param topicCommentContent 话题内容
     * @param topicReplayUserId 回复人
     * @param topicReplyCommentId 被回复评论id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicComment> listForAdmin(String appId, String memberId, String topicId, String topicCommentContent, String topicReplayMemberId, String topicReplyCommentId, Integer pageIndex, Integer pageSize);
    
    
    /**
     * 话题评论统计
     *
     * @param topicId 话题编号
     * @return Integer 话题评论统计
     */
    Integer countByTopicId(String topicId);

    
    /**
     * 根据话题编号查询话题评论列表
     * 
     * @param topicId 话题编号
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicCommentView> listByTopicId(String topicId);
    
    /**
     * 根据话题编号查询话题评论分页列表
     * 
     * @param topicId 话题编号
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicCommentView> listByTopicId(String topicId, Integer pageIndex, Integer pageSize);
    
    
    /**
     * 根据话题编号查询话题评论分页列表
     * (用于滚动加载,根据上次查询记录的最后一个记录的创建时间
     * 和
     * 被排除的最后一个创建时间的commentIdList)
     * 
     * @param topicId 话题编号
     * @return List<TopicComment> 话题评论列表
     */
    List<TopicCommentView> listByTopicId(String appId, String topicId,  List<String> excludeCommentIdList, Date systemCreateTime, Integer pageIndex, Integer pageSize);
    
    
    /**
     * 根据话题编号逻辑删除话题评论信息
     * 
     * @param topicId 话题编号
     * @param appId 应用编号
     * @param systemRequestUserId 请求用户编号
     */
    void deleteByTopicId(String topicId, String appId, String systemRequestUserId);
    
    /**
     * 保存话题评论
     * 
     * @param appId 应用编号
     * @param topicId 话题编号
     * @param member 用户编号
     * @param topicComment 话题评论对象信息
     * @param systemRequestUserId 请求用户编号
     * @return Boolean true 成功   false 失败
     */
    Boolean save(String appId, String topicId, String memberId, TopicComment topicComment, String systemRequestUserId);

}
