package com.nowui.cloud.shop.product.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.repository.ProductRepository;
import com.nowui.cloud.shop.product.router.ProductRouter;
import com.nowui.cloud.shop.product.service.ProductService;
import com.nowui.cloud.shop.product.view.ProductView;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
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

    @Autowired
    private ProductRepository productRepository;

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

        Criteria criteria = Criteria.where(ProductView.SYSTEM_STATUS).is(true);

        long resultTotal = productRepository.count(criteria);
        List<ProductView> resultList = productRepository.list(criteria);

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

        ProductView result = productRepository.find(product.getProductId());

        validateResponse(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);

        return renderJson(result);
    }

    @ApiOperation(value = "商品新增")
    @RequestMapping(value = "/product/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Product product = getEntry(Product.class);

        String productId = Util.getRandomUUID();

        validateRequest(product, Product.APP_ID, Product.PRODUCT_NAME);

        Boolean result = productService.save(product, productId, product.getAppId(), ProductRouter.PRODUCT_V1_SAVE, product.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "商品修改")
    @RequestMapping(value = "/product/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Product product = getEntry(Product.class);

        validateRequest(product, Product.APP_ID, Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.SYSTEM_VERSION);

        Boolean result = productService.update(product, product.getProductId(), product.getAppId(), ProductRouter.PRODUCT_V1_UPDATE, product.getSystemRequestUserId(), product.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "商品删除")
    @RequestMapping(value = "/product/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Product product = getEntry(Product.class);

        validateRequest(product, Product.PRODUCT_ID, Product.SYSTEM_VERSION);

        Boolean result = productService.delete(product.getProductId(), product.getAppId(), ProductRouter.PRODUCT_V1_DELETE, product.getSystemRequestUserId(), product.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "商品更新")
    @RequestMapping(value = "/product/admin/v1/replace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        Product product = getEntry(Product.class);

        validateRequest(product, Product.PRODUCT_ID);

        productService.replace(product.getProductId());

        return renderJson(true);
    }

}