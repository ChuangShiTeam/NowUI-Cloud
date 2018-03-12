package com.nowui.cloud.shop.product.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 产品
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Component
@Document(collection = "product_info")
public class ProductView extends BaseView {

    @KeyId
    @Field
    private String productId;
    public static final String PRODUCT_ID = "productId";

    @Field
    private String appId;
    public static final String APP_ID = "appId";

    @Field
    private String productName;
    public static final String PRODUCT_NAME = "productName";

    /**
     * 商品分类
     */
    @Field
    @NotNull(message = "商品分类编号不能为空")
    @Length(max = 32, message = "商品分类长度超出限制")
    private String productCategoryId;
    public static final String PRODUCT_CATEGORY_ID = "productCategoryId";

    /**
     * 商品分类
     */
    @Field
    @NotNull(message = "商品分类名称不能为空")
    @Length(max = 32, message = "商品分类名称长度超出限制")
    private String productCategoryName;
    public static final String PRODUCT_CATEGORY_NAME = "productCategoryName";

    /**
     * 商品图片
     */
    @Field
    @NotNull(message = "商品图片编号不能为空")
    @Length(max = 32, message = "商品图片编号长度超出限制")
    private String productImageId;
    public static final String PRODUCT_IMAGE_ID = "productImageId";

    /**
     * 商品图片
     */
    @Field
    @NotNull(message = "商品图片路径不能为空")
    @Length(max = 200, message = "商品图片路径长度超出限制")
    private String productImagePath;
    public static final String PRODUCT_IMAGE_PATH = "productImagePath";

    public String getProductId() {
        return getString(PRODUCT_ID);
    }

    public void setProductId(String productId) {
        put(PRODUCT_ID, productId);
    }

    public String getAppId() {
        return getString(APP_ID);
    }

    public void setAppId(String appId) {
        put(APP_ID, appId);
    }

    public String getProductName() {
        return getString(PRODUCT_NAME);
    }

    public void setProductName(String productName) {
        put(PRODUCT_NAME, productName);
    }

    public String getProductCategoryId() {
        return getString(PRODUCT_CATEGORY_ID);
    }

    public void setProductCategoryId(String productCategoryId) {
        put(PRODUCT_CATEGORY_ID, productCategoryId);
    }

    public String getProductCategoryName() {
        return getString(PRODUCT_CATEGORY_NAME);
    }

    public void setProductCategoryName(String productCategoryName) {
        put(PRODUCT_CATEGORY_NAME, productCategoryName);
    }

    public String getProductImageId() {
        return getString(PRODUCT_IMAGE_ID);
    }

    public void setProductImageId(String productImageId) {
        put(PRODUCT_IMAGE_ID, productImageId);
    }

    public String getProductImagePath() {
        return getString(PRODUCT_IMAGE_PATH);
    }

    public void setProductImagePath(String productImagePath) {
        put(PRODUCT_IMAGE_PATH, productImagePath);
    }

}
