package com.nowui.cloud.base.user.listener;

import com.nowui.cloud.constant.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 用户角色消息队列接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@RabbitListener(queues = Constant.QUEUE)
public class UserRoleListener {

    @RabbitHandler
    public void process(String message) {

    }

}
