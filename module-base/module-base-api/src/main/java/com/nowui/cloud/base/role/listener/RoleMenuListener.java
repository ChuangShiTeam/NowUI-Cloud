package com.nowui.cloud.base.role.listener;

import com.nowui.cloud.constant.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 角色菜单消息队列接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@RabbitListener(queues = Constant.QUEUE)
public class RoleMenuListener {

    @RabbitHandler
    public void process(String message) {

    }

}
