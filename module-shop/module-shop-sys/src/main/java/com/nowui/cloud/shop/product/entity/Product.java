package com.nowui.cloud.shop.product.entity;

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
 * @author ZhongYongQiang
 */
@Component
@Document(indexName = "nowui", type = "product_info")
@TableName(value = "product_info")
public class Product extends BaseEntity {

    /**
     * 商品编号
     */
    @Id
    @TableId
    @NotNull(message = "商品编号不能为空")
    @Length(max = 32, message = "商品编号长度超出限制")
    private String productId;
    public static final String PRODUCT_ID = "productId";

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
     * 商品名称
     */
    @Field
    @TableField
    @NotNull(message = "商品名称不能为空")
    @Length(max = 100, message = "商品名称长度超出限制")
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
