package com.nowui.cloud.base.admin.rpc.fallback;

import com.nowui.cloud.base.admin.entity.Admin;
import com.nowui.cloud.base.admin.rpc.AdminRpc;
import org.springframework.stereotype.Component;

/**
 * 管理员服务调用异常处理
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "AdminRpcFallback")
public class AdminRpcFallback implements AdminRpc {

}
