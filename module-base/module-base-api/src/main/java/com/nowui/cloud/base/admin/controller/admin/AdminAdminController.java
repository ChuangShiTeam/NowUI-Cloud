package com.nowui.cloud.base.admin.controller.admin;

import java.util.List;
import java.util.Map;

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
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.view.UserView;
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
    @RequestMapping(value = "/admin/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        User userEntity = getEntry(User.class);

        validateRequest(
                userEntity,
                User.APP_ID,
                User.PAGE_INDEX,
                User.PAGE_SIZE
        );

        Integer resultTotal = adminService.countForAdmin(userEntity.getAppId());
        List<AdminView> resultList = adminService.listForAdmin(userEntity.getAppId(), userEntity.getPageIndex(), userEntity.getPageSize());

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
    @RequestMapping(value = "/admin/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        AdminView adminEntity = getEntry(AdminView.class);

        validateRequest(
                adminEntity,
                AdminView.APP_ID,
                AdminView.ADMIN_ID
        );

        AdminView result = adminService.find(adminEntity.getAdminId());

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
    @RequestMapping(value = "/admin/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        User userEntity = getEntry(User.class);

        validateRequest(
                userEntity,
                User.APP_ID
        );

        //验证用户账号
        UserAccount userAccountEntity = getEntry(UserAccount.class).keepTableFieldValue();
        validateRequest(
                userAccountEntity,
                UserAccount.USER_ACCOUNT
        );

        //验证用户昵称
        UserNickName userNickNameEntity = getEntry(UserNickName.class).keepTableFieldValue();
        validateRequest(
                userNickNameEntity,
                UserNickName.USER_NICK_NAME
        );

        //验证用户邮箱
        UserEmail userEmailEntity = getEntry(UserEmail.class).keepTableFieldValue();
        validateRequest(
                userEmailEntity,
                UserEmail.USER_EMAIL
        );

        //验证用户手机
        UserMobile userMobileEntity = getEntry(UserMobile.class).keepTableFieldValue();
        validateRequest(
                userMobileEntity,
                UserMobile.USER_MOBILE
        );

        String adminId = Util.getRandomUUID();
        String userId = Util.getRandomUUID();

        userEntity.setUserId(userId);
        userEntity.setUserType(UserType.ADMIN.getKey());
        userEntity.setObjectId(adminId);

        Admin admin = new Admin();
        admin.setUserId(userId);
        admin.setAppId(userEntity.getAppId());

        Boolean success = true;

        return renderJson(success);
    }

    @ApiOperation(value = "修改管理员")
    @RequestMapping(value = "/admin/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        User userEntity = getEntry(User.class);

        validateRequest(
                userEntity,
                User.APP_ID,
                User.USER_ID,
                User.SYSTEM_VERSION
        );

        Admin adminEntity = getEntry(Admin.class);
        validateRequest(
                adminEntity,
                Admin.ADMIN_ID,
                Admin.APP_ID,
                Admin.SYSTEM_VERSION
        );

        //验证用户账号
        UserAccount userAccountEntity = getEntry(UserAccount.class).keepTableFieldValue();
        validateRequest(
                userAccountEntity,
                UserAccount.USER_ACCOUNT
        );

        //验证用户昵称
        UserNickName userNickNameEntity = getEntry(UserNickName.class).keepTableFieldValue();
        validateRequest(
                userNickNameEntity,
                UserNickName.USER_NICK_NAME
        );

        //验证用户邮箱
        UserEmail userEmailEntity = getEntry(UserEmail.class).keepTableFieldValue();
        validateRequest(
                userEmailEntity,
                UserEmail.USER_EMAIL
        );

        //验证用户手机
        UserMobile userMobileEntity = getEntry(UserMobile.class).keepTableFieldValue();
        validateRequest(
                userMobileEntity,
                UserMobile.USER_MOBILE
        );

        Admin result = adminService.update(adminEntity, adminEntity.getAdminId(), adminEntity.getSystemRequestUserId(), adminEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, AdminRouter.ADMIN_V1_UPDATE, adminEntity.getAppId(), adminEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除管理员")
    @RequestMapping(value = "/admin/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Admin adminEntity = getEntry(Admin.class);

        validateRequest(
                adminEntity,
                Admin.ADMIN_ID,
                Admin.APP_ID,
                Admin.SYSTEM_VERSION
        );

        Admin result = adminService.delete(adminEntity.getAdminId(), adminEntity.getSystemRequestUserId(), adminEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, AdminRouter.ADMIN_V1_DELETE, adminEntity.getAppId(), adminEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "管理员数据同步")
    @RequestMapping(value = "/admin/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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