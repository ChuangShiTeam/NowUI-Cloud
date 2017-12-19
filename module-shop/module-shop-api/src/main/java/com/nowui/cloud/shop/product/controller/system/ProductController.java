package com.nowui.cloud.shop.product.controller.system;

import com.nowui.cloud.shop.product.rpc.ProductRpc;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhongYongQiang
 */
@Api(value = "商品", description = "商品后台接口管理")
@RestController(value = "productSystemController")
public class ProductController implements ProductRpc {

}