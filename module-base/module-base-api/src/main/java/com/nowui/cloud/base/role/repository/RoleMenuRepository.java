package com.nowui.cloud.base.role.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.role.view.RoleMenuView;
import org.springframework.stereotype.Component;

/**
 * 角色菜单视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface RoleMenuRepository extends BaseRepository<RoleMenuView> {

}
