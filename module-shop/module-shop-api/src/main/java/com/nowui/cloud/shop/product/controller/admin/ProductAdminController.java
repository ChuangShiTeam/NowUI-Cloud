package com.nowui.cloud.shop.product.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.service.ProductService;
import com.nowui.cloud.shop.product.view.ProductView;
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
    public Map<String, Object> listV1() {
        Product product = getEntry(Product.class);

        validateRequest(
                product,
                Product.APP_ID,
                Product.PRODUCT_NAME,
                Product.PAGE_INDEX,
                Product.PAGE_SIZE
        );

        Integer resultTotal = productService.countForAdmin(product.getAppId(), product.getProductName());
        List<ProductView> resultList = productService.listForAdmin(product.getAppId(), product.getProductName(), product.getPageIndex(), product.getPageSize());

        validateResponse(
                ProductView.PRODUCT_ID,
                ProductView.PRODUCT_NAME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "商品信息")
    @RequestMapping(value = "/product/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Product product = getEntry(Product.class);

        validateRequest(product, Product.APP_ID, Product.PRODUCT_ID);

        ProductView result = productService.find(product.getProductId());

        validateResponse(
                ProductView.PRODUCT_ID,
                ProductView.PRODUCT_NAME,
                ProductView.PRODUCT_CATEGORY_ID,
                ProductView.PRODUCT_CATEGORY_NAME,
                ProductView.PRODUCT_IMAGE_ID,
                ProductView.PRODUCT_IMAGE_PATH,
                ProductView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "商品新增")
    @RequestMapping(value = "/product/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Product product = getEntry(Product.class);

        System.out.println(product.toJSONString());

        validateRequest(product, Product.APP_ID, Product.PRODUCT_NAME);

        String productId = Util.getRandomUUID();

        Product result = productService.save(product, productId, product.getSystemRequestUserId());

        if (result != null) {
            ProductView productView = new ProductView();
            productView.putAll(result);

            productService.save(productView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "商品修改")
    @RequestMapping(value = "/product/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Product product = getEntry(Product.class);

        validateRequest(product, Product.APP_ID, Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);

        Product result = productService.update(product, product.getProductId(), product.getSystemRequestUserId(), product.getSystemVersion());

        if (result != null) {
            ProductView productView = new ProductView();
            productView.putAll(result);

            productService.update(productView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "商品删除")
    @RequestMapping(value = "/product/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Product product = getEntry(Product.class);

        validateRequest(product, Product.PRODUCT_ID, Product.SYSTEM_VERSION);

        Product result = productService.delete(product.getProductId(), product.getSystemRequestUserId(), product.getSystemVersion());

        if (result != null) {
            ProductView productView = new ProductView();
            productView.putAll(result);

            productService.update(productView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "商品数据同步")
    @RequestMapping(value = "/product/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        List<Product> productList = productService.listByMysql();

        for (Product product : productList) {
            ProductView productView = new ProductView();
            productView.putAll(product);

            productService.saveOrUpdate(productView);
        }

        return renderJson(true);
    }

}