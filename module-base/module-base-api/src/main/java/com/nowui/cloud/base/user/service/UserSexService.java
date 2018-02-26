package com.nowui.cloud.base.user.service;
import com.nowui.cloud.base.user.entity.UserSex;
import com.nowui.cloud.base.user.view.UserSexView;
import com.nowui.cloud.service.SuperService;

/**
 * 用户性别业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserSexService extends SuperService<UserSex, UserSexView> {
    
    /**
     * 根据用户编号查询用户账号信息
     * 
     * @param userId 用户编号
     * @return UserIdcard 用户性别信息
     */
    UserSex findByUserId(String userId);
    
    /**
     * 根据用户编号逻辑删除用户性别信息
     * 
     * @param userId 用户编号
     * @param systemUpdateUserId 更新用户编号
     * @return
     */
    void deleteByUserId(String userId, String systemUpdateUserId);

}
