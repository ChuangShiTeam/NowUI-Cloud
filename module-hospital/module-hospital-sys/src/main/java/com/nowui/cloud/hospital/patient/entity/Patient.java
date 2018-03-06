package com.nowui.cloud.hospital.patient.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 患者
 *
 * @author HuangChengLin
 *
 * 2018-03-02
 */
@Component

@TableName(value = "patient_info")
public class Patient extends BaseEntity {

    /**
     * 患者ID
     */
    @Id
    @TableId
    @NotNull(message = "患者ID不能为空")
    @Length(max = 32, message = "患者ID长度超出限制")
    private String patientId;
    public static final String PATIENT_ID = "patientId";

    /**
     * 用户ID
     */
    @TableField
    @NotNull(message = "用户ID不能为空")
    @Length(max = 32, message = "用户ID长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 会员ID
     */
    @TableField
    @NotNull(message = "会员ID不能为空")
    @Length(max = 32, message = "会员ID长度超出限制")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 手机号码
     */
    @TableField
    @NotNull(message = "手机号码不能为空")
    @Length(max = 20, message = "手机号码长度超出限制")
    private String patientPhone;
    public static final String PATIENT_PHONE = "patientPhone";

    /**
     * 头像
     */
    @TableField
    @NotNull(message = "头像不能为空")
    @Length(max = 32, message = "头像长度超出限制")
    private String patientImageId;
    public static final String PATIENT_IMAGE_ID = "patientImageId";

    /**
     * 昵称
     */
    @TableField
    @NotNull(message = "昵称不能为空")
    @Length(max = 20, message = "昵称长度超出限制")
    private String patientNickname;
    public static final String PATIENT_NICKNAME = "patientNickname";

    /**
     * 性别
     */
    @TableField
    @NotNull(message = "性别不能为空")
    @Length(max = 10, message = "性别长度超出限制")
    private String patientSex;
    public static final String PATIENT_SEX = "patientSex";

    /**
     * 出生日期
     */
    @TableField
    private Date patientBirthDate;
    public static final String PATIENT_BIRTH_DATE = "patientBirthDate";

    /**
     * 所在省
     */
    @TableField
    @NotNull(message = "所在省不能为空")
    @Length(max = 10, message = "所在省长度超出限制")
    private String patientProvince;
    public static final String PATIENT_PROVINCE = "patientProvince";

    /**
     * 所在市
     */
    @TableField
    @NotNull(message = "所在市不能为空")
    @Length(max = 10, message = "所在市长度超出限制")
    private String patientCity;
    public static final String PATIENT_CITY = "patientCity";

    /**
     * 所在区
     */
    @TableField
    @NotNull(message = "所在区不能为空")
    @Length(max = 10, message = "所在区长度超出限制")
    private String patientArea;
    public static final String PATIENT_AREA = "patientArea";

    /**
     * 详细地址
     */
    @TableField
    @NotNull(message = "详细地址不能为空")
    @Length(max = 50, message = "详细地址长度超出限制")
    private String patientAddress;
    public static final String PATIENT_ADDRESS = "patientAddress";

    /**
     * 邀请码
     */
    @TableField
    @NotNull(message = "邀请码不能为空")
    @Length(max = 10, message = "邀请码长度超出限制")
    private String patientInvitationCode;
    public static final String PATIENT_INVITATION_CODE = "patientInvitationCode";

    /**
     * 是否锁定
     */
    @TableField
    @NotNull(message = "是否锁定不能为空")
    private Boolean patientIsLock;
    public static final String PATIENT_IS_LOCK = "patientIsLock";

    /**
     * 来源入口
     */
    @TableField
    @NotNull(message = "来源入口不能为空")
    @Length(max = 255, message = "来源入口长度超出限制")
    private String patientSource;
    public static final String PATIENT_SOURCE = "patientSource";

    /**
     * 
     */
    @TableField
    @NotNull(message = "不能为空")
    @Length(max = 32, message = "长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";


    public String getPatientId() {
        return getString(PATIENT_ID);
    }
    
    public void setPatientId(String patientId) {
        put(PATIENT_ID, patientId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getMemberId() {
        return getString(MEMBER_ID);
    }
    
    public void setMemberId(String memberId) {
        put(MEMBER_ID, memberId);
    }

    public String getPatientPhone() {
        return getString(PATIENT_PHONE);
    }
    
    public void setPatientPhone(String patientPhone) {
        put(PATIENT_PHONE, patientPhone);
    }

    public String getPatientImageId() {
        return getString(PATIENT_IMAGE_ID);
    }
    
    public void setPatientImageId(String patientImageId) {
        put(PATIENT_IMAGE_ID, patientImageId);
    }

    public String getPatientNickname() {
        return getString(PATIENT_NICKNAME);
    }
    
    public void setPatientNickname(String patientNickname) {
        put(PATIENT_NICKNAME, patientNickname);
    }

    public String getPatientSex() {
        return getString(PATIENT_SEX);
    }
    
    public void setPatientSex(String patientSex) {
        put(PATIENT_SEX, patientSex);
    }

    public Date getPatientBirthDate() {
        return getDate(PATIENT_BIRTH_DATE);
    }
    
    public void setPatientBirthDate(Date patientBirthDate) {
        put(PATIENT_BIRTH_DATE, patientBirthDate);
    }

    public String getPatientProvince() {
        return getString(PATIENT_PROVINCE);
    }
    
    public void setPatientProvince(String patientProvince) {
        put(PATIENT_PROVINCE, patientProvince);
    }

    public String getPatientCity() {
        return getString(PATIENT_CITY);
    }
    
    public void setPatientCity(String patientCity) {
        put(PATIENT_CITY, patientCity);
    }

    public String getPatientArea() {
        return getString(PATIENT_AREA);
    }
    
    public void setPatientArea(String patientArea) {
        put(PATIENT_AREA, patientArea);
    }

    public String getPatientAddress() {
        return getString(PATIENT_ADDRESS);
    }
    
    public void setPatientAddress(String patientAddress) {
        put(PATIENT_ADDRESS, patientAddress);
    }

    public String getPatientInvitationCode() {
        return getString(PATIENT_INVITATION_CODE);
    }
    
    public void setPatientInvitationCode(String patientInvitationCode) {
        put(PATIENT_INVITATION_CODE, patientInvitationCode);
    }

    public Boolean getPatientIsLock() {
        return getBoolean(PATIENT_IS_LOCK);
    }
    
    public void setPatientIsLock(Boolean patientIsLock) {
        put(PATIENT_IS_LOCK, patientIsLock);
    }

    public String getPatientSource() {
        return getString(PATIENT_SOURCE);
    }
    
    public void setPatientSource(String patientSource) {
        put(PATIENT_SOURCE, patientSource);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }


}