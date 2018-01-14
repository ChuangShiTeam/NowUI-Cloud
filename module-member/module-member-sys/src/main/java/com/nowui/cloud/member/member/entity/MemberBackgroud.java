package com.nowui.cloud.member.member.entity;

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
 * 会员背景
 *
 * @author marcus
 *
 * 2018-01-14
 */
@Component
@Document(indexName = "nowui", type = "member_backgroud_info")
@TableName(value = "member_backgroud_info")
public class MemberBackgroud extends BaseEntity {

    /**
     * 会员背景编号
     */
    @Id
    @TableId
    @NotNull(message = "会员背景编号不能为空")
    @Length(max = 32, message = "会员背景编号长度超出限制")
    private String memberBackgroundId;
    public static final String MEMBER_BACKGROUND_ID = "memberBackgroundId";

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
     * 会员背景图文件编号
     */
    @Field
    @TableField
    @NotNull(message = "会员背景图文件编号不能为空")
    @Length(max = 32, message = "会员背景图文件编号长度超出限制")
    private String memberBackgroundFileId;
    public static final String MEMBER_BACKGROUND_FILE_ID = "memberBackgroundFileId";


    public String getMemberBackgroundId() {
        return getString(MEMBER_BACKGROUND_ID);
    }
    
    public void setMemberBackgroundId(String memberBackgroundId) {
        put(MEMBER_BACKGROUND_ID, memberBackgroundId);
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

    public String getMemberBackgroundFileId() {
        return getString(MEMBER_BACKGROUND_FILE_ID);
    }
    
    public void setMemberBackgroundFileId(String memberBackgroundFileId) {
        put(MEMBER_BACKGROUND_FILE_ID, memberBackgroundFileId);
    }


}