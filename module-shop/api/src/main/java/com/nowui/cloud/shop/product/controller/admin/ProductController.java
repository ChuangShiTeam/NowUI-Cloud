package com.nowui.cloud.shop.product.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.shop.product.entity.Product;
import com.nowui.cloud.shop.product.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author ZhongYongQiang
 */
@RestController
public class ProductController extends BaseController{
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "admin list")
    @RequestMapping(value = "/admin/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String home(@RequestBody String body){
        validateRequest();
        String appId = "";
        String productName = "";
        Integer pageIndex = 0;
        Integer pageSize = 0;

        Integer resultCount = productService.adminCount(appId, productName);
        List<Product> resultList = productService.adminList(appId, productName, pageIndex, pageSize);

        validateResponse();

        return "{\"aaa\":\"333\"}";
    }

}