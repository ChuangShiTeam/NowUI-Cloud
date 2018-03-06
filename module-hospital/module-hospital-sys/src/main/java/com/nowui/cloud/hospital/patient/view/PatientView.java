package com.nowui.cloud.hospital.patient.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 患者视图
 *
 * @author HuangChengLin
 *
 * 2018-03-02
 */
@Component
@Document(collection = "patient_info")
public class PatientView extends BaseView {

    /**
     * 患者ID
     */
    @KeyId
    @Field
    @NotNull(message = "患者ID不能为空")
    private String patientId;
    public static final String PATIENT_ID = "patientId";

    /**
     * 用户ID
     */
    @Field
    @NotNull(message = "用户ID不能为空")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 会员ID
     */
    @Field
    @NotNull(message = "会员ID不能为空")
    private String memberId;
    public static final String MEMBER_ID = "memberId";

    /**
     * 手机号码
     */
    @Field
    @NotNull(message = "手机号码不能为空")
    private String patientPhone;
    public static final String PATIENT_PHONE = "patientPhone";

    /**
     * 头像编号
     */
    @Field
    @NotNull(message = "头像编号不能为空")
    private String patientImageId;
    public static final String PATIENT_IMAGE_ID = "patientImageId";

    /**
     * 头像地址
     */
    @Field
    @NotNull(message = "头像地址不能为空")
    private String patientImagePath;
    public static final String PATIENT_IMAGE_PATH = "patientImagePath";

    /**
     * 昵称
     */
    @Field
    @NotNull(message = "昵称不能为空")
    private String patientNickname;
    public static final String PATIENT_NICKNAME = "patientNickname";

    /**
     * 性别
     */
    @Field
    @NotNull(message = "性别不能为空")
    private String patientSex;
    public static final String PATIENT_SEX = "patientSex";

    /**
     * 出生日期
     */
    @Field
    @NotNull(message = "出生日期不能为空")
    private String patientBirthDate;
    public static final String PATIENT_BIRTH_DATE = "patientBirthDate";

    /**
     * 所在省
     */
    @Field
    @NotNull(message = "所在省不能为空")
    private String patientProvince;
    public static final String PATIENT_PROVINCE = "patientProvince";

    /**
     * 所在市
     */
    @Field
    @NotNull(message = "所在市不能为空")
    private String patientCity;
    public static final String PATIENT_CITY = "patientCity";

    /**
     * 所在区
     */
    @Field
    @NotNull(message = "所在区不能为空")
    private String patientArea;
    public static final String PATIENT_AREA = "patientArea";

    /**
     * 详细地址
     */
    @Field
    @NotNull(message = "详细地址不能为空")
    private String patientAddress;
    public static final String PATIENT_ADDRESS = "patientAddress";

    /**
     * 邀请码
     */
    @Field
    @NotNull(message = "邀请码不能为空")
    private String patientInvitationCode;
    public static final String PATIENT_INVITATION_CODE = "patientInvitationCode";

    /**
     * 是否锁定
     */
    @Field
    @NotNull(message = "是否锁定不能为空")
    private Boolean patientIsLock;
    public static final String PATIENT_IS_LOCK = "patientIsLock";

    /**
     * 来源入口
     */
    @Field
    @NotNull(message = "来源入口不能为空")
    private String patientSource;
    public static final String PATIENT_SOURCE = "patientSource";

    /**
     * 
     */
    @Field
    @NotNull(message = "不能为空")
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

    public String getPatientImagePath() {
        return getString(PATIENT_IMAGE_PATH);
    }

    public void setPatientImageId(String patientImageId) {
        put(PATIENT_IMAGE_ID, patientImageId);
    }
    public void setPatientImagePath(String patientImagePath) {
        put(PATIENT_IMAGE_PATH, patientImagePath);
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
    public String getPatientBirthDate() {
        return getString(PATIENT_BIRTH_DATE);
    }

    public void setPatientBirthDate(String patientBirthDate) {
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