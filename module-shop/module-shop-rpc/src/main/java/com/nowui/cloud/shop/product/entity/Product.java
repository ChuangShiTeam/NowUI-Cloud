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
    @NotNull(message = "商品编号不能为空")
    @Length(max = 32, message = "商品编号字数超出限制")
    private String productId;

    /**
     * 应用编号
     */
    @TableField
    @NotNull(message = "应用编号不能为空")
    @Length(max = 32, message = "应用编号字数超出限制")
    private String appId;

    /**
     * 商品名称
     */
    @TableField
    @NotNull(message = "商品名称不能为空")
    @Length(max = 100, message = "商品名称字数超出限制")
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
