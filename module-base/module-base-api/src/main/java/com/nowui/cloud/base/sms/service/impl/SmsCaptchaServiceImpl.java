package com.nowui.cloud.base.sms.service.impl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

/*import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;*/
import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.mapper.SmsCaptchaMapper;
import com.nowui.cloud.base.sms.service.SmsCaptchaService;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 短信验证码业务实现
 *
 * @author marcus
 *
 * 2018-01-05
 */
@Service
public class SmsCaptchaServiceImpl extends BaseServiceImpl<SmsCaptchaMapper, SmsCaptcha> implements SmsCaptchaService {

    @Override
    public Integer countForAdmin(String appId, String smsCaptchaType, String smsCaptchaMobile, String smsCaptchaIpAddress) {
        Integer count = count(
                new BaseWrapper<SmsCaptcha>()
                        .eq(SmsCaptcha.APP_ID, appId)
                        .eqAllowEmpty(SmsCaptcha.SMS_CAPTCHA_TYPE, smsCaptchaType)
                        .likeAllowEmpty(SmsCaptcha.SMS_CAPTCHA_MOBILE, smsCaptchaMobile)
                        .likeAllowEmpty(SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS, smsCaptchaIpAddress)
                        .eq(SmsCaptcha.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<SmsCaptcha> listForAdmin(String appId, String smsCaptchaType, String smsCaptchaMobile, String smsCaptchaIpAddress, Integer pageIndex, Integer pageSize) {
        List<SmsCaptcha> smsCaptchaList = list(
                new BaseWrapper<SmsCaptcha>()
                        .eq(SmsCaptcha.APP_ID, appId)
                        .eqAllowEmpty(SmsCaptcha.SMS_CAPTCHA_TYPE, smsCaptchaType)
                        .likeAllowEmpty(SmsCaptcha.SMS_CAPTCHA_MOBILE, smsCaptchaMobile)
                        .likeAllowEmpty(SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS, smsCaptchaIpAddress)
                        .eq(SmsCaptcha.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(SmsCaptcha.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return smsCaptchaList;
    }

    @Override
    public Integer countByTypeAndMobile(String appId, String smsCaptchaType, String smsCaptchaMobile, Date startDate) {
        Integer count = count(
                new BaseWrapper<SmsCaptcha>()
                .eq(SmsCaptcha.APP_ID, appId)
                .eq(SmsCaptcha.SMS_CAPTCHA_TYPE, smsCaptchaType)
                .eq(SmsCaptcha.SMS_CAPTCHA_MOBILE, smsCaptchaMobile)
                .between(SmsCaptcha.SYSTEM_CREATE_TIME, startDate, new Date())
                .eq(SmsCaptcha.SYSTEM_STATUS, true) 
        );
        return count;
    }

    @Override
    public Integer countByTypeAndIpAddress(String appId, String smsCaptchaType, String smsCaptchaIpAddress,
            Date startDate) {
        Integer count = count(
                new BaseWrapper<SmsCaptcha>()
                .eq(SmsCaptcha.APP_ID, appId)
                .eq(SmsCaptcha.SMS_CAPTCHA_TYPE, smsCaptchaType)
                .eq(SmsCaptcha.SMS_CAPTCHA_IP_ADDRESS, smsCaptchaIpAddress)
                .between(SmsCaptcha.SYSTEM_CREATE_TIME, startDate, new Date())
                .eq(SmsCaptcha.SYSTEM_STATUS, true) 
        );
        return count;
    }

    @Override
    public void aliyunSend(String appId, String smsCaptchaType, String smsCaptchaMobile, String smsCaptchaIpAddress,
            int captchaMinute, String systemRequestUserId) {
       /* Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -captcha_minute);

        if (ValidateUtil.isNullOrEmpty(captcha_mobile)) {
            throw new RuntimeException(captcha_minute + "手机号码格式不正确");
        }

        Integer count = captchaMobileCount(request_app_id, captcha_type, captcha_mobile, calendar);

        if (count > 0) {
            throw new RuntimeException(captcha_minute + "分钟内不能重复申请");
        }

        count = captchaIpAddressCount(request_app_id, captcha_type, captcha_ip_address, calendar);

        if (count > 0) {
            throw new RuntimeException(captcha_minute + "分钟内不能重复申请");
        }

        String captcha_code = Util.getRandomNumber(6);

        Captcha captcha = new Captcha();
        captcha.setCaptcha_id(Util.getRandomUUID());
        captcha.setApp_id(request_app_id);
        captcha.setCaptcha_type(captcha_type);
        captcha.setCaptcha_mobile(captcha_mobile);
        captcha.setCaptcha_code(captcha_code);
        captcha.setCaptcha_ip_address(captcha_ip_address);
        Boolean result = save(captcha, system_create_user_id);

        if (!result) {
            throw new RuntimeException("验证码发送不成功");
        }


        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = access_id;//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = access_key;//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(captcha_mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(sign_name);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(template_code);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{\"" + template_param + "\":\"" + captcha_code + "\"}");
        //可选-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //请求成功
        } else {
            System.out.println(sendSmsResponse.getCode());
            System.out.println(sendSmsResponse.getMessage());

            throw new RuntimeException("短信发送不成功");
        }*/
        
    }

}