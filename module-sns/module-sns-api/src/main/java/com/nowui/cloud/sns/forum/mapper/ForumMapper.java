package com.nowui.cloud.sns.forum.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nowui.cloud.mapper.BaseMapper;
import com.nowui.cloud.sns.forum.entity.Forum;

/**
 * 论坛信息mapping映射接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface ForumMapper extends BaseMapper<Forum> {
    
    /**
     * 获取随机个数推荐的且用户没有关注的论坛编号列表
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param n 个数
     * @return List<String> 论坛编号列表
     */
   List<String> getRandomRecommendAndNotFollowListByUserId(
           @Param("appId") String appId, 
           @Param("userId") String userId, 
           @Param("n") int n
   ); 
   
   /**
    * 获取用户没有关注的最新论坛编号列表
    * 
    * @param appId 应用编号
    * @param userId 用户编号
    * @param n 个数
    * @return List<String> 论坛编号列表
    */
   List<String> getLatestAndNotFollowListByUserId(
           @Param("appId") String appId, 
           @Param("userId") String userId, 
           @Param("m") int m, 
           @Param("n") int n
   ); 

}
