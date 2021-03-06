package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserUnlike;
import com.nowui.cloud.sns.topic.view.TopicCommentUserUnlikeView;

import java.util.List;

/**
 * 话题评论的取消点赞业务接口
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
public interface TopicCommentUserUnlikeService extends SuperService<TopicCommentUserUnlike, TopicCommentUserUnlikeView> {

    /**
     * 话题评论的取消点赞统计
     *
     * @param appId 应用编号
     * @param commentId 被取消点赞的话题评论编号
     * @param memberId 用户编号
     * @return Integer 话题评论的取消点赞统计
     */
    Integer countForAdmin(String appId, String commentId, String memberId);

    /**
     * 话题评论的取消点赞列表
     *
     * @param appId 应用编号
     * @param commentId 被取消点赞的话题评论编号
     * @param memberId 用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicCommentUserUnlike> 话题评论的取消点赞列表
     */
    List<TopicCommentUserUnlike> listForAdmin(String appId, String commentId, String memberId, Integer pageIndex, Integer pageSize);
    
    /**
     * 删除话题评论的取消点赞记录
     * 
     * @param commentId 被取消点赞的话题评论编号
     * @param appId 应用编号
     * @param memberId 取消点赞的会员编号
     * @param systemRequestUserId 操作用户编号
     * @return boolean 操作结果
     */
    TopicCommentUserUnlike deleteByCommentIdAndMemberId(String commentId, String appId, String memberId, String systemRequestUserId);
    
    /**
     * 根据评论id和取消点赞的用户id查找用户评论取消点赞记录
     * @param appId 应用编号
     * @param commentId 被取消点赞的评论编号
     * @param memberId 取消点赞的用户编号
     * @return TopicCommentUserUnlike 评论点赞记录
     */
    TopicCommentUserUnlikeView findTheCommentUserUnlike(String appId, String commentId, String memberId);
    
}
