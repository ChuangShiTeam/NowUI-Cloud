package com.nowui.cloud.base.user.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.user.entity.UserMessage;
import com.nowui.cloud.base.user.mapper.UserMessageMapper;
import com.nowui.cloud.base.user.service.UserMessageService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户消息业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserMessageServiceImpl extends BaseServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {

    @Override
    public Integer adminCount(String appId, String userId, String messageId, Boolean userMessageIsRead) {
        Integer count = count(
                new BaseWrapper<UserMessage>()
                        .eq(UserMessage.APP_ID, appId)
                        .likeAllowEmpty(UserMessage.USER_ID, userId)
                        .likeAllowEmpty(UserMessage.MESSAGE_ID, messageId)
                        .eqAllowEmpty(UserMessage.USER_MESSAGE_IS_READ, userMessageIsRead)
                        .eq(UserMessage.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<UserMessage> adminList(String appId, String userId, String messageId, Boolean userMessageIsRead, Integer pageIndex, Integer pageSize) {
        List<UserMessage> userMessageList = list(
                new BaseWrapper<UserMessage>()
                        .eq(UserMessage.APP_ID, appId)
                        .likeAllowEmpty(UserMessage.USER_ID, userId)
                        .likeAllowEmpty(UserMessage.MESSAGE_ID, messageId)
                        .eqAllowEmpty(UserMessage.USER_MESSAGE_IS_READ, userMessageIsRead)
                        .eq(UserMessage.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(UserMessage.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return userMessageList;
    }

}