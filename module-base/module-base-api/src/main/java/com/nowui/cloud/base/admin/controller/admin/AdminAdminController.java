package com.nowui.cloud.base.admin.controller.admin;

import java.util.List;
import java.util.Map;

import com.nowui.cloud.base.user.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.admin.view.AdminView;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 管理员管理端控制器
 *
 * @author marcus
 * <p>
 * 2018-01-01
 */
@Api(value = "管理员", description = "管理员管理端接口管理")
@RestController
public class AdminAdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "管理员列表")
    @RequestMapping(value = "/admin/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        UserView userEntityView = getEntry(UserView.class);

        validateRequest(
                userEntityView,
                UserView.APP_ID,
                UserView.PAGE_INDEX,
                UserView.PAGE_SIZE
        );

        Integer resultTotal = adminService.countForAdmin(userEntityView.getAppId());
        List<AdminView> resultList = adminService.listForAdmin(userEntityView.getAppId(), userEntityView.getPageIndex(), userEntityView.getPageSize());

        validateResponse(
                AdminView.ADMIN_ID,
                UserView.USER_ID,
                UserView.USER_TYPE,
                UserView.USER_ACCOUNT,
                UserView.USER_NICK_NAME,
                UserView.USER_MOBILE,
                UserView.USER_EMAIL,
                UserView.USER_AVATAR_FILE_PATH
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "管理员信息")
    @RequestMapping(value = "/admin/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        AdminView adminEntityView = getEntry(AdminView.class);

        validateRequest(
                adminEntityView,
                AdminView.APP_ID,
                AdminView.ADMIN_ID
        );

        AdminView result = adminService.find(adminEntityView.getAdminId());

        validateResponse(
                AdminView.ADMIN_ID,
                UserView.USER_ID,
                UserView.USER_ACCOUNT,
                UserView.USER_NICK_NAME,
                UserView.USER_MOBILE,
                UserView.USER_EMAIL,
                UserView.USER_AVATAR_FILE_PATH,
                AdminView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增管理员")
    @RequestMapping(value = "/admin/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        UserView userEntityView = getEntry(UserView.class);

        validateRequest(
                userEntityView,
                UserView.APP_ID
        );

        //验证用户账号
        UserAccountView userAccountEntityView = getEntry(UserAccountView.class);
        validateRequest(
                userAccountEntityView,
                UserAccountView.USER_ACCOUNT
        );

        //验证用户昵称
        UserNickNameView userNickNameEntityView = getEntry(UserNickNameView.class);
        validateRequest(
                userNickNameEntityView,
                UserNickNameView.USER_NICK_NAME
        );

        //验证用户邮箱
        UserEmailView userEmailEntityView = getEntry(UserEmailView.class);
        validateRequest(
                userEmailEntityView,
                UserEmailView.USER_EMAIL
        );

        //验证用户手机
        UserMobileView userMobileEntityView = getEntry(UserMobileView.class);
        validateRequest(
                userMobileEntityView,
                UserMobileView.USER_MOBILE
        );

        String adminId = Util.getRandomUUID();
        String userId = Util.getRandomUUID();

        User user = getEntry(User.class);

        user.setUserId(userId);
        user.setUserType(UserType.ADMIN.getKey());
        user.setObjectId(adminId);

        Admin admin = getEntry(Admin.class);
        admin.setUserId(userId);
        admin.setAppId(user.getAppId());

        Admin result = adminService.save(admin, admin.getAdminId(), admin.getSystemRequestUserId());

        if (result != null) {

        }

        return renderJson(true);
    }

    @ApiOperation(value = "修改管理员")
    @RequestMapping(value = "/admin/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        UserView userEntityView = getEntry(UserView.class);

        validateRequest(
                userEntityView,
                UserView.APP_ID,
                UserView.USER_ID,
                UserView.SYSTEM_VERSION
        );

        AdminView adminEntityView = getEntry(AdminView.class);
        validateRequest(
                adminEntityView,
                AdminView.ADMIN_ID,
                AdminView.APP_ID,
                AdminView.SYSTEM_VERSION
        );

        //验证用户账号
        UserAccountView userAccountEntity = getEntry(UserAccountView.class);
        validateRequest(
                userAccountEntity,
                UserAccount.USER_ACCOUNT
        );

        //验证用户昵称
        UserNickNameView userNickNameEntityView = getEntry(UserNickNameView.class);
        validateRequest(
                userNickNameEntityView,
                UserNickNameView.USER_NICK_NAME
        );

        //验证用户邮箱
        UserEmailView userEmailEntityView = getEntry(UserEmailView.class);
        validateRequest(
                userEmailEntityView,
                UserEmailView.USER_EMAIL
        );

        //验证用户手机
        UserMobileView userMobileEntityView = getEntry(UserMobileView.class);
        validateRequest(
                userMobileEntityView,
                UserMobileView.USER_MOBILE
        );

        Admin admin = getEntry(Admin.class);

        Admin result = adminService.update(admin, admin.getAdminId(), admin.getSystemRequestUserId(), admin.getSystemVersion());

        if (result != null) {

        }

        return renderJson(true);
    }

    @ApiOperation(value = "删除管理员")
    @RequestMapping(value = "/admin/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        AdminView adminEntityView = getEntry(AdminView.class);

        validateRequest(
                adminEntityView,
                AdminView.ADMIN_ID,
                AdminView.APP_ID,
                AdminView.SYSTEM_VERSION
        );

        Admin result = adminService.delete(adminEntityView.getAdminId(), adminEntityView.getSystemRequestUserId(), adminEntityView.getSystemVersion());

        if (result != null) {

        }

        return renderJson(true);
    }

    @ApiOperation(value = "管理员数据同步")
    @RequestMapping(value = "/admin/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Admin> adminList = adminService.listByMysql();

        for (Admin admin : adminList) {
            AdminView adminView = new AdminView();
            adminView.putAll(admin);

            adminService.update(adminView);
        }

        return renderJson(true);
    }

}