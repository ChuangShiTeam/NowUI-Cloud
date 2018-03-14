package com.nowui.cloud.hospital.patient.service.impl;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.hospital.patient.entity.Patient;
import com.nowui.cloud.hospital.patient.mapper.PatientMapper;
import com.nowui.cloud.hospital.patient.repository.PatientRepository;
import com.nowui.cloud.hospital.patient.service.PatientService;
import com.nowui.cloud.hospital.patient.view.PatientView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 患者业务实现
 *
 * @author HuangChengLin
 *
 * 2018-03-02
 */
@Service
public class PatientServiceImpl extends BaseServiceImpl<PatientMapper, Patient, PatientRepository, PatientView> implements PatientService {

    @Override
        public Integer countForAdmin(String appId, String patientPhone, String patientNickname, String patientProvince, String patientCity, String patientArea, String patientSource) {
            Criteria criteria = Criteria.where(PatientView.APP_ID).is(appId)
                    .and(PatientView.PATIENT_PHONE).regex(".*?" + patientPhone + ".*")
                    .and(PatientView.PATIENT_NICKNAME).regex(".*?" + patientNickname + ".*")
                    .and(PatientView.PATIENT_PROVINCE).regex(".*?" + patientProvince + ".*")
                    .and(PatientView.PATIENT_CITY).regex(".*?" + patientCity + ".*")
                    .and(PatientView.PATIENT_AREA).regex(".*?" + patientArea + ".*")
                    .and(PatientView.PATIENT_SOURCE).regex(".*?" + patientSource + ".*")
                    .and(PatientView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<PatientView> listForAdmin(String appId, String patientPhone, String patientNickname, String patientProvince, String patientCity, String patientArea, String patientSource, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(Patient.APP_ID).is(appId)
                    .and(PatientView.PATIENT_PHONE).regex(".*?" + patientPhone + ".*")
                    .and(PatientView.PATIENT_NICKNAME).regex(".*?" + patientNickname + ".*")
                    .and(PatientView.PATIENT_PROVINCE).regex(".*?" + patientProvince + ".*")
                    .and(PatientView.PATIENT_CITY).regex(".*?" + patientCity + ".*")
                    .and(PatientView.PATIENT_AREA).regex(".*?" + patientArea + ".*")
                    .and(PatientView.PATIENT_SOURCE).regex(".*?" + patientSource + ".*")
                    .and(PatientView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, PatientView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<PatientView> patientViewList = list(query, sort, pageIndex, pageSize);

            return patientViewList;
        }

}