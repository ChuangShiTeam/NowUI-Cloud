package com.nowui.cloud.hospital.doctor.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 医生服务调用
 *
 * @author ZhongYongQiangZ
 *
 * 2018-03-01
 */
@Component(value = "doctorRpc")
@FeignClient(name = "module-hospital")
public interface DoctorRpc {

}