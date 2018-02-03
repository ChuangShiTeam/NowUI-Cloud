package com.nowui.cloud.base.admin.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.admin.view.AdminView;
import org.springframework.stereotype.Component;

/**
 * 管理员视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-03
 */
@Component
public interface AdminRepository extends BaseRepository<AdminView> {

}
