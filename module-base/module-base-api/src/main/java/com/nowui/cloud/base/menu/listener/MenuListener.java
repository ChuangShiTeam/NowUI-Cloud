package com.nowui.cloud.base.menu.listener;

import com.nowui.cloud.constant.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 菜单消息队列接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Component
@RabbitListener(queues = Constant.QUEUE)
public class MenuListener {

    @RabbitHandler
    public void process(String message) {

    }

}
