package com.nowui.cloud.sns.forum.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.mapper.ForumMapper;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.util.Util;

/**
 * 论坛信息业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class ForumServiceImpl extends BaseServiceImpl<ForumMapper, Forum> implements ForumService {

    @Override
    public Integer countForAdmin(String appId, String forumName, Boolean forumIsActive, Boolean forumIsRecommend, String forumAuditStatus) {
        Integer count = count(
                new BaseWrapper<Forum>()
                        .eq(Forum.APP_ID, appId)
                        .likeAllowEmpty(Forum.FORUM_NAME, forumName)
                        .eqAllowEmpty(Forum.FORUM_IS_ACTIVE, forumIsActive)
                        .eqAllowEmpty(Forum.FORUM_IS_RECOMAND, forumIsRecommend)
                        .eqAllowEmpty(Forum.FORUM_AUDIT_STATUS, forumAuditStatus)
                        .eq(Forum.SYSTEM_STATUS, true)
        );
        return count;
    }
    
    @Override
    public List<Forum> listForAdmin(String appId, String forumName, Boolean forumIsActive, Boolean forumIsRecommend, String forumAuditStatus, Integer pageIndex, Integer pageSize) {
        List<Forum> forumList = list(
                new BaseWrapper<Forum>()
                        .eq(Forum.APP_ID, appId)
                        .likeAllowEmpty(Forum.FORUM_NAME, forumName)
                        .eqAllowEmpty(Forum.FORUM_IS_ACTIVE, forumIsActive)
                        .eqAllowEmpty(Forum.FORUM_IS_RECOMAND, forumIsRecommend)
                        .eqAllowEmpty(Forum.FORUM_AUDIT_STATUS, forumAuditStatus)
                        .eq(Forum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Forum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumList;
    }

	@Override
	public Integer countSearchForMobile(String appId, String forumName) {
		Integer count = count(
                new BaseWrapper<Forum>()
                        .eq(Forum.APP_ID, appId)
                        .likeAllowEmpty(Forum.FORUM_NAME, forumName)
                        .eq(Forum.FORUM_AUDIT_STATUS, ForumAuditStatus.AUDIT_PASS.getKey())
                        .eq(Forum.FORUM_IS_ACTIVE, true)
                        .eq(Forum.SYSTEM_STATUS, true)
        );
        return count;
	}

	@Override
	public List<Forum> searchForMobile(String appId, String forumName, Integer pageIndex, Integer pageSize) {
		List<Forum> forumList = list(
                new BaseWrapper<Forum>()
                        .eq(Forum.APP_ID, appId)
                        .like(Forum.FORUM_NAME, forumName)
                        .eq(Forum.FORUM_AUDIT_STATUS, ForumAuditStatus.AUDIT_PASS.getKey())
                        .eq(Forum.SYSTEM_STATUS, true)
                        .eq(Forum.FORUM_IS_ACTIVE, true)
                        .orderDesc(Arrays.asList(Forum.FORUM_IS_TOP))
                        .orderDesc(Arrays.asList(Forum.FORUM_IS_RECOMAND))
                        .orderDesc(Arrays.asList(Forum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumList;
	}

    @Override
    public List<Forum> getRandomRecommendAndNotFollowListByUserId(String appId, String userId, int n) {
        List<String> forumIdList = mapper.getRandomRecommendAndNotFollowListByUserId(appId, userId, n);
        if (Util.isNullOrEmpty(forumIdList)) {
            return new ArrayList<>();
        }
        return forumIdList.stream().map(forumId -> find(forumId)).collect(Collectors.toList());
    }

    @Override
    public List<Forum> getLatestAndNotFollowListByUserId(String appId, String userId, int m, int n) {
        List<String> forumIdList = mapper.getLatestAndNotFollowListByUserId(appId, userId, m, n);
        if (Util.isNullOrEmpty(forumIdList)) {
            return new ArrayList<>();
        }
        return forumIdList.stream().map(forumId -> find(forumId)).collect(Collectors.toList());
    }

    @Override
    public Boolean checkName(String appId, String forumName) {
        Integer count = count(
                new BaseWrapper<Forum>()
                        .eq(Forum.APP_ID, appId)
                        .eq(Forum.FORUM_NAME, forumName)
                        .eq(Forum.SYSTEM_STATUS, true)
                        .andNew()
                        .eq(Forum.FORUM_AUDIT_STATUS, ForumAuditStatus.AUDIT_PASS.getKey())
                        .or()
                        .eq(Forum.FORUM_AUDIT_STATUS, ForumAuditStatus.WAIT_AUDIT.getKey())
        );
        return count > 0;
    }

}