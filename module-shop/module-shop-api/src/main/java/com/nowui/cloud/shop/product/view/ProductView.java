package com.nowui.cloud.shop.product.view;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

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

    @Id
    private String productId;
    public static final String PRODUCT_ID = "productId";

    @Field
    private String appId;
    public static final String APP_ID = "appId";

    @Field
    private String productName;
    public static final String PRODUCT_NAME = "productName";

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

}
