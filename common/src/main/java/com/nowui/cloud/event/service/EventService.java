package com.nowui.cloud.event.service;

/**
 * 事件接口
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public interface EventService {

    Boolean save(String eventId, String appId, String eventRouting, String eventMessage, String systemCreateUserId);

    Boolean delete(String eventId);

}
