package com.nowui.cloud.cms.navigation.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.rpc.NavigationRpc;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;

/**
 * 导航栏系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "导航栏", description = "导航栏系统端接口管理")
@RestController
public class NavigationSystemController implements NavigationRpc {
    
    @Autowired
    private NavigationService navigationService;

    @Override
    public List<Navigation> listByCategoryCodeV1(String appId, String navigationCategoryCode) {
        
        List<Navigation> navigationList = navigationService.listByCategoryCode(appId, navigationCategoryCode);
       
        if (Util.isNullOrEmpty(navigationList)) {
            return new ArrayList<>();
        }
        
        String fileIds = Util.beanToFieldString(navigationList, Navigation.NAVIGATION_IMAGE);
        
//        List<File> fileList = fileRpc.findsV1(fileIds);
        
//        navigationList = Util.beanAddField(navigationList, Navigation.NAVIGATION_IMAGE, fileList, File.FILE_PATH);
        
        return navigationList;
    }

}