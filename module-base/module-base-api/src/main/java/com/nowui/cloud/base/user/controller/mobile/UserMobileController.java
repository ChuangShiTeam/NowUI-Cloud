package com.nowui.cloud.base.user.controller.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.entity.UserNickName;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.router.UserAvatarRouter;
import com.nowui.cloud.base.user.router.UserNickNameRouter;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.file.file.entity.File;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户移动端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "用户", description = "用户移动端接口管理")
@RestController
public class UserMobileController extends BaseController {
    
    @Autowired
    private UserService userService;
    
    @ApiOperation(value = "用户忘记密码找回")
    @RequestMapping(value = "/user/mobile/v1/forget/password/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> forgetPasswordFindV1() {
        UserPassword userPassword = getEntry(UserPassword.class);
        validateRequest(
                userPassword,
                UserPassword.APP_ID,
                UserPassword.USER_ID,
                UserPassword.USER_PASSWORD,
                UserPassword.SYSTEM_REQUEST_USER_ID
        );
        
        Boolean success = userService.updateUserPassword(userPassword.getAppId(), userPassword.getUserId(), userPassword.getUserPassword(), userPassword.getSystemRequestUserId());
        
        return renderJson(success);
    }
    
    @ApiOperation(value = "用户密码更新")
    @RequestMapping(value = "/user/mobile/v1/update/password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updatePasswordV1() {
        UserPassword userPassword = getEntry(UserPassword.class);
        validateRequest(
                userPassword,
                UserPassword.APP_ID,
                UserPassword.SYSTEM_REQUEST_USER_ID,
                UserPassword.USER_PASSWORD
        );
        
        Boolean success = userService.updateUserPassword(userPassword.getAppId(), userPassword.getUserId(), userPassword.getUserPassword(), userPassword.getSystemRequestUserId());
        
        return renderJson(success);
    }
    
    @ApiOperation(value = "用户昵称更新")
    @RequestMapping(value = "/user/mobile/v1/update/nick/name", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateNickNameV1() {
        UserNickName userNickName = getEntry(UserNickName.class);
        validateRequest(
                userNickName,
                UserNickName.APP_ID,
                UserNickName.SYSTEM_REQUEST_USER_ID,
                UserNickName.USER_NICK_NAME
        );
        
        Boolean success = userService.updateUserNickName(userNickName.getAppId(), userNickName.getSystemRequestUserId(), userNickName.getUserNickName(), userNickName.getSystemRequestUserId());

        if (success) {
            userNickName.setUserId(userNickName.getSystemRequestUserId());
            // 发送用户昵称更新消息
            sendMessage(userNickName, UserNickNameRouter.USER_NICK_NAME_V1_UPDATE, userNickName.getAppId(), userNickName.getSystemRequestUserId());

            success = true;
        }            
        return renderJson(success);
    }
    
    @ApiOperation(value = "用户头像更新")
    @RequestMapping(value = "/user/mobile/v1/update/avatar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateAvatarV1() {
        UserAvatar userAvatar = getEntry(UserAvatar.class);
        validateRequest(
                userAvatar,
                UserAvatar.APP_ID,
                UserAvatar.SYSTEM_REQUEST_USER_ID,
                UserAvatar.USER_AVATAR_FILE_ID,
                UserAvatar.USER_AVATAR_FILE_PATH
        );
        
        Boolean result = userService.updateUserAvatar(userAvatar.getAppId(), userAvatar.getSystemRequestUserId(), userAvatar.getUserAvatarFileId(), userAvatar.getUserAvatarFilePath(), userAvatar.getSystemRequestUserId());

        if (result) {
            // 发送用户头像更新消息
            userAvatar.setUserId(userAvatar.getSystemRequestUserId());
            sendMessage(userAvatar, UserAvatarRouter.USER_AVATAR_V1_UPDATE, userAvatar.getAppId(), userAvatar.getSystemRequestUserId());
        }                
        return renderJson(result);
    }


}