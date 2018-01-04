package com.nowui.cloud.base.file.rpc.fallback;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import org.springframework.stereotype.Component;

/**
 * 文件服务调用异常处理
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "FileRpcFallback")
public class FileRpcFallback implements FileRpc {

    @Override
    public File find(String fileId) {
        return null;
    }

}
