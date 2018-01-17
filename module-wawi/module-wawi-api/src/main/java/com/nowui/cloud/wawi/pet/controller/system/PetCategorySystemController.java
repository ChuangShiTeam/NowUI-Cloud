package com.nowui.cloud.wawi.pet.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.wawi.pet.entity.PetCategory;
import com.nowui.cloud.wawi.pet.rpc.PetCategoryRpc;
import com.nowui.cloud.wawi.pet.service.PetCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 宠物分类系统端控制器
 *
 * @author marcus
 *
 * 2018-01-16
 */
@Api(value = "宠物分类", description = "宠物分类系统端接口管理")
@RestController
public class PetCategorySystemController implements PetCategoryRpc {

}