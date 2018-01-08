package com.nowui.cloud.sns.forum.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 论坛审核信息
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Component
@TableName(value = "forum_audit_info")
public class ForumAudit extends BaseEntity {

    /**
     * 论坛审核信息id
     */
    @TableId
    @NotNull(message = "论坛审核信息id不能为空")
    @Length(max = 32, message = "论坛审核信息id长度超出限制")
    private String forumAuditId;
    public static final String FORUM_AUDIT_ID = "forumAuditId";

    /**
     * 审核状态
     */
    @TableField
    @NotNull(message = "审核状态不能为空")
    @Length(max = 11, message = "审核状态长度超出限制")
    private Integer forumAuditStatus;
    public static final String FORUM_AUDIT_STATUS = "forumAuditStatus";

    /**
     * 应用Id
     */
    @TableField
    @NotNull(message = "应用Id不能为空")
    @Length(max = 32, message = "应用Id长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 审核内容
     */
    @TableField
    @NotNull(message = "审核内容不能为空")
    @Length(max = 500, message = "审核内容长度超出限制")
    private String auditSuggestContent;
    public static final String AUDIT_SUGGEST_CONTENT = "auditSuggestContent";

    /**
     * 论坛id
     */
    @TableField
    @NotNull(message = "论坛id不能为空")
    @Length(max = 32, message = "论坛id长度超出限制")
    private String forumId;
    public static final String FORUM_ID = "forumId";


    public String getForumAuditId() {
        return getString(FORUM_AUDIT_ID);
    }
    
    public void setForumAuditId(String forumAuditId) {
        put(FORUM_AUDIT_ID, forumAuditId);
    }

    public Integer getForumAuditStatus() {
        return getInteger(FORUM_AUDIT_STATUS);
    }
    
    public void setForumAuditStatus(Integer forumAuditStatus) {
        put(FORUM_AUDIT_STATUS, forumAuditStatus);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getAuditSuggestContent() {
        return getString(AUDIT_SUGGEST_CONTENT);
    }
    
    public void setAuditSuggestContent(String auditSuggestContent) {
        put(AUDIT_SUGGEST_CONTENT, auditSuggestContent);
    }

    public String getForumId() {
        return getString(FORUM_ID);
    }
    
    public void setForumId(String forumId) {
        put(FORUM_ID, forumId);
    }


}