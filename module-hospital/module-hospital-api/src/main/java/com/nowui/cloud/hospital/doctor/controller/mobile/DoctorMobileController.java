package com.nowui.cloud.hospital.doctor.controller.mobile;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.hospital.doctor.service.DoctorService;
import com.nowui.cloud.hospital.doctor.view.DoctorView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 医生移动端控制器
 *
 * @author WangZhiCai
 *
 * 2018-03-08
 */
@Api(value = "医生", description = "医生移动端接口管理")
@RestController
public class DoctorMobileController extends BaseController {

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
                DoctorView.DOCTOR_PROFESSIONAL,
                DoctorView.DOCTOR_ORGANIZATION_NAME,
                DoctorView.DOCTOR_DEPARTMENT_NAME,
                DoctorView.DOCTOR_MASTER,
                DoctorView.DOCTOR_IMAGE_ID,
                DoctorView.DOCTOR_RANK,
                DoctorView.DOCTOR_STATUS,
                DoctorView.DOCTOR_CERT
        );

        return renderJson(resultTotal, resultList);
    }
}