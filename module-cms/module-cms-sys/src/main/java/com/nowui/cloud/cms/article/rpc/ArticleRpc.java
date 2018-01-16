package com.nowui.cloud.cms.article.rpc;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.cms.article.entity.Article;

/**
 * 文章服务调用
 * 
 * @author marcus
 *
 * 2018年1月15日
 */
@Component(value = "articleRpc")
@FeignClient(name = "module-cms")
public interface ArticleRpc {
    
    /**
     * 根据文章分类编码查询主分类文章信息按排序字段排序
     * 
     * @param appId 应用编号
     * @param articleCategoryCode 文章分类编码
     * @return List<Article> 文章信息列表
     */
    @RequestMapping(value = "/article/system/v1/list/by/category/code", method = RequestMethod.POST)
    List<Article> listByCategoryCodeV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "articleCategoryCode", required = true) String articleCategoryCode
    );

}
