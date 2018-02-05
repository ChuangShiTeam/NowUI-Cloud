package com.nowui.cloud.base.role.controller.admin;

import com.nowui.cloud.base.role.router.RoleRouter;
import com.nowui.cloud.base.role.view.RoleView;
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
 * <p>
 * 2018-01-02
 */
@Api(value = "角色", description = "角色管理端接口管理")
@RestController
public class RoleAdminController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "/role/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        Role roleEntity = getEntry(Role.class);

        validateRequest(
                roleEntity,
                Role.APP_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.PAGE_INDEX,
                Role.PAGE_SIZE
        );

        Integer resultTotal = roleService.countForAdmin(roleEntity.getAppId(), roleEntity.getRoleName(), roleEntity.getRoleCode());
        List<Role> resultList = roleService.listForAdmin(roleEntity.getAppId(), roleEntity.getRoleName(), roleEntity.getRoleCode(), roleEntity.getPageIndex(), roleEntity.getPageSize());

        validateResponse(
                Role.ROLE_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.ROLE_SORT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "角色信息")
    @RequestMapping(value = "/role/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        Role roleEntity = getEntry(Role.class);

        validateRequest(
                roleEntity,
                Role.APP_ID,
                Role.ROLE_ID
        );

        RoleView result = roleService.find(roleEntity.getRoleId());

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
    @RequestMapping(value = "/role/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Role roleEntity = getEntry(Role.class);

        validateRequest(
                roleEntity,
                Role.APP_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.ROLE_DESCRIPTION,
                Role.ROLE_SORT
        );

        String roleId = Util.getRandomUUID();

        Role result = roleService.save(roleEntity, roleId, roleEntity.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            sendMessage(result, RoleRouter.ROLE_V1_SAVE, roleEntity.getAppId(), roleEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/role/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Role roleEntity = getEntry(Role.class);

        validateRequest(
                roleEntity,
                Role.ROLE_ID,
                Role.APP_ID,
                Role.ROLE_NAME,
                Role.ROLE_CODE,
                Role.ROLE_DESCRIPTION,
                Role.ROLE_SORT,
                Role.SYSTEM_VERSION
        );

        Role result = roleService.update(roleEntity, roleEntity.getRoleId(), roleEntity.getSystemUpdateUserId(), roleEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            sendMessage(result, RoleRouter.ROLE_V1_UPDATE, roleEntity.getAppId(), roleEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/role/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
            sendMessage(result, RoleRouter.ROLE_V1_DELETE, roleEntity.getAppId(), roleEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "角色数据同步")
    @RequestMapping(value = "/role/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Role> roleList = roleService.listByMysql();

        for(Role role : roleList) {
            RoleView roleView = new RoleView();
            roleView.putAll(role);

            roleService.update(roleView);
        }

        return renderJson(true);
    }

}