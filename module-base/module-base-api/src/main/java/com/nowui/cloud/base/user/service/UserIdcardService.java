package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserIdcard;

import java.util.List;

/**
 * 用户身份证业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserIdcardService extends BaseService<UserIdcard> {

    /**
     * 用户身份证统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userName 真实姓名
     * @param userIdcardNumber 身份证号码
     * @return Integer 用户身份证统计
     */
    Integer countForAdmin(String appId, String userId, String userName, String userIdcardNumber);

    /**
     * 用户身份证列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param userName 真实姓名
     * @param userIdcardNumber 身份证号码
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserIdcard> 用户身份证列表
     */
    List<UserIdcard> listForAdmin(String appId, String userId, String userName, String userIdcardNumber, Integer pageIndex, Integer pageSize);
}
