package com.nowui.cloud.member.member.service;

import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.member.member.entity.MemberDefaultAvatar;
import com.nowui.cloud.member.member.view.MemberDefaultAvatarView;

import java.util.List;

/**
 * 用户默认头像业务接口
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
public interface MemberDefaultAvatarService extends SuperService<MemberDefaultAvatar, MemberDefaultAvatarView> {

    /**
     * 用户默认头像统计
     *
     * @param appId 应用编号
     * @param userAvatarFileId 头像文件编号
     * @return Integer 用户默认头像统计
     */
    Integer countForAdmin(String appId, String userAvatarFileId);

    /**
     * 用户默认头像列表
     *
     * @param appId 应用编号
     * @param userAvatarFileId 头像文件编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberDefaultAvatar> 用户默认头像列表
     */
    List<MemberDefaultAvatarView> listForAdmin(String appId, String userAvatarFileId, Integer pageIndex, Integer pageSize);

}