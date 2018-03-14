package com.nowui.cloud.base.admin.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.entity.AdminAccount;
import com.nowui.cloud.base.admin.entity.AdminPassword;
import com.nowui.cloud.base.admin.service.AdminAccountService;
import com.nowui.cloud.base.admin.service.AdminPasswordService;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.admin.view.AdminAccountView;
import com.nowui.cloud.base.admin.view.AdminPasswordView;
import com.nowui.cloud.base.admin.view.AdminView;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.view.UserEmailView;
import com.nowui.cloud.base.user.view.UserMobileView;
import com.nowui.cloud.base.user.view.UserNickNameView;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AdminAccountService adminAccountService;
    
    @Autowired
    private AdminPasswordService adminPasswordService;
    
    @ApiOperation(value = "管理员列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AdminView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = AdminView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/admin/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore AdminView adminView) {

        validateRequest(
                adminView,
                AdminView.APP_ID,
                AdminView.PAGE_INDEX,
                AdminView.PAGE_SIZE
        );

        Integer resultTotal = adminService.countForAdmin(adminView.getAppId());
        List<AdminView> resultList = adminService.listForAdmin(adminView.getAppId(), adminView.getPageIndex(), adminView.getPageSize());

        validateResponse(
                AdminView.ADMIN_ID,
                AdminView.ADMIN_ACCOUNT
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "管理员信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AdminView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.ADMIN_ID, value = "管理员编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/admin/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore AdminView adminView) {

        validateRequest(
                adminView,
                AdminView.APP_ID,
                AdminView.ADMIN_ID
        );

        AdminView result = adminService.find(adminView.getAdminId());

        validateResponse(
                AdminView.ADMIN_ID,
                AdminView.ADMIN_ACCOUNT,
                AdminView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增管理员", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AdminView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.ADMIN_ID, value = "管理员编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.ADMIN_ACCOUNT, value = "管理员账号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.ADMIN_PASSWORD, value = "管理员密码", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/admin/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore Admin admin, @ApiIgnore AdminView adminView) {
        validateRequest(
                adminView,
                AdminView.APP_ID,
                AdminView.ADMIN_ID,
                AdminView.ADMIN_ACCOUNT,
                AdminView.ADMIN_PASSWORD,
                AdminView.SYSTEM_REQUEST_USER_ID
        );

        String requestUserId = adminView.getSystemRequestUserId();
        String adminId = Util.getRandomUUID();
        String userId = Util.getRandomUUID();

        admin.setUserId(userId);

        Admin result = adminService.save(admin, adminId, requestUserId);

        if (result != null) {
            // 保存用户信息
            User user = new User();
            user.setAppId(adminView.getAppId());
            user.setUserId(userId);
            user.setUserType(UserType.ADMIN.getKey());
            user.setObjectId(adminId);
            
            userService.save(user, userId, requestUserId);
            
            // 保存管理员账号信息
            AdminAccount adminAccount = new AdminAccount();
            adminAccount.setAppId(adminView.getAppId());
            adminAccount.setAdminId(adminId);
            adminAccount.setAdminAccount(adminView.getAdminAccount());
            AdminAccount adminAccountResult = adminAccountService.save(adminAccount, Util.getRandomUUID(), requestUserId);
            
            // 保存管理员密码信息
            AdminPassword adminPassword = new AdminPassword();
            adminPassword.setAppId(adminView.getAppId());
            adminPassword.setAdminId(adminId);
            adminPassword.setAdminPassword(adminView.getAdminPassword());
            AdminPassword adminPasswordResult = adminPasswordService.save(adminPassword, Util.getRandomUUID(), requestUserId);
            
            // 保存管理员视图信息
            adminView.putEntry(admin);
            
            adminService.save(adminView);
            
            // 保存管理员账号视图信息
            AdminAccountView adminAccountView = new AdminAccountView();
            adminAccountView.putEntry(adminAccountResult);
            
            adminAccountService.save(adminAccountView);
            
            // 保存管理员账号视图信息
            AdminPasswordView adminPasswordView = new AdminPasswordView();
            adminPasswordView.putEntry(adminPasswordResult);
            
            adminPasswordService.save(adminPasswordView);
            
            // 保存用户视图信息
            UserView userView = new UserView();
            userView.putEntry(user);
            
            userService.save(userView);
            
        }

        return renderJson(true);
    }

    @ApiOperation(value = "修改管理员", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AdminView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.ADMIN_ID, value = "管理员编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.ADMIN_ACCOUNT, value = "管理员账号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.ADMIN_PASSWORD, value = "管理员密码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/admin/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore AdminView adminView) {
        
        validateRequest(
                adminView,
                AdminView.APP_ID,
                AdminView.ADMIN_ID,
                AdminView.ADMIN_ACCOUNT,
                AdminView.ADMIN_PASSWORD,
                AdminView.SYSTEM_VERSION
        );

        AdminView adminViewBean = adminService.find(adminView.getAdminId());
        
        if (adminViewBean == null) {
            return renderJson(false);
        }
        
        // 验证管理员账号是否更改
        if (!adminView.getAdminAccount().equals(adminViewBean.getAdminAccount())) {
            // 刪除
        }
        
        // 验证管理员

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

        Admin result = adminService.update(admin, admin.getAdminId(), adminView.getSystemRequestUserId(), admin.getSystemVersion());

        if (result != null) {

        }

        return renderJson(true);
    }

    @ApiOperation(value = "删除管理员", httpMethod = "POST")
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

    @ApiOperation(value = "管理员数据同步", httpMethod = "POST")
    @RequestMapping(value = "/admin/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Admin> adminList = adminService.listByMysql();

        for (Admin admin : adminList) {
            AdminView adminView = new AdminView();
            adminView.putEntry(admin);

            // TODO 管理员账号和密码
            adminService.saveOrUpdate(adminView, admin.getAdminId());
        }

        return renderJson(true);
    }

}