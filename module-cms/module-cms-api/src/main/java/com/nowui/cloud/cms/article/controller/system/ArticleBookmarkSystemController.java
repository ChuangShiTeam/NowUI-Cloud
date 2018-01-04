package com.nowui.cloud.cms.article.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.article.entity.ArticleBookmark;
import com.nowui.cloud.cms.article.rpc.ArticleBookmarkRpc;
import com.nowui.cloud.cms.article.service.ArticleBookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章收藏系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章收藏", description = "文章收藏系统端接口管理")
@RestController
public class ArticleBookmarkSystemController implements ArticleBookmarkRpc {

}