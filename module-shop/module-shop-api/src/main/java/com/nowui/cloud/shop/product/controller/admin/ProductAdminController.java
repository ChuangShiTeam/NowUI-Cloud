package com.nowui.cloud.shop.product.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.service.ProductService;
import com.nowui.cloud.util.Util;

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
@Api(value = "商品", description = "商品后台端接口管理")
@RestController
public class ProductAdminController extends BaseController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "商品列表")
    @RequestMapping(value = "/product/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Product body) {
        validateRequest(
                body,
                Product.APP_ID,
                Product.PRODUCT_NAME,
                Product.PAGE_INDEX,
                Product.PAGE_SIZE
        );

        Integer resultTotal = productService.countForAdmin(body.getAppId(), body.getProductName());
        List<Product> resultList = productService.listForAdmin(body.getAppId(), body.getProductName(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                Product.PRODUCT_ID,
                Product.PRODUCT_NAME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "商品信息")
    @RequestMapping(value = "/product/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Product body) {
        validateRequest(body, Product.APP_ID, Product.PRODUCT_ID);

        Product result = productService.find(body.getProductId());

        validateResponse(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);

        return renderJson(result);
    }

    @ApiOperation(value = "商品新增")
    @RequestMapping(value = "/product/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Product body) {
        validateRequest(body, Product.APP_ID, Product.PRODUCT_NAME);

        Boolean result = productService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "商品修改")
    @RequestMapping(value = "/product/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Product body) {
        validateRequest(body, Product.APP_ID, Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);

        Boolean result = productService.update(body, body.getProductId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "商品删除")
    @RequestMapping(value = "/product/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Product body) {
        validateRequest(body, Product.PRODUCT_ID, Product.SYSTEM_VERSION);

        Boolean result = productService.delete(body.getProductId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "商品更新")
    @RequestMapping(value = "/product/admin/replace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replace(@RequestBody Product body) {
        validateRequest(body, Product.PRODUCT_ID);

        productService.replace(body.getProductId());

        return renderJson(true);
    }

}