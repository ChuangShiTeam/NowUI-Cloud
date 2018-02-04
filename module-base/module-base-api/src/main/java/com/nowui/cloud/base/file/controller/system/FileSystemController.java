package com.nowui.cloud.base.file.controller.system;
import java.util.List;
import java.util.stream.Collectors;
import com.nowui.cloud.base.file.view.FileView;
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
    public File findV1(String fileId) {

        return null;
//        if (Util.isNullOrEmpty(fileId)) {
//            return null;
//        }
//        FileView file = fileService.find(fileId);
//        if (Util.isNullOrEmpty(file)) {
//            return null;
//        }
////        file.removeBaseTableField();
//        return file;
    }

    @Override
    public String downloadWechatHeadImgToNativeV1(String appId, String userId, String wechatHeadImgUrl) {
        File file = fileService.downloadWechatHeadImgToNative(appId, userId, wechatHeadImgUrl);
        if (file == null) {
            return null;
        }
        return file.getFileId();
    }

    @Override
    public List<File> findsV1(String fileIds) {
        return null;
//        if (Util.isNullOrEmpty(fileIds)) {
//            return null;
//        }
//        List<String> fileIdList = JSONArray.parseArray(fileIds, String.class);
//
//        if (Util.isNullOrEmpty(fileIdList)) {
//            return null;
//        }
//
//        List<FileView> fileList = fileIdList.stream().map(fileId -> findV1(fileId)).filter(file -> !Util.isNullOrEmpty(file)).collect(Collectors.toList());
//
//        return fileList;
    }

}