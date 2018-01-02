package com.nowui.cloud.base.file.rpc;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.fallback.FileRpcFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文件服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "FileRpc")
@FeignClient(name = "module-base", fallback = FileRpcFallback.class)
public interface FileRpc {

}