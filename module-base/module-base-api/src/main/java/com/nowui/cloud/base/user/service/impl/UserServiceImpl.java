package com.nowui.cloud.base.user.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.mapper.UserMapper;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserAvatarService;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.base.user.service.UserIdcardService;
import com.nowui.cloud.base.user.service.UserMessageService;
import com.nowui.cloud.base.user.service.UserNickNameService;
import com.nowui.cloud.base.user.service.UserRoleService;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.service.UserWechatService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.Util;

/**
 * 用户业务实现
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

	@Autowired
	private FileRpc fileRpc;
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserAvatarService userAvatarService;
	
	@Autowired
	private UserEmailService userEmailService;
	
	@Autowired
	private UserIdcardService userIdcardService;
	
	@Autowired
	private UserMessageService userMessageService;
	
	@Autowired
	private UserNickNameService userNickNameService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
    private UserWechatService userWechatService;
	
    @Override
    public Integer count(String appId, String userType) {
        Integer count = count(
                new BaseWrapper<User>()
                        .eq(User.APP_ID, appId)
                        .likeAllowEmpty(User.USER_TYPE, userType)
                        .eq(User.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<User> list(String appId, String userType, Integer pageIndex, Integer pageSize) {
        List<User> userList = list(
                new BaseWrapper<User>()
                        .eq(User.APP_ID, appId)
                        .likeAllowEmpty(User.USER_TYPE, userType)
                        .eq(User.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(User.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );
        
        //查询用户头像
        for (User user : userList) {
        	if (!Util.isNullOrEmpty(user.getUserAvatar())) {
        		File file = fileRpc.find(user.getUserAvatar());
        		if (!Util.isNullOrEmpty(file)) {
        			file.keep(File.FILE_ID, File.FILE_PATH);
                    user.put(User.USER_AVATAR, file);
        		}
        	}
        }

        return userList;
    }
    
    @Override
    public Boolean save(User user, String id, String systemCreateUserId) {
        if (Util.isNullOrEmpty(user.getUserPassword())) {
           throw new RuntimeException("用户密码不能为空");
        }
        user.setUserPassword(Util.generatePassword(user.getUserPassword()));
        return super.save(user, id, systemCreateUserId);
    }
    
    @Override
    public Boolean update(User user, String id, String systemUpdateUserId, Integer systemVersion) {
        if (!Util.isNullOrEmpty(user.getUserPassword())) {
            user.setUserPassword(Util.generatePassword(user.getUserPassword()));
        }
        return super.update(user, id, systemUpdateUserId, systemVersion);
    }

    @Override
    public User findByObjectIdAndUserType(String objectId, String userType) {
        List<User> userList = list(
                new BaseWrapper<User>()
                        .eq(User.OBJECT_ID, objectId)
                        .eq(User.USER_TYPE, userType)
                        .eq(User.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(User.SYSTEM_CREATE_TIME))
        );
        if (userList == null || userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Boolean deleteByObjectIdAndUserType(String objectId, String userType, String systemRequestUserId) {
        User user = findByObjectIdAndUserType(objectId, userType);
        if (user == null) {
            return false;
        }
        return delete(user.getUserId(), systemRequestUserId, user.getSystemVersion());
    }

}