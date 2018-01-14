package com.nowui.cloud.base.file.controller.system;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.file.service.FileService;
import com.nowui.cloud.util.Util;

import io.swagger.annotations.Api;

/**
 * 文件系统端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "文件", description = "文件系统端接口管理")
@RestController
public class FileSystemController implements FileRpc {
    
    @Autowired
    private FileService fileService;

    @Override
    public File find(String fileId) {
        if (Util.isNullOrEmpty(fileId)) {
            return null;
        }
        System.out.println(fileId);
        File file = fileService.find(fileId);
        file.defaultKeep();
        System.out.println(file.toJSONString());
        return file;
    }

    @Override
    public String downloadWechatHeadImgToNative(String appId, String userId, String wechatHeadImgUrl) {
        File file = fileService.downloadWechatHeadImgToNative(appId, userId, wechatHeadImgUrl);
        if (file == null) {
            return null;
        }
        return file.getFileId();
    }

    @Override
    public List<File> finds(String fileIds) {
        if (Util.isNullOrEmpty(fileIds)) {
            return null;
        }
        List<String> fileIdList = JSONArray.parseArray(fileIds, String.class);
        
        if (Util.isNullOrEmpty(fileIdList)) {
            return null;
        }
        
        List<File> fileList = fileIdList.stream().map((fileId) -> find(fileId)).collect(Collectors.toList());
        
        return fileList;
    }

}