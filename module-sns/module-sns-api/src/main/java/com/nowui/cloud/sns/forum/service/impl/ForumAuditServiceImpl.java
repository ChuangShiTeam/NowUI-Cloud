package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumAudit;
import com.nowui.cloud.sns.forum.mapper.ForumAuditMapper;
import com.nowui.cloud.sns.forum.service.ForumAuditService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 论坛审核信息业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class ForumAuditServiceImpl extends BaseServiceImpl<ForumAuditMapper, ForumAudit> implements ForumAuditService {

    @Override
    public Integer countForAdmin(String appId, Integer forumAuditStatus, String forumId) {
        Integer count = count(
                new BaseWrapper<ForumAudit>()
                        .eq(ForumAudit.APP_ID, appId)
                        .eq(ForumAudit.FORUM_AUDIT_STATUS, forumAuditStatus)
                        .eq(ForumAudit.FORUM_ID, forumId)
                        .eq(ForumAudit.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<ForumAudit> listForAdmin(String appId, Integer forumAuditStatus, String forumId, Integer pageIndex, Integer pageSize) {
        List<ForumAudit> forumAuditList = list(
                new BaseWrapper<ForumAudit>()
                        .eq(ForumAudit.APP_ID, appId)
                        .eq(ForumAudit.FORUM_AUDIT_STATUS, forumAuditStatus)
                        .eq(ForumAudit.FORUM_ID, forumId)
                        .eq(ForumAudit.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(ForumAudit.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return forumAuditList;
    }

}