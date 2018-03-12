package com.nowui.cloud.sns.member.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.member.view.MemberHomepageView;
import com.nowui.cloud.sns.topic.entity.MemberHomepage;

/**
 * 话题信息业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface MemberHomepageService extends BaseService<MemberHomepage, MemberHomepageView> {

	/**
     * 根据memberId查询会员主页信息
     * 
     * @param appId 话题编号
     * @param memberId 会员编号
     * @return MemberHomepageView 会员主页信息
     */
	MemberHomepageView findByMemberId(String appId, String memberId);
	
	/**
     * 根据userId查询会员主页信息
     * 
     * @param appId 话题编号
     * @param userId 会员编号
     * @return MemberHomepageView 会员主页信息
     */
	MemberHomepageView findByUserId(String appId, String userId);
}
