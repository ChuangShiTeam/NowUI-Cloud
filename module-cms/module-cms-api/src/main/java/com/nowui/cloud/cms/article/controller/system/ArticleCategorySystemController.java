package com.nowui.cloud.cms.article.controller.system;

import com.nowui.cloud.cms.article.rpc.ArticleCategoryRpc;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章分类系统端控制器
 *
 * @author ZhongYongQiang
 *
 * 2018-03-15
 */
@Api(value = "文章分类", description = "文章分类系统端接口管理")
@RestController
public class ArticleCategorySystemController implements ArticleCategoryRpc {

}