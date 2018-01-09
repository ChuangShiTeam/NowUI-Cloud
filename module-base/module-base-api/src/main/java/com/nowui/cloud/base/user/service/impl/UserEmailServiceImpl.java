package com.nowui.cloud.base.user.service.impl;

import org.springframework.stereotype.Service;

import com.nowui.cloud.base.user.entity.UserEmail;
import com.nowui.cloud.base.user.mapper.UserEmailMapper;
import com.nowui.cloud.base.user.service.UserEmailService;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 用户邮箱业务实现
 *
 * @author marcus
 *
 * 2018-01-08
 */
@Service
public class UserEmailServiceImpl extends BaseServiceImpl<UserEmailMapper, UserEmail> implements UserEmailService {

}