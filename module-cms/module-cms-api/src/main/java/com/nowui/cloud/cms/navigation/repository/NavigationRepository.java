package com.nowui.cloud.cms.navigation.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.cms.navigation.view.NavigationView;
import org.springframework.stereotype.Component;

/**
 * 导航栏视图访问组件接口
 *
 * @author marcus
 *
 * 2018-03-04
 */
@Component
public interface NavigationRepository extends BaseRepository<NavigationView> {

}
