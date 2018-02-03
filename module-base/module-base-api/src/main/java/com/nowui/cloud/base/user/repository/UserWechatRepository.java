package com.nowui.cloud.base.user.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.user.view.UserWechatView;
import org.springframework.stereotype.Component;

/**
 * 用户微信视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface UserWechatRepository extends BaseRepository<UserWechatView> {

}
