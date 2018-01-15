package com.nowui.cloud.cms.navigation.rpc;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.cms.navigation.entity.Navigation;

/**
 * 导航栏服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "navigationRpc")
@FeignClient(name = "module-cms")
public interface NavigationRpc {
    
    /**
     * 根据导航栏分类编码查询导航栏列表信息
     * 
     * @param appId 应用编号
     * @param navigationCategoryCode 导航栏分类编码
     * @return List<Navigation> 导航栏信息列表
     */
    @RequestMapping(value = "/navigation/system/v1/list/by/category/code", method = RequestMethod.POST)
    List<Navigation> listByCategoryCodeV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "navigationCategoryCode", required = true) String navigationCategoryCode
    );

}