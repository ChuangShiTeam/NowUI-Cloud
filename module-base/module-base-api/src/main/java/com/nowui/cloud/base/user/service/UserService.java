package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.User;

import java.util.List;

/**
 * 用户业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface UserService extends BaseService<User> {

    /**
     * 用户统计
     *
     * @param appId 应用编号
     * @param userType 类型
     * @return Integer 用户统计
     */
    Integer count(String appId, String userType);

    /**
     * 用户列表
     *
     * @param appId 应用编号
     * @param userType 类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<User> 用户列表
     */
    List<User> list(String appId, String userType, Integer pageIndex, Integer pageSize);

    /**
     * 根据用户主体编号和用户类型查询用户信息
     * 
     * @param objectId 用户主体编号
     * @param userType 用户类型
     * @return User 用户信息
     */
    User findByObjectIdAndUserType(String objectId, String userType);

    /**
     * 根据用户主体编号和用户类型逻辑删除用户信息
     * 
     * @param objectId 用户主体编号
     * @param userType 用户信息
     * @param systemRequestUserId 请求用户编号
     * @param Boolean 成功或失败
     */
    Boolean deleteByObjectIdAndUserType(String objectId, String userType, String systemRequestUserId);
}
