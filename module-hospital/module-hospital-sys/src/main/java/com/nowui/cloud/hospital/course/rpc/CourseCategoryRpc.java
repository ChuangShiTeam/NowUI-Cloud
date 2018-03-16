package com.nowui.cloud.hospital.course.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * 课程分类服务调用
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Component(value = "courseCategoryRpc")
@FeignClient(name = "module-hospital")
public interface CourseCategoryRpc {

}