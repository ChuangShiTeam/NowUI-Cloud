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
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation(value = "商品列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ProductView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_NAME, value = "商品名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = ProductView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/product/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore ProductView productView) {

        validateRequest(
                productView,
                ProductView.APP_ID,
                ProductView.PRODUCT_NAME,
                ProductView.PAGE_INDEX,
                ProductView.PAGE_SIZE
        );

        Integer resultTotal = productService.countForAdmin(productView.getAppId(), productView.getProductName());
        List<ProductView> resultList = productService.listForAdmin(productView.getAppId(), productView.getProductName(), productView.getPageIndex(), productView.getPageSize());

        validateResponse(
                ProductView.PRODUCT_ID,
                ProductView.PRODUCT_NAME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "商品信息", httpMethod = "POST")
    @RequestMapping(value = "/product/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = ProductView.PRODUCT_ID, value = "商品编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
    })
    public Map<String, Object> findV1(@ApiIgnore ProductView productView) {

        validateRequest(
                productView,
                ProductView.PRODUCT_ID,
                ProductView.APP_ID
        );

        ProductView result = productService.find(productView.getProductId(), productView.getAppId());

        validateResponse(
                ProductView.PRODUCT_ID,
                ProductView.PRODUCT_NAME,
                ProductView.PRODUCT_CATEGORY_ID,
                ProductView.PRODUCT_CATEGORY_NAME,
                ProductView.PRODUCT_IMAGE_FILE_ID,
                ProductView.PRODUCT_IMAGE_FILE_PATH,
                ProductView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "商品新增", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ProductView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_NAME, value = "商品名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_CATEGORY_ID, value = "商品分类", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_CATEGORY_NAME, value = "商品分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_IMAGE_FILE_ID, value = "商品图片编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_IMAGE_FILE_PATH, value = "商品图片路径", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/product/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore Product product, @ApiIgnore ProductView productView) {

        validateRequest(
                productView,
                ProductView.APP_ID,
                ProductView.PRODUCT_NAME,
                ProductView.PRODUCT_CATEGORY_ID,
                ProductView.PRODUCT_CATEGORY_NAME,
                ProductView.PRODUCT_IMAGE_FILE_ID,
                ProductView.PRODUCT_IMAGE_FILE_PATH
        );

        String productId = Util.getRandomUUID();

        Product result = productService.save(product, productId, productView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            productView.putEntry(result);

            productService.save(productView);

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "商品修改", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ProductView.PRODUCT_ID, value = "商品编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_NAME, value = "商品名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_CATEGORY_ID, value = "商品分类", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_CATEGORY_NAME, value = "商品分类名称", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_IMAGE_FILE_ID, value = "商品图片编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.PRODUCT_IMAGE_FILE_PATH, value = "商品图片路径", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/product/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore Product product, @ApiIgnore ProductView productView) {

        validateRequest(
                productView,
                ProductView.PRODUCT_ID,
                ProductView.APP_ID,
                ProductView.PRODUCT_NAME,
                ProductView.PRODUCT_CATEGORY_ID,
                ProductView.PRODUCT_CATEGORY_NAME,
                ProductView.PRODUCT_IMAGE_FILE_ID,
                ProductView.PRODUCT_IMAGE_FILE_PATH,
                ProductView.SYSTEM_VERSION
        );

        Product result = productService.update(product, productView.getProductId(), productView.getSystemRequestUserId(), productView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            productView.putEntry(result);

            productService.update(productView, productView.getProductId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "商品删除", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = ProductView.PRODUCT_ID, value = "商品编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = ProductView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/product/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore ProductView productView) {

        validateRequest(
                productView,
                ProductView.PRODUCT_ID,
                ProductView.SYSTEM_VERSION
        );

        Product result = productService.delete(productView.getProductId(), productView.getSystemRequestUserId(), productView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            productView.putEntry(result);

            productService.delete(productView, productView.getProductId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "商品数据同步", httpMethod = "POST")
    @RequestMapping(value = "/product/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        List<Product> productList = productService.listByMysql();

        for (Product product : productList) {
            ProductView productView = new ProductView();
            productView.putEntry(product);

            productService.saveOrUpdate(productView, productView.getProductId());
        }

        return renderJson(true);
    }

}