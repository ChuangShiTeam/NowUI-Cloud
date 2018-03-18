package com.nowui.cloud.hospital.course.service.impl;

import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.hospital.course.entity.CourseVideo;
import com.nowui.cloud.hospital.course.mapper.CourseVideoMapper;
import com.nowui.cloud.hospital.course.repository.CourseVideoRepository;
import com.nowui.cloud.hospital.course.service.CourseVideoService;
import com.nowui.cloud.hospital.course.view.CourseVideoView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

/**
 * 课程视频业务实现
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Service
public class CourseVideoServiceImpl extends BaseServiceImpl<CourseVideoMapper, CourseVideo, CourseVideoRepository, CourseVideoView> implements CourseVideoService {

    @Override
        public Integer countForAdmin(String appId, String courseId, String courseVideoTitle, String courseVideoIntroduce) {
            Criteria criteria = Criteria.where(CourseVideoView.APP_ID).is(appId)
                    .and(CourseVideoView.COURSE_ID).regex(".*?" + courseId + ".*")
                    .and(CourseVideoView.COURSE_VIDEO_TITLE).regex(".*?" + courseVideoTitle + ".*")
                    .and(CourseVideoView.COURSE_VIDEO_INTRODUCE).regex(".*?" + courseVideoIntroduce + ".*")
                    .and(CourseVideoView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<CourseVideoView> listForAdmin(String appId, String courseId, String courseVideoTitle, String courseVideoIntroduce, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(CourseVideoView.APP_ID).is(appId)
                    .and(CourseVideoView.COURSE_ID).regex(".*?" + courseId + ".*")
                    .and(CourseVideoView.COURSE_VIDEO_TITLE).regex(".*?" + courseVideoTitle + ".*")
                    .and(CourseVideoView.COURSE_VIDEO_INTRODUCE).regex(".*?" + courseVideoIntroduce + ".*")
                    .and(CourseVideoView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, CourseVideoView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<CourseVideoView> courseVideoViewList = list(query, sort, pageIndex, pageSize);

            return courseVideoViewList;
        }

}