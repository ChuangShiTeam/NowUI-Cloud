package com.nowui.cloud.wawi.member.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 会员访问圈子
 *
 * @author marcus
 *
 * 2018-01-17
 */
@Component
@Document(indexName = "nowui", type = "member_visit_forum_info")
@TableName(value = "member_visit_forum_info")
public class MemberVisitForum extends BaseEntity {

    /**
     * 会员访问圈子编号
     */
    @Id
    @TableId
    @NotNull(message = "会员访问圈子编号不能为空")
    @Length(max = 32, message = "会员访问圈子编号长度超出限制")
    private String memberVisitForumId;
    public static final String MEMBER_VISIT_FORUM_ID = "memberVisitForumId";

    /**
     * 应用编号
     */
    @Field
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 会员编号
     */
    @Field
    @TableField
    @NotNull(message = "会员编号不能为空")
    @Length(max = 32, message = "会员编号长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 用户编号
     */
    @Field
    @TableField
    @NotNull(message = "用户编号不能为空")
    @Length(max = 32, message = "用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";


    public String getMemberVisitForumId() {
        return getString(MEMBER_VISIT_FORUM_ID);
    }
    
    public void setMemberVisitForumId(String memberVisitForumId) {
        put(MEMBER_VISIT_FORUM_ID, memberVisitForumId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }
    
    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }


}