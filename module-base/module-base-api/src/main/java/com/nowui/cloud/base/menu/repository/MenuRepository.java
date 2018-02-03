package com.nowui.cloud.base.menu.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.menu.view.MenuView;
import org.springframework.stereotype.Component;

/**
 * 菜单视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface MenuRepository extends BaseRepository<MenuView> {

}
