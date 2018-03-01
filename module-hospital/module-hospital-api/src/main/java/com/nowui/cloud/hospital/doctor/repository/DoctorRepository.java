package com.nowui.cloud.hospital.doctor.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.hospital.doctor.view.DoctorView;
import org.springframework.stereotype.Component;

/**
 * 医生视图访问组件接口
 *
 * @author ZhongYongQiangZ
 *
 * 2018-03-01
 */
@Component
public interface DoctorRepository extends BaseRepository<DoctorView> {

}
