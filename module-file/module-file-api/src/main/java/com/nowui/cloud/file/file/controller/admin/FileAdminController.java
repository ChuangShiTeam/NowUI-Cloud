package com.nowui.cloud.file.file.controller.admin;
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
import com.nowui.cloud.view.CommonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation(value = "文件列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = FileView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = FileView.FILE_NAME, value = "文件名称", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = FileView.FILE_TYPE, value = "文件类型", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/file/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore FileView fileView, @ApiIgnore CommonView commonView) {

        validateRequest(
                fileView,
                FileView.APP_ID,
                FileView.FILE_NAME,
                FileView.FILE_TYPE
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = fileService.countForAdmin(fileView.getAppId(), commonView.getSystemRequestUserId(), fileView.getFileName(), fileView.getFileType());
        List<FileView> resultList = fileService.listForAdmin(fileView.getAppId(), commonView.getSystemRequestUserId(), fileView.getFileName(), fileView.getFileType(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                FileView.FILE_ID,
                FileView.FILE_TYPE,
                FileView.FILE_NAME,
                FileView.FILE_PATH,
                FileView.FILE_COVER_IMAGE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "文件信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = FileView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = FileView.FILE_ID, value = "文件编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/file/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore FileView fileView) {

        validateRequest(
                fileView,
                FileView.APP_ID,
                FileView.FILE_ID
        );

        FileView result = fileService.find(fileView.getFileId());

        validateResponse(
                FileView.FILE_ID,
                FileView.FILE_TYPE,
                FileView.FILE_NAME,
                FileView.FILE_SUFFIX,
                FileView.FILE_SIZE,
                FileView.FILE_PATH,
                FileView.FILE_THUMBNAIL_PATH,
                FileView.FILE_ORIGINAL_PATH,
                FileView.FILE_COVER_IMAGE,
                FileView.FILE_IS_OUTER,
                FileView.FILE_OUTER_LINK
        );

        return renderJson(result);
    }

    @ApiOperation(value = "删除文件", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = FileView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = FileView.FILE_ID, value = "文件编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = FileView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int")
    })
    @RequestMapping(value = "/file/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore FileView fileView, @ApiIgnore CommonView commonView) {

        validateRequest(
                fileView,
                FileView.FILE_ID,
                FileView.APP_ID,
                FileView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        File result = fileService.delete(fileView.getFileId(), fileView.getAppId(), commonView.getSystemRequestUserId(), fileView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            // 删除MongoDB里面存储的视图信息
            fileView.copy(result);
            
            fileService.delete(fileView, fileView.getFileId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "图片上传", httpMethod = "POST")
    @RequestMapping(value = "/file/admin/v1/image/upload", method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadImageV1(
            @RequestParam(FileView.APP_ID) String appId,
            @RequestParam(CommonView.SYSTEM_REQUEST_USER_ID) String systemRequestUserId,
            @RequestParam("file") MultipartFile[] multipartFiles) {
        if (multipartFiles.length == 0) {
            throw new BusinessException("上传文件为空");
        }
        List<File> fileList = fileService.uploadImage(appId, systemRequestUserId, multipartFiles);

        for(File file : fileList) {
            FileView fileView = new FileView();
            fileView.copy(file);
            
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
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = FileView.BASE_64_DATA, value = "版本号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/file/admin/v1/image/base64/upload", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> imageBase64UploadV1(@ApiIgnore FileView fileView, @ApiIgnore CommonView commonView) {

        File file = fileService.uploadBase64(fileView.getAppId(), commonView.getSystemRequestUserId(), fileView.getBase64Data());

        if (file != null) {
            fileView.copy(file);
            
            fileService.save(fileView);
        }

        validateResponse(
            FileView.FILE_ID,
            FileView.FILE_NAME,
            FileView.FILE_PATH
        );
        return renderJson(file);
    }

    @ApiOperation(value = "图片数据同步", httpMethod = "POST")
    @RequestMapping(value = "/file/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<File> fileList = fileService.listByMysql();

        for (File file : fileList) {
            FileView fileView = new FileView();
            fileView.copy(file);

            fileService.saveOrUpdate(fileView, fileView.getFileId());
        }

        return renderJson(true);
    }

}