package com.nowui.cloud.hospital.doctor.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 医生
 *
 * @author WangZhiCai
 *
 * 2018-03-08
 */
@Component

@TableName(value = "doctor_info")
public class Doctor extends BaseEntity {

    /**
     * 医生ID
     */
    @Id
    @TableId
    @NotNull(message = "医生ID不能为空")
    @Length(max = 32, message = "医生ID长度超出限制")
    private String doctorId;
    public static final String DOCTOR_ID = "doctorId";

    /**
     * 用户ID
     */
    @TableField
    @NotNull(message = "用户ID不能为空")
    @Length(max = 32, message = "用户ID长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 医生姓名
     */
    @TableField
    @NotNull(message = "医生姓名不能为空")
    @Length(max = 50, message = "医生姓名长度超出限制")
    private String doctorName;
    public static final String DOCTOR_NAME = "doctorName";

    /**
     * 医生性别
     */
    @TableField
    @NotNull(message = "医生性别不能为空")
    @Length(max = 10, message = "医生性别长度超出限制")
    private String doctorSex;
    public static final String DOCTOR_SEX = "doctorSex";

    /**
     * 医生年龄
     */
    @TableField
    private Integer doctorAge;
    public static final String DOCTOR_AGE = "doctorAge";

    /**
     * 医生简介
     */
    @TableField
    @NotNull(message = "医生简介不能为空")
    @Length(max = 200, message = "医生简介长度超出限制")
    private String doctorSummary;
    public static final String DOCTOR_SUMMARY = "doctorSummary";

    /**
     * 专业技能
     */
    @TableField
    @NotNull(message = "专业技能不能为空")
    @Length(max = 200, message = "专业技能长度超出限制")
    private String doctorProfessional;
    public static final String DOCTOR_PROFESSIONAL = "doctorProfessional";

    /**
     * 单位名称
     */
    @TableField
    @NotNull(message = "单位名称不能为空")
    @Length(max = 50, message = "单位名称长度超出限制")
    private String doctorOrganizationName;
    public static final String DOCTOR_ORGANIZATION_NAME = "doctorOrganizationName";

    /**
     * 部门名称
     */
    @TableField
    @NotNull(message = "部门名称不能为空")
    @Length(max = 50, message = "部门名称长度超出限制")
    private String doctorDepartmentName;
    public static final String DOCTOR_DEPARTMENT_NAME = "doctorDepartmentName";

    /**
     * 擅长
     */
    @TableField
    @NotNull(message = "擅长不能为空")
    @Length(max = 50, message = "擅长长度超出限制")
    private String doctorMaster;
    public static final String DOCTOR_MASTER = "doctorMaster";

    /**
     * 头像
     */
    @TableField
    @NotNull(message = "头像不能为空")
    @Length(max = 32, message = "头像长度超出限制")
    private String doctorImageId;
    public static final String DOCTOR_IMAGE_ID = "doctorImageId";

    /**
     * 职称
     */
    @TableField
    @NotNull(message = "职称不能为空")
    @Length(max = 200, message = "职称长度超出限制")
    private String doctorRank;
    public static final String DOCTOR_RANK = "doctorRank";

    /**
     * 状态
     */
    @TableField
    @NotNull(message = "状态不能为空")
    @Length(max = 200, message = "状态长度超出限制")
    private String doctorStatus;
    public static final String DOCTOR_STATUS = "doctorStatus";

    /**
     * 证书
     */
    @TableField
    @NotNull(message = "证书不能为空")
    @Length(max = 200, message = "证书长度超出限制")
    private String doctorCert;
    public static final String DOCTOR_CERT = "doctorCert";

    /**
     * 
     */
    @TableField
    @NotNull(message = "不能为空")
    @Length(max = 32, message = "长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";


    public String getDoctorId() {
        return getString(DOCTOR_ID);
    }
    
    public void setDoctorId(String doctorId) {
        put(DOCTOR_ID, doctorId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
    
    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getDoctorName() {
        return getString(DOCTOR_NAME);
    }
    
    public void setDoctorName(String doctorName) {
        put(DOCTOR_NAME, doctorName);
    }

    public String getDoctorSex() {
        return getString(DOCTOR_SEX);
    }
    
    public void setDoctorSex(String doctorSex) {
        put(DOCTOR_SEX, doctorSex);
    }

    public Integer getDoctorAge() {
        return getInteger(DOCTOR_AGE);
    }
    
    public void setDoctorAge(Integer doctorAge) {
        put(DOCTOR_AGE, doctorAge);
    }

    public String getDoctorSummary() {
        return getString(DOCTOR_SUMMARY);
    }
    
    public void setDoctorSummary(String doctorSummary) {
        put(DOCTOR_SUMMARY, doctorSummary);
    }

    public String getDoctorProfessional() {
        return getString(DOCTOR_PROFESSIONAL);
    }
    
    public void setDoctorProfessional(String doctorProfessional) {
        put(DOCTOR_PROFESSIONAL, doctorProfessional);
    }

    public String getDoctorOrganizationName() {
        return getString(DOCTOR_ORGANIZATION_NAME);
    }
    
    public void setDoctorOrganizationName(String doctorOrganizationName) {
        put(DOCTOR_ORGANIZATION_NAME, doctorOrganizationName);
    }

    public String getDoctorDepartmentName() {
        return getString(DOCTOR_DEPARTMENT_NAME);
    }
    
    public void setDoctorDepartmentName(String doctorDepartmentName) {
        put(DOCTOR_DEPARTMENT_NAME, doctorDepartmentName);
    }

    public String getDoctorMaster() {
        return getString(DOCTOR_MASTER);
    }
    
    public void setDoctorMaster(String doctorMaster) {
        put(DOCTOR_MASTER, doctorMaster);
    }

    public String getDoctorImageId() {
        return getString(DOCTOR_IMAGE_ID);
    }
    
    public void setDoctorImageId(String doctorImageId) {
        put(DOCTOR_IMAGE_ID, doctorImageId);
    }

    public String getDoctorRank() {
        return getString(DOCTOR_RANK);
    }
    
    public void setDoctorRank(String doctorRank) {
        put(DOCTOR_RANK, doctorRank);
    }

    public String getDoctorStatus() {
        return getString(DOCTOR_STATUS);
    }
    
    public void setDoctorStatus(String doctorStatus) {
        put(DOCTOR_STATUS, doctorStatus);
    }

    public String getDoctorCert() {
        return getString(DOCTOR_CERT);
    }
    
    public void setDoctorCert(String doctorCert) {
        put(DOCTOR_CERT, doctorCert);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }


}