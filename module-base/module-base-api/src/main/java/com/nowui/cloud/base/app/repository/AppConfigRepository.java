package com.nowui.cloud.base.app.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.app.view.AppConfigView;
import org.springframework.stereotype.Component;

/**
 * 应用配置视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface AppConfigRepository extends BaseRepository<AppConfigView> {

}
