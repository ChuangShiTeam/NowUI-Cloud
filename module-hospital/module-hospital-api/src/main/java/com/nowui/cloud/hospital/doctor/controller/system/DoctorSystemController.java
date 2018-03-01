package com.nowui.cloud.hospital.doctor.controller.system;

import com.nowui.cloud.hospital.doctor.rpc.DoctorRpc;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * 医生系统端控制器
 *
 * @author ZhongYongQiangZ
 *
 * 2018-03-01
 */
@Api(value = "医生", description = "医生系统端接口管理")
@RestController
public class DoctorSystemController implements DoctorRpc {

}