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
    Integer adminCount(String appId, String messageTitle, String messageType);

    /**
     * 消息列表
     *
     * @param appId 应用编号
     * @param messageTitle 标题
     * @param messageType 类型
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<Message> 消息列表
     */
    List<Message> adminList(String appId, String messageTitle, String messageType, Integer m, Integer n);
}
