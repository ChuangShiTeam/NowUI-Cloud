package com.nowui.cloud.base.file.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
                File.PAGE_INDEX,
                File.PAGE_SIZE
        );

        Integer resultTotal = fileService.adminCount(body.getAppId() , body.getFileName());
        List<File> resultList = fileService.adminList(body.getAppId(), body.getFileName(), body.getM(), body.getN());

        validateResponse(
                File.FILE_ID,
                File.FILE_TYPE,
                File.FILE_NAME,
                File.FILE_SUFFIX,
                File.FILE_SIZE,
                File.FILE_PATH,
                File.FILE_COVER_IMAGE,
                File.FILE_IS_EXTERNAL
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

    @ApiOperation(value = "新增文件")
    @RequestMapping(value = "/file/admin/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> save(@RequestBody File body) {
        validateRequest(
                body,
                File.APP_ID,
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

        Boolean result = fileService.save(body, body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改文件")
    @RequestMapping(value = "/file/admin/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> update(@RequestBody File body) {
        validateRequest(
                body,
                File.FILE_ID,
                File.APP_ID,
                File.FILE_TYPE,
                File.FILE_NAME,
                File.FILE_SUFFIX,
                File.FILE_SIZE,
                File.FILE_PATH,
                File.FILE_THUMBNAIL_PATH,
                File.FILE_ORIGINAL_PATH,
                File.FILE_COVER_IMAGE,
                File.FILE_IS_EXTERNAL,
                File.SYSTEM_VERSION
        );

        Boolean result = fileService.update(body, body.getFileId(), body.getSystemRequestUserId(), body.getSystemVersion());

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

}