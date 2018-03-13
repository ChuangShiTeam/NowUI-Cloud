package com.nowui.cloud.shop.product.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 产品
 *
 * @author ZhongYongQiang
 * <p>
 * 2018-01-29
 */
@Component
@Document(collection = "product_info")
public class ProductView extends BaseView {

    @KeyId
    @Field
    @NotNull(message = "商品编号不能为空")
    @Length(max = 32, message = "商品编号长度超出限制")
    private String productId;
    public static final String PRODUCT_ID = "productId";

    @Field
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    @Field
    @NotNull(message = "商品名称不能为空")
    @Length(max = 100, message = "商品名称长度超出限制")
    private String productName;
    public static final String PRODUCT_NAME = "productName";

    /**
     * 商品分类编号
     */
    @Field
    @NotNull(message = "商品分类编号不能为空")
    @Length(max = 32, message = "商品分类编号长度超出限制")
    private String productCategoryId;
    public static final String PRODUCT_CATEGORY_ID = "productCategoryId";

    /**
     * 商品分类名称
     */
    @Field
    @NotNull(message = "商品分类名称不能为空")
    @Length(max = 32, message = "商品分类名称长度超出限制")
    private String productCategoryName;
    public static final String PRODUCT_CATEGORY_NAME = "productCategoryName";

    /**
     * 商品图片编号
     */
    @Field
    @NotNull(message = "商品图片编号不能为空")
    @Length(max = 32, message = "商品图片编号长度超出限制")
    private String productImageFileId;
    public static final String PRODUCT_IMAGE_FILE_ID = "productImageFileId";

    /**
     * 商品图片路径
     */
    @Field
    @NotNull(message = "商品图片路径不能为空")
    @Length(max = 200, message = "商品图片路径长度超出限制")
    private String productImageFilePath;
    public static final String PRODUCT_IMAGE_FILE_PATH = "productImageFilePath";

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductImageFileId() {
        return productImageFileId;
    }

    public void setProductImageFileId(String productImageFileId) {
        this.productImageFileId = productImageFileId;
    }

    public String getProductImageFilePath() {
        return productImageFilePath;
    }

    public void setProductImageFilePath(String productImageFilePath) {
        this.productImageFilePath = productImageFilePath;
    }

}
