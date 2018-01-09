package com.nowui.cloud.base.user.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.user.entity.UserMessage;

import java.util.List;

/**
 * 用户消息业务接口
 *
 * @author marcus
 *
 * 2018-01-08
 */
public interface UserMessageService extends BaseService<UserMessage> {

    /**
     * 用户消息统计
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param messageId 消息编号
     * @param userMessageIsRead 是否已读
     * @return Integer 用户消息统计
     */
    Integer countForAdmin(String appId, String userId, String messageId, Boolean userMessageIsRead);

    /**
     * 用户消息列表
     *
     * @param appId 应用编号
     * @param userId 用户编号
     * @param messageId 消息编号
     * @param userMessageIsRead 是否已读
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<UserMessage> 用户消息列表
     */
    List<UserMessage> listForAdmin(String appId, String userId, String messageId, Boolean userMessageIsRead, Integer pageIndex, Integer pageSize);
}
