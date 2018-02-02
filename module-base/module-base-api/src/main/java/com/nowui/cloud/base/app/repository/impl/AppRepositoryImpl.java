package com.nowui.cloud.base.app.repository.impl;

import com.nowui.cloud.repository.impl.BaseRepositoryImpl;
import com.nowui.cloud.base.app.repository.AppRepository;
import com.nowui.cloud.base.app.view.AppView;
import org.springframework.stereotype.Component;

/**
 * 应用视图访问组件接口实现
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
public class AppRepositoryImpl extends BaseRepositoryImpl<AppView> implements AppRepository {

}
