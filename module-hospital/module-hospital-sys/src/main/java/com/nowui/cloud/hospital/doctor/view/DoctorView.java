package com.nowui.cloud.hospital.doctor.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 医生视图
 *
 * @author ZhongYongQiangZ
 *
 * 2018-03-01
 */
@Component
@Document(collection = "doctor_info")
public class DoctorView extends BaseView {

    /**
     * 医生ID
     */
    @KeyId
    @Field
    @NotNull(message = "医生ID不能为空")
    private String doctorId;
    public static final String DOCTOR_ID = "doctorId";

    /**
     * 用户ID
     */
    @Field
    @NotNull(message = "用户ID不能为空")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 医生姓名
     */
    @Field
    @NotNull(message = "医生姓名不能为空")
    private String doctorName;
    public static final String DOCTOR_NAME = "doctorName";

    /**
     * 医生性别
     */
    @Field
    @NotNull(message = "医生性别不能为空")
    private String doctorSex;
    public static final String DOCTOR_SEX = "doctorSex";

    /**
     * 医生年龄
     */
    @Field
    @NotNull(message = "医生年龄不能为空")
    private Integer doctorAge;
    public static final String DOCTOR_AGE = "doctorAge";

    /**
     * 医生简介
     */
    @Field
    @NotNull(message = "医生简介不能为空")
    private String doctorSummary;
    public static final String DOCTOR_SUMMARY = "doctorSummary";

    /**
     * 专业技能
     */
    @Field
    @NotNull(message = "专业技能不能为空")
    private String doctorProfessional;
    public static final String DOCTOR_PROFESSIONAL = "doctorProfessional";

    /**
     * 单位名称
     */
    @Field
    @NotNull(message = "单位名称不能为空")
    private String doctorOrganizationName;
    public static final String DOCTOR_ORGANIZATION_NAME = "doctorOrganizationName";

    /**
     * 部门名称
     */
    @Field
    @NotNull(message = "部门名称不能为空")
    private String doctorDepartmentName;
    public static final String DOCTOR_DEPARTMENT_NAME = "doctorDepartmentName";

    /**
     * 擅长
     */
    @Field
    @NotNull(message = "擅长不能为空")
    private String doctorMaster;
    public static final String DOCTOR_MASTER = "doctorMaster";

    /**
     * 头像
     */
    @Field
    @NotNull(message = "头像不能为空")
    private String doctorImage;
    public static final String DOCTOR_IMAGE = "doctorImage";

    /**
     * 职称
     */
    @Field
    @NotNull(message = "职称不能为空")
    private String doctorRank;
    public static final String DOCTOR_RANK = "doctorRank";

    /**
     * 状态
     */
    @Field
    @NotNull(message = "状态不能为空")
    private String doctorStatus;
    public static final String DOCTOR_STATUS = "doctorStatus";

    /**
     * 证书
     */
    @Field
    @NotNull(message = "证书不能为空")
    private String doctorCert;
    public static final String DOCTOR_CERT = "doctorCert";

    /**
     * 
     */
    @Field
    @NotNull(message = "不能为空")
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
    public String getDoctorImage() {
        return getString(DOCTOR_IMAGE);
    }

    public void setDoctorImage(String doctorImage) {
        put(DOCTOR_IMAGE, doctorImage);
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