package com.nowui.cloud.base.menu.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.menu.entity.Menu;
import com.nowui.cloud.base.menu.service.MenuService;
import com.nowui.cloud.base.menu.view.MenuView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 菜单管理端控制器
 *
 * @author marcus
 * <p>
 * 2018-01-01
 */
@Api(value = "菜单", description = "菜单管理端接口管理")
@RestController
public class MenuAdminController extends BaseController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "菜单分类树形列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MenuView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_NAME, value = "菜单名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/menu/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore MenuView menuView, @ApiIgnore CommonView commonView) {

        validateRequest(
                menuView,
                MenuView.APP_ID,
                MenuView.MENU_NAME
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = menuService.countForAdmin(menuView.getAppId(), menuView.getMenuName());

        if (Util.isNullOrEmpty(menuView.getMenuName())) {

            List<Map<String, Object>> resultList = menuService.treeListForAdmin(menuView.getAppId(), commonView.getPageIndex(), commonView.getPageSize());

            validateResponse(MenuView.MENU_ID, MenuView.MENU_NAME, MenuView.MENU_URL, MenuView.MENU_SORT, Constant.CHILDREN);

            return renderJson(resultTotal, resultList);

        } else {
            List<MenuView> resultList = menuService.listForAdmin(menuView.getAppId(), menuView.getMenuName(), commonView.getPageIndex(), commonView.getPageSize());

            validateResponse(MenuView.MENU_ID, MenuView.MENU_NAME, MenuView.MENU_URL, MenuView.MENU_SORT, Constant.CHILDREN);

            return renderJson(resultTotal, resultList);
        }
    }

    @ApiOperation(value = "菜单信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MenuView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_ID, value = "菜单编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/menu/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore MenuView menuView) {

        validateRequest(
                menuView,
                MenuView.APP_ID,
                MenuView.MENU_ID
        );

        MenuView result = menuService.find(menuView.getMenuId());

        validateResponse(
                MenuView.MENU_ID,
                MenuView.MENU_PARENT_ID,
                MenuView.MENU_PARENT_PATH,
                MenuView.MENU_NAME,
                MenuView.MENU_IMAGE,
                MenuView.MENU_URL,
                MenuView.MENU_SORT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增菜单", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MenuView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_PARENT_ID, value = "菜单父编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_NAME, value = "菜单名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_URL, value = "url", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_SORT, value = "排序", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/menu/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore Menu menu, @ApiIgnore MenuView menuView, @ApiIgnore CommonView commonView) {

        validateRequest(
                menuView,
                MenuView.APP_ID,
                MenuView.MENU_PARENT_ID,
                MenuView.MENU_NAME,
                MenuView.MENU_URL,
                MenuView.MENU_SORT
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String menuParentPath = "";

        if (Util.isNullOrEmpty(menuView.getMenuParentId())) {

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(menuView.getMenuId());

            menuParentPath = jsonArray.toJSONString();
        } else {
            MenuView parent = menuService.find(menuView.getMenuParentId());

            JSONArray jsonArray;
            if (Util.isNullOrEmpty(parent.getMenuParentPath())) {
                jsonArray = new JSONArray();
            } else {
                jsonArray = JSONArray.parseArray(parent.getMenuParentPath());
            }
            jsonArray.add(parent.getMenuId());

            menuParentPath = jsonArray.toJSONString();
        }

        menu.setMenuParentPath(menuParentPath);

        Menu result = menuService.save(menu, Util.getRandomUUID(), commonView.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            // 保存菜单视图
            menuView.copy(result);
            
            menuService.save(menuView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改菜单", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MenuView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_ID, value = "菜单编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_PARENT_ID, value = "菜单父编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_NAME, value = "菜单名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_URL, value = "url", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_SORT, value = "排序", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = MenuView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/menu/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore Menu menu, @ApiIgnore MenuView menuView, @ApiIgnore CommonView commonView) {
       
        validateRequest(
                menuView,
                MenuView.MENU_ID,
                MenuView.APP_ID,
                MenuView.MENU_PARENT_ID,
                MenuView.MENU_PARENT_PATH,
                MenuView.MENU_NAME,
                MenuView.MENU_URL,
                MenuView.MENU_SORT,
                MenuView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        Menu result = menuService.update(menu, menu.getMenuId(), menu.getAppId(), commonView.getSystemRequestUserId(), menu.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 更新菜单视图
            menuView.copy(result);
            
            menuService.update(menuView, menuView.getMenuId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除菜单", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MenuView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.MENU_ID, value = "菜单编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MenuView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/menu/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore MenuView menuView, @ApiIgnore CommonView commonView) {

        validateRequest(
                menuView,
                MenuView.MENU_ID,
                MenuView.APP_ID,
                MenuView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        Menu result = menuService.delete(menuView.getMenuId(), menuView.getAppId(), commonView.getSystemRequestUserId(), menuView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 删除菜单视图
            menuView.copy(result);
            
            menuService.delete(menuView, menuView.getAppId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "菜单数据同步", httpMethod = "POST")
    @RequestMapping(value = "/menu/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        List<Menu> menuList = menuService.listByMysql();

        for (Menu menu : menuList) {
            MenuView menuView = new MenuView();
            menuView.copy(menu);

            menuService.saveOrUpdate(menuView, menuView.getMenuId());
        }

        return renderJson(true);
    }

}