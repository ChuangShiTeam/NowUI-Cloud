package com.nowui.cloud.hospital.doctor.service;

import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.hospital.doctor.entity.Doctor;
import com.nowui.cloud.hospital.doctor.view.DoctorView;

import java.util.List;

/**
 * 医生业务接口
 *
 * @author ZhongYongQiangZ
 *
 * 2018-03-01
 */
public interface DoctorService extends SuperService<Doctor, DoctorView> {

    /**
     * 医生统计
     *
     * @param appId 应用编号
     * @param doctorName 医生姓名
     * @param doctorSex 医生性别
     * @param doctorAge 医生年龄
     * @return Integer 医生统计
     */
    Integer countForAdmin(String appId, String doctorName, String doctorSex, Integer doctorAge);

    /**
     * 医生列表
     *
     * @param appId 应用编号
     * @param doctorName 医生姓名
     * @param doctorSex 医生性别
     * @param doctorAge 医生年龄
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Doctor> 医生列表
     */
    List<DoctorView> listForAdmin(String appId, String doctorName, String doctorSex, Integer doctorAge, Integer pageIndex, Integer pageSize);

}