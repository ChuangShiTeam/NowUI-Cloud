package com.nowui.cloud.cms.article.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.article.entity.ArticleMedia;
import com.nowui.cloud.cms.article.rpc.ArticleMediaRpc;
import com.nowui.cloud.cms.article.service.ArticleMediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章多媒体系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章多媒体", description = "文章多媒体系统端接口管理")
@RestController
public class ArticleMediaSystemController implements ArticleMediaRpc {

}