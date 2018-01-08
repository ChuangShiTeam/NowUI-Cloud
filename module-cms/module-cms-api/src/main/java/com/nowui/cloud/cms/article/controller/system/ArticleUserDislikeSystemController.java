package com.nowui.cloud.cms.article.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.article.entity.ArticleUserDislike;
import com.nowui.cloud.cms.article.rpc.ArticleUserDislikeRpc;
import com.nowui.cloud.cms.article.service.ArticleUserDislikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章用户鄙视系统端控制器
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Api(value = "文章用户鄙视", description = "文章用户鄙视系统端接口管理")
@RestController
public class ArticleUserDislikeSystemController implements ArticleUserDislikeRpc {

}