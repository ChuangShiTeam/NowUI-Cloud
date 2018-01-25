package com.nowui.cloud.wawi.pet.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.wawi.pet.entity.PetCategory;
import com.nowui.cloud.wawi.pet.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 宠物移动端
 *
 * @author hucy
 *
 * 2018-01-21
 */
@Api(value = "宠物", description = "宠物移动端接口管理")
@RestController
public class PetMobileController extends BaseController {

}