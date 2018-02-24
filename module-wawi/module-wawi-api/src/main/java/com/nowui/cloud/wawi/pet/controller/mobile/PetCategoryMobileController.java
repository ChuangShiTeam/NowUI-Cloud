package com.nowui.cloud.wawi.pet.controller.mobile;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.pet.entity.PetCategory;
import com.nowui.cloud.wawi.pet.service.PetCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 宠物分类移动端控制器
 *
 * @author marcus
 *
 * 2018-01-24
 */
@Api(value = "宠物分类", description = "宠物分类移动端接口管理")
@RestController
public class PetCategoryMobileController extends BaseController {

    @Autowired
    private PetCategoryService petCategoryService;
    
    @Autowired
    private FileRpc fileRpc;

    @ApiOperation(value = "获取宠物分类列表")
    @RequestMapping(value = "/wawi/pet/category/mobile/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> petCategoryListV1() {
        PetCategory petCategoryEntity = getEntry(PetCategory.class);
        validateRequest(
                petCategoryEntity,
                PetCategory.APP_ID
        );

        List<PetCategory> resultList = petCategoryService.topList(petCategoryEntity.getAppId());
        
        String fileIds = Util.beanToFieldString(resultList, PetCategory.PET_CATEGORY_IMAGE);
        List<File> fileList = fileRpc.findsV1(fileIds);
        resultList = Util.beanReplaceField(resultList, PetCategory.PET_CATEGORY_IMAGE, fileList, File.FILE_PATH);

        validateResponse(
                PetCategory.PET_CATEGORY_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_IMAGE
        );

        return renderJson(resultList);
    }

    @ApiOperation(value = "获取宠物子分类列表")
    @RequestMapping(value = "/wawi/pet/category/mobile/v1/child/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> childListV1(){
        PetCategory petCategoryEntity = getEntry(PetCategory.class);
        validateRequest(
                petCategoryEntity,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_ID
        );

        List<PetCategory> resultList = petCategoryService.listByParentId(petCategoryEntity.getPetCategoryId());
        
        String fileIds = Util.beanToFieldString(resultList, PetCategory.PET_CATEGORY_IMAGE);
        List<File> fileList = fileRpc.findsV1(fileIds);
        resultList = Util.beanReplaceField(resultList, PetCategory.PET_CATEGORY_IMAGE, fileList, File.FILE_PATH);

        validateResponse(
                PetCategory.PET_CATEGORY_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_IMAGE
        );

        return renderJson(resultList);
    }

}