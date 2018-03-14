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
import com.nowui.cloud.base.admin.view.AdminView;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;

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
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/admin/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore AdminView adminView, @ApiIgnore CommonView commonView) {

        validateRequest(
                adminView,
                AdminView.APP_ID
        );
        
        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = adminService.countForAdmin(adminView.getAppId());
        List<AdminView> resultList = adminService.listForAdmin(adminView.getAppId(), commonView.getPageIndex(), commonView.getPageSize());

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
        @ApiImplicitParam(name = AdminView.ADMIN_PASSWORD, value = "管理员密码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/admin/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore Admin admin, @ApiIgnore AdminView adminView, @ApiIgnore CommonView commonView) {
        validateRequest(
                adminView,
                AdminView.APP_ID,
                AdminView.ADMIN_ID,
                AdminView.ADMIN_ACCOUNT,
                AdminView.ADMIN_PASSWORD
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String requestUserId = commonView.getSystemRequestUserId();
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
            adminAccountService.save(adminAccount, Util.getRandomUUID(), requestUserId);
            
            // 保存管理员密码信息
            AdminPassword adminPassword = new AdminPassword();
            adminPassword.setAppId(adminView.getAppId());
            adminPassword.setAdminId(adminId);
            adminPassword.setAdminPassword(Util.generatePassword(adminView.getAdminPassword()));
            adminPasswordService.save(adminPassword, Util.getRandomUUID(), requestUserId);
            
            // 保存管理员视图信息
            adminView.putEntry(admin);
            adminView.setAdminPassword(Util.generatePassword(adminView.getAdminPassword()));
            
            adminService.save(adminView);
            
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
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/admin/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore Admin admin, @ApiIgnore AdminView adminView, @ApiIgnore CommonView commonView) {
        
        validateRequest(
                adminView,
                AdminView.APP_ID,
                AdminView.ADMIN_ID,
                AdminView.ADMIN_ACCOUNT,
                AdminView.ADMIN_PASSWORD,
                AdminView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        AdminView adminViewBean = adminService.find(adminView.getAdminId());
        
        if (adminViewBean == null) {
            return renderJson(false);
        }
        
        // 验证管理员账号是否更改
        if (!adminView.getAdminAccount().equals(adminViewBean.getAdminAccount())) {
            // 刪除旧的管理员账号
            adminAccountService.deleteByAdminId(adminView.getAdminId(), commonView.getSystemRequestUserId());
            // 保存新的管理员账号
            AdminAccount adminAccount = new AdminAccount();
            adminAccount.setAppId(adminView.getAppId());
            adminAccount.setAdminId(adminView.getAdminId());
            adminAccount.setAdminAccount(adminView.getAdminAccount());
            
            adminAccountService.save(adminAccount, Util.getRandomUUID(), commonView.getSystemRequestUserId());
            
            adminViewBean.setAdminAccount(adminView.getAdminAccount());
        }
        
        // 验证管理员密码是否更改
        if (!Util.generatePassword(adminView.getAdminPassword()).equals(adminViewBean.getAdminPassword())) {
            // 刪除旧的管理员密码
            adminPasswordService.deleteByAdminId(adminView.getAdminId(), commonView.getSystemRequestUserId());
            // 保存新的管理员密码
            AdminPassword adminPassword = new AdminPassword();
            adminPassword.setAppId(adminView.getAppId());
            adminPassword.setAdminId(adminView.getAdminId());
            adminPassword.setAdminPassword(Util.generatePassword(adminView.getAdminPassword()));
            
            adminPasswordService.save(adminPassword, Util.getRandomUUID(), commonView.getSystemRequestUserId());
            
            adminViewBean.setAdminPassword(Util.generatePassword(adminView.getAdminPassword()));
        }

        // 更新管理员视图信息
        adminService.update(adminViewBean, adminViewBean.getAdminId());

        return renderJson(true);
    }

    @ApiOperation(value = "删除管理员", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = AdminView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.ADMIN_ID, value = "管理员编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = AdminView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/admin/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore AdminView adminView, @ApiIgnore CommonView commonView) {
        validateRequest(
                adminView,
                AdminView.ADMIN_ID,
                AdminView.APP_ID,
                AdminView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        Admin result = adminService.delete(adminView.getAdminId(), adminView.getAppId(), commonView.getSystemRequestUserId(), adminView.getSystemVersion());
        
        Boolean success = false;
        
        if (result != null) {
            // 删除管理员账号
            adminAccountService.deleteByAdminId(adminView.getAdminId(), commonView.getSystemRequestUserId());
            // 删除管理员密码
            adminPasswordService.deleteByAdminId(adminView.getAdminId(), commonView.getSystemRequestUserId());
            // 删除管理员视图信息
            adminView.putEntry(result);
            
            adminService.delete(adminView, adminView.getAdminId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "管理员数据同步", httpMethod = "POST")
    @RequestMapping(value = "/admin/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Admin> adminList = adminService.listByMysql();

        for (Admin admin : adminList) {
            AdminView adminView = new AdminView();
            adminView.putEntry(admin);

            AdminAccount adminAccount = adminAccountService.findByAdminId(adminView.getAdminId());
            adminView.setAdminAccount(adminAccount.getAdminAccount());
            
            AdminPassword adminPassword = adminPasswordService.findByAdminId(adminView.getAdminId());
            adminView.setAdminPassword(adminPassword.getAdminPassword());
            
            adminService.saveOrUpdate(adminView, admin.getAdminId());
        }

        return renderJson(true);
    }

}