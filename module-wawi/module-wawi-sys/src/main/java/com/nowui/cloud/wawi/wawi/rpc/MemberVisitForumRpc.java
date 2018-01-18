package com.nowui.cloud.wawi.wawi.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 会员访问圈子服务调用
 *
 * @author marcus
 *
 * 2018-01-17
 */
@Component(value = "memberVisitForumRpc")
@FeignClient(name = "module-wawi")
public interface MemberVisitForumRpc {

}