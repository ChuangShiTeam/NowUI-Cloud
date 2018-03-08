package com.nowui.cloud.shop.product.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.service.ProductService;
import com.nowui.cloud.shop.product.view.ProductView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.*;
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
        Product productEntity = getEntry(Product.class);

        validateRequest(
                productEntity,
                ProductView.APP_ID,
                ProductView.PRODUCT_NAME,
                ProductView.PAGE_INDEX,
                ProductView.PAGE_SIZE
        );

        Integer resultTotal = productService.countForAdmin(productEntity.getAppId(), productEntity.getProductName());
        List<ProductView> resultList = productService.listForAdmin(productEntity.getAppId(), productEntity.getProductName(), productEntity.getPageIndex(), productEntity.getPageSize());

        validateResponse(
                ProductView.PRODUCT_ID,
                ProductView.PRODUCT_NAME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "商品信息")
    @RequestMapping(value = "/product/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Product productEntity = getEntry(Product.class);

        validateRequest(
                productEntity,
                ProductView.APP_ID,
                ProductView.PRODUCT_ID
        );

        ProductView result = productService.find(productEntity.getProductId());

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
        Product productEntity = getEntry(Product.class);

        validateRequest(
                productEntity,
                Product.APP_ID,
                Product.PRODUCT_NAME,
                Product.PRODUCT_CATEGORY_ID,
                Product.PRODUCT_IMAGE_ID
        );

        ProductView productView = getEntry(ProductView.class);

        validateRequest(
                productView,
                ProductView.PRODUCT_CATEGORY_NAME,
                ProductView.PRODUCT_IMAGE_PATH
        );

        String productId = Util.getRandomUUID();

        Product result = productService.save(productEntity, productId, productEntity.getSystemRequestUserId());

        if (result != null) {
            productView = new ProductView();
            productView.putAll(result);

            productService.save(productView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "商品修改")
    @RequestMapping(value = "/product/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Product productEntity = getEntry(Product.class);

        validateRequest(
                productEntity,
                Product.APP_ID,
                Product.PRODUCT_ID,
                Product.PRODUCT_NAME,
                Product.PRODUCT_CATEGORY_ID,
                Product.PRODUCT_IMAGE_ID,
                ProductView.SYSTEM_VERSION
        );

        ProductView productView = getEntry(ProductView.class);

        validateRequest(
                productView,
                ProductView.PRODUCT_CATEGORY_NAME,
                ProductView.PRODUCT_IMAGE_PATH
        );

        Product result = productService.update(productEntity, productEntity.getProductId(), productEntity.getSystemRequestUserId(), productEntity.getSystemVersion());

        if (result != null) {
            productView = new ProductView();
            productView.putAll(result);

            productService.update(productView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "商品删除")
    @RequestMapping(value = "/product/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Product productEntity = getEntry(Product.class);

        validateRequest(
                productEntity,
                Product.PRODUCT_ID,
                Product.SYSTEM_VERSION
        );

        Product result = productService.delete(productEntity.getProductId(), productEntity.getSystemRequestUserId(), productEntity.getSystemVersion());

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