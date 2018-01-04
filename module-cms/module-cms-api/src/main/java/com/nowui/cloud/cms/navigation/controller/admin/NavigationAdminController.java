package com.nowui.cloud.cms.navigation.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.cms.navigation.service.NavigationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/navigation/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.APP_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.PAGE_INDEX,
                Navigation.PAGE_SIZE
        );

        Integer resultTotal = navigationService.adminCount(body.getAppId() , body.getNavigationCategoryCode(), body.getNavigationCode(), body.getNavigationName());
        List<Navigation> resultList = navigationService.adminList(body.getAppId(), body.getNavigationCategoryCode(), body.getNavigationCode(), body.getNavigationName(), body.getM(), body.getN());
        
        validateResponse(
                Navigation.NAVIGATION_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.NAVIGATION_IMAGE,
                Navigation.NAVIGATION_URL,
                Navigation.NAVIGATION_POSITION,
                Navigation.NAVIGATION_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "导航栏信息")
    @RequestMapping(value = "/navigation/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.APP_ID,
                Navigation.NAVIGATION_ID
        );

        Navigation result = navigationService.find(body.getNavigationId());

        File file = fileRpc.find(result.getNavigationImage());
        file.keep(File.FILE_ID, File.FILE_PATH);
        result.put(Navigation.NAVIGATION_IMAGE, file);
        
        validateResponse(
                Navigation.NAVIGATION_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.NAVIGATION_IMAGE,
                Navigation.NAVIGATION_URL,
                Navigation.NAVIGATION_POSITION,
                Navigation.NAVIGATION_SORT
        );
        
        return renderJson(result);
    }

    @ApiOperation(value = "新增导航栏")
    @RequestMapping(value = "/navigation/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.APP_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.NAVIGATION_IMAGE,
                Navigation.NAVIGATION_URL,
                Navigation.NAVIGATION_POSITION,
                Navigation.NAVIGATION_SORT
        );

        Boolean result = navigationService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改导航栏")
    @RequestMapping(value = "/navigation/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.NAVIGATION_ID,
                Navigation.APP_ID,
                Navigation.NAVIGATION_CATEGORY_CODE,
                Navigation.NAVIGATION_CODE,
                Navigation.NAVIGATION_NAME,
                Navigation.NAVIGATION_IMAGE,
                Navigation.NAVIGATION_URL,
                Navigation.NAVIGATION_POSITION,
                Navigation.NAVIGATION_SORT,
                Navigation.SYSTEM_VERSION
        );

        Boolean result = navigationService.update(body, body.getNavigationId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除导航栏")
    @RequestMapping(value = "/navigation/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Navigation body) {
        validateRequest(
                body,
                Navigation.NAVIGATION_ID,
                Navigation.APP_ID,
                Navigation.SYSTEM_VERSION
        );

        Boolean result = navigationService.delete(body.getNavigationId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}