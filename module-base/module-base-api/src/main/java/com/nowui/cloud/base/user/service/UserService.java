package com.nowui.cloud.base.user.service;
import java.util.List;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.service.BaseService;

/**
 * 用户业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface UserService extends BaseService<User,UserView> {

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
     * @return List<UserView> 用户视图列表
     */
    List<UserView> list(String appId, String userType, Integer pageIndex, Integer pageSize);
    
}
