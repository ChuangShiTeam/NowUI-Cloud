package com.nowui.cloud.shop.product.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.rpc.ProductRpc;
import com.nowui.cloud.shop.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhongYongQiang
 */
@Api(value = "商品", description = "商品系统端接口管理")
@RestController
public class ProductSystemController implements ProductRpc {

    @Autowired
    private ProductService productService;

    @Override
    @ApiOperation(value = "商品信息")
    public Product find(String productId) {
        Product product = productService.find(productId);
        System.out.println(JSON.toJSONString(product));
        return product;
    }
}