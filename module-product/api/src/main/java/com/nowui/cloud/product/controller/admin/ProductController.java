package com.nowui.cloud.product.controller.admin;

import com.nowui.cloud.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController extends BaseController {

    @ApiOperation(value = "创建用户")
    @RequestMapping(value = "/hello", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String home(@RequestBody String body) {
        System.out.println(body);
        return "{\"aaa\":\"333\"}";
    }

}