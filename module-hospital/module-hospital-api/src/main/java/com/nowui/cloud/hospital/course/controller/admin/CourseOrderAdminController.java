package com.nowui.cloud.hospital.course.controller.admin;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.hospital.course.entity.CourseOrder;
import com.nowui.cloud.hospital.course.view.CourseOrderView;
import com.nowui.cloud.hospital.course.service.CourseOrderService;
import com.nowui.cloud.view.CommonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.util.List;
import java.util.Map;

/**
 * 课程订单管理端控制器
 *
 * @author xupengfei
 *
 * 2018-03-18
 */
@Api(value = "课程订单", description = "课程订单管理端接口管理")
@RestController
public class CourseOrderAdminController extends BaseController {

    @Autowired
    private CourseOrderService courseOrderService;

    @ApiOperation(value = "课程订单列表", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseOrderView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.USER_ID, value = "订阅课程的用户编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/course/order/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore CourseOrderView courseOrderView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseOrderView,
                CourseOrderView.APP_ID,
                CourseOrderView.COURSE_ID,
                CourseOrderView.USER_ID
        );

        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = courseOrderService.countForAdmin(courseOrderView.getAppId(), courseOrderView.getCourseId(), courseOrderView.getUserId());
        List<CourseOrderView> resultList = courseOrderService.listForAdmin(courseOrderView.getAppId(), courseOrderView.getCourseId(), courseOrderView.getUserId(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                CourseOrderView.COURSE_ORDER_ID,
                CourseOrderView.COURSE_ID,
                CourseOrderView.USER_ID,
                CourseOrderView.COURSE_TRYOUT_STATUS,
                CourseOrderView.COURSE_SUBSCRIBE_STATUS,
                CourseOrderView.COURSE_ORDER_PAYMENT_TYPE,
                CourseOrderView.COURSE_ORDER_PAYMENT_STATUS
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "课程订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseOrderView.COURSE_ORDER_ID, value = "课程订单编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/order/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore CourseOrderView courseOrderView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseOrderView,
                CourseOrderView.COURSE_ORDER_ID,
                CourseOrderView.APP_ID
        );

        CourseOrderView result = courseOrderService.find(courseOrderView.getCourseOrderId(), courseOrderView.getAppId());

        validateResponse(
                CourseOrderView.COURSE_ORDER_ID,
            	CourseOrderView.COURSE_ID,
            	CourseOrderView.USER_ID,
            	CourseOrderView.COURSE_TRYOUT_STATUS,
            	CourseOrderView.COURSE_SUBSCRIBE_STATUS,
            	CourseOrderView.COURSE_ORDER_PAYMENT_TYPE,
            	CourseOrderView.COURSE_ORDER_PAYMENT_STATUS,
            	CourseOrderView.COURSE_ORDER_PAYMENT_AMOUNT,
                CourseOrderView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "课程订单新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseOrderView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.USER_ID, value = "订阅课程的用户编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_TRYOUT_STATUS, value = "是否试看", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_SUBSCRIBE_STATUS, value = "订阅状态:是否订阅", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ORDER_PAYMENT_TYPE, value = "支付类型: 微信,支付宝", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ORDER_PAYMENT_STATUS, value = "支付状态:已支付, 生成订单未支付,订单关闭", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ORDER_PAYMENT_AMOUNT, value = "支付金额", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/order/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore CourseOrder courseOrder, @ApiIgnore CourseOrderView courseOrderView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseOrderView,
                CourseOrderView.APP_ID,
                CourseOrderView.COURSE_ID,
                CourseOrderView.USER_ID,
                CourseOrderView.COURSE_TRYOUT_STATUS,
                CourseOrderView.COURSE_SUBSCRIBE_STATUS,
                CourseOrderView.COURSE_ORDER_PAYMENT_TYPE,
                CourseOrderView.COURSE_ORDER_PAYMENT_STATUS,
                CourseOrderView.COURSE_ORDER_PAYMENT_AMOUNT
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String courseOrderId = Util.getRandomUUID();

        CourseOrder result = courseOrderService.save(courseOrder, courseOrderId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            courseOrderView.copy(result);

            courseOrderService.save(courseOrderView);

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程订单修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseOrderView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ID, value = "课程编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.USER_ID, value = "订阅课程的用户编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_TRYOUT_STATUS, value = "是否试看", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_SUBSCRIBE_STATUS, value = "订阅状态:是否订阅", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ORDER_PAYMENT_TYPE, value = "支付类型: 微信,支付宝", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ORDER_PAYMENT_STATUS, value = "支付状态:已支付, 生成订单未支付,订单关闭", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.COURSE_ORDER_PAYMENT_AMOUNT, value = "支付金额", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/order/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore CourseOrder courseOrder, @ApiIgnore CourseOrderView courseOrderView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseOrderView,
                CourseOrderView.COURSE_ORDER_ID,
                CourseOrderView.APP_ID,
                CourseOrderView.COURSE_ID,
                CourseOrderView.USER_ID,
                CourseOrderView.COURSE_TRYOUT_STATUS,
                CourseOrderView.COURSE_SUBSCRIBE_STATUS,
                CourseOrderView.COURSE_ORDER_PAYMENT_TYPE,
                CourseOrderView.COURSE_ORDER_PAYMENT_STATUS,
                CourseOrderView.COURSE_ORDER_PAYMENT_AMOUNT,
                CourseOrderView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        CourseOrder result = courseOrderService.update(courseOrder, courseOrder.getCourseOrderId(), courseOrder.getAppId(), commonView.getSystemRequestUserId(), courseOrder.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseOrderView.copy(result);

            courseOrderService.update(courseOrderView, courseOrderView.getCourseOrderId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程订单删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = CourseOrderView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = CourseOrderView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/course/order/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore CourseOrderView courseOrderView, @ApiIgnore CommonView commonView) {

        validateRequest(
                courseOrderView,
                CourseOrderView.COURSE_ORDER_ID,
                CourseOrderView.APP_ID,
                CourseOrderView.SYSTEM_VERSION
        );

        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        CourseOrder result = courseOrderService.delete(courseOrderView.getCourseOrderId(), courseOrderView.getAppId(), commonView.getSystemRequestUserId(), courseOrderView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            courseOrderView.copy(result);

            courseOrderService.update(courseOrderView, courseOrderView.getCourseOrderId());

            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "课程订单数据同步")
    @RequestMapping(value = "/course/order/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {

        List<CourseOrder> courseOrderList = courseOrderService.listByMysql();

        for (CourseOrder courseOrder : courseOrderList) {
            CourseOrderView courseOrderView = new CourseOrderView();
            courseOrderView.copy(courseOrder);

            courseOrderService.saveOrUpdate(courseOrderView, courseOrderView.getCourseOrderId());
        }

        return renderJson(true);
    }

}