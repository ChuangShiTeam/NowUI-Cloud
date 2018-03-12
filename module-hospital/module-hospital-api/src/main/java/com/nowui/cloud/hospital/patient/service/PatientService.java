package com.nowui.cloud.hospital.patient.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.hospital.patient.entity.Patient;
import com.nowui.cloud.hospital.patient.view.PatientView;

import java.util.List;

/**
 * 患者业务接口
 *
 * @author HuangChengLin
 *
 * 2018-03-02
 */
public interface PatientService extends BaseService<Patient, PatientView> {

    /**
     * 患者统计
     *
     * @param appId 应用编号
     * @param patientPhone 手机号码
     * @param patientNickname 昵称
     * @param patientProvince 所在省
     * @param patientCity 所在市
     * @param patientArea 所在区
     * @param patientSource 来源入口
     * @return Integer 患者统计
     */
    Integer countForAdmin(String appId, String patientPhone, String patientNickname, String patientProvince, String patientCity, String patientArea, String patientSource);

    /**
     * 患者列表
     *
     * @param appId 应用编号
     * @param patientPhone 手机号码
     * @param patientNickname 昵称
     * @param patientProvince 所在省
     * @param patientCity 所在市
     * @param patientArea 所在区
     * @param patientSource 来源入口
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Patient> 患者列表
     */
    List<PatientView> listForAdmin(String appId, String patientPhone, String patientNickname, String patientProvince, String patientCity, String patientArea, String patientSource, Integer pageIndex, Integer pageSize);

}