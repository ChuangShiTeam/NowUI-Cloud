package com.nowui.cloud.base.member.rpc.fallback;

import com.nowui.cloud.base.member.entity.Member;
import com.nowui.cloud.base.member.rpc.MemberRpc;
import org.springframework.stereotype.Component;

/**
 * 会员服务调用异常处理
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component(value = "MemberRpcFallback")
public class MemberRpcFallback implements MemberRpc {

}
