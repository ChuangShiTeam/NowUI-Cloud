package com.nowui.cloud.hospital.course.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.hospital.course.view.CourseCategoryView;
import org.springframework.stereotype.Component;

/**
 * 课程分类视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Component
public interface CourseCategoryRepository extends BaseRepository<CourseCategoryView> {

}
