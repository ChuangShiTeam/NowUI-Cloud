package com.nowui.cloud.base.role.controller.admin;

import com.nowui.cloud.base.role.view.RoleView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;
import com.nowui.cloud.base.menu.view.MenuView;
import com.nowui.cloud.base.role.entity.Role;
import com.nowui.cloud.base.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理端控制器
 *
 * @author marcus
 * <p>
 * 2018-01-02
 */
@Api(value = "角色", description = "角色管理端接口管理")
@RestController
public class RoleAdminController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "角色列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = RoleView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_NAME, value = "角色名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_CODE, value = "角色编码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/role/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore RoleView roleView, @ApiIgnore CommonView commonView) {
        validateRequest(
                roleView,
                RoleView.APP_ID,
                RoleView.ROLE_NAME,
                RoleView.ROLE_CODE
        );
        
        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = roleService.countForAdmin(roleView.getAppId(), roleView.getRoleName(), roleView.getRoleCode());
        List<RoleView> resultList = roleService.listForAdmin(roleView.getAppId(), roleView.getRoleName(), roleView.getRoleCode(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                RoleView.ROLE_ID,
                RoleView.ROLE_NAME,
                RoleView.ROLE_CODE,
                RoleView.ROLE_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "角色信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = RoleView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_ID, value = "角色编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/role/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore RoleView roleView) {
        validateRequest(
                roleView,
                RoleView.APP_ID,
                RoleView.ROLE_ID
        );

        RoleView result = roleService.find(roleView.getRoleId());

        validateResponse(
                RoleView.ROLE_ID,
                RoleView.ROLE_NAME,
                RoleView.ROLE_CODE,
                RoleView.ROLE_DESCRIPTION,
                RoleView.ROLE_SORT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增角色", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = RoleView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_NAME, value = "角色名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_CODE, value = "角色编码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_DESCRIPTION, value = "角色描述", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_SORT, value = "角色排序", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/role/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore Role role, @ApiIgnore RoleView roleView, @ApiIgnore CommonView commonView) {
        validateRequest(
                roleView,
                RoleView.APP_ID,
                RoleView.ROLE_NAME,
                RoleView.ROLE_CODE,
                RoleView.ROLE_DESCRIPTION,
                RoleView.ROLE_SORT
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String roleId = Util.getRandomUUID();

        Role result = roleService.save(role, roleId, commonView.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            // 保存角色视图信息
            roleView.copy(result);
            
            roleService.save(roleView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改角色", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = RoleView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_ID, value = "角色编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_NAME, value = "角色名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_CODE, value = "角色编码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_DESCRIPTION, value = "角色描述", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = RoleView.ROLE_SORT, value = "角色排序", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = RoleView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/role/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore Role role, @ApiIgnore RoleView roleView, @ApiIgnore CommonView commonView) {

        validateRequest(
                roleView,
                RoleView.ROLE_ID,
                RoleView.APP_ID,
                RoleView.ROLE_NAME,
                RoleView.ROLE_CODE,
                RoleView.ROLE_DESCRIPTION,
                RoleView.ROLE_SORT,
                RoleView.SYSTEM_VERSION
        );

        Role result = roleService.update(roleEntity, roleEntity.getRoleId(), roleEntity.getSystemUpdateUserId(), roleEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除角色", httpMethod = "POST")
    @RequestMapping(value = "/role/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Role roleEntity = getEntry(Role.class);

        validateRequest(
                roleEntity,
                Role.ROLE_ID,
                Role.APP_ID,
                Role.SYSTEM_VERSION
        );

        Role result = roleService.delete(roleEntity.getRoleId(), roleEntity.getSystemUpdateUserId(), roleEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "角色数据同步", httpMethod = "POST")
    @RequestMapping(value = "/role/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Role> roleList = roleService.listByMysql();

        for(Role role : roleList) {
            RoleView roleView = new RoleView();
            roleView.copy(role);

            roleService.saveOrUpdate(roleView, roleView.getRoleId());
        }

        return renderJson(true);
    }

}