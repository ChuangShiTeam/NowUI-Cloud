package com.nowui.cloud.base.user.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.user.view.UserMobileView;
import org.springframework.stereotype.Component;

/**
 * 	用户手机号码视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface UserMobileRepository extends BaseRepository<UserMobileView> {

}
