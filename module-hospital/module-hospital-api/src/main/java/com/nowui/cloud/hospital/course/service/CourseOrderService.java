package com.nowui.cloud.hospital.course.service;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.hospital.course.entity.CourseOrder;
import com.nowui.cloud.hospital.course.view.CourseOrderView;
import java.util.List;

/**
 * 课程订单编号业务接口
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
public interface CourseOrderService extends BaseService<CourseOrder, CourseOrderView> {

    /**
     * 课程订单编号统计
     *
     * @param appId 应用编号
     * @param courseId 课程编号
     * @param userId 订阅课程的用户编号
     * @return Integer 课程订单编号统计
     */
    Integer countForAdmin(String appId, String courseId, String userId);

    /**
     * 课程订单编号列表
     *
     * @param appId 应用编号
     * @param courseId 课程编号
     * @param userId 订阅课程的用户编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<CourseOrder> 课程订单编号列表
     */
    List<CourseOrderView> listForAdmin(String appId, String courseId, String userId, Integer pageIndex, Integer pageSize);

}