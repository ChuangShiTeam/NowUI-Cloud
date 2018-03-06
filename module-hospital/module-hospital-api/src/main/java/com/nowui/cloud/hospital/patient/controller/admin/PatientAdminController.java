package com.nowui.cloud.hospital.patient.controller.admin;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.hospital.patient.entity.Patient;
import com.nowui.cloud.hospital.patient.view.PatientView;
import com.nowui.cloud.hospital.patient.service.PatientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 患者管理端控制器
 *
 * @author HuangChengLin
 *
 * 2018-03-02
 */
@Api(value = "患者", description = "患者管理端接口管理")
@RestController
public class PatientAdminController extends BaseController {

    @Autowired
    private PatientService patientService;

    @ApiOperation(value = "患者列表")
    @RequestMapping(value = "/patient/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        PatientView patientView = getEntry(PatientView.class);

        validateRequest(
                patientView,
                PatientView.APP_ID,
                PatientView.PATIENT_PHONE,
                PatientView.PATIENT_NICKNAME,
                PatientView.PATIENT_PROVINCE,
                PatientView.PATIENT_CITY,
                PatientView.PATIENT_AREA,
                PatientView.PATIENT_SOURCE,
                PatientView.PAGE_INDEX,
                PatientView.PAGE_SIZE
        );

        Integer resultTotal = patientService.countForAdmin(patientView.getAppId(), patientView.getPatientPhone(), patientView.getPatientNickname(), patientView.getPatientProvince(), patientView.getPatientCity(), patientView.getPatientArea(), patientView.getPatientSource());
        List<PatientView> resultList = patientService.listForAdmin(patientView.getAppId(), patientView.getPatientPhone(), patientView.getPatientNickname(), patientView.getPatientProvince(), patientView.getPatientCity(), patientView.getPatientArea(), patientView.getPatientSource(), patientView.getPageIndex(), patientView.getPageSize());

        validateResponse(
                PatientView.PATIENT_ID,
                PatientView.PATIENT_PHONE,
                PatientView.PATIENT_IMAGE_ID,
                PatientView.PATIENT_NICKNAME,
                PatientView.PATIENT_SEX,
                PatientView.PATIENT_BIRTH_DATE,
                PatientView.PATIENT_PROVINCE,
                PatientView.PATIENT_CITY,
                PatientView.PATIENT_AREA,
                PatientView.PATIENT_ADDRESS,
                PatientView.PATIENT_INVITATION_CODE,
                PatientView.PATIENT_IS_LOCK,
                PatientView.PATIENT_SOURCE
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "患者信息")
    @RequestMapping(value = "/patient/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        PatientView patientView = getEntry(PatientView.class);

        validateRequest(
                patientView,
                PatientView.APP_ID,
                PatientView.PATIENT_ID
        );

        PatientView result = patientService.find(patientView.getPatientId());

        validateResponse(
                PatientView.PATIENT_ID,
            	PatientView.USER_ID,
            	PatientView.MEMBER_ID,
            	PatientView.PATIENT_PHONE,
            	PatientView.PATIENT_IMAGE_ID,
            	PatientView.PATIENT_NICKNAME,
            	PatientView.PATIENT_SEX,
            	PatientView.PATIENT_BIRTH_DATE,
            	PatientView.PATIENT_PROVINCE,
            	PatientView.PATIENT_CITY,
            	PatientView.PATIENT_AREA,
            	PatientView.PATIENT_ADDRESS,
            	PatientView.PATIENT_INVITATION_CODE,
            	PatientView.PATIENT_IS_LOCK,
            	PatientView.PATIENT_SOURCE,
                PatientView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "患者新增")
    @RequestMapping(value = "/patient/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        Patient patientEntity = getEntry(Patient.class);

        String patientId = Util.getRandomUUID();

        validateRequest(
                patientEntity,
                Patient.APP_ID,
                Patient.USER_ID,
                Patient.MEMBER_ID,
                Patient.PATIENT_PHONE,
                Patient.PATIENT_IMAGE_ID,
                PatientView.PATIENT_IMAGE_PATH,
                Patient.PATIENT_NICKNAME,
                Patient.PATIENT_SEX,
                Patient.PATIENT_BIRTH_DATE,
                Patient.PATIENT_PROVINCE,
                Patient.PATIENT_CITY,
                Patient.PATIENT_AREA,
                Patient.PATIENT_ADDRESS,
                Patient.PATIENT_INVITATION_CODE,
                Patient.PATIENT_IS_LOCK,
                Patient.PATIENT_SOURCE
        );

        Patient result = patientService.save(patientEntity, patientId, patientEntity.getSystemRequestUserId());

        if (result != null) {
            PatientView patientView = JSON.parseObject(result.toJSONString(), PatientView.class);
            patientService.save(patientView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "患者修改")
    @RequestMapping(value = "/patient/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        Patient patientEntity = getEntry(Patient.class);

        validateRequest(
                patientEntity,
                Patient.PATIENT_ID,
                Patient.APP_ID,
                Patient.USER_ID,
                Patient.MEMBER_ID,
                Patient.PATIENT_PHONE,
                Patient.PATIENT_IMAGE_ID,
                Patient.PATIENT_NICKNAME,
                Patient.PATIENT_SEX,
                Patient.PATIENT_BIRTH_DATE,
                Patient.PATIENT_PROVINCE,
                Patient.PATIENT_CITY,
                Patient.PATIENT_AREA,
                Patient.PATIENT_ADDRESS,
                Patient.PATIENT_INVITATION_CODE,
                Patient.PATIENT_IS_LOCK,
                Patient.PATIENT_SOURCE,
                Patient.SYSTEM_VERSION
        );

        Patient result = patientService.update(patientEntity, patientEntity.getPatientId(), patientEntity.getSystemRequestUserId(), patientEntity.getSystemVersion());

        if (result != null) {
            PatientView patientView = JSON.parseObject(result.toJSONString(), PatientView.class);
            patientService.update(patientView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "患者删除")
    @RequestMapping(value = "/patient/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        Patient patientEntity = getEntry(Patient.class);

        validateRequest(
                patientEntity,
                Patient.PATIENT_ID,
                Patient.APP_ID,
                Patient.SYSTEM_VERSION
        );

        Patient result = patientService.delete(patientEntity.getPatientId(), patientEntity.getSystemRequestUserId(), patientEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            PatientView patientView = JSON.parseObject(result.toJSONString(), PatientView.class);
            patientService.update(patientView);
        }

        return renderJson(true);
    }

    @ApiOperation(value = "患者数据同步")
    @RequestMapping(value = "/patient/admin/v1/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<Patient> patientList = patientService.listByMysql();

        for (Patient patient : patientList) {
            PatientView patientView = new PatientView();
            patientView.putAll(patient);

            patientService.saveOrUpdate(patientView);
        }

        return renderJson(true);
    }

}