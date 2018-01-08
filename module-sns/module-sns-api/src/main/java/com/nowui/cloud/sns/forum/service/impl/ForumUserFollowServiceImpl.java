package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumUserFollow;
import com.nowui.cloud.sns.forum.mapper.ForumUserFollowMapper;
import com.nowui.cloud.sns.forum.service.ForumUserFollowService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 论坛用户关注业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class ForumUserFollowServiceImpl extends BaseServiceImpl<ForumUserFollowMapper, ForumUserFollow> implements ForumUserFollowService {

    @Override
    public Integer adminCount(String appId, String userId, String forumId) {
        Integer count = count(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .likeAllowEmpty(ForumUserFollow.USER_ID, userId)
                        .likeAllowEmpty(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ForumUserFollow> adminList(String appId, String userId, String forumId, Integer pageIndex, Integer pageSize) {
        List<ForumUserFollow> forumUserFollowList = list(
                new BaseWrapper<ForumUserFollow>()
                        .eq(ForumUserFollow.APP_ID, appId)
                        .likeAllowEmpty(ForumUserFollow.USER_ID, userId)
                        .likeAllowEmpty(ForumUserFollow.FORUM_ID, forumId)
                        .eq(ForumUserFollow.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumUserFollow.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumUserFollowList;
    }

}