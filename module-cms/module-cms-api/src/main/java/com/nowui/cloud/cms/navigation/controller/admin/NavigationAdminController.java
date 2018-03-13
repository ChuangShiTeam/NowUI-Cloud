package com.nowui.cloud.cms.navigation.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import com.nowui.cloud.cms.navigation.view.NavigationView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 导航栏管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "导航栏", description = "导航栏管理端接口管理")
@RestController
public class NavigationAdminController extends BaseController {

    @Autowired
    private NavigationService navigationService;
    
    @Autowired
    private FileRpc fileRpc;
    
    @ApiOperation(value = "导航栏列表")
    @RequestMapping(value = "/navigation/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.APP_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.PAGE_INDEX,
                Navigation.PAGE_SIZE
        );

        Integer resultTotal = navigationService.countForAdmin(body.getAppId() , body.getNavigationCategoryCode(), body.getNavigationCode(), body.getNavigationName());
        List<NavigationView> resultList = navigationService.listForAdmin(body.getAppId(), body.getNavigationCategoryCode(), body.getNavigationCode(), body.getNavigationName(), body.getM(), body.getN());
        
//        String fileIds = Util.beanToFieldString(resultList, Navigation.NAVIGATION_IMAGE_FILE_ID);
//        List<File> fileList = fileRpc.findsV1(fileIds);
        
//        resultList = Util.beanAddField(resultList, Navigation.NAVIGATION_IMAGE, fileList, File.FILE_PATH);
        
        validateResponse(
                Navigation.NAVIGATION_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.NAVIGATION_IMAGE_FILE_ID,
                Navigation.NAVIGATION_IMAGE_FILE_PATH,
                Navigation.NAVIGATION_URL,
                Navigation.NAVIGATION_POSITION,
                Navigation.NAVIGATION_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "导航栏信息")
    @RequestMapping(value = "/navigation/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.APP_ID,
                Navigation.NAVIGATION_ID
        );

        NavigationView result = navigationService.find(body.getNavigationId());

//        File file = fileRpc.findV1(result.getNavigationImage());
//        file.keep(File.FILE_ID, File.FILE_PATH);
//        result.put(Navigation.NAVIGATION_IMAGE, file);
        
        validateResponse(
                Navigation.NAVIGATION_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.NAVIGATION_IMAGE_FILE_ID,
                Navigation.NAVIGATION_IMAGE_FILE_PATH,
                Navigation.NAVIGATION_URL,
                Navigation.NAVIGATION_POSITION,
                Navigation.NAVIGATION_SORT,
                Navigation.SYSTEM_VERSION
        );
        
        return renderJson(result);
    }

    @ApiOperation(value = "新增导航栏")
    @RequestMapping(value = "/navigation/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.APP_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.NAVIGATION_IMAGE_FILE_ID,
                Navigation.NAVIGATION_IMAGE_FILE_PATH,
                Navigation.NAVIGATION_URL,
                Navigation.NAVIGATION_POSITION,
                Navigation.NAVIGATION_SORT
        );

        Navigation result = navigationService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        Boolean success = false;
        
        if (result != null) {
           
        	// 保存导航栏视图信息到MongoDB
        	NavigationView navigationView = new NavigationView();
            
        	navigationView.putAll(result);
            
        	navigationService.save(navigationView);
        	
            success = true;
        }
        return renderJson(success);
    }

    @ApiOperation(value = "修改导航栏")
    @RequestMapping(value = "/navigation/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.NAVIGATION_ID,
                Navigation.APP_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.NAVIGATION_IMAGE_FILE_ID,
                Navigation.NAVIGATION_IMAGE_FILE_PATH,
                Navigation.NAVIGATION_URL,
                Navigation.NAVIGATION_POSITION,
                Navigation.NAVIGATION_SORT,
                Navigation.SYSTEM_VERSION
        );

        Navigation result = navigationService.update(body, body.getNavigationId(), body.getSystemRequestUserId(), body.getSystemVersion());

        Boolean success = false;
        
        if (result != null) {
        	
        	// 更新导航栏视图信息到MongoDB
        	NavigationView navigationView = new NavigationView();
            
        	navigationView.putAll(result);
            
        	navigationService.update(navigationView);
           
            success = true;
        }
        return renderJson(success);
    }

    @ApiOperation(value = "删除导航栏")
    @RequestMapping(value = "/navigation/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.NAVIGATION_ID,
                Navigation.APP_ID,
                Navigation.SYSTEM_VERSION
        );

        Navigation result = navigationService.delete(body.getNavigationId(), body.getSystemRequestUserId(), body.getSystemVersion());

        Boolean success = false;
        
        if (result != null) {
        	
        	// 删除MongoDB导航栏视图信息
        	NavigationView navigationView = new NavigationView();
            
        	navigationView.putAll(result);
            
        	navigationService.delete(navigationView);
           
            success = true;
        }
        return renderJson(success);
    }
    
    @ApiOperation(value = "工具栏数据同步")
    @RequestMapping(value = "/navigation/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        List<Navigation> navigationList = navigationService.listByMysql();

        for(Navigation navigation : navigationList) {
            NavigationView navigationView = new NavigationView();
            navigationView.putAll(navigation);
            
            File navigationImageFile = fileRpc.findByMysqlV1(navigationView.getNavigationImageFileId());
            
            navigationView.setNavigationImageFilePath(navigationImageFile.getFilePath());

            navigationService.saveOrUpdate(navigationView);
        }

        return renderJson(true);
    }

}