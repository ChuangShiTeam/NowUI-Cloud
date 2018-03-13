package com.nowui.cloud.file.file.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.file.file.service.FileService;
import com.nowui.cloud.file.file.view.FileView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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
    
    @ApiOperation(value = "图片上传", httpMethod = "POST")
    @RequestMapping(value = "/file/mobile/v1/image/upload", method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadImageV1(
            @RequestParam(FileView.APP_ID) String appId,
            @RequestParam(FileView.SYSTEM_REQUEST_USER_ID) String systemRequestUserId,
            @RequestParam("file") MultipartFile[] multipartFiles) {
        if (multipartFiles.length == 0) {
            throw new BusinessException("上传文件为空");
        }
        List<File> fileList = fileService.uploadImage(appId, systemRequestUserId, multipartFiles);

        for(File file : fileList) {
            FileView fileView = new FileView();
            fileView.putEntry(file);
            
            fileService.save(fileView);
        }

        validateResponse(
            FileView.FILE_ID,
            FileView.FILE_NAME,
            FileView.FILE_PATH
        );

        return renderJson(fileList);
    }

    @ApiOperation(value = "base64图片上传", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = FileView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = FileView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = FileView.BASE_64_DATA, value = "版本号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/file/mobile/v1/image/base64/upload", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> imageBase64UploadV1(@ApiIgnore FileView fileView) {

        File file = fileService.uploadBase64(fileView.getAppId(), fileView.getSystemRequestUserId(), fileView.getBase64Data());

        if (file != null) {
            fileView.putEntry(file);
            
            fileService.save(fileView);
        }

        validateResponse(
            FileView.FILE_ID,
            FileView.FILE_NAME,
            FileView.FILE_PATH
        );
        return renderJson(file);
    }

}