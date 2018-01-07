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
     * @param userAccount 用户账号
     * @param userNickName 用户昵称
     * @param userName 用户姓名
     * @param userMobile 用户手机号码
     * @return Integer 用户数量
     */
    @RequestMapping(value = "/user/system/count", method = RequestMethod.GET)
    Integer count(
            @RequestParam(value = "appId", required = true) String appId, 
            @RequestParam(value = "userType", required = true) String userType, 
            @RequestParam(value = "userAccount", required = true) String userAccount, 
            @RequestParam(value = "userNickName", required = true) String userNickName, 
            @RequestParam(value = "userName", required = true) String userName, 
            @RequestParam(value = "userMobile", required = true) String userMobile
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
    @RequestMapping(value = "/user/system/list", method = RequestMethod.GET)
    List<User> list(
            @RequestParam(value = "appId", required = true) String appId, 
            @RequestParam(value = "userType", required = true) String userType, 
            @RequestParam(value = "userAccount", required = true) String userAccount, 
            @RequestParam(value = "userNickName", required = true) String userNickName, 
            @RequestParam(value = "userName", required = true) String userName, 
            @RequestParam(value = "userMobile", required = true) String userMobile, 
            @RequestParam(value = "pageIndex", required = true) Integer pageIndex, 
            @RequestParam(value = "pageSize", required = true) Integer pageSize
    );

    /**
     * 根据ID查询用户信息
     * 
     * @param userId 用户编号
     * @return User 用户信息
     */
    @RequestMapping(value = "/user/system/find", method = RequestMethod.GET)
    User find(@RequestParam(value = "userId", required = true) String userId);
}