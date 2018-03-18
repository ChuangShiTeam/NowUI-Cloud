package com.nowui.cloud.member.member.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.member.member.entity.MemberDefaultAvatar;
import com.nowui.cloud.member.member.view.MemberDefaultAvatarView;

import java.util.List;

/**
 * 会员默认头像业务接口
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
public interface MemberDefaultAvatarService extends BaseService<MemberDefaultAvatar, MemberDefaultAvatarView> {

    /**
     * 会员默认头像统计
     *
     * @param appId 应用编号
     * @return Integer 会员默认头像统计
     */
    Integer countForAdmin(String appId);

    /**
     * 会员默认头像列表
     *
     * @param appId 应用编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberDefaultAvatarView> 会员默认头像列表
     */
    List<MemberDefaultAvatarView> listForAdmin(String appId, Integer pageIndex, Integer pageSize);
    
    /**
     * 所有会员默认头像统计
     * 
     * @param appId 应用编号
     * @return Integer 会员默认头像统计
     */
    Integer countAll(String appId);
    
    /**
     * 随机获取一条会员头像记录
     * 
     * @param appId 应用编号
     * @return MemberDefaultAvatarView 会员默认头像视图
     */
    MemberDefaultAvatarView randomFind(String appId);

}