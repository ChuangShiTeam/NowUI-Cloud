package com.nowui.cloud.base.message.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.message.entity.Message;
import com.nowui.cloud.base.message.mapper.MessageMapper;
import com.nowui.cloud.base.message.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 消息业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public Integer countForAdmin(String appId, String messageTitle, String messageType) {
        Integer count = count(
                new BaseWrapper<Message>()
                        .eq(Message.APP_ID, appId)
                        .likeAllowEmpty(Message.MESSAGE_TITLE, messageTitle)
                        .likeAllowEmpty(Message.MESSAGE_TYPE, messageType)
                        .eq(Message.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Message> listForAdmin(String appId, String messageTitle, String messageType, Integer pageIndex, Integer pageSize) {
        List<Message> messageList = list(
                new BaseWrapper<Message>()
                        .eq(Message.APP_ID, appId)
                        .likeAllowEmpty(Message.MESSAGE_TITLE, messageTitle)
                        .likeAllowEmpty(Message.MESSAGE_TYPE, messageType)
                        .eq(Message.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Message.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return messageList;
    }

}