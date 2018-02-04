package com.nowui.cloud.wawi.pet.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 宠物分类视图
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
@Document(collection = "pet_category_info")
public class PetCategoryView extends BaseView {

    /**
     * 宠物分类编号
     */
    @KeyId
    @Field
    @NotNull(message = "宠物分类编号不能为空")
    private String petCategoryId;
    public static final String PET_CATEGORY_ID = "petCategoryId";

    /**
     * 应用编号
     */
    @Field
    @NotNull(message = "应用编号不能为空")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 宠物父分类编号
     */
    @Field
    @NotNull(message = "宠物父分类编号不能为空")
    private String petCategoryParentId;
    public static final String PET_CATEGORY_PARENT_ID = "petCategoryParentId";

    /**
     * 父级路径
     */
    @Field
    @NotNull(message = "父级路径不能为空")
    private String petCategoryParentPath;
    public static final String PET_CATEGORY_PARENT_PATH = "petCategoryParentPath";

    /**
     * 宠物分类名称
     */
    @Field
    @NotNull(message = "宠物分类名称不能为空")
    private String petCategoryName;
    public static final String PET_CATEGORY_NAME = "petCategoryName";

    /**
     * 宠物分类编码
     */
    @Field
    @NotNull(message = "宠物分类编码不能为空")
    private String petCategoryCode;
    public static final String PET_CATEGORY_CODE = "petCategoryCode";

    /**
     * 宠物分类图片
     */
    @Field
    @NotNull(message = "宠物分类图片不能为空")
    private String petCategoryImage;
    public static final String PET_CATEGORY_IMAGE = "petCategoryImage";

    /**
     * 宠物分类简介
     */
    @Field
    @NotNull(message = "宠物分类简介不能为空")
    private String petCategoryDescription;
    public static final String PET_CATEGORY_DESCRIPTION = "petCategoryDescription";

    /**
     * 宠物分类排序
     */
    @Field
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

    public String getPetCategoryParentId() {
        return getString(PET_CATEGORY_PARENT_ID);
    }

    public void setPetCategoryParentId(String petCategoryParentId) {
        put(PET_CATEGORY_PARENT_ID, petCategoryParentId);
    }

    public String getPetCategoryParentPath() {
        return getString(PET_CATEGORY_PARENT_PATH);
    }

    public void setPetCategoryParentPath(String petCategoryParentPath) {
        put(PET_CATEGORY_PARENT_PATH, petCategoryParentPath);
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