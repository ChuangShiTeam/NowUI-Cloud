package com.nowui.cloud.base.user.rpc;

import com.nowui.cloud.base.user.entity.User;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户服务调用
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "userRpc")
@FeignClient(name = "module-base")
public interface UserRpc {

    /**
     * 用户统计
     *
     * @param appId 应用编号
     * @param userType 用户类型
     * @return Integer 用户数量
     */
    @RequestMapping(value = "/user/system/v1/count", method = RequestMethod.POST)
    Integer countV1(
            @RequestParam(value = "appId", required = true) String appId, 
            @RequestParam(value = "userType", required = true) String userType
    );

    /**
     * 用户查询
     *
     * @param appId 应用编号
     * @param userType 用户类型
     * @param userAccount 用户账号
     * @param userNickName 用户昵称
     * @param userName 用户姓名
     * @param userMobile 用户手机号码
     * @param pageIndex 页码
     * @param pageSize	每页个数
     * @return List<User> 用户列表
     */
    @RequestMapping(value = "/user/system/v1/list", method = RequestMethod.POST)
    List<User> listV1(
            @RequestParam(value = "appId", required = true) String appId, 
            @RequestParam(value = "userType", required = true) String userType, 
            @RequestParam(value = "pageIndex", required = true) Integer pageIndex, 
            @RequestParam(value = "pageSize", required = true) Integer pageSize
    );

    /**
     * 根据ID查询用户信息
     * 
     * @param userId 用户编号
     * @return User 用户信息
     */
    @RequestMapping(value = "/user/system/v1/find", method = RequestMethod.POST)
    User findV1(@RequestParam(value = "userId", required = true) String userId);
    
    /**
     * 根据用户账号查询用户信息
     * 
     * @param appId 应用编号
     * @param userAccount 用户账号
     * @return User 用户信息
     */
    @RequestMapping(value = "/user/system/v1/find/by/user/account", method = RequestMethod.POST)
    User findByUserAccount(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userAccount", required = true) String userAccount
    );

}