package com.nowui.cloud.cms.article.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.cms.article.entity.ArticleComment;
import com.nowui.cloud.cms.article.rpc.ArticleCommentRpc;
import com.nowui.cloud.cms.article.service.ArticleCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章评论系统端控制器
 *
 * @author xupengfei
 *
 * 2018-01-03
 */
@Api(value = "文章评论", description = "文章评论系统端接口管理")
@RestController
public class ArticleCommentSystemController implements ArticleCommentRpc {

}