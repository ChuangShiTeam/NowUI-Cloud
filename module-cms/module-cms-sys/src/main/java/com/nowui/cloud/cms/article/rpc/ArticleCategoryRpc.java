package com.nowui.cloud.cms.article.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 文章分类服务调用
 *
 * @author ZhongYongQiang
 *
 * 2018-03-15
 */
@Component(value = "articleCategoryRpc")
@FeignClient(name = "module-cms")
public interface ArticleCategoryRpc {

}