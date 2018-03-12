package com.nowui.cloud.base.sms.service;
import java.util.Date;
import java.util.List;

import com.nowui.cloud.base.sms.entity.SmsCaptcha;
import com.nowui.cloud.base.sms.view.SmsCaptchaView;
import com.nowui.cloud.service.BaseService;

/**
 * 短信验证码业务接口
 *
 * @author marcus
 *
 * 2018-01-05
 */
public interface SmsCaptchaService extends BaseService<SmsCaptcha,SmsCaptchaView> {

    /**
     * 短信验证码统计
     *
     * @param appId 应用编号
     * @param smsCaptchaType 验证码类型
     * @param smsCaptchaMobile 手机号码
     * @param smsCaptchaIpAddress IP地址
     * @return Integer 短信验证码统计
     */
    Integer countForAdmin(String appId, String smsCaptchaType, String smsCaptchaMobile, String smsCaptchaIpAddress);

    /**
     * 短信验证码列表
     *
     * @param appId 应用编号
     * @param smsCaptchaType 验证码类型
     * @param smsCaptchaMobile 手机号码
     * @param smsCaptchaIpAddress IP地址
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<SmsCaptcha> 短信验证码列表
     */
    List<SmsCaptcha> listForAdmin(String appId, String smsCaptchaType, String smsCaptchaMobile, String smsCaptchaIpAddress, Integer pageIndex, Integer pageSize);
    
    /**
     * 统计开始时间到当前时间同一类型的单个手机号码的验证码发送次数
     * 
     * @param appId
     * @param smsCaptchaType 验证码类型
     * @param smsCaptchaMobile 手机号码
     * @param startDate 开始时间
     * @return
     */
    Integer countByTypeAndMobile(String appId, String smsCaptchaType, String smsCaptchaMobile, String startDate);
    
    /**
     * 统计开始时间到当前时间同一验证码单个手机号码的验证码发送次数
     * 
     * @param appId
     * @param smsCaptchaMobile 手机号码
     * @param smsCaptchaCode 验证码
     * @param startDate 开始时间
     * @return
     */
    Integer countByMobileAndCode(String appId, String smsCaptchaMobile, String smsCaptchaCode, String startDate);

    /**
     * 统计开始时间到当前时间同一类型的同一IP地址的验证码发送次数
     * 
     * @param appId 应用编号
     * @param smsCaptchaType 验证码类型
     * @param smsCaptchaIpAddress IP地址
     * @param startDate 开始时间
     * @return
     */
    Integer countByTypeAndIpAddress(String appId, String smsCaptchaType, String smsCaptchaIpAddress, String startDate);

    /**
     * 阿里云发送短信
     * 
     * @param appId 应用编号
     * @param smsCaptchaType 验证码类型
     * @param smsCaptchaMobile 手机号码
     * @param smsCaptchaIpAddress IP地址
     * @param captchaMinute 验证码限制发送时间
     * @param systemRequestUserId 请求用户编号
     */
    void aliyunSend(String appId, String smsCaptchaType, String smsCaptchaMobile, String smsCaptchaIpAddress, int captchaMinute, String systemRequestUserId);
}
