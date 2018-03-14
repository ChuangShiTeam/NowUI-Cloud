package com.nowui.cloud.sns.member.service.impl;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.member.mapper.MemberHomepageMapper;
import com.nowui.cloud.sns.member.repository.MemberHomepageRepository;
import com.nowui.cloud.sns.member.service.MemberHomepageService;
import com.nowui.cloud.sns.member.view.MemberHomepageView;
import com.nowui.cloud.sns.topic.entity.MemberHomepage;
import com.nowui.cloud.util.Util;

/**
 * 话题信息业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class MemberHomepageServiceImpl extends BaseServiceImpl<MemberHomepageMapper, MemberHomepage, MemberHomepageRepository, MemberHomepageView> implements MemberHomepageService {

	@Override
	public MemberHomepageView findByMemberId(String appId, String memberId) {
		Criteria criteria = Criteria.where(MemberHomepageView.APP_ID).is(appId)
                .and(MemberHomepageView.MEMBER_ID).is(memberId)
                .and(MemberHomepageView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);
        
        MemberHomepageView memberHomepageView = find(query);
        
        if (Util.isNullOrEmpty(memberHomepageView)) {
			return null;
		}

        return memberHomepageView;
	}

	@Override
	public MemberHomepageView findByUserId(String appId, String userId) {
		Criteria criteria = Criteria.where(MemberHomepageView.APP_ID).is(appId)
                .and(MemberHomepageView.USER_ID).is(userId)
                .and(MemberHomepageView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);
        
        MemberHomepageView memberHomepageView = find(query);
        
        if (Util.isNullOrEmpty(memberHomepageView)) {
			return null;
		}

        return memberHomepageView;
	}
    
	
	
	
}