package com.nowui.cloud.base.file.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.base.file.service.FileService;

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

    /**
     * 根据ID查询文件信息
     */
    @Override
    public File find(String fileId) {
        System.out.println(fileId);
        File file = fileService.find(fileId);
        file.defaultKeep();
        System.out.println(file.toJSONString());
        return file;
    }

}