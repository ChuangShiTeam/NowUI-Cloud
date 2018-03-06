package com.nowui.cloud.hospital.patient.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 患者服务调用
 *
 * @author HuangChengLin
 *
 * 2018-03-02
 */
@Component(value = "patientRpc")
@FeignClient(name = "module-hospital")
public interface PatientRpc {

}