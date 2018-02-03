package com.nowui.cloud.base.user.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.user.view.UserIdcardView;
import org.springframework.stereotype.Component;

/**
 * 用户身份证视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface UserIdcardRepository extends BaseRepository<UserIdcardView> {

}
