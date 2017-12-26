package com.nowui.cloud.cms.article.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.article.service.ArticleCategoryService;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;

/**
 * 文章分类移动端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "文章分类", description = "文章分类移动端接口管理")
@RestController
public class ArticleCategoryMobileController extends BaseController {
    
    @Autowired
    private ArticleCategoryService articleCategoryService;

}
