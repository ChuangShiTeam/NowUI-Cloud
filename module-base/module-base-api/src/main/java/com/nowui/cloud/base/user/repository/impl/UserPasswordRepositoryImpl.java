package com.nowui.cloud.base.user.repository.impl;

import com.nowui.cloud.repository.impl.BaseRepositoryImpl;
import com.nowui.cloud.base.user.repository.UserPasswordRepository;
import com.nowui.cloud.base.user.view.UserPasswordView;
import org.springframework.stereotype.Component;

/**
 * 用户密码视图访问组件接口实现
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
public class UserPasswordRepositoryImpl extends BaseRepositoryImpl<UserPasswordView> implements UserPasswordRepository {

}
