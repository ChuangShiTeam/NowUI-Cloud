package com.nowui.cloud.wawi.pet.controller.admin;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.rpc.FileRpc;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.pet.entity.PetCategory;
import com.nowui.cloud.wawi.pet.service.PetCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 宠物分类管理端控制器
 *
 * @author marcus
 *
 * 2018-01-24
 */
@Api(value = "宠物分类", description = "宠物分类管理端接口管理")
@RestController
public class PetCategoryAdminController extends BaseController {

    @Autowired
    private PetCategoryService petCategoryService;
    
    @Autowired
    private FileRpc fileRpc;

    @ApiOperation(value = "宠物分类列表")
    @RequestMapping(value = "/pet/category/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        PetCategory petCategoryEntity = getEntry(PetCategory.class);

        validateRequest(
                petCategoryEntity,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PAGE_INDEX,
                PetCategory.PAGE_SIZE
        );
        
        Integer resultTotal;
        List<PetCategory> resultList;
        if (Util.isNullOrEmpty(petCategoryEntity.getPetCategoryName()) && Util.isNullOrEmpty(petCategoryEntity.getPetCategoryCode())) {
            resultTotal = petCategoryService.countTop(petCategoryEntity.getAppId());
            resultList = petCategoryService.adminTreeList(petCategoryEntity.getAppId(), petCategoryEntity.getPageIndex(), petCategoryEntity.getPageSize());
        } else {
            resultTotal = petCategoryService.countForAdmin(petCategoryEntity.getAppId(), petCategoryEntity.getPetCategoryName(), petCategoryEntity.getPetCategoryCode());
            resultList = petCategoryService.listForAdmin(petCategoryEntity.getAppId(), petCategoryEntity.getPetCategoryName(), petCategoryEntity.getPetCategoryCode(), petCategoryEntity.getPageIndex(), petCategoryEntity.getPageSize());
        }
        
        String fileIds = Util.beanToFieldString(resultList, PetCategory.PET_CATEGORY_IMAGE);
        List<File> fileList = fileRpc.findsV1(fileIds);
        
        resultList = Util.beanReplaceField(resultList, PetCategory.PET_CATEGORY_IMAGE, fileList, File.FILE_PATH);
                
        validateResponse(
                PetCategory.PET_CATEGORY_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PET_CATEGORY_SORT
        );

        return renderJson(resultTotal, resultList);
    }
    
    @ApiOperation(value = "宠物分类树形列表")
    @RequestMapping(value = "/article/category/admin/v1/all/tree/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> allTreeListV1() {
        PetCategory petCategoryEntity = getEntry(PetCategory.class);

        validateRequest(
            petCategoryEntity, 
            PetCategory.APP_ID
        );

        List<PetCategory> resultList = petCategoryService.adminAllTreeList(petCategoryEntity.getAppId());

        validateResponse(PetCategory.PET_CATEGORY_ID, PetCategory.PET_CATEGORY_NAME, Constant.CHILDREN);

        return renderJson(resultList);
    }

    @ApiOperation(value = "宠物分类信息")
    @RequestMapping(value = "/pet/category/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        PetCategory petCategoryEntity = getEntry(PetCategory.class);

        validateRequest(
                petCategoryEntity,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_ID
        );

        PetCategory result = petCategoryService.find(petCategoryEntity.getPetCategoryId());

        File file = fileRpc.findV1(result.getPetCategoryImage());
        file.keep(File.FILE_ID, File.FILE_PATH);
        result.put(PetCategory.PET_CATEGORY_IMAGE, file);
        
        validateResponse(
                PetCategory.PET_CATEGORY_ID,
                PetCategory.PET_CATEGORY_PARENT_ID,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PET_CATEGORY_DESCRIPTION,
                PetCategory.PET_CATEGORY_SORT,
                PetCategory.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增宠物分类")
    @RequestMapping(value = "/pet/category/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        PetCategory petCategoryEntity = getEntry(PetCategory.class);
        
        String petCategoryParentPath = "";

        if (Util.isNullOrEmpty(petCategoryEntity.getPetCategoryParentId())) {

            JSONArray jsonArray = new JSONArray();

            petCategoryParentPath = jsonArray.toJSONString();
        } else {
            PetCategory parent = petCategoryService.find(petCategoryEntity.getPetCategoryParentId());

            JSONArray jsonArray = new JSONArray();;
            if (!Util.isNullOrEmpty(parent.getPetCategoryParentPath())) {
                jsonArray = JSONArray.parseArray(parent.getPetCategoryParentPath());
            } 
            jsonArray.add(parent.getPetCategoryId());

            petCategoryParentPath = jsonArray.toJSONString();
        }
        
        petCategoryEntity.setPetCategoryParentPath(petCategoryParentPath);

        validateRequest(
                petCategoryEntity,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_PARENT_ID,
                PetCategory.PET_CATEGORY_PARENT_PATH,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PET_CATEGORY_DESCRIPTION,
                PetCategory.PET_CATEGORY_SORT
        );

        Boolean result = petCategoryService.save(petCategoryEntity, Util.getRandomUUID(), petCategoryEntity.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改宠物分类")
    @RequestMapping(value = "/pet/category/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        PetCategory petCategoryEntity = getEntry(PetCategory.class);

        validateRequest(
                petCategoryEntity,
                PetCategory.PET_CATEGORY_ID,
                PetCategory.APP_ID,
                PetCategory.PET_CATEGORY_PARENT_ID,
                PetCategory.PET_CATEGORY_PARENT_PATH,
                PetCategory.PET_CATEGORY_NAME,
                PetCategory.PET_CATEGORY_CODE,
                PetCategory.PET_CATEGORY_IMAGE,
                PetCategory.PET_CATEGORY_DESCRIPTION,
                PetCategory.PET_CATEGORY_SORT,
                PetCategory.SYSTEM_VERSION
        );

        Boolean result = petCategoryService.update(petCategoryEntity, petCategoryEntity.getPetCategoryId(), petCategoryEntity.getSystemRequestUserId(), petCategoryEntity.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除宠物分类")
    @RequestMapping(value = "/pet/category/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        PetCategory petCategoryEntity = getEntry(PetCategory.class);

        validateRequest(
                petCategoryEntity,
                PetCategory.PET_CATEGORY_ID,
                PetCategory.APP_ID,
                PetCategory.SYSTEM_VERSION
        );

        Boolean result = petCategoryService.delete(petCategoryEntity.getAppId(), petCategoryEntity.getPetCategoryId(), petCategoryEntity.getSystemRequestUserId(), petCategoryEntity.getSystemVersion());

        return renderJson(result);
    }

}