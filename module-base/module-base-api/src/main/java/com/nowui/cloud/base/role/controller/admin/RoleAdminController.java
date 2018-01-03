package com.nowui.cloud.base.role.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.role.entity.Role;
import com.nowui.cloud.base.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "角色", description = "角色管理端接口管理")
@RestController
public class RoleAdminController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "/role/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody Role body) {
        validateRequest(
                body,
                Role.APP_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.PAGE_INDEX,
                Role.PAGE_SIZE
        );

        Integer resultTotal = roleService.adminCount(body.getAppId() , body.getRoleName(), body.getRoleCode());
        List<Role> resultList = roleService.adminList(body.getAppId(), body.getRoleName(), body.getRoleCode(), body.getM(), body.getN());

        validateResponse(
                Role.ROLE_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.ROLE_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "角色信息")
    @RequestMapping(value = "/role/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Role body) {
        validateRequest(
                body,
                Role.APP_ID,
                Role.ROLE_ID
        );

        Role result = roleService.find(body.getRoleId());

        validateResponse(
                Role.ROLE_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.ROLE_DESCRIPTION,
                Role.ROLE_SORT
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增角色")
    @RequestMapping(value = "/role/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody Role body) {
        validateRequest(
                body,
                Role.APP_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.ROLE_DESCRIPTION,
                Role.ROLE_SORT
        );

        Boolean result = roleService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/role/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Role body) {
        validateRequest(
                body,
                Role.ROLE_ID,
                Role.APP_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.ROLE_DESCRIPTION,
                Role.ROLE_SORT,
                Role.SYSTEM_VERSION
        );

        Boolean result = roleService.update(body, body.getRoleId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/role/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Role body) {
        validateRequest(
                body,
                Role.ROLE_ID,
                Role.APP_ID,
                Role.SYSTEM_VERSION
        );

        Boolean result = roleService.delete(body.getRoleId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}