package com.nowui.cloud.shop.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author ZhongYongQiang
 */
@TableName(value = "productInfo")
public class Product extends BaseEntity {

    /**
     * 商品编号
     */
    @TableId
    @NotNull(message = "productId must not be null")
    @Length(max = 32, message = "productId length must not exceed 32")
    private String productId;

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "appId must not be null")
    @Length(max = 32, message = "appId length must not exceed 32")
    private String appId;

    /**
     * 商品名称
     */
    @TableField
    @NotNull(message = "productName must not be null")
    @Length(max = 32, message = "productName length must not exceed 100")
    private String productName;

    public String getProductId() {
        return productId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
