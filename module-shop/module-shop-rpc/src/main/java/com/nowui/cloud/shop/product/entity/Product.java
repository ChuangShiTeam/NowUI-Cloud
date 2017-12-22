package com.nowui.cloud.shop.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author ZhongYongQiang
 */
@Component
@TableName(value = "product_info")
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
    private String productName = "productName";

    public String getProductId() {
        return get("productId").toString();
    }

    public void setProductId(String productId) {
        put("productId", productId);
    }

    public String getAppId() {
        return get("appId").toString();
    }

    public void setAppId(String appId) {
        put("appId", appId);
    }

    public String getProductName() {
        return get("productName").toString();
    }

    public void setProductName(String productName) {
        put("productName", productName);
    }

}
