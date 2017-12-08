package com.nowui.cloud.shop.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author ZhongYongQiang
 */
@TableName(value = "productInfo")
public class Product {

    /**
     * 商品编号
     */
    @TableId
    private String productId;

    /**
     * 商品名称
     */
    @TableField
    private String productName;

    public String getProductId() {
        return productId;
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
