package com.nowui.cloud.cms.article.controller.desktop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.article.service.ArticleService;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;

/**
 * 文章桌面端控制器
 * 
 * @author marcus
 *
 * 2017年12月26日
 */
@Api(value = "文章", description = "文章接口桌面端管理")
@RestController
public class ArticleDesktopController extends BaseController {
    
    @Autowired
    private ArticleService articleService;

}