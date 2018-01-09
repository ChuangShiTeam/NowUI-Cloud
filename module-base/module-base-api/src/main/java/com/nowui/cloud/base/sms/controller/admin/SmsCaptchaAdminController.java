package com.nowui.cloud.base.sms.controller.admin;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.service.SmsCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 短信验证码管理端控制器
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Api(value = "短信验证码", description = "短信验证码管理端接口管理")
@RestController
public class SmsCaptchaAdminController extends BaseController {

    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @ApiOperation(value = "短信验证码列表")
    @RequestMapping(value = "/sms/captcha/admin/v1/list", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@RequestBody SmsCaptcha body) {
        validateRequest(
                body,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SMS_CAPTCHA_TYPE,
                SmsCaptcha.SMS_CAPTCHA_MOBILE,
                SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS,
                SmsCaptcha.PAGE_INDEX,
                SmsCaptcha.PAGE_SIZE
        );

        Integer resultTotal = smsCaptchaService.countForAdmin(body.getAppId() , body.getSmsCaptchaType(), body.getSmsCaptchaMobile(), body.getSmsCaptchaIpAddress());
        List<SmsCaptcha> resultList = smsCaptchaService.listForAdmin(body.getAppId(), body.getSmsCaptchaType(), body.getSmsCaptchaMobile(), body.getSmsCaptchaIpAddress(), body.getPageIndex(), body.getPageSize());

        validateResponse(
                SmsCaptcha.SMS_CAPTCHA_ID,
                SmsCaptcha.SMS_CAPTCHA_TYPE,
                SmsCaptcha.SMS_CAPTCHA_MOBILE,
                SmsCaptcha.SMS_CAPTCHA_CODE,
                SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "短信验证码信息")
    @RequestMapping(value = "/sms/captcha/admin/v1/find", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@RequestBody SmsCaptcha body) {
        validateRequest(
                body,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SMS_CAPTCHA_ID
        );

        SmsCaptcha result = smsCaptchaService.find(body.getSmsCaptchaId());

        validateResponse(
                SmsCaptcha.SMS_CAPTCHA_ID,
                SmsCaptcha.SMS_CAPTCHA_TYPE,
                SmsCaptcha.SMS_CAPTCHA_MOBILE,
                SmsCaptcha.SMS_CAPTCHA_CODE,
                SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS
        );

        return renderJson(result);
    }

    @ApiOperation(value = "新增短信验证码")
    @RequestMapping(value = "/sms/captcha/admin/v1/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@RequestBody SmsCaptcha body) {
        validateRequest(
                body,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SMS_CAPTCHA_TYPE,
                SmsCaptcha.SMS_CAPTCHA_MOBILE,
                SmsCaptcha.SMS_CAPTCHA_CODE,
                SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS
        );

        Boolean result = smsCaptchaService.save(body, Util.getRandomUUID(), body.getSystemRequestUserId());

        return renderJson(result);
    }

    @ApiOperation(value = "修改短信验证码")
    @RequestMapping(value = "/sms/captcha/admin/v1/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@RequestBody SmsCaptcha body) {
        validateRequest(
                body,
                SmsCaptcha.SMS_CAPTCHA_ID,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SMS_CAPTCHA_TYPE,
                SmsCaptcha.SMS_CAPTCHA_MOBILE,
                SmsCaptcha.SMS_CAPTCHA_CODE,
                SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS,
                SmsCaptcha.SYSTEM_VERSION
        );

        Boolean result = smsCaptchaService.update(body, body.getSmsCaptchaId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

    @ApiOperation(value = "删除短信验证码")
    @RequestMapping(value = "/sms/captcha/admin/v1/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@RequestBody SmsCaptcha body) {
        validateRequest(
                body,
                SmsCaptcha.SMS_CAPTCHA_ID,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SYSTEM_VERSION
        );

        Boolean result = smsCaptchaService.delete(body.getSmsCaptchaId(), body.getSystemRequestUserId(), body.getSystemVersion());

        return renderJson(result);
    }

}