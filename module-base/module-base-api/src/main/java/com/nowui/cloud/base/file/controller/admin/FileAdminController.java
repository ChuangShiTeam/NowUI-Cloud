package com.nowui.cloud.base.file.controller.admin;
import java.util.List;
import java.util.Map;

import com.nowui.cloud.base.file.router.FileRouter;
import com.nowui.cloud.base.file.view.FileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.service.FileService;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.exception.BusinessException;

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
    @RequestMapping(value = "/file/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        File fileEntity = getEntry(File.class);

        validateRequest(
                fileEntity,
                File.APP_ID,
                File.SYSTEM_REQUEST_USER_ID,
                File.FILE_NAME,
                File.FILE_TYPE,
                File.PAGE_INDEX,
                File.PAGE_SIZE
        );

        Integer resultTotal = fileService.countForAdmin(fileEntity.getAppId(), fileEntity.getSystemRequestUserId(), fileEntity.getFileName(), fileEntity.getFileType());
        List<FileView> resultList = fileService.listForAdmin(fileEntity.getAppId(), fileEntity.getSystemRequestUserId(), fileEntity.getFileName(), fileEntity.getFileType(), fileEntity.getPageIndex(), fileEntity.getPageSize());

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
    @RequestMapping(value = "/file/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        File fileEntity = getEntry(File.class);

        validateRequest(
                fileEntity,
                File.APP_ID,
                File.FILE_ID
        );

        FileView result = fileService.find(fileEntity.getFileId());

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
                File.FILE_IS_OUTER,
                File.FILE_OUTER_LINK
        );

        return renderJson(result);
    }

    @ApiOperation(value = "删除文件")
    @RequestMapping(value = "/file/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        File fileEntity = getEntry(File.class);

        validateRequest(
                fileEntity,
                File.FILE_ID,
                File.APP_ID,
                File.SYSTEM_VERSION
        );

        File result = fileService.delete(fileEntity.getFileId(), fileEntity.getSystemUpdateUserId(),fileEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            //sendMessage(result, FileRouter.FILE_V1_DELETE, fileEntity.getAppId(), fileEntity.getSystemRequestUserId());

            success = true;
        }

        return renderJson(success);
    }
    
    @ApiOperation(value = "图片上传")
    @RequestMapping(value = "/file/admin/v1/image/upload", method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadImageV1(
            @RequestParam(File.APP_ID) String appId,
            @RequestParam(File.SYSTEM_REQUEST_USER_ID) String systemRequestUserId,
            @RequestParam("file") MultipartFile[] multipartFiles) {
        if (multipartFiles.length == 0) {
            throw new BusinessException("上传文件为空");
        }
        List<File> fileList = fileService.uploadImage(appId, systemRequestUserId, multipartFiles);
        
        validateResponse(
            File.FILE_ID,
            File.FILE_NAME,
            File.FILE_PATH
        );
        
        return renderJson(fileList);
    }
    
    @ApiOperation(value = "base64图片上传")
    @RequestMapping(value = "/file/admin/image/base64/upload", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> imageBase64Upload() {
        File fileEntity = getEntry(File.class);

        String appId = fileEntity.getString(File.APP_ID);
        String userId = fileEntity.getString(File.SYSTEM_REQUEST_USER_ID);
        String base64Data = fileEntity.getString(Constant.DATA);
        
        File file = fileService.uploadBase64(appId, userId, base64Data);
        
        validateResponse(
                File.FILE_ID,
                File.FILE_NAME,
                File.FILE_PATH
            );
        return renderJson(file);
    }

    @ApiOperation(value = "图片数据同步")
    @RequestMapping(value = "/file/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<File> fileList = fileService.listByMysql();

        for (File file : fileList) {
            FileView fileView = new FileView();
            fileView.putAll(file);

            fileService.update(fileView);
        }

        return renderJson(true);
    }

}