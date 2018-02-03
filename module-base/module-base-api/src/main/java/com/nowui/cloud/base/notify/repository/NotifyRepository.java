package com.nowui.cloud.base.notify.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.notify.view.NotifyView;
import org.springframework.stereotype.Component;

/**
 * 消息视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface NotifyRepository extends BaseRepository<NotifyView> {

}
