package com.nowui.cloud.sns.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.sns.member.view.MemberFollowView;

import org.springframework.stereotype.Component;

/**
 * 关注会员信息视图访问组件接口
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
public interface MemberFollowRepository extends BaseRepository<MemberFollowView> {

}
