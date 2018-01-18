package com.nowui.cloud.wawi.wawi.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.wawi.wawi.entity.MemberVisitForum;
import com.nowui.cloud.wawi.wawi.mapper.MemberVisitForumMapper;
import com.nowui.cloud.wawi.wawi.service.MemberVisitForumService;

/**
 * 会员访问圈子业务实现
 *
 * @author marcus
 *
 * 2018-01-17
 */
@Service
public class MemberVisitForumServiceImpl extends BaseServiceImpl<MemberVisitForumMapper, MemberVisitForum> implements MemberVisitForumService {

    @Override
    public Integer countForAdmin(String appId, String memberId, String userId) {
        Integer count = count(
                new BaseWrapper<MemberVisitForum>()
                        .eq(MemberVisitForum.APP_ID, appId)
                        .likeAllowEmpty(MemberVisitForum.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberVisitForum.USER_ID, userId)
                        .eq(MemberVisitForum.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberVisitForum> listForAdmin(String appId, String memberId, String userId, Integer pageIndex, Integer pageSize) {
        List<MemberVisitForum> memberVisitForumList = list(
                new BaseWrapper<MemberVisitForum>()
                        .eq(MemberVisitForum.APP_ID, appId)
                        .likeAllowEmpty(MemberVisitForum.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberVisitForum.USER_ID, userId)
                        .eq(MemberVisitForum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberVisitForum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return memberVisitForumList;
    }

	@Override
	public MemberVisitForum findByUserId(String userId) {
		MemberVisitForum memberVisitForum = find(
                new BaseWrapper<MemberVisitForum>()
                        .likeAllowEmpty(MemberVisitForum.USER_ID, userId)
                        .eq(MemberVisitForum.SYSTEM_STATUS, true)
        );
		
		return memberVisitForum;
	}

}