package com.nowui.cloud.base.sms.controller.admin;

import com.nowui.cloud.base.sms.view.SmsCaptchaView;
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
 * <p>
 * 2018-01-05
 */
@Api(value = "短信验证码", description = "短信验证码管理端接口管理")
@RestController
public class SmsCaptchaAdminController extends BaseController {

    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @ApiOperation(value = "短信验证码列表")
    @RequestMapping(value = "/sms/captcha/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1() {
        SmsCaptcha smsCaptchaEntity = getEntry(SmsCaptcha.class);

        validateRequest(
                smsCaptchaEntity,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SMS_CAPTCHA_TYPE,
                SmsCaptcha.SMS_CAPTCHA_MOBILE,
                SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS,
                SmsCaptcha.PAGE_INDEX,
                SmsCaptcha.PAGE_SIZE
        );

        Integer resultTotal = smsCaptchaService.countForAdmin(smsCaptchaEntity.getAppId(), smsCaptchaEntity.getSmsCaptchaType(), smsCaptchaEntity.getSmsCaptchaMobile(), smsCaptchaEntity.getSmsCaptchaIpAddress());
        List<SmsCaptcha> resultList = smsCaptchaService.listForAdmin(smsCaptchaEntity.getAppId(), smsCaptchaEntity.getSmsCaptchaType(), smsCaptchaEntity.getSmsCaptchaMobile(), smsCaptchaEntity.getSmsCaptchaIpAddress(), smsCaptchaEntity.getPageIndex(), smsCaptchaEntity.getPageSize());

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
    @RequestMapping(value = "/sms/captcha/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1() {
        SmsCaptcha smsCaptchaEntity = getEntry(SmsCaptcha.class);

        validateRequest(
                smsCaptchaEntity,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SMS_CAPTCHA_ID
        );

        SmsCaptchaView result = smsCaptchaService.find(smsCaptchaEntity.getSmsCaptchaId());

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
    @RequestMapping(value = "/sms/captcha/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1() {
        SmsCaptcha smsCaptchaEntity = getEntry(SmsCaptcha.class);

        validateRequest(
                smsCaptchaEntity,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SMS_CAPTCHA_TYPE,
                SmsCaptcha.SMS_CAPTCHA_MOBILE,
                SmsCaptcha.SMS_CAPTCHA_CODE,
                SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS
        );

        String smsCaptchaId = Util.getRandomUUID();

        SmsCaptcha result = smsCaptchaService.save(smsCaptchaEntity, smsCaptchaId, smsCaptchaEntity.getSystemCreateUserId());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "修改短信验证码")
    @RequestMapping(value = "/sms/captcha/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1() {
        SmsCaptcha smsCaptchaEntity = getEntry(SmsCaptcha.class);

        validateRequest(
                smsCaptchaEntity,
                SmsCaptcha.SMS_CAPTCHA_ID,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SMS_CAPTCHA_TYPE,
                SmsCaptcha.SMS_CAPTCHA_MOBILE,
                SmsCaptcha.SMS_CAPTCHA_CODE,
                SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS,
                SmsCaptcha.SYSTEM_VERSION
        );

        SmsCaptcha result = smsCaptchaService.update(smsCaptchaEntity, smsCaptchaEntity.getSmsCaptchaId(), smsCaptchaEntity.getSystemUpdateUserId(), smsCaptchaEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "删除短信验证码")
    @RequestMapping(value = "/sms/captcha/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1() {
        SmsCaptcha smsCaptchaEntity = getEntry(SmsCaptcha.class);

        validateRequest(
                smsCaptchaEntity,
                SmsCaptcha.SMS_CAPTCHA_ID,
                SmsCaptcha.APP_ID,
                SmsCaptcha.SYSTEM_VERSION
        );

        SmsCaptcha result = smsCaptchaService.delete(smsCaptchaEntity.getSmsCaptchaId(), smsCaptchaEntity.getSystemUpdateUserId(), smsCaptchaEntity.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "短信验证码数据同步")
    @RequestMapping(value = "/sms/captcha/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<SmsCaptcha> smsCaptchaList = smsCaptchaService.listByMysql();

        for(SmsCaptcha smsCaptcha : smsCaptchaList) {
            SmsCaptchaView smsCaptchaView = new SmsCaptchaView();
            smsCaptchaView.putAll(smsCaptcha);

            smsCaptchaService.update(smsCaptchaView);
        }

        return renderJson(true);
    }

}