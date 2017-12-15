package com.nowui.cloud.shop.product.controller.admin;
import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ZhongYongQiang
 */
@Api(value = "商品", description = "商品管理")
@RestController
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "商品列表")
    @RequestMapping(value = "/product/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Product body) {
        validateRequest(body, "appId", "productName", "pageIndex", "pageSize");

        Integer resultTotal = productService.adminCount(body.getAppId(), body.getProductName());
        List<Product> resultList = productService.adminList(body.getAppId(), body.getProductName(), body.getM(), body.getN());

        validateResponse("productId");

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "商品新增")
    @RequestMapping(value = "/product/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Product body) {
        validateRequest(body, "appId", "productName");

        Boolean result = productService.save(body);

        return renderJson(result);
    }

}