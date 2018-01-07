package com.nowui.cloud.base.admin.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.entity.enums.AdminType;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 管理员管理端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "管理员", description = "管理员管理端接口管理")
@RestController
public class AdminAdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;
    
    @ApiOperation(value = "管理员列表")
    @RequestMapping(value = "/admin/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody User body) {
    	
        validateRequest(
                body,
                User.APP_ID,
                User.PAGE_INDEX,
                User.PAGE_SIZE
        );

        Integer resultTotal = adminService.adminCount(body.getAppId() , body.getUserId());
        List<Admin> resultList = adminService.adminList(body.getAppId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
        		User.USER_ID,
                User.USER_TYPE,
                User.USER_ACCOUNT,
                User.USER_NICK_NAME,
                User.USER_NAME,
                User.USER_MOBILE,
                User.USER_EMAIL,
                User.USER_AVATAR
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "管理员信息")
    @RequestMapping(value = "/admin/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody Admin body) {
        validateRequest(
                body,
                Admin.APP_ID,
                Admin.ADMIN_ID
        );

        Admin result = adminService.find(body.getAdminId());

        validateResponse(
                Admin.ADMIN_ID,
                Admin.USER_ID
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增管理员")
    @RequestMapping(value = "/admin/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody User body) {
        validateRequest(
                body,
                User.APP_ID,
                User.USER_ACCOUNT,
                User.USER_PASSWORD,
                User.USER_NICK_NAME,
                User.USER_NAME,
                User.USER_MOBILE,
                User.USER_EMAIL,
                User.USER_AVATAR
        );
        
        String adminIdAndObjId = Util.getRandomUUID();
        String userId = Util.getRandomUUID();
        
        body.setUserType(AdminType.ADMIN.getKey());
        body.setObjectId(adminIdAndObjId);
        body.setWeixinOpenId("");
        body.setWeixinUnionId("");
        
        Admin admin = new Admin();
        admin.setUserId(userId);
        admin.setAppId(body.getAppId());
        
        Boolean result = adminService.save(admin, adminIdAndObjId, body.getSystemRequestUserId());
        if (result) {
        	result = userService.save(body, userId, body.getSystemRequestUserId());
		}
        
        return renderJson(result);
    }

    @ApiOperation(value = "修改管理员")
    @RequestMapping(value = "/admin/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody Admin body) {
        validateRequest(
                body,
                Admin.ADMIN_ID,
                Admin.APP_ID,
                Admin.USER_ID,
                Admin.SYSTEM_VERSION
        );

        Boolean result = adminService.update(body, body.getAdminId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除管理员")
    @RequestMapping(value = "/admin/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody Admin body) {
        validateRequest(
                body,
                Admin.ADMIN_ID,
                Admin.APP_ID,
                Admin.SYSTEM_VERSION
        );

        Boolean result = adminService.delete(body.getAdminId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}