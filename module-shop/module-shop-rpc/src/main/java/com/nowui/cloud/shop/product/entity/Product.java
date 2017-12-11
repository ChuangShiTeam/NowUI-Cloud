package com.nowui.cloud.shop.product.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

/**
 * @author ZhongYongQiang
 */
@TableName(value = "productInfo")
public class Product extends BaseEntity {

    /**
     * 商品编号
     */
    @TableId
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
