package com.nowui.cloud.base.user.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.entity.UserSex;
import com.nowui.cloud.base.user.entity.UserWechat;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.service.UserPasswordService;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.service.UserSexService;
import com.nowui.cloud.base.user.service.UserWechatService;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "用户", description = "用户管理端接口管理")
@RestController
public class UserAdminController extends BaseController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserAvatarService userAvatarService;
	
	@Autowired
    private UserEmailService userEmailService;
	
	@Autowired
	private UserMobileService userMobileService;
	
	@Autowired
	private UserNickNameService userNickNameService;
	
	@Autowired
	private UserPasswordService userPasswordService;
	
	@Autowired
	private UserSexService userSexService;
	
	@Autowired
	private UserWechatService userWechatService;
	
	@ApiOperation(value = "用户数据同步")
    @RequestMapping(value = "/user/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<User> userList = userService.listByMysql();

        for (User user : userList) {
            UserView userView = new UserView();
            userView.putAll(user);

            UserAccount userAccount = userAccountService.findByUserId(user.getUserId());
            if (userAccount != null) {
                userView.setUserAccount(userAccount.getUserAccount());
            }
            
            UserAvatar userAvatar = userAvatarService.findByUserId(user.getUserId());
            if (userAvatar != null) {
                userAvatar.setUserAvatarFileId(userAvatar.getUserAvatarFileId());
                userAvatar.setUserAvatarFilePath(userAvatar.getUserAvatarFilePath());
            }
           
            UserEmail userEmail = userEmailService.findByUserId(user.getUserId());
            if (userEmail != null) {
                userView.setUserEmail(userEmail.getUserEmail());
            }
            
            UserMobile userMobile = userMobileService.findByUserId(user.getUserId());
            if (userMobile != null) {
                userView.setUserMobile(userMobile.getUserMobile());
            }
            
            UserNickName userNickName = userNickNameService.findByUserId(user.getUserId());
            if (userNickName != null) {
                userView.setUserNickName(userNickName.getUserNickName());
            }
            
            UserPassword userPassword = userPasswordService.findByUserId(user.getUserId());
            if (userPassword != null) {
                userView.setUserPassword(userPassword.getUserPassword());
            }
            
            UserSex userSex = userSexService.findByUserId(user.getUserId());
            if (userSex != null) {
                userView.setUserSex(userSex.getUserSex());
            }
            
            UserWechat userWechat = userWechatService.findByUserId(user.getUserId());
            if (userWechat != null) {
                userView.setWechatOpenId(userWechat.getWechatOpenId());
                userView.setWechatUnionId(userWechat.getWechatUnionId());
                userView.setWechatHeadImgFileId(userWechat.getWechatHeadImgFileId());
                userView.setWechatHeadImgUrl(userWechat.getWechatHeadImgUrl());
                userView.setWechatNickName(userWechat.getWechatNickName());
                userView.setWechatCity(userWechat.getWechatCity()); 
                userView.setWechatCountry(userWechat.getWechatCountry());
                userView.setWechatProvince(userWechat.getWechatProvince());
                userView.setWechatSex(userWechat.getWechatSex());
                userView.setWehchatPrivilege(userWechat.getWehchatPrivilege());
            }

            userService.saveOrUpdate(userView);
        }

        return renderJson(true);
    }


}