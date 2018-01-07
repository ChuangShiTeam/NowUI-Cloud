package com.nowui.cloud.base.menu.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.menu.entity.Menu;
import com.nowui.cloud.base.menu.service.MenuService;
import com.nowui.cloud.constant.Constant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.plaf.BorderUIResource.BevelBorderUIResource;

import java.util.Set;

/**
 * 菜单管理端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "菜单", description = "菜单管理端接口管理")
@RestController
public class MenuAdminController extends BaseController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "菜单分类树形列表")
    @RequestMapping(value = "/menu/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Menu body) {
        validateRequest(
                body,
                Menu.APP_ID,
                Menu.MENU_NAME,
                Menu.PAGE_INDEX,
                Menu.PAGE_SIZE
        );

        Integer resultTotal = menuService.adminCount(body.getAppId() , body.getMenuName());
        
        if (Util.isNullOrEmpty(body.getMenuName())) {
			
        	List<Map<String, Object>> resultList = menuService.adminTreeList(body.getAppId(), body.getMenuName(), body.getPageIndex(), body.getPageSize());
        	
        	validateResponse(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_URL, Menu.MENU_SORT, Constant.CHILDREN);
        	
        	return renderJson(resultTotal, resultList);

		}else {
			List<Menu> resultList = menuService.adminList(body.getAppId(), body.getMenuName(), body.getPageIndex(), body.getPageSize());
			
			validateResponse(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_URL, Menu.MENU_SORT, Constant.CHILDREN);
			
			return renderJson(resultTotal, resultList);
		}
    }

    @ApiOperation(value = "菜单信息")
    @RequestMapping(value = "/menu/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Menu body) {
        validateRequest(
                body,
                Menu.APP_ID,
                Menu.MENU_ID
        );

        Menu result = menuService.find(body.getMenuId());

        validateResponse(
                Menu.MENU_ID,
                Menu.MENU_PARENT_ID,
                Menu.MENU_PARENT_PATH,
                Menu.MENU_NAME,
                Menu.MENU_IMAGE,
                Menu.MENU_URL,
                Menu.MENU_SORT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增菜单")
    @RequestMapping(value = "/menu/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Menu body) {
        validateRequest(
                body,
                Menu.APP_ID,
                Menu.MENU_PARENT_ID,
                Menu.MENU_NAME,
                Menu.MENU_URL,
                Menu.MENU_SORT
        );
        
        String menuParentPath = "";

        if (Util.isNullOrEmpty(body.getMenuParentId())) {

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(body.getMenuId());

            menuParentPath = jsonArray.toJSONString();
        } else {
            Menu parent = menuService.find(body.getMenuParentId());

            JSONArray jsonArray;
            if (Util.isNullOrEmpty(parent.getMenuParentPath())) {
                jsonArray = new JSONArray();
            } else {
                jsonArray = JSONArray.parseArray(parent.getMenuParentPath());
            }
            jsonArray.add(parent.getMenuId());

            menuParentPath = jsonArray.toJSONString();
        }
        
        body.setMenuParentPath(menuParentPath);

        Boolean result = menuService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改菜单")
    @RequestMapping(value = "/menu/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Menu body) {
        validateRequest(
                body,
                Menu.MENU_ID,
                Menu.APP_ID,
                Menu.MENU_PARENT_ID,
                Menu.MENU_PARENT_PATH,
                Menu.MENU_NAME,
                Menu.MENU_URL,
                Menu.MENU_SORT,
                Menu.SYSTEM_VERSION
        );

        Boolean result = menuService.update(body, body.getMenuId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除菜单")
    @RequestMapping(value = "/menu/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Menu body) {
        validateRequest(
                body,
                Menu.MENU_ID,
                Menu.APP_ID,
                Menu.SYSTEM_VERSION
        );

        Boolean result = menuService.delete(body.getMenuId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}