package com.nowui.cloud.base.user.rpc.fallback;

import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.rpc.UserRpc;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 用户服务调用异常处理
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "UserRpcFallback")
public class UserRpcFallback implements UserRpc {

	@Override
	public Integer count(String appId, String userType, String userAccount, String userNickName, String userName,
			String userMobile) {
		return 0;
	}

	@Override
	public List<User> list(String appId, String userType, String userAccount, String userNickName, String userName, String userMobile,
			Integer pageIndex, Integer pageSize) {
		return null;
	}

}
