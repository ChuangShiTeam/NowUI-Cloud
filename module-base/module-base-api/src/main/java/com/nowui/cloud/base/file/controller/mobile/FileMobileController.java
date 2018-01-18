package com.nowui.cloud.base.file.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.service.FileService;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件移动端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "文件", description = "文件移动端接口管理")
@RestController
public class FileMobileController extends BaseController {
    
    @Autowired
    private FileService fileService;
    
    @ApiOperation(value = "图片上传")
    @RequestMapping(value = "/file/mobile/v1/image/upload", method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadImageV1(
            @RequestParam(File.APP_ID) String appId,
            @RequestParam(File.SYSTEM_REQUEST_USER_ID) String systemRequestUserId,
            @RequestParam("file") MultipartFile[] multipartFiles) {
        if (multipartFiles.length == 0) {
            throw new RuntimeException("上传文件为空");
        }
        List<File> fileList = fileService.uploadImage(appId, systemRequestUserId, multipartFiles);
        
        validateResponse(
            File.FILE_ID,
            File.FILE_NAME,
            File.FILE_PATH
        );
        
        return renderJson(fileList);
    }

}