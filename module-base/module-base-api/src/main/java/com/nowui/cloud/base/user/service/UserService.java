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
     * @param userAccount 账号
     * @param userName 姓名
     * @param userMobile 手机号码
     * @return Integer 用户统计
     */
    Integer adminCount(String appId, String userType, String userAccount, String userName, String userMobile);

    /**
     * 用户列表
     *
     * @param appId 应用编号
     * @param userType 类型
     * @param userAccount 账号
     * @param userName 姓名
     * @param userMobile 手机号码
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<User> 用户列表
     */
    List<User> adminList(String appId, String userType, String userAccount, String userName, String userMobile, Integer m, Integer n);
}
