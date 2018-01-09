package com.nowui.cloud.base.message.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.message.entity.Message;

import java.util.List;

/**
 * 消息业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface MessageService extends BaseService<Message> {

    /**
     * 消息统计
     *
     * @param appId 应用编号
     * @param messageTitle 标题
     * @param messageType 类型
     * @return Integer 消息统计
     */
    Integer countForAdmin(String appId, String messageTitle, String messageType);

    /**
     * 消息列表
     *
     * @param appId 应用编号
     * @param messageTitle 标题
     * @param messageType 类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Message> 消息列表
     */
    List<Message> listForAdmin(String appId, String messageTitle, String messageType, Integer pageIndex, Integer pageSize);
}
