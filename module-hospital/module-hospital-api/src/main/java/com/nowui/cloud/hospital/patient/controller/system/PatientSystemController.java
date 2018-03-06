package com.nowui.cloud.hospital.patient.controller.system;

import com.nowui.cloud.hospital.patient.rpc.PatientRpc;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * 患者系统端控制器
 *
 * @author HuangChengLin
 *
 * 2018-03-02
 */
@Api(value = "患者", description = "患者系统端接口管理")
@RestController
public class PatientSystemController implements PatientRpc {

}