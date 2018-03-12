package com.nowui.cloud.hospital.doctor.service.impl;

import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.hospital.doctor.entity.Doctor;
import com.nowui.cloud.hospital.doctor.mapper.DoctorMapper;
import com.nowui.cloud.hospital.doctor.repository.DoctorRepository;
import com.nowui.cloud.hospital.doctor.service.DoctorService;
import com.nowui.cloud.hospital.doctor.view.DoctorView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 医生业务实现
 *
 * @author WangZhiCai
 *
 * 2018-03-08
 */
@Service
public class DoctorServiceImpl extends BaseServiceImpl<DoctorMapper, Doctor, DoctorRepository, DoctorView> implements DoctorService {

    @Override
        public Integer countForAdmin(String appId, String doctorName, String doctorSex, Integer doctorAge) {
            Criteria criteria = Criteria.where(DoctorView.APP_ID).is(appId)
                    .and(DoctorView.DOCTOR_NAME).regex(".*?" + doctorName + ".*")
                    .and(DoctorView.DOCTOR_SEX).regex(".*?" + doctorSex + ".*")
                    .and(DoctorView.DOCTOR_AGE).regex(".*?" + doctorAge + ".*")
                    .and(DoctorView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<DoctorView> listForAdmin(String appId, String doctorName, String doctorSex, Integer doctorAge, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(Doctor.APP_ID).is(appId)
                    .and(DoctorView.DOCTOR_NAME).regex(".*?" + doctorName + ".*")
                    .and(DoctorView.DOCTOR_SEX).regex(".*?" + doctorSex + ".*")
                    .and(DoctorView.DOCTOR_AGE).regex(".*?" + doctorAge + ".*")
                    .and(DoctorView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, DoctorView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<DoctorView> doctorViewList = list(query, sort, pageIndex, pageSize);

            return doctorViewList;
        }

}