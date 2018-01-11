package com.nowui.cloud.base.admin.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.service.AdminService;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserIdcard;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.enums.UserType;
import com.nowui.cloud.base.user.mq.UserMq;
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
    private UserMq userMq;
    
    @ApiOperation(value = "管理员列表")
    @RequestMapping(value = "/admin/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody User body) {
    	
        validateRequest(
                body,
                User.APP_ID,
                User.PAGE_INDEX,
                User.PAGE_SIZE
        );

        Integer resultTotal = adminService.countForAdmin(body.getAppId());
        List<Admin> resultList = adminService.listForAdmin(body.getAppId(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                Admin.ADMIN_ID,
        		User.USER_ID,
                User.USER_TYPE,
                User.USER_ACCOUNT,
                User.USER_NICK_NAME,
                User.USER_IDCARD,
                User.USER_MOBILE,
                User.USER_EMAIL,
                User.USER_AVATAR
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "管理员信息")
    @RequestMapping(value = "/admin/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody Admin body) {
        validateRequest(
                body,
                Admin.APP_ID,
                Admin.ADMIN_ID
        );

        Admin result = adminService.find(body.getAdminId());

        validateResponse(
                Admin.ADMIN_ID,
                User.USER_ID,
                User.USER_ACCOUNT,
                User.USER_NICK_NAME,
                User.USER_IDCARD,
                User.USER_MOBILE,
                User.USER_EMAIL,
                User.USER_AVATAR,
                Admin.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增管理员")
    @RequestMapping(value = "/admin/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody User body) {
        validateRequest(
                body,
                User.APP_ID
        );
        //验证用户账号
        UserAccount userAccount = JSONObject.parseObject(body.toJSONString(), UserAccount.class).keepTableFieldValue();
        validateRequest(
                userAccount,
                UserAccount.USER_ACCOUNT,
                UserAccount.USER_PASSWORD
        );
        //验证用户昵称
        UserNickName userNickName = JSONObject.parseObject(body.toJSONString(), UserNickName.class).keepTableFieldValue();
        validateRequest(
                userNickName,
                UserNickName.USER_NICK_NAME
        );
        //验证用户姓名
        UserIdcard userIdcard = JSONObject.parseObject(body.toJSONString(), UserIdcard.class).keepTableFieldValue();
        validateRequest(
                userIdcard,
                UserIdcard.USER_NAME
        );
        //验证用户邮箱
        UserEmail userEmail = JSONObject.parseObject(body.toJSONString(), UserEmail.class).keepTableFieldValue();
        validateRequest(
                userEmail,
                UserEmail.USER_EMAIL
        );
        //验证用户手机
        UserMobile userMobile = JSONObject.parseObject(body.toJSONString(), UserMobile.class).keepTableFieldValue();
        validateRequest(
                userMobile,
                UserMobile.USER_MOBILE
        );
        String adminId = Util.getRandomUUID();
        String userId = Util.getRandomUUID();
        
        body.setUserId(userId);
        body.setUserType(UserType.ADMIN.getKey());
        body.setObjectId(adminId);
        
        Admin admin = new Admin();
        admin.setUserId(userId);
        admin.setAppId(body.getAppId());
        
        Boolean result = true;
        if (result) {
            userMq.sendSave(body, userId, body.getSystemRequestUserId());
            userMq.sendSaveAccount(userAccount, userId, body.getSystemRequestUserId());
            userMq.sendSaveNickName(userNickName, userId, body.getSystemRequestUserId());
            userMq.sendSaveIdcard(userIdcard, userId, body.getSystemRequestUserId());
            userMq.sendSaveMobile(userMobile, userId, body.getSystemRequestUserId());
		}
        
        return renderJson(result);
    }

    @ApiOperation(value = "修改管理员")
    @RequestMapping(value = "/admin/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody User body) {
        validateRequest(
                body,
                User.APP_ID,
                User.USER_ID,
                User.SYSTEM_VERSION
        );
        Admin admin = JSONObject.parseObject(body.toJSONString(), Admin.class);
        validateRequest(
                admin,
                Admin.ADMIN_ID,
                Admin.APP_ID,
                Admin.SYSTEM_VERSION
        );
        
        //验证用户账号
        UserAccount userAccount = JSONObject.parseObject(body.toJSONString(), UserAccount.class).keepTableFieldValue();
        validateRequest(
                userAccount,
                UserAccount.USER_ACCOUNT,
                UserAccount.USER_PASSWORD
        );
        //验证用户昵称
        UserNickName userNickName = JSONObject.parseObject(body.toJSONString(), UserNickName.class).keepTableFieldValue();
        validateRequest(
                userNickName,
                UserNickName.USER_NICK_NAME
        );
        //验证用户姓名
        UserIdcard userIdcard = JSONObject.parseObject(body.toJSONString(), UserIdcard.class).keepTableFieldValue();
        userIdcard.setUserIdcardNumber("");
        validateRequest(
                userIdcard,
                UserIdcard.USER_NAME
        );
        //验证用户邮箱
        UserEmail userEmail = JSONObject.parseObject(body.toJSONString(), UserEmail.class).keepTableFieldValue();
        validateRequest(
                userEmail,
                UserEmail.USER_EMAIL
        );
        //验证用户手机
        UserMobile userMobile = JSONObject.parseObject(body.toJSONString(), UserMobile.class).keepTableFieldValue();
        validateRequest(
                userMobile,
                UserMobile.USER_MOBILE
        );
        
        Boolean result = adminService.update(admin, admin.getAdminId(), admin.getSystemRequestUserId(), admin.getSystemVersion());

        if (result) {
            userMq.sendUpdate(body, body.getUserId(), body.getSystemRequestUserId(), body.getSystemVersion());
            userMq.sendUpdateAccount(userAccount, body.getUserId(), body.getSystemRequestUserId());
            userMq.sendUpdateNickName(userNickName, body.getUserId(), body.getSystemRequestUserId());
            userMq.sendUpdateIdcard(userIdcard, body.getUserId(), body.getSystemRequestUserId());
            userMq.sendUpdateMobile(userMobile, body.getUserId(), body.getSystemRequestUserId());
        }
        
        return renderJson(result);
    }

    @ApiOperation(value = "删除管理员")
    @RequestMapping(value = "/admin/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody Admin body) {
        validateRequest(
                body,
                Admin.ADMIN_ID,
                Admin.APP_ID,
                Admin.SYSTEM_VERSION
        );

        Boolean result = adminService.delete(body.getAdminId(), body.getSystemRequestUserId(), body.getSystemVersion());

        if (result) {
            //adminMq.sendDeleteUser(body.getAdminId(), body.getSystemRequestUserId());
        }
        return renderJson(result);
    }

}