package com.nowui.cloud.cms.advertisement.view;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 广告视图
 *
 * @author xupengfei
 *
 * 2018-02-03
 */
@Component
@Document(collection = "advertisement_info")
public class AdvertisementView extends BaseView {

    /**
     * 广告编号
     */
    @KeyId
    @Field
    private String advertisementId;
    public static final String ADVERTISEMENT_ID = "advertisementId";

    /**
     * 应用编号
     */
    @Field
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 分类编码
     */
    @Field
    private String advertisementCategoryCode;
    public static final String ADVERTISEMENT_CATEGORY_CODE = "advertisementCategoryCode";

    /**
     * 编码
     */
    @Field
    private String advertisementCode;
    public static final String ADVERTISEMENT_CODE = "advertisementCode";

    /**
     * 标题
     */
    @Field
    private String advertisementTitle;
    public static final String ADVERTISEMENT_TITLE = "advertisementTitle";

    /**
     * 图片
     */
    @Field
    private String advertisementImageId;
    public static final String ADEVERTISEMENT_IMAGE_ID = "advertisementImageId";

    /**
     * 图片
     */
    @Field
    private JSONObject advertisementImage;
    public static final String ADVERTISEMENT_IMAGE = "advertisementImage";

    /**
     * 内容
     */
    @Field
    private String advertisementContent;
    public static final String ADVERTISEMENT_CONTENT = "advertisementContent";

    /**
     * 位置
     */
    @Field
    private String advertisementPosition;
    public static final String ADVERTISEMENT_POSITION = "advertisementPosition";

    /**
     * 超链接
     */
    @Field
    private String advertisementLink;
    public static final String ADVERTISEMENT_LINK = "advertisementLink";

    /**
     * 是否生效
     */
    @Field
    private Boolean advertisementIsEfficient;
    public static final String ADVERTISEMENT_IS_EFFICIENT = "advertisementIsEfficient";

    /**
     * 排序
     */
    @Field
    private Integer advertisementSort;
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
        return getString(ADEVERTISEMENT_IMAGE_ID);
    }

    public void setAdvertisementImageId(String advertisementImageId) {
        put(ADEVERTISEMENT_IMAGE_ID, advertisementImageId);
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
        return getString(ADVERTISEMENT_LINK);
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

    public Integer getAdvertisementSort() {
        return getInteger(ADVERTISEMENT_SORT);
    }

    public void setAdvertisementSort(Integer advertisementSort) {
        put(ADVERTISEMENT_SORT, advertisementSort);
    }


}