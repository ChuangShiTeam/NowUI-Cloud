package com.nowui.cloud.file.file.rpc;

import java.util.List;

import com.nowui.cloud.file.file.view.FileView;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nowui.cloud.file.file.entity.File;

/**
 * 文件服务调用
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component(value = "fileRpc")
@FeignClient(name = "module-file")
public interface FileRpc {
    
    /**
     * 文件查找
     *
     * @param fileId 文件编号
     * @return file 文件
     */
    @RequestMapping(value = "/file/system/v1/find", method = RequestMethod.POST)
    FileView findV1(@RequestParam(value = "fileId", required = true) String fileId);

    /**
     * 文件查找
     *
     * @param fileId 文件编号
     * @return file 文件
     */
    @RequestMapping(value = "/file/system/mysql/v1/find", method = RequestMethod.POST)
    File findByMysqlV1(@RequestParam(value = "fileId", required = true) String fileId);
    
    /**
     * 多文件查找
     *
     * @param fileIds 文件编号列表
     * @return file 文件列表
     */
    @RequestMapping(value = "/file/system/v1/finds", method = RequestMethod.POST)
    List<File> findsV1(@RequestParam(value = "fileIds", required = true) String fileIds);
    
    /**
     * 下载微信头像到本地并保存文件
     *
     * @param appId
     * @param userId
     * @param wechatHeadImgUrl
     * @return
     */
    @RequestMapping(value = "/file/system/v1/download/wechat/head/img/to/native", method = RequestMethod.POST)
    String downloadWechatHeadImgToNativeV1(
            @RequestParam(value = "appId", required = true) String appId,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "wechatHeadImgUrl", required = true) String wechatHeadImgUrl
    );

}