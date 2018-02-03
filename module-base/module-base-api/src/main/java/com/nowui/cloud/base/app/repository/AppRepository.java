package com.nowui.cloud.base.app.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.app.view.AppView;
import org.springframework.stereotype.Component;

/**
 * 应用视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface AppRepository extends BaseRepository<AppView> {

}
