package com.nowui.cloud.hospital.course.service.impl;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.hospital.course.entity.CourseOrder;
import com.nowui.cloud.hospital.course.mapper.CourseOrderMapper;
import com.nowui.cloud.hospital.course.repository.CourseOrderRepository;
import com.nowui.cloud.hospital.course.service.CourseOrderService;
import com.nowui.cloud.hospital.course.view.CourseOrderView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * 课程订单业务实现
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Service
public class CourseOrderServiceImpl extends BaseServiceImpl<CourseOrderMapper, CourseOrder, CourseOrderRepository, CourseOrderView> implements CourseOrderService {

    @Override
        public Integer countForAdmin(String appId, String courseId, String userId) {
            Criteria criteria = Criteria.where(CourseOrderView.APP_ID).is(appId)
                    .and(CourseOrderView.COURSE_ID).regex(".*?" + courseId + ".*")
                    .and(CourseOrderView.USER_ID).regex(".*?" + userId + ".*")
                    .and(CourseOrderView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<CourseOrderView> listForAdmin(String appId, String courseId, String userId, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(CourseOrderView.APP_ID).is(appId)
                    .and(CourseOrderView.COURSE_ID).regex(".*?" + courseId + ".*")
                    .and(CourseOrderView.USER_ID).regex(".*?" + userId + ".*")
                    .and(CourseOrderView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, CourseOrderView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<CourseOrderView> courseOrderViewList = list(query, sort, pageIndex, pageSize);

            return courseOrderViewList;
        }

}