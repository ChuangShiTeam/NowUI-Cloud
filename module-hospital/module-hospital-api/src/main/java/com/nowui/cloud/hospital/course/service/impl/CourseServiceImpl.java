package com.nowui.cloud.hospital.course.service.impl;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.hospital.course.entity.Course;
import com.nowui.cloud.hospital.course.mapper.CourseMapper;
import com.nowui.cloud.hospital.course.repository.CourseRepository;
import com.nowui.cloud.hospital.course.service.CourseService;
import com.nowui.cloud.hospital.course.view.CourseView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * 课程业务实现
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<CourseMapper, Course, CourseRepository, CourseView> implements CourseService {

    @Override
        public Integer countForAdmin(String appId, String courseAuthorUserId, String courseAuthorDoctorId, String courseTitle, String courseIntroduce) {
            Criteria criteria = Criteria.where(CourseView.APP_ID).is(appId)
                    .and(CourseView.COURSE_AUTHOR_USER_ID).regex(".*?" + courseAuthorUserId + ".*")
                    .and(CourseView.COURSE_AUTHOR_DOCTOR_ID).regex(".*?" + courseAuthorDoctorId + ".*")
                    .and(CourseView.COURSE_TITLE).regex(".*?" + courseTitle + ".*")
                    .and(CourseView.COURSE_INTRODUCE).regex(".*?" + courseIntroduce + ".*")
                    .and(CourseView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<CourseView> listForAdmin(String appId, String courseAuthorUserId, String courseAuthorDoctorId, String courseTitle, String courseIntroduce, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(CourseView.APP_ID).is(appId)
                    .and(CourseView.COURSE_AUTHOR_USER_ID).regex(".*?" + courseAuthorUserId + ".*")
                    .and(CourseView.COURSE_AUTHOR_DOCTOR_ID).regex(".*?" + courseAuthorDoctorId + ".*")
                    .and(CourseView.COURSE_TITLE).regex(".*?" + courseTitle + ".*")
                    .and(CourseView.COURSE_INTRODUCE).regex(".*?" + courseIntroduce + ".*")
                    .and(CourseView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, CourseView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<CourseView> courseViewList = list(query, sort, pageIndex, pageSize);

            return courseViewList;
        }

}