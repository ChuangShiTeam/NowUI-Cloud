package com.nowui.cloud.hospital.course.view;

import com.nowui.cloud.annotation.KeyId;
import com.nowui.cloud.view.BaseView;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

/**
 * 课程订单视图
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Component
@Document(collection = "course_order_info")
public class CourseOrderView extends BaseView {

    /**
     * 课程订单编号
     */
    @KeyId
    @Field
    @NotNull(message = "课程订单编号不能为空")
    @Length(max = 32, message = "课程订单编号长度超出限制")
    private String courseOrderId;
    public static final String COURSE_ORDER_ID = "courseOrderId";

    /**
     * 项目编号
     */
    @Field
    @NotNull(message = "项目编号不能为空")
    @Length(max = 32, message = "项目编号长度超出限制")
    private String appId;
    public static final String APP_ID = "appId";

    /**
     * 课程编号
     */
    @Field
    @NotNull(message = "课程编号不能为空")
    @Length(max = 32, message = "课程编号长度超出限制")
    private String courseId;
    public static final String COURSE_ID = "courseId";

    /**
     * 订阅课程的用户编号
     */
    @Field
    @NotNull(message = "订阅课程的用户编号不能为空")
    @Length(max = 32, message = "订阅课程的用户编号长度超出限制")
    private String userId;
    public static final String USER_ID = "userId";

    /**
     * 是否试看
     */
    @Field
    @NotNull(message = "是否试看不能为空")
    @Digits(integer = 1, fraction = 0, message = "是否试看长度超出限制")
    private Boolean courseTryoutStatus;
    public static final String COURSE_TRYOUT_STATUS = "courseTryoutStatus";

    /**
     * 订阅状态:是否订阅
     */
    @Field
    @NotNull(message = "订阅状态:是否订阅不能为空")
    @Digits(integer = 1, fraction = 0, message = "订阅状态:是否订阅长度超出限制")
    private Boolean courseSubscribeStatus;
    public static final String COURSE_SUBSCRIBE_STATUS = "courseSubscribeStatus";

    /**
     * 支付类型: 微信,支付宝
     */
    @Field
    @NotNull(message = "支付类型: 微信,支付宝不能为空")
    @Length(max = 25, message = "支付类型: 微信,支付宝长度超出限制")
    private String courseOrderPaymentType;
    public static final String COURSE_ORDER_PAYMENT_TYPE = "courseOrderPaymentType";

    /**
     * 支付状态:已支付, 生成订单未支付,订单关闭
     */
    @Field
    @NotNull(message = "支付状态:已支付, 生成订单未支付,订单关闭不能为空")
    @Length(max = 25, message = "支付状态:已支付, 生成订单未支付,订单关闭长度超出限制")
    private String courseOrderPaymentStatus;
    public static final String COURSE_ORDER_PAYMENT_STATUS = "courseOrderPaymentStatus";

    /**
     * 支付金额
     */
    @Field
    @NotNull(message = "支付金额不能为空")
    @Digits(integer = 0, fraction = 2, message = "支付金额长度超出限制")
    private BigDecimal courseOrderPaymentAmount;
    public static final String COURSE_ORDER_PAYMENT_AMOUNT = "courseOrderPaymentAmount";


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