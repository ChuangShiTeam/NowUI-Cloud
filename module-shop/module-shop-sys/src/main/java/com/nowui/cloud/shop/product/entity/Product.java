package com.nowui.cloud.shop.product.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;
import org.springframework.stereotype.Component;

/**
 * 产品
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Component
@TableName(value = "product_info")
public class Product extends BaseEntity {

    /**
     * 商品编号
     */
    @TableId
    @TableField
    private String productId;

    /**
     * 应用编号
     */
    @TableField
    private String appId;

    /**
     * 商品名称
     */
    @TableField
    private String productName;

    /**
     * 商品分类
     */
    @TableField
    private String productCategoryId;

    /**
     * 商品图片
     */
    @TableField
    private String productImageFileId;

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

    public String getProductImageFileId() {
        return productImageFileId;
    }

    public void setProductImageFileId(String productImageFileId) {
        this.productImageFileId = productImageFileId;
    }
}
