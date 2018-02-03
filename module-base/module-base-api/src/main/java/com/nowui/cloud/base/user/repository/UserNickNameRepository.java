package com.nowui.cloud.base.user.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.base.user.view.UserNickNameView;
import org.springframework.stereotype.Component;

/**
 * 用户昵称视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface UserNickNameRepository extends BaseRepository<UserNickNameView> {

}
