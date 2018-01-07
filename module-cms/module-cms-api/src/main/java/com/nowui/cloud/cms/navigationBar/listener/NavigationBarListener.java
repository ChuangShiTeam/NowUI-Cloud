package com.nowui.cloud.cms.navigationBar.listener;

import com.nowui.cloud.constant.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 导航栏消息队列接口
 *
 * @author shawn
 *
 * 2017-12-31
 */
@Component
@RabbitListener(queues = Constant.QUEUE)
public class NavigationBarListener {

    @RabbitHandler
    public void process(String message) {

    }

}
