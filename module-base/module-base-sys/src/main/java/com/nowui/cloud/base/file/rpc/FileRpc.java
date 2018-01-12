package com.nowui.cloud.base.file.rpc;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.base.file.entity.File;

/**
 * 文件服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "fileRpc")
@FeignClient(name = "module-base")
public interface FileRpc {
    
    /**
     * 文件查找
     *
     * @param fileId 文件编号
     * @return file 文件
     */
    @RequestMapping(value = "/file/system/v1/find", method = RequestMethod.POST)
    File find(@RequestParam(value = "fileId", required = true) String fileId);
    
    /**
     * 多文件查找
     *
     * @param fileIds 文件编号列表
     * @return file 文件列表
     */
    @RequestMapping(value = "/file/system/v1/finds", method = RequestMethod.POST)
    List<File> finds(@RequestParam(value = "fileIds", required = true) String fileIds);
    
    /**
     * 下载微信头像到本地并保存文件
     * 
     * @param fileId
     * @return
     */
    @RequestMapping(value = "/file/system/v1/download/wechat/head/img/to/native", method = RequestMethod.POST)
    String downloadWechatHeadImgToNative(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "wechatHeadImgUrl", required = true) String wechatHeadImgUrl
    );

}