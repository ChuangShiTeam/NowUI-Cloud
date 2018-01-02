package com.nowui.cloud.base.member.listener;

import com.nowui.cloud.constant.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 会员收藏消息队列接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Component
@RabbitListener(queues = Constant.QUEUE)
public class MemberBookmarkListener {

    @RabbitHandler
    public void process(String message) {

    }

}
