package com.nowui.cloud.cms.advertisement.entity;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSONObject;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * 广告
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Component(value = "advertisement")
@TableName(value = "advertisement_info")
public class Advertisement extends BaseEntity {
    
    /**
     * 广告编号
     */
    @Id
    @TableId
    @NotNull(message = "广告编号不能为空")
    @Length(max = 32, message = "广告编号长度超出限制")
    private String advertisementId;
    public static final String ADVERTISEMENT_ID = "advertisementId";
    
    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";
    
    /**
     * 广告分类编码
     */
    @TableField
    @NotNull(message = "广告分类编码不能为空")
    @Length(max = 50, message = "广告分类编码长度超出限制")
    private String advertisementCategoryCode;
    public static final String ADVERTISEMENT_CATEGORY_CODE = "advertisementCategoryCode";
    
    /**
     * 广告编码
     */
    @TableField
    @NotNull(message = "广告编码不能为空")
    @Length(max = 50, message = "广告编码长度超出限制")
    private String advertisementCode;
    public static final String ADVERTISEMENT_CODE = "advertisementCode";
    
    /**
     * 广告标题
     */
    @TableField
    @NotNull(message = "广告标题不能为空")
    @Length(max = 200, message = "广告标题长度超出限制")
    private String advertisementTitle;
    public static final String ADVERTISEMENT_TITLE = "advertisementTitle";
    
    /**
     * 广告图片
     */
    @TableField
    @NotNull(message = "广告图片不能为空")
    @Length(max = 200, message = "广告图片长度超出限制")
    private String advertisementImageId;
    public static final String ADVERTISEMENT_IMAGE_ID = "advertisementImageId";

    /**
     * 广告图片
     */
    @TableField(exist = false)
    @NotNull(message = "广告图片不能为空")
    private JSONObject advertisementImage;
    public static final String ADVERTISEMENT_IMAGE = "advertisementImage";
    
    /**
     * 广告内容
     */
    @TableField
    @NotNull(message = "广告内容不能为空")
    @Length(max = 2000, message = "广告内容长度超出限制")
    private String advertisementContent;
    public static final String ADVERTISEMENT_CONTENT = "advertisementContent";
    
    /**
     * 广告位置
     */
    @TableField
    @NotNull(message = "广告位置不能为空")
    @Length(max = 200, message = "广告位置长度超出限制")
    private String advertisementPosition;
    public static final String ADVERTISEMENT_POSITION = "advertisementPosition";
    
    /**
     * 广告超链接
     */
    @TableField
    @NotNull(message = "广告超链接不能为空")
    @Length(max = 200, message = "广告超链接长度超出限制")
    private String advertisementLink;
    public static final String ADVERTISEMENT_LINK = "advertisementLink";
    
    /**
     * 广告是否失效
     */
    @TableField
    @NotNull(message = "广告是否失效不能为空")
    private Boolean advertisementIsEfficient;
    public static final String ADVERTISEMENT_IS_EFFICIENT = "advertisementIsEfficient";
    
    /**
     * 广告排序
     */
    @TableField
    @NotNull(message = "广告排序不能为空")
    private Boolean advertisementSort;
    public static final String ADVERTISEMENT_SORT = "advertisementSort";
    
    public String getAdvertisementId() {
        return getString(ADVERTISEMENT_ID);
    }
    
    public void setAdvertisementId(String advertisementId) {
        put(ADVERTISEMENT_ID, advertisementId);
    }
    
    public String getAppId() {
        return getString(APP_ID);
    }
    
    public void setAppId(String appId) {
        put(APP_ID, appId);
    }
    
    public String getAdvertisementCategoryCode() {
        return getString(ADVERTISEMENT_CATEGORY_CODE);
    }
    
    public void setAdvertisementCategoryCode(String advertisementCategoryCode) {
        put(ADVERTISEMENT_CATEGORY_CODE, advertisementCategoryCode);
    }
    
    public String getAdvertisementCode() {
        return getString(ADVERTISEMENT_CODE);
    }
    
    public void setAdvertisementCode(String advertisementCode) {
        put(ADVERTISEMENT_CODE, advertisementCode);
    }
    
    public String getAdvertisementTitle() {
        return getString(ADVERTISEMENT_TITLE);
    }
    
    public void setAdvertisementTitle(String advertisementTitle) {
        put(ADVERTISEMENT_TITLE, advertisementTitle);
    }
    
    public String getAdvertisementImageId() {
        return getString(ADVERTISEMENT_IMAGE_ID);
    }
    
    public void setAdvertisementImageId(String advertisementImageId) {
        put(ADVERTISEMENT_IMAGE_ID, advertisementImageId);
    }

    public JSONObject getAdvertisementImage() {
        return getJSONObject(ADVERTISEMENT_IMAGE);
    }

    public void setAdvertisementImage(JSONObject advertisementImage) {
        put(ADVERTISEMENT_IMAGE, advertisementImage);
    }
    
    public String getAdvertisementContent() {
        return getString(ADVERTISEMENT_CONTENT);
    }
    
    public void setAdvertisementContent(String advertisementContent) {
        put(ADVERTISEMENT_CONTENT, advertisementContent);
    }
    
    public String getAdvertisementPosition() {
        return getString(ADVERTISEMENT_POSITION);
    }
    
    public void setAdvertisementPosition(String advertisementPosition) {
        put(ADVERTISEMENT_POSITION, advertisementPosition);
    }
    
    public String getAdvertisementLink() {
        return getAdvertisementLink();
    }
    
    public void setAdvertisementLink(String advertisementLink) {
        put(ADVERTISEMENT_LINK, advertisementLink);
    }
    
    public Boolean getAdvertisementIsEfficient() {
        return getBoolean(ADVERTISEMENT_IS_EFFICIENT);
    }
    
    public void setAdvertisementIsEfficient(Boolean advertisementIsEfficient) {
        put(ADVERTISEMENT_IS_EFFICIENT, advertisementIsEfficient);
    }
    
    public Boolean getAdvertisementSort() {
        return getBoolean(ADVERTISEMENT_SORT);
    }
    
    public void setAdvertisementSort(Boolean advertisementSort) {
        put(ADVERTISEMENT_SORT, advertisementSort);
    }

}
