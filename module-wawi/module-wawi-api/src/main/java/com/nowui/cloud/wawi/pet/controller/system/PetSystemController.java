package com.nowui.cloud.wawi.pet.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.wawi.pet.entity.Pet;
import com.nowui.cloud.wawi.pet.rpc.PetRpc;
import com.nowui.cloud.wawi.pet.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 宠物系统端控制器
 *
 * @author hucy
 *
 * 2018-01-22
 */
@Api(value = "宠物", description = "宠物系统端接口管理")
@RestController
public class PetSystemController implements PetRpc {

}