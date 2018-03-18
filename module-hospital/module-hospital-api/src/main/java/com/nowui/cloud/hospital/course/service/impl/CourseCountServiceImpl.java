package com.nowui.cloud.hospital.course.service.impl;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.hospital.course.entity.CourseCount;
import com.nowui.cloud.hospital.course.mapper.CourseCountMapper;
import com.nowui.cloud.hospital.course.repository.CourseCountRepository;
import com.nowui.cloud.hospital.course.service.CourseCountService;
import com.nowui.cloud.hospital.course.view.CourseCountView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * 课程统计业务实现
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Service
public class CourseCountServiceImpl extends BaseServiceImpl<CourseCountMapper, CourseCount, CourseCountRepository, CourseCountView> implements CourseCountService {

    @Override
        public Integer countForAdmin(String appId, String courseId, String courseCountType) {
            Criteria criteria = Criteria.where(CourseCountView.APP_ID).is(appId)
                    .and(CourseCountView.COURSE_ID).regex(".*?" + courseId + ".*")
                    .and(CourseCountView.COURSE_COUNT_TYPE).regex(".*?" + courseCountType + ".*")
                    .and(CourseCountView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<CourseCountView> listForAdmin(String appId, String courseId, String courseCountType, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(CourseCountView.APP_ID).is(appId)
                    .and(CourseCountView.COURSE_ID).regex(".*?" + courseId + ".*")
                    .and(CourseCountView.COURSE_COUNT_TYPE).regex(".*?" + courseCountType + ".*")
                    .and(CourseCountView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, CourseCountView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<CourseCountView> courseCountViewList = list(query, sort, pageIndex, pageSize);

            return courseCountViewList;
        }

}