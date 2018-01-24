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
 * 宠物分类
 *
 * @author marcus
 *
 * 2018-01-16
 */
@Component
@Document(indexName = "nowui", type = "pet_category_info")
@TableName(value = "pet_category_info")
public class PetCategory extends BaseEntity {

    /**
     * 宠物分类编号
     */
    @Id
    @TableId
    @NotNull(message = "宠物分类编号不能为空")
    @Length(max = 32, message = "宠物分类编号长度超出限制")
    private String petCategoryId;
    public static final String PET_CATEGORY_ID = "petCategoryId";

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
     * 宠物分类名称
     */
    @Field
    @TableField
    @NotNull(message = "宠物分类名称不能为空")
    @Length(max = 50, message = "宠物分类名称长度超出限制")
    private String petCategoryName;
    public static final String PET_CATEGORY_NAME = "petCategoryName";

    /**
     * 宠物分类编码
     */
    @Field
    @TableField
    @NotNull(message = "宠物分类编码不能为空")
    @Length(max = 50, message = "宠物分类编码长度超出限制")
    private String petCategoryCode;
    public static final String PET_CATEGORY_CODE = "petCategoryCode";

    /**
     * 宠物分类图片
     */
    @Field
    @TableField
    @NotNull(message = "宠物分类图片不能为空")
    @Length(max = 32, message = "宠物分类图片长度超出限制")
    private String petCategoryImage;
    public static final String PET_CATEGORY_IMAGE = "petCategoryImage";

    /**
     * 宠物分类简介
     */
    @Field
    @TableField
    @NotNull(message = "宠物分类简介不能为空")
    @Length(max = 2000, message = "宠物分类简介长度超出限制")
    private String petCategoryDescription;
    public static final String PET_CATEGORY_DESCRIPTION = "petCategoryDescription";

    /**
     * 宠物分类排序
     */
    @Field
    @TableField
    @NotNull(message = "宠物分类排序不能为空")
    private Integer petCategorySort;
    public static final String PET_CATEGORY_SORT = "petCategorySort";


    public String getPetCategoryId() {
        return getString(PET_CATEGORY_ID);
    }
    
    public void setPetCategoryId(String petCategoryId) {
        put(PET_CATEGORY_ID, petCategoryId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getPetCategoryName() {
        return getString(PET_CATEGORY_NAME);
    }
    
    public void setPetCategoryName(String petCategoryName) {
        put(PET_CATEGORY_NAME, petCategoryName);
    }

    public String getPetCategoryCode() {
        return getString(PET_CATEGORY_CODE);
    }
    
    public void setPetCategoryCode(String petCategoryCode) {
        put(PET_CATEGORY_CODE, petCategoryCode);
    }

    public String getPetCategoryImage() {
        return getString(PET_CATEGORY_IMAGE);
    }
    
    public void setPetCategoryImage(String petCategoryImage) {
        put(PET_CATEGORY_IMAGE, petCategoryImage);
    }

    public String getPetCategoryDescription() {
        return getString(PET_CATEGORY_DESCRIPTION);
    }
    
    public void setPetCategoryDescription(String petCategoryDescription) {
        put(PET_CATEGORY_DESCRIPTION, petCategoryDescription);
    }

    public Integer getPetCategorySort() {
        return getInteger(PET_CATEGORY_SORT);
    }
    
    public void setPetCategorySort(Integer petCategorySort) {
        put(PET_CATEGORY_SORT, petCategorySort);
    }


}