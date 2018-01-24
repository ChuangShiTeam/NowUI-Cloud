package com.nowui.cloud.sns.topic.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.topic.entity.TopicCommentUserLike;

import java.util.List;

/**
 * 话题的评论用户点赞业务接口
 *
 * @author xupengfei
 *
 * 2018-01-23
 */
public interface TopicCommentUserLikeService extends BaseService<TopicCommentUserLike> {

    /**
     * 话题的评论用户点赞统计
     *
     * @param appId 应用编号
     * @param commentId 被点赞的评论编号
     * @param userId 点赞的用户编号
     * @return Integer 话题的评论用户点赞统计
     */
    Integer countForAdmin(String appId, String commentId, String userId);

    /**
     * 话题的评论用户点赞列表
     *
     * @param appId 应用编号
     * @param commentId 被点赞的评论编号
     * @param userId 点赞的用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<TopicCommentUserLike> 话题的评论用户点赞列表
     */
    List<TopicCommentUserLike> listForAdmin(String appId, String commentId, String userId, Integer pageIndex, Integer pageSize);
    
    /**
     * 根据评论id和点赞用户id查找用户评论点赞记录
     * @param appId 应用编号
     * @param commentId 被点赞的评论编号
     * @param userId 点赞的用户编号
     * @return TopicCommentUserLike 评论点赞记录
     */
    TopicCommentUserLike findTheCommentUserLike(String commentId, String userId);
    
    /**
     * 删除话题评论的点赞记录
     * 
     * @param commentId 被点赞的话题评论编号
     * @param userId 点赞的用户编号
     * @param systemRequestUserId 操作用户编号
     * @return boolean 操作结果
     */
    boolean deleteByCommentIdAndUserIdWithRedis(String commentId, String userId, String systemRequestUserId);
    
    /**
     * 根据评论id删除所有 话题评论的点赞记录(redis中的点赞数也删除)
     * 
     * @param commentId 被点赞的话题评论编号
     * @param systemRequestUserId 操作用户编号
     * @return boolean 操作结果
     */
    boolean deleteAllCommentLikeByCommentIdWithRedis(String commentId, String systemRequestUserId);
    
    /**
     * 根据评论id查找所有此评论的点赞记录(不分页)
     * @param commentId 被点赞的话题编号
     * @return List<TopicCommentUserLike> 评论点赞记录列表
     */
    List<TopicCommentUserLike> listByCommentIdWithoutPage(String commentId);
    
    /**
     * 保存记录时使用缓存记录评论被点赞数量
     * 
     * @param appId 应用编号
     * @param commentId 被点赞的话题评论编号
     * @param userId 点赞的用户编号
     * @param systemRequestUserId 操作的用户编号
     * @return boolean 操作结果
     */
    boolean saveWithRedis(String appId, String commentId, String userId, String systemRequestUserId );
    
    /**
     * 根据评论id查询相应评论的点赞数(使用redis)
     * @param commentId 被点赞的话题评论编号
     * @return Integer 被点赞的数量
     */
    Integer countByCommentIdWithRedis(String commentId);
  
}
