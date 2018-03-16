package com.nowui.cloud.member.member.service.impl;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.member.member.entity.MemberSex;
import com.nowui.cloud.member.member.entity.MemberSex;
import com.nowui.cloud.member.member.mapper.MemberSexMapper;
import com.nowui.cloud.member.member.repository.MemberSexRepository;
import com.nowui.cloud.member.member.service.MemberSexService;
import com.nowui.cloud.member.member.view.MemberSexView;
import com.nowui.cloud.member.member.view.MemberSexView;
import com.nowui.cloud.mybatisplus.BaseWrapper;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * 会员性别业务实现
 *
 * @author marcus
 *
 * 2018-03-16
 */
@Service
public class MemberSexServiceImpl extends BaseServiceImpl<MemberSexMapper, MemberSex, MemberSexRepository, MemberSexView> implements MemberSexService {

    @Override
    public MemberSex findByMemberId(String memberId) {
        MemberSex memberSex = find(
                new BaseWrapper<MemberSex>()
                        .eq(MemberSexView.MEMBER_ID, memberId)
                        .eq(MemberSexView.SYSTEM_STATUS, true)
        );
        return memberSex;
    }

    @Override
    public void deleteByMemberId(String memberId, String systemRequestUserId) {
        List<MemberSex> memberSexList = list(
                new BaseWrapper<MemberSex>()
                        .eq(MemberSexView.MEMBER_ID, memberId)
                        .eq(MemberSexView.SYSTEM_STATUS, true)
        );

        if (memberSexList != null && memberSexList.size() > 0) {
            memberSexList.stream().forEach(memberSex -> delete(memberSex.getMemberSexId(), memberSex.getAppId(), systemRequestUserId, memberSex.getSystemVersion()));
        }
        
    }
}