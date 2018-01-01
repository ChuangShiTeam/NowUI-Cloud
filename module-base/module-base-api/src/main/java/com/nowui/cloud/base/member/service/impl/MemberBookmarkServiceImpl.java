package com.nowui.cloud.base.member.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.member.entity.MemberBookmark;
import com.nowui.cloud.base.member.mapper.MemberBookmarkMapper;
import com.nowui.cloud.base.member.service.MemberBookmarkService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 会员收藏业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class MemberBookmarkServiceImpl extends BaseServiceImpl<MemberBookmarkMapper, MemberBookmark> implements MemberBookmarkService {

    @Override
    public Integer adminCount(String appId, String memberId, String memberBookmarkTitle) {
        Integer count = count(
                new BaseWrapper<MemberBookmark>()
                        .eq(MemberBookmark.APP_ID, appId)
                        .likeAllowEmpty(MemberBookmark.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberBookmark.MEMBER_BOOKMARK_TITLE, memberBookmarkTitle)
                        .eq(MemberBookmark.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<MemberBookmark> adminList(String appId, String memberId, String memberBookmarkTitle, Integer m, Integer n) {
        List<MemberBookmark> memberBookmarkList = list(
                new BaseWrapper<MemberBookmark>()
                        .eq(MemberBookmark.APP_ID, appId)
                        .likeAllowEmpty(MemberBookmark.MEMBER_ID, memberId)
                        .likeAllowEmpty(MemberBookmark.MEMBER_BOOKMARK_TITLE, memberBookmarkTitle)
                        .eq(MemberBookmark.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(MemberBookmark.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return memberBookmarkList;
    }

}