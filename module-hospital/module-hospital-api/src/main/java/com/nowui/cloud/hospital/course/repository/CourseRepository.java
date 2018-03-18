package com.nowui.cloud.hospital.course.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.hospital.course.view.CourseView;
import org.springframework.stereotype.Component;

/**
 * 课程视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Component
public interface CourseRepository extends BaseRepository<CourseView> {

}
