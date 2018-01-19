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

import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/menu/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        Menu menuEntity = getEntry(Menu.class);

        validateRequest(
                menuEntity,
                Menu.APP_ID,
                Menu.MENU_NAME,
                Menu.PAGE_INDEX,
                Menu.PAGE_SIZE
        );

        Integer resultTotal = menuService.countForAdmin(menuEntity.getAppId() , menuEntity.getMenuName());
        
        if (Util.isNullOrEmpty(menuEntity.getMenuName())) {
			
        	List<Map<String, Object>> resultList = menuService.treeListForAdmin(menuEntity.getAppId(), menuEntity.getMenuName(), menuEntity.getPageIndex(), menuEntity.getPageSize());
        	
        	validateResponse(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_URL, Menu.MENU_SORT, Constant.CHILDREN);
        	
        	return renderJson(resultTotal, resultList);

		}else {
			List<Menu> resultList = menuService.listForAdmin(menuEntity.getAppId(), menuEntity.getMenuName(), menuEntity.getPageIndex(), menuEntity.getPageSize());
			
			validateResponse(Menu.MENU_ID, Menu.MENU_NAME, Menu.MENU_URL, Menu.MENU_SORT, Constant.CHILDREN);
			
			return renderJson(resultTotal, resultList);
		}
    }

    @ApiOperation(value = "菜单信息")
    @RequestMapping(value = "/menu/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Menu menuEntity = getEntry(Menu.class);

        validateRequest(
                menuEntity,
                Menu.APP_ID,
                Menu.MENU_ID
        );

        Menu result = menuService.find(menuEntity.getMenuId());

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
    @RequestMapping(value = "/menu/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Menu menuEntity = getEntry(Menu.class);

        validateRequest(
                menuEntity,
                Menu.APP_ID,
                Menu.MENU_PARENT_ID,
                Menu.MENU_NAME,
                Menu.MENU_URL,
                Menu.MENU_SORT
        );
        
        String menuParentPath = "";

        if (Util.isNullOrEmpty(menuEntity.getMenuParentId())) {

            JSONArray jsonArray = new JSONArray();
            jsonArray.add(menuEntity.getMenuId());

            menuParentPath = jsonArray.toJSONString();
        } else {
            Menu parent = menuService.find(menuEntity.getMenuParentId());

            JSONArray jsonArray;
            if (Util.isNullOrEmpty(parent.getMenuParentPath())) {
                jsonArray = new JSONArray();
            } else {
                jsonArray = JSONArray.parseArray(parent.getMenuParentPath());
            }
            jsonArray.add(parent.getMenuId());

            menuParentPath = jsonArray.toJSONString();
        }

        menuEntity.setMenuParentPath(menuParentPath);

        Boolean result = menuService.save(menuEntity, Util.getRandomUUID(), menuEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改菜单")
    @RequestMapping(value = "/menu/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Menu menuEntity = getEntry(Menu.class);

        validateRequest(
                menuEntity,
                Menu.MENU_ID,
                Menu.APP_ID,
                Menu.MENU_PARENT_ID,
                Menu.MENU_PARENT_PATH,
                Menu.MENU_NAME,
                Menu.MENU_URL,
                Menu.MENU_SORT,
                Menu.SYSTEM_VERSION
        );

        Boolean result = menuService.update(menuEntity, menuEntity.getMenuId(), menuEntity.getSystemRequestUserId(), menuEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除菜单")
    @RequestMapping(value = "/menu/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Menu menuEntity = getEntry(Menu.class);

        validateRequest(
                menuEntity,
                Menu.MENU_ID,
                Menu.APP_ID,
                Menu.SYSTEM_VERSION
        );

        Boolean result = menuService.delete(menuEntity.getMenuId(), menuEntity.getSystemRequestUserId(), menuEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "菜单重建缓存")
    @RequestMapping(value = "/menu/admin/v1/replace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> replaceV1() {
        Menu menuEntity = getEntry(Menu.class);

        validateRequest(menuEntity, Menu.MENU_ID);

        menuService.replace(menuEntity.getMenuId());

        return renderJson(true);
    }

}