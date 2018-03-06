package com.nowui.cloud.hospital.doctor.controller.admin;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.hospital.doctor.entity.Doctor;
import com.nowui.cloud.hospital.doctor.view.DoctorView;
import com.nowui.cloud.hospital.doctor.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 医生管理端控制器
 *
 * @author ZhongYongQiangZ
 *
 * 2018-03-01
 */
@Api(value = "医生", description = "医生管理端接口管理")
@RestController
public class DoctorAdminController extends BaseController {

    @Autowired
    private DoctorService doctorService;

    @ApiOperation(value = "医生列表")
    @RequestMapping(value = "/doctor/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        DoctorView doctorView = getEntry(DoctorView.class);

        validateRequest(
                doctorView,
                DoctorView.APP_ID,
                DoctorView.DOCTOR_NAME,
                DoctorView.DOCTOR_SEX,
                DoctorView.DOCTOR_AGE,
                DoctorView.PAGE_INDEX,
                DoctorView.PAGE_SIZE
        );

        Integer resultTotal = doctorService.countForAdmin(doctorView.getAppId(), doctorView.getDoctorName(), doctorView.getDoctorSex(), doctorView.getDoctorAge());
        List<DoctorView> resultList = doctorService.listForAdmin(doctorView.getAppId(), doctorView.getDoctorName(), doctorView.getDoctorSex(), doctorView.getDoctorAge(), doctorView.getPageIndex(), doctorView.getPageSize());

        validateResponse(
                DoctorView.DOCTOR_ID,
                DoctorView.USER_ID,
                DoctorView.DOCTOR_NAME,
                DoctorView.DOCTOR_SEX,
                DoctorView.DOCTOR_AGE,
                DoctorView.DOCTOR_SUMMARY,
                DoctorView.DOCTOR_ORGANIZATION_NAME,
                DoctorView.DOCTOR_DEPARTMENT_NAME
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "医生信息")
    @RequestMapping(value = "/doctor/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        DoctorView doctorView = getEntry(DoctorView.class);

        validateRequest(
                doctorView,
                DoctorView.APP_ID,
                DoctorView.DOCTOR_ID
        );

        DoctorView result = doctorService.find(doctorView.getDoctorId());

        validateResponse(
                DoctorView.DOCTOR_ID,
            	DoctorView.USER_ID,
            	DoctorView.DOCTOR_NAME,
            	DoctorView.DOCTOR_SEX,
            	DoctorView.DOCTOR_AGE,
            	DoctorView.DOCTOR_SUMMARY,
            	DoctorView.DOCTOR_PROFESSIONAL,
            	DoctorView.DOCTOR_ORGANIZATION_NAME,
            	DoctorView.DOCTOR_DEPARTMENT_NAME,
            	DoctorView.DOCTOR_MASTER,
            	DoctorView.DOCTOR_IMAGE,
            	DoctorView.DOCTOR_RANK,
            	DoctorView.DOCTOR_STATUS,
            	DoctorView.DOCTOR_CERT,
                DoctorView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "医生新增")
    @RequestMapping(value = "/doctor/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Doctor doctorEntity = getEntry(Doctor.class);

        String doctorId = Util.getRandomUUID();

        validateRequest(
                doctorEntity,
                Doctor.APP_ID,
                Doctor.USER_ID,
                Doctor.DOCTOR_NAME,
                Doctor.DOCTOR_SEX,
                Doctor.DOCTOR_AGE,
                Doctor.DOCTOR_SUMMARY,
                Doctor.DOCTOR_PROFESSIONAL,
                Doctor.DOCTOR_ORGANIZATION_NAME,
                Doctor.DOCTOR_DEPARTMENT_NAME,
                Doctor.DOCTOR_MASTER,
                Doctor.DOCTOR_IMAGE,
                Doctor.DOCTOR_RANK,
                Doctor.DOCTOR_STATUS,
                Doctor.DOCTOR_CERT
        );

        Doctor result = doctorService.save(doctorEntity, doctorId, doctorEntity.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            DoctorView doctorView = JSON.parseObject(result.toJSONString(), DoctorView.class);
            doctorService.save(doctorView);
        }

        return renderJson(success);
    }

    @ApiOperation(value = "医生修改")
    @RequestMapping(value = "/doctor/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Doctor doctorEntity = getEntry(Doctor.class);

        validateRequest(
                doctorEntity,
                Doctor.DOCTOR_ID,
                Doctor.APP_ID,
                Doctor.USER_ID,
                Doctor.DOCTOR_NAME,
                Doctor.DOCTOR_SEX,
                Doctor.DOCTOR_AGE,
                Doctor.DOCTOR_SUMMARY,
                Doctor.DOCTOR_PROFESSIONAL,
                Doctor.DOCTOR_ORGANIZATION_NAME,
                Doctor.DOCTOR_DEPARTMENT_NAME,
                Doctor.DOCTOR_MASTER,
                Doctor.DOCTOR_IMAGE,
                Doctor.DOCTOR_RANK,
                Doctor.DOCTOR_STATUS,
                Doctor.DOCTOR_CERT,
                Doctor.SYSTEM_VERSION
        );

        Doctor result = doctorService.update(doctorEntity, doctorEntity.getDoctorId(), doctorEntity.getSystemRequestUserId(), doctorEntity.getSystemVersion());

        if (result != null) {
            DoctorView doctorView = JSON.parseObject(result.toJSONString(), DoctorView.class);
            doctorService.update(doctorView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "医生删除")
    @RequestMapping(value = "/doctor/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Doctor doctorEntity = getEntry(Doctor.class);

        validateRequest(
                doctorEntity,
                Doctor.DOCTOR_ID,
                Doctor.APP_ID,
                Doctor.SYSTEM_VERSION
        );

        Doctor result = doctorService.delete(doctorEntity.getDoctorId(), doctorEntity.getSystemRequestUserId(), doctorEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            DoctorView doctorView = JSON.parseObject(result.toJSONString(), DoctorView.class);
            doctorService.update(doctorView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "医生数据同步")
    @RequestMapping(value = "/doctor/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Doctor> doctorList = doctorService.listByMysql();

        for (Doctor doctor : doctorList) {
            DoctorView doctorView = new DoctorView();
            doctorView.putAll(doctor);

            doctorService.saveOrUpdate(doctorView);
        }

        return renderJson(true);
    }

}