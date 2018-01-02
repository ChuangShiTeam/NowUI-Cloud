package com.nowui.cloud.base.file.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.service.FileService;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件管理端控制器
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Api(value = "文件", description = "文件管理端接口管理")
@RestController
public class FileAdminController extends BaseController {

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件列表")
    @RequestMapping(value = "/file/admin/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> list(@RequestBody File body) {
        validateRequest(
                body,
                File.APP_ID,
                File.FILE_NAME,
                File.FILE_TYPE,
                File.PAGE_INDEX,
                File.PAGE_SIZE
        );

        Integer resultTotal = fileService.adminCount(body.getAppId(), body.getFileName(), body.getFileType());
        List<File> resultList = fileService.adminList(body.getAppId(), body.getFileName(), body.getFileType(), body.getM(), body.getN());

        validateResponse(
                File.FILE_ID,
                File.FILE_TYPE,
                File.FILE_NAME,
                File.FILE_PATH,
                File.FILE_COVER_IMAGE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文件信息")
    @RequestMapping(value = "/file/admin/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> find(@RequestBody File body) {
        validateRequest(
                body,
                File.APP_ID,
                File.FILE_ID
        );

        File result = fileService.find(body.getFileId());

        validateResponse(
                File.FILE_ID,
                File.FILE_TYPE,
                File.FILE_NAME,
                File.FILE_SUFFIX,
                File.FILE_SIZE,
                File.FILE_PATH,
                File.FILE_THUMBNAIL_PATH,
                File.FILE_ORIGINAL_PATH,
                File.FILE_COVER_IMAGE,
                File.FILE_IS_EXTERNAL
        );

        return renderJson(result);
    }

    @ApiOperation(value = "删除文件")
    @RequestMapping(value = "/file/admin/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> delete(@RequestBody File body) {
        validateRequest(
                body,
                File.FILE_ID,
                File.APP_ID,
                File.SYSTEM_VERSION
        );

        Boolean result = fileService.delete(body.getFileId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }
    
    @ApiOperation(value = "图片上传")
    @RequestMapping(value = "/file/admin/image/upload", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> upload(
            @RequestParam(File.APP_ID) String appId,
            @RequestParam(File.SYSTEM_REQUEST_USER_ID) String systemRequestUserId,
            @RequestParam("file") CommonsMultipartFile[] commonsMultipartFiles) {
        if (commonsMultipartFiles.length == 0) {
            throw new RuntimeException("上传文件为空");
        }
        List<File> fileList = fileService.imageUpload(appId, systemRequestUserId, commonsMultipartFiles);
        
        validateResponse(
            File.FILE_ID,
            File.FILE_NAME,
            File.FILE_PATH
        );
        
        return renderJson(fileList);
    }
    
    @ApiOperation(value = "base64图片上传")
    @RequestMapping(value = "/file/admin/image/base64/upload", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> imageBase64Upload(@RequestBody JSONObject body) {
        String appId = body.getString(File.APP_ID);
        String userId = body.getString(File.SYSTEM_REQUEST_USER_ID);
        String base64Data = body.getString(Constant.DATA);
        
        File file = fileService.uploadBase64(appId, userId, base64Data);
        
        validateResponse(
                File.FILE_ID,
                File.FILE_NAME,
                File.FILE_PATH
            );
        return renderJson(file);
    }

}