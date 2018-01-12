package com.nowui.cloud.base.user.service.impl;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserMessage;
import com.nowui.cloud.base.user.mapper.UserMessageMapper;
import com.nowui.cloud.base.user.service.UserMessageService;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 用户消息业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserMessageServiceImpl extends BaseServiceImpl<UserMessageMapper, UserMessage> implements UserMessageService {

}