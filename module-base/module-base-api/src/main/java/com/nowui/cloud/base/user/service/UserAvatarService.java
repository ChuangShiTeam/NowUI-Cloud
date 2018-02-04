package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserAvatar;
import com.nowui.cloud.base.user.view.UserAvatarView;
import com.nowui.cloud.service.SuperService;

/**
 * 用户头像业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserAvatarService extends SuperService<UserAvatar,UserAvatarView> {
    
    /**
     * 根据用户编号查询用户头像信息
     * 
     * @param userId 用户编号
     * @return UserAvatar 用户头像
     */
    UserAvatar findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户头像信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deleteByUserId(String userId, String systemUpdateUserId);

}
