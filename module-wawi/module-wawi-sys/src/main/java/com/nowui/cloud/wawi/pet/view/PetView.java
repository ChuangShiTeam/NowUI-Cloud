package com.nowui.cloud.wawi.pet.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 宠物视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "pet_info")
public class PetView extends BaseView {

    /**
     * 宠物编号
     */
    @KeyId
    @Field
    @NotNull(message = "宠物编号不能为空")
    private String petId;
    public static final String PET_ID = "petId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 用户编号
     */
    @Field
    @NotNull(message = "用户编号不能为空")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 宠物分类编号
     */
    @Field
    @NotNull(message = "宠物分类编号不能为空")
    private String petCategoryId;
    public static final String PET_CATEGORY_ID = "petCategoryId";

    /**
     * 宠物名称
     */
    @Field
    @NotNull(message = "宠物名称不能为空")
    private String petName;
    public static final String PET_NAME = "petName";

    /**
     * 宠物性别
     */
    @Field
    @NotNull(message = "宠物性别不能为空")
    private String petSex;
    public static final String PET_SEX = "petSex";

    /**
     * 宠物生日
     */
    @Field
    @NotNull(message = "宠物生日不能为空")
    private String petBirthday;
    public static final String PET_BIRTHDAY = "petBirthday";

    /**
     * 宠物头像
     */
    @Field
    @NotNull(message = "宠物头像不能为空")
    private String petAvatar;
    public static final String PET_AVATAR = "petAvatar";

    /**
     * 宠物简介
     */
    @Field
    @NotNull(message = "宠物简介不能为空")
    private String petDescription;
    public static final String PET_DESCRIPTION = "petDescription";


    public String getPetId() {
        return getString(PET_ID);
    }

    public void setPetId(String petId) {
        put(PET_ID, petId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }

    public void setUserId(String userId) {
        put(USER_ID, userId);
    }

    public String getPetCategoryId() {
        return getString(PET_CATEGORY_ID);
    }

    public void setPetCategoryId(String petCategoryId) {
        put(PET_CATEGORY_ID, petCategoryId);
    }

    public String getPetName() {
        return getString(PET_NAME);
    }

    public void setPetName(String petName) {
        put(PET_NAME, petName);
    }

    public String getPetSex() {
        return getString(PET_SEX);
    }

    public void setPetSex(String petSex) {
        put(PET_SEX, petSex);
    }

    public String getPetBirthday() {
        return getString(PET_BIRTHDAY);
    }

    public void setPetBirthday(String petBirthday) {
        put(PET_BIRTHDAY, petBirthday);
    }

    public String getPetAvatar() {
        return getString(PET_AVATAR);
    }

    public void setPetAvatar(String petAvatar) {
        put(PET_AVATAR, petAvatar);
    }

    public String getPetDescription() {
        return getString(PET_DESCRIPTION);
    }

    public void setPetDescription(String petDescription) {
        put(PET_DESCRIPTION, petDescription);
    }


}