package com.nowui.cloud.cms.navigationBar.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.cms.navigationBar.entity.NavigationBar;
import com.nowui.cloud.cms.navigationBar.service.NavigationBarService;
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
 * @author shawn
 *
 * 2017-12-31
 */
@Api(value = "导航栏", description = "导航栏管理端接口管理")
@RestController
public class NavigationBarAdminController extends BaseController {

    @Autowired
    private NavigationBarService navigationBarService;

    @ApiOperation(value = "导航栏列表")
    @RequestMapping(value = "/navigation/bar/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody NavigationBar body) {
        validateRequest(
                body,
                NavigationBar.APP_ID,
                NavigationBar.NAVIGATION_BAR_CATEGORY_CODE,
                NavigationBar.NAVIGATION_BAR_CODE,
                NavigationBar.NAVIGATION_BAR_NAME,
                NavigationBar.PAGE_INDEX,
                NavigationBar.PAGE_SIZE
        );

        Integer resultTotal = navigationBarService.adminCount(body.getAppId() , body.getNavigationBarCategoryCode(), body.getNavigationBarCode(), body.getNavigationBarName());
        List<NavigationBar> resultList = navigationBarService.adminList(body.getAppId(), body.getNavigationBarCategoryCode(), body.getNavigationBarCode(), body.getNavigationBarName(), body.getM(), body.getN());

        validateResponse(
                NavigationBar.NAVIGATION_BAR_ID,
                NavigationBar.NAVIGATION_BAR_CATEGORY_CODE,
                NavigationBar.NAVIGATION_BAR_CODE,
                NavigationBar.NAVIGATION_BAR_NAME,
                NavigationBar.NAVIGATION_BAR_IMAGE,
                NavigationBar.NAVIGATION_BAR_URL,
                NavigationBar.NAVIGATION_BAR_POSITION,
                NavigationBar.NAVIGATION_BAR_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "导航栏信息")
    @RequestMapping(value = "/navigation/bar/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody NavigationBar body) {
        validateRequest(
                body,
                NavigationBar.APP_ID,
                NavigationBar.NAVIGATION_BAR_ID
        );

        NavigationBar result = navigationBarService.find(body.getNavigationBarId());

        validateResponse(
                NavigationBar.NAVIGATION_BAR_ID,
                NavigationBar.NAVIGATION_BAR_CATEGORY_CODE,
                NavigationBar.NAVIGATION_BAR_CODE,
                NavigationBar.NAVIGATION_BAR_NAME,
                NavigationBar.NAVIGATION_BAR_IMAGE,
                NavigationBar.NAVIGATION_BAR_URL,
                NavigationBar.NAVIGATION_BAR_POSITION,
                NavigationBar.NAVIGATION_BAR_SORT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增导航栏")
    @RequestMapping(value = "/navigation/bar/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody NavigationBar body) {
        validateRequest(
                body,
                NavigationBar.APP_ID,
                NavigationBar.NAVIGATION_BAR_CATEGORY_CODE,
                NavigationBar.NAVIGATION_BAR_CODE,
                NavigationBar.NAVIGATION_BAR_NAME,
                NavigationBar.NAVIGATION_BAR_IMAGE,
                NavigationBar.NAVIGATION_BAR_URL,
                NavigationBar.NAVIGATION_BAR_POSITION,
                NavigationBar.NAVIGATION_BAR_SORT
        );

        Boolean result = navigationBarService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改导航栏")
    @RequestMapping(value = "/navigation/bar/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody NavigationBar body) {
        validateRequest(
                body,
                NavigationBar.NAVIGATION_BAR_ID,
                NavigationBar.APP_ID,
                NavigationBar.NAVIGATION_BAR_CATEGORY_CODE,
                NavigationBar.NAVIGATION_BAR_CODE,
                NavigationBar.NAVIGATION_BAR_NAME,
                NavigationBar.NAVIGATION_BAR_IMAGE,
                NavigationBar.NAVIGATION_BAR_URL,
                NavigationBar.NAVIGATION_BAR_POSITION,
                NavigationBar.NAVIGATION_BAR_SORT,
                NavigationBar.SYSTEM_VERSION
        );

        Boolean result = navigationBarService.update(body, body.getNavigationBarId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除导航栏")
    @RequestMapping(value = "/navigation/bar/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody NavigationBar body) {
        validateRequest(
                body,
                NavigationBar.NAVIGATION_BAR_ID,
                NavigationBar.APP_ID,
                NavigationBar.SYSTEM_VERSION
        );

        Boolean result = navigationBarService.delete(body.getNavigationBarId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}