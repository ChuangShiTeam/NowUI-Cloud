package com.nowui.cloud.cms.article.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.article.entity.ArticleArticleCategory;
import com.nowui.cloud.cms.article.rpc.ArticleArticleCategoryRpc;
import com.nowui.cloud.cms.article.service.ArticleArticleCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章文章分类系统端控制器
 *
 * @author marcus
 *
 * 2018-01-03
 */
@Api(value = "文章文章分类", description = "文章文章分类系统端接口管理")
@RestController
public class ArticleArticleCategorySystemController implements ArticleArticleCategoryRpc {

}