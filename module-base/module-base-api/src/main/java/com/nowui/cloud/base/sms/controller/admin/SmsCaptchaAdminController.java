package com.nowui.cloud.base.sms.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.service.SmsCaptchaService;
import com.nowui.cloud.base.sms.view.SmsCaptchaView;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 短信验证码管理端控制器
 *
 * @author marcus
 * <p>
 * 2018-01-05
 */
@Api(value = "短信验证码", description = "短信验证码管理端接口管理")
@RestController
public class SmsCaptchaAdminController extends BaseController {

    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @ApiOperation(value = "短信验证码列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = SmsCaptchaView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_TYPE, value = "验证码类型", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_MOBILE, value = "手机号码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_IP_ADDRESS, value = "IP地址", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/sms/captcha/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore SmsCaptchaView smsCaptchaView, @ApiIgnore CommonView commonView) {
        validateRequest(
                smsCaptchaView,
                SmsCaptchaView.APP_ID,
                SmsCaptchaView.SMS_CAPTCHA_TYPE,
                SmsCaptchaView.SMS_CAPTCHA_MOBILE,
                SmsCaptchaView.SMS_CAPTCHA_IP_ADDRESS
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = smsCaptchaService.countForAdmin(smsCaptchaView.getAppId(), smsCaptchaView.getSmsCaptchaType(), smsCaptchaView.getSmsCaptchaMobile(), smsCaptchaView.getSmsCaptchaIpAddress());
        List<SmsCaptchaView> resultList = smsCaptchaService.listForAdmin(smsCaptchaView.getAppId(), smsCaptchaView.getSmsCaptchaType(), smsCaptchaView.getSmsCaptchaMobile(), smsCaptchaView.getSmsCaptchaIpAddress(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                SmsCaptchaView.SMS_CAPTCHA_ID,
                SmsCaptchaView.SMS_CAPTCHA_TYPE,
                SmsCaptchaView.SMS_CAPTCHA_MOBILE,
                SmsCaptchaView.SMS_CAPTCHA_CODE,
                SmsCaptchaView.SMS_CAPTCHA_IP_ADDRESS
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "短信验证码信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = SmsCaptchaView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_ID, value = "短信验证码编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/sms/captcha/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore SmsCaptchaView smsCaptchaView) {

        validateRequest(
                smsCaptchaView,
                SmsCaptchaView.APP_ID,
                SmsCaptchaView.SMS_CAPTCHA_ID
        );

        SmsCaptchaView result = smsCaptchaService.find(smsCaptchaView.getSmsCaptchaId());

        validateResponse(
                SmsCaptchaView.SMS_CAPTCHA_ID,
                SmsCaptchaView.SMS_CAPTCHA_TYPE,
                SmsCaptchaView.SMS_CAPTCHA_MOBILE,
                SmsCaptchaView.SMS_CAPTCHA_CODE,
                SmsCaptchaView.SMS_CAPTCHA_IP_ADDRESS
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增短信验证码", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = SmsCaptchaView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_TYPE, value = "验证码类型", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_MOBILE, value = "手机号码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_CODE, value = "验证码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_IP_ADDRESS, value = "IP地址", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/sms/captcha/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore SmsCaptcha smsCaptcha, @ApiIgnore SmsCaptchaView smsCaptchaView, @ApiIgnore CommonView commonView) {
        validateRequest(
                smsCaptchaView,
                SmsCaptchaView.APP_ID,
                SmsCaptchaView.SMS_CAPTCHA_TYPE,
                SmsCaptchaView.SMS_CAPTCHA_MOBILE,
                SmsCaptchaView.SMS_CAPTCHA_CODE,
                SmsCaptchaView.SMS_CAPTCHA_IP_ADDRESS
        );
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        String smsCaptchaId = Util.getRandomUUID();

        SmsCaptcha result = smsCaptchaService.save(smsCaptcha, smsCaptchaId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            // 保存验证码视图信息
            smsCaptchaView.copy(result);
            
            smsCaptchaService.save(smsCaptchaView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改短信验证码", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = SmsCaptchaView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_TYPE, value = "验证码类型", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_MOBILE, value = "手机号码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_CODE, value = "验证码", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_IP_ADDRESS, value = "IP地址", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_ID, value = "短信验证码编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/sms/captcha/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore SmsCaptcha smsCaptcha, @ApiIgnore SmsCaptchaView smsCaptchaView, @ApiIgnore CommonView commonView) {
        validateRequest(
                smsCaptchaView,
                SmsCaptchaView.SMS_CAPTCHA_ID,
                SmsCaptchaView.APP_ID,
                SmsCaptchaView.SMS_CAPTCHA_TYPE,
                SmsCaptchaView.SMS_CAPTCHA_MOBILE,
                SmsCaptchaView.SMS_CAPTCHA_CODE,
                SmsCaptchaView.SMS_CAPTCHA_IP_ADDRESS,
                SmsCaptchaView.SYSTEM_VERSION
        );
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        SmsCaptcha result = smsCaptchaService.update(smsCaptcha, smsCaptcha.getSmsCaptchaId(), smsCaptcha.getAppId(), commonView.getSystemRequestUserId(), smsCaptcha.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            smsCaptchaView.copy(result);
            
            smsCaptchaService.update(smsCaptchaView, smsCaptchaView.getSmsCaptchaId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除短信验证码", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = SmsCaptchaView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SMS_CAPTCHA_ID, value = "短信验证码编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = SmsCaptchaView.SYSTEM_VERSION, value = "版本号", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/sms/captcha/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore SmsCaptchaView smsCaptchaView, @ApiIgnore CommonView commonView) {
        validateRequest(
                smsCaptchaView,
                SmsCaptchaView.SMS_CAPTCHA_ID,
                SmsCaptchaView.APP_ID,
                SmsCaptchaView.SYSTEM_VERSION
        );
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        SmsCaptcha result = smsCaptchaService.delete(smsCaptchaView.getSmsCaptchaId(), smsCaptchaView.getAppId(), commonView.getSystemRequestUserId(), smsCaptchaView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            smsCaptchaView.copy(result);
            
            smsCaptchaService.delete(smsCaptchaView, smsCaptchaView.getSmsCaptchaId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "短信验证码数据同步", httpMethod = "POST")
    @RequestMapping(value = "/sms/captcha/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<SmsCaptcha> smsCaptchaList = smsCaptchaService.listByMysql();

        for(SmsCaptcha smsCaptcha : smsCaptchaList) {
            SmsCaptchaView smsCaptchaView = new SmsCaptchaView();
            smsCaptchaView.copy(smsCaptcha);

            smsCaptchaService.saveOrUpdate(smsCaptchaView, smsCaptchaView.getSmsCaptchaId());
        }

        return renderJson(true);
    }

}