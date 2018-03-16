package com.nowui.cloud.hospital.course.service.impl;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.hospital.course.entity.CourseCategory;
import com.nowui.cloud.hospital.course.mapper.CourseCategoryMapper;
import com.nowui.cloud.hospital.course.repository.CourseCategoryRepository;
import com.nowui.cloud.hospital.course.service.CourseCategoryService;
import com.nowui.cloud.hospital.course.view.CourseCategoryView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * 课程分类业务实现
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Service
public class CourseCategoryServiceImpl extends BaseServiceImpl<CourseCategoryMapper, CourseCategory, CourseCategoryRepository, CourseCategoryView> implements CourseCategoryService {

    @Override
        public Integer countForAdmin(String appId, String courseCategoryName, String courseCategoryCode, String courseCategoryDescription) {
            Criteria criteria = Criteria.where(CourseCategoryView.APP_ID).is(appId)
                    .and(CourseCategoryView.COURSE_CATEGORY_NAME).regex(".*?" + courseCategoryName + ".*")
                    .and(CourseCategoryView.COURSE_CATEGORY_CODE).regex(".*?" + courseCategoryCode + ".*")
                    .and(CourseCategoryView.COURSE_CATEGORY_DESCRIPTION).regex(".*?" + courseCategoryDescription + ".*")
                    .and(CourseCategoryView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<CourseCategoryView> listForAdmin(String appId, String courseCategoryName, String courseCategoryCode, String courseCategoryDescription, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(CourseCategoryView.APP_ID).is(appId)
                    .and(CourseCategoryView.COURSE_CATEGORY_NAME).regex(".*?" + courseCategoryName + ".*")
                    .and(CourseCategoryView.COURSE_CATEGORY_CODE).regex(".*?" + courseCategoryCode + ".*")
                    .and(CourseCategoryView.COURSE_CATEGORY_DESCRIPTION).regex(".*?" + courseCategoryDescription + ".*")
                    .and(CourseCategoryView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, CourseCategoryView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<CourseCategoryView> courseCategoryViewList = list(query, sort, pageIndex, pageSize);

            return courseCategoryViewList;
        }

}