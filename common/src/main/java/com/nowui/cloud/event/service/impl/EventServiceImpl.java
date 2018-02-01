package com.nowui.cloud.event.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.event.entity.Event;
import com.nowui.cloud.event.mapper.EventMapper;
import com.nowui.cloud.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 事件接口实现
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    protected EventMapper eventMapper;

    @Override
    public Boolean save(String eventId, String appId, String eventRouting, String eventMessage, String systemCreateUserId) {
        Event event = new Event();
        event.setEventId(eventId);
        event.setAppId(appId);
        event.setEventRouting(eventRouting);
        event.setEventMessage(eventMessage);
        event.setSystemCreateUserId(systemCreateUserId);
        event.setSystemCreateTime(new Date());
        event.setSystemUpdateUserId(systemCreateUserId);
        event.setSystemUpdateTime(new Date());
        event.setSystemVersion(0);
        event.setSystemStatus(true);

        Boolean success = eventMapper.insert(event) != 0;

        return success;
    }

    @Override
    public Boolean delete(String eventId) {
        Event event = new Event();
        event.setSystemUpdateTime(new Date());
        event.setSystemVersion(1);
        event.setSystemStatus(false);

        Boolean success = eventMapper.update(
                event,
                new EntityWrapper<Event>()
                        .eq(Event.EVENT_ID, eventId)
                        .eq(BaseEntity.SYSTEM_STATUS, true)
        ) != 0;

        return success;
    }
}
