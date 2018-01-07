package com.nowui.cloud.base.user.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.user.entity.UserRole;
import com.nowui.cloud.base.user.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户角色管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "用户角色", description = "用户角色管理端接口管理")
@RestController
public class UserRoleAdminController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "用户角色列表")
    @RequestMapping(value = "/user/role/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody UserRole body) {
        validateRequest(
                body,
                UserRole.APP_ID,
                UserRole.USER_ID,
                UserRole.ROLE_ID,
                UserRole.USER_TYPE,
                UserRole.PAGE_INDEX,
                UserRole.PAGE_SIZE
        );

        Integer resultTotal = userRoleService.adminCount(body.getAppId() , body.getUserId(), body.getRoleId(), body.getUserType());
        List<UserRole> resultList = userRoleService.adminList(body.getAppId(), body.getUserId(), body.getRoleId(), body.getUserType(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                UserRole.USER_ROLE_ID,
                UserRole.USER_ID,
                UserRole.ROLE_ID,
                UserRole.USER_TYPE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "用户角色信息")
    @RequestMapping(value = "/user/role/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody UserRole body) {
        validateRequest(
                body,
                UserRole.APP_ID,
                UserRole.USER_ROLE_ID
        );

        UserRole result = userRoleService.find(body.getUserRoleId());

        validateResponse(
                UserRole.USER_ROLE_ID,
                UserRole.USER_ID,
                UserRole.ROLE_ID,
                UserRole.USER_TYPE
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增用户角色")
    @RequestMapping(value = "/user/role/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody UserRole body) {
        validateRequest(
                body,
                UserRole.APP_ID,
                UserRole.USER_ID,
                UserRole.ROLE_ID,
                UserRole.USER_TYPE
        );

        Boolean result = userRoleService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改用户角色")
    @RequestMapping(value = "/user/role/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody UserRole body) {
        validateRequest(
                body,
                UserRole.USER_ROLE_ID,
                UserRole.APP_ID,
                UserRole.USER_ID,
                UserRole.ROLE_ID,
                UserRole.USER_TYPE,
                UserRole.SYSTEM_VERSION
        );

        Boolean result = userRoleService.update(body, body.getUserRoleId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除用户角色")
    @RequestMapping(value = "/user/role/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody UserRole body) {
        validateRequest(
                body,
                UserRole.USER_ROLE_ID,
                UserRole.APP_ID,
                UserRole.SYSTEM_VERSION
        );

        Boolean result = userRoleService.delete(body.getUserRoleId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}