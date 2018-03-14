package com.nowui.cloud.base.admin.repository;

import org.springframework.stereotype.Component;

import com.nowui.cloud.base.admin.view.AdminPasswordView;
import com.nowui.cloud.repository.BaseRepository;

/**
 * 管理员密码视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-03
 */
@Component
public interface AdminPasswordRepository extends BaseRepository<AdminPasswordView> {

}
