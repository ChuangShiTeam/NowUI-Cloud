package com.nowui.cloud.base.user.rpc;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.rpc.fallback.UserRpcFallback;

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
@Component(value = "UserRpc")
@FeignClient(name = "module-base", fallback = UserRpcFallback.class)
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
	Integer count(String appId, String userType, String userAccount, String userNickName, String userName, String userMobile);
	
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
	List<User> list(String appId, String userType, String userAccount, String userNickName, String userMobile, Integer pageIndex, Integer pageSize);

}