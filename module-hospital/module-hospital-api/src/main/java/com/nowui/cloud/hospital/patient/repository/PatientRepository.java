package com.nowui.cloud.hospital.patient.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.hospital.patient.view.PatientView;
import org.springframework.stereotype.Component;

/**
 * 患者视图访问组件接口
 *
 * @author HuangChengLin
 *
 * 2018-03-02
 */
@Component
public interface PatientRepository extends BaseRepository<PatientView> {

}
