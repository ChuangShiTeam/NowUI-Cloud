package com.nowui.cloud.base.user.mapper;
import java.util.List;

import com.nowui.cloud.base.user.entity.UserNotify;
import com.nowui.cloud.mapper.BaseMapper;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * 用户消息队列表mapping映射接口
 *
 * @author shawn
 * <p>
 * 2018-01-28
 */
public interface UserNotifyMapper extends BaseMapper<UserNotify> {

    List<UserNotify> getUserNotifyByUserId(
            @Param("userNotifyOwerId") String userNotifyOwerId,
            @Param("appId") String appId,
            @Param("notifyType") String notifyType
    );
}
