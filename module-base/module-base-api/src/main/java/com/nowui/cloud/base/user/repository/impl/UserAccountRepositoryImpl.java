package com.nowui.cloud.base.user.repository.impl;

import com.nowui.cloud.repository.impl.BaseRepositoryImpl;
import com.nowui.cloud.base.user.repository.UserAccountRepository;
import com.nowui.cloud.base.user.view.UserAccountView;
import org.springframework.stereotype.Component;

/**
 * 用户账号视图访问组件接口实现
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
public class UserAccountRepositoryImpl extends BaseRepositoryImpl<UserAccountView> implements UserAccountRepository {

}
