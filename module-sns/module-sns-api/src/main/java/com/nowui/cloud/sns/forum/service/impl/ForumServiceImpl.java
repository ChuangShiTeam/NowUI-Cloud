package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.entity.enums.ForumAuditStatus;
import com.nowui.cloud.sns.forum.mapper.ForumMapper;
import com.nowui.cloud.sns.forum.service.ForumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public Integer countForAdmin(String appId, String forumMediaId, String forumMediaType, String forumBackgroundMediaId, String forumBackgroundMediaType, String forumName, String forumDescription, String forumModerator, String forumTopicLocation, Integer forumSort, Boolean forumTop, Integer forumTopLevel, Date forumTopEndTime, Boolean forumIsActive, Boolean forumIsRecomand, String forumAuditContent, String forumAuditStatus) {
        Integer count = count(
                new BaseWrapper<Forum>()
                        .eq(Forum.APP_ID, appId)
                        .likeAllowEmpty(Forum.FORUM_MEDIA_ID, forumMediaId)
                        .likeAllowEmpty(Forum.FORUM_MEDIA_TYPE, forumMediaType)
                        .likeAllowEmpty(Forum.FORUM_BACKGROUND_MEDIA_ID, forumBackgroundMediaId)
                        .likeAllowEmpty(Forum.FORUM_BACKGROUND_MEDIA_TYPE, forumBackgroundMediaType)
                        .likeAllowEmpty(Forum.FORUM_NAME, forumName)
                        .likeAllowEmpty(Forum.FORUM_DESCRIPTION, forumDescription)
                        .likeAllowEmpty(Forum.FORUM_MODERATOR, forumModerator)
                        .likeAllowEmpty(Forum.FORUM_TOPIC_LOCATION, forumTopicLocation)
                        .eqAllowEmpty(Forum.FORUM_SORT, forumSort)
                        .eqAllowEmpty(Forum.FORUM_TOP, forumTop)
                        .eqAllowEmpty(Forum.FORUM_TOP_LEVEL, forumTopLevel)
                        .eqAllowEmpty(Forum.FORUM_TOP_END_TIME, forumTopEndTime)
                        .eqAllowEmpty(Forum.FORUM_IS_ACTIVE, forumIsActive)
                        .eqAllowEmpty(Forum.FORUM_IS_RECOMAND, forumIsRecomand)
                        .eqAllowEmpty(Forum.FORUM_AUDIT_CONTENT, forumAuditContent)
                        .eqAllowEmpty(Forum.FORUM_AUDIT_STATUS, forumAuditStatus)
                        .eq(Forum.SYSTEM_STATUS, true)
        );
        return count;
    }
    
    @Override
    public List<Forum> listForAdmin(String appId, String forumMediaId, String forumMediaType, String forumBackgroundMediaId, String forumBackgroundMediaType, String forumName, String forumDescription, String forumModerator, String forumTopicLocation, Integer forumSort, Boolean forumTop, Integer forumTopLevel, Date forumTopEndTime, Boolean forumIsActive, Boolean forumIsRecomand, String forumAuditContent, String forumAuditStatus, Integer pageIndex, Integer pageSize) {
        List<Forum> forumList = list(
                new BaseWrapper<Forum>()
                        .eq(Forum.APP_ID, appId)
                        .likeAllowEmpty(Forum.FORUM_MEDIA_ID, forumMediaId)
                        .likeAllowEmpty(Forum.FORUM_MEDIA_TYPE, forumMediaType)
                        .likeAllowEmpty(Forum.FORUM_BACKGROUND_MEDIA_ID, forumBackgroundMediaId)
                        .likeAllowEmpty(Forum.FORUM_BACKGROUND_MEDIA_TYPE, forumBackgroundMediaType)
                        .likeAllowEmpty(Forum.FORUM_NAME, forumName)
                        .likeAllowEmpty(Forum.FORUM_DESCRIPTION, forumDescription)
                        .likeAllowEmpty(Forum.FORUM_MODERATOR, forumModerator)
                        .likeAllowEmpty(Forum.FORUM_TOPIC_LOCATION, forumTopicLocation)
                        .eqAllowEmpty(Forum.FORUM_SORT, forumSort)
                        .eqAllowEmpty(Forum.FORUM_TOP, forumTop)
                        .eqAllowEmpty(Forum.FORUM_TOP_LEVEL, forumTopLevel)
                        .eqAllowEmpty(Forum.FORUM_TOP_END_TIME, forumTopEndTime)
                        .eqAllowEmpty(Forum.FORUM_IS_ACTIVE, forumIsActive)
                        .eqAllowEmpty(Forum.FORUM_IS_RECOMAND, forumIsRecomand)
                        .eqAllowEmpty(Forum.FORUM_AUDIT_CONTENT, forumAuditContent)
                        .eqAllowEmpty(Forum.FORUM_AUDIT_STATUS, forumAuditStatus)
                        .eq(Forum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Forum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumList;
    }

	@Override
	public List<Forum> listRandom(String randomAppId, String randomForumMediaId, String randomForumModerator, Integer pageIndex, Integer pageSize) {
		List<Forum> forumList = list(
                new BaseWrapper<Forum>()
                        .like(Forum.APP_ID, randomAppId)
                        .likeAllowEmpty(Forum.FORUM_MEDIA_ID, randomForumMediaId)
                        .likeAllowEmpty(Forum.FORUM_MODERATOR, randomForumModerator)
                        .eq(Forum.FORUM_AUDIT_STATUS, ForumAuditStatus.AUDIT_PASS.getKey())
                        .eq(Forum.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Forum.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );
        return forumList;
	}

}