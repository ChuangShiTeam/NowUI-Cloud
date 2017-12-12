package com.nowui.cloud.shop.product.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhongYongQiang
 */
@RestController
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "商品列表")
    @RequestMapping(value = "/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> adminList(@RequestBody Product entity) {
        validateRequest(entity, "productName");

        String appId = "11";
        String productName = "111";
        Integer pageIndex = 1;
        Integer pageSize = 10;

        Integer resultTotal = productService.adminCount(appId, productName);
        List<Product> resultList = productService.adminList(appId, productName, pageIndex, pageSize);
        for (Product product : resultList) {
            System.out.println(product.getProductId());
        }

        validateResponse("productId");

        return renderJson(resultTotal, resultList);
    }

}