package com.nowui.cloud.sns.forum.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.sns.forum.entity.ForumAudit;

import java.util.List;

/**
 * 论坛审核信息业务接口
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
public interface ForumAuditService extends BaseService<ForumAudit> {

    /**
     * 论坛审核信息统计
     *
     * @param appId 应用编号
     * @param forumAuditStatus 审核状态
     * @param auditSuggestContent 审核内容
     * @param forumId 论坛id
     * @return Integer 论坛审核信息统计
     */
    Integer countForAdmin(String appId, Integer forumAuditStatus, String forumId);

    /**
     * 论坛审核信息列表
     *
     * @param appId 应用编号
     * @param forumAuditStatus 审核状态
     * @param auditSuggestContent 审核内容
     * @param forumId 论坛id
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<ForumAudit> 论坛审核信息列表
     */
    List<ForumAudit> listForAdmin(String appId, Integer forumAuditStatus, String forumId, Integer pageIndex, Integer pageSize);
}
