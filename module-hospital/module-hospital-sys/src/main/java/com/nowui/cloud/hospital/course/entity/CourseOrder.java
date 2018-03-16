package com.nowui.cloud.hospital.course.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.entity.BaseEntity;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**
 * 课程订单编号
 *
 * @author xupengfei
 *
 * 2018-03-16
 */
@Component

@TableName(value = "course_order_info")
public class CourseOrder extends BaseEntity {

    /**
     * 课程订单编号
     */
    @TableId
    @TableField
    private String courseOrderId;

    /**
     * 项目编号
     */
    @TableField
    private String appId;

    /**
     * 课程编号
     */
    @TableField
    private String courseId;

    /**
     * 订阅课程的用户编号
     */
    @TableField
    private String userId;

    /**
     * 是否试看
     */
    @TableField
    private Boolean courseTryoutStatus;

    /**
     * 订阅状态:是否订阅
     */
    @TableField
    private Boolean courseSubscribeStatus;

    /**
     * 支付类型: 微信,支付宝
     */
    @TableField
    private String courseOrderPaymentType;

    /**
     * 支付状态:已支付, 生成订单未支付,订单关闭
     */
    @TableField
    private String courseOrderPaymentStatus;

    /**
     * 支付金额
     */
    @TableField
    private BigDecimal courseOrderPaymentAmount;


    public String getCourseOrderId() {
        return courseOrderId;
    }
    
    public void setCourseOrderId(String courseOrderId) {
        this.courseOrderId = courseOrderId;
    }

    public String getAppId() {
        return appId;
    }
    
    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getCourseTryoutStatus() {
        return courseTryoutStatus;
    }
    
    public void setCourseTryoutStatus(Boolean courseTryoutStatus) {
        this.courseTryoutStatus = courseTryoutStatus;
    }

    public Boolean getCourseSubscribeStatus() {
        return courseSubscribeStatus;
    }
    
    public void setCourseSubscribeStatus(Boolean courseSubscribeStatus) {
        this.courseSubscribeStatus = courseSubscribeStatus;
    }

    public String getCourseOrderPaymentType() {
        return courseOrderPaymentType;
    }
    
    public void setCourseOrderPaymentType(String courseOrderPaymentType) {
        this.courseOrderPaymentType = courseOrderPaymentType;
    }

    public String getCourseOrderPaymentStatus() {
        return courseOrderPaymentStatus;
    }
    
    public void setCourseOrderPaymentStatus(String courseOrderPaymentStatus) {
        this.courseOrderPaymentStatus = courseOrderPaymentStatus;
    }

    public BigDecimal getCourseOrderPaymentAmount() {
        return courseOrderPaymentAmount;
    }
    
    public void setCourseOrderPaymentAmount(BigDecimal courseOrderPaymentAmount) {
        this.courseOrderPaymentAmount = courseOrderPaymentAmount;
    }


}