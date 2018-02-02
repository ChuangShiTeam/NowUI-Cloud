package com.nowui.cloud.shop.product.controller.system;

import com.nowui.cloud.shop.product.rpc.ProductRpc;
import com.nowui.cloud.shop.product.service.ProductService;
import io.swagger.annotations.Api;
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
}