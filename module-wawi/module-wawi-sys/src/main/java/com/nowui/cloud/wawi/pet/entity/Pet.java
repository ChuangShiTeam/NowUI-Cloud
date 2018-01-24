package com.nowui.cloud.wawi.pet.entity;

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
 * 宠物
 *
 * @author hucy
 *
 * 2018-01-22
 */
@Component
@Document(indexName = "nowui", type = "pet_info")
@TableName(value = "pet_info")
public class Pet extends BaseEntity {

    /**
     * 宠物编号
     */
    @Id
    @TableId
    @NotNull(message = "宠物编号不能为空")
    @Length(max = 32, message = "宠物编号长度超出限制")
    private String petId;
    public static final String PET_ID = "petId";

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
     * 宠物名称
     */
    @Field
    @TableField
    @NotNull(message = "宠物名称不能为空")
    @Length(max = 100, message = "宠物名称长度超出限制")
    private String petName;
    public static final String PET_NAME = "petName";

    /**
     * 宠物性别 (1：弟弟，2：妹妹)
     */
    @Field
    @TableField
    @NotNull(message = "宠物性别 (1：弟弟，2：妹妹)不能为空")
    private Boolean petSex;
    public static final String PET_SEX = "petSex";

    /**
     * 宠物生日
     */
    @Field
    @TableField
    @NotNull(message = "宠物生日不能为空")
    @Length(max = 10, message = "宠物生日长度超出限制")
    private String petBirthday;
    public static final String PET_BIRTHDAY = "petBirthday";

    /**
     * 宠物头像（url）
     */
    @Field
    @TableField
    @NotNull(message = "宠物头像（url）不能为空")
    @Length(max = 255, message = "宠物头像（url）长度超出限制")
    private String petAvatar;
    public static final String PET_AVATAR = "petAvatar";

    /**
     * 宠物简介
     */
    @Field
    @TableField
    @NotNull(message = "宠物简介不能为空")
    @Length(max = 2000, message = "宠物简介长度超出限制")
    private String petDescription;
    public static final String PET_DESCRIPTION = "petDescription";

    /**
     * 宠物分类编号
     */
    @Field
    @TableField
    @NotNull(message = "宠物分类编号不能为空")
    @Length(max = 32, message = "宠物分类编号长度超出限制")
    private String petCategoryId;
    public static final String PET_CATEGORY_ID = "petCategoryId";


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

    public String getPetName() {
        return getString(PET_NAME);
    }
    
    public void setPetName(String petName) {
        put(PET_NAME, petName);
    }

    public Boolean getPetSex() {
        return getBoolean(PET_SEX);
    }
    
    public void setPetSex(Boolean petSex) {
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

    public String getPetCategoryId() {
        return getString(PET_CATEGORY_ID);
    }
    
    public void setPetCategoryId(String petCategoryId) {
        put(PET_CATEGORY_ID, petCategoryId);
    }


}