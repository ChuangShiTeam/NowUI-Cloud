package com.nowui.cloud.base.user.listener;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.user.entity.User;
import com.nowui.cloud.base.user.entity.UserAccount;
import com.nowui.cloud.base.user.entity.UserMobile;
import com.nowui.cloud.base.user.entity.UserPassword;
import com.nowui.cloud.base.user.router.UserRouter;
import com.nowui.cloud.base.user.service.UserAccountService;
import com.nowui.cloud.base.user.service.UserMobileService;
import com.nowui.cloud.base.user.service.UserPasswordService;
import com.nowui.cloud.base.user.service.UserService;
import com.nowui.cloud.base.user.view.UserView;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.rabbit.RabbitListener;
import com.nowui.cloud.util.Util;

/**
 * 用户手机号码注册消息队列
 * 
 * @author marcus
 *
 * 2018年3月2日
 */
@Configuration
public class UserV1MobileRegisterListener {
    
    private final String queueName = "user_v1_mobile_register";

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserAccountService userAccountService;
    
    @Autowired
    private UserMobileService userMobileService;
    
    @Autowired
    private UserPasswordService userPasswordService;

    @Bean
    Queue UserV1MobileRegisterQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    Binding UserV1MobileRegisterQueueBindingExchange(Queue UserV1MobileRegisterQueue, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(UserV1MobileRegisterQueue).to(exchange).with(UserRouter.USER_V1_MOBILE_REGISTER);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public MessageListenerContainer UserV1MobileRegisterMessageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(queueName);
        simpleMessageListenerContainer.setMessageListener(UserV1MobileRegisterMessageListener());
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        simpleMessageListenerContainer.setPrefetchCount(Constant.PREFETCH_COUNT);
        return simpleMessageListenerContainer;
    }

    @Bean
    public RabbitListener UserV1MobileRegisterMessageListener() {
        return new RabbitListener() {
            @Override
            public void receive(String message) {
                User user = JSON.parseObject(message, User.class);
                
                String userAccount = user.getString(UserAccount.USER_ACCOUNT);
                String userPassword = user.getString(UserPassword.USER_PASSWORD);
                
                User result = userService.save(user, user.getUserId(), user.getSystemRequestUserId());
                
                if (result != null) {
                    // 保存用户账号
                    UserAccount userAccountBean = new UserAccount();
                    userAccountBean.setAppId(result.getAppId());
                    userAccountBean.setUserId(result.getUserId());
                    userAccountBean.setUserAccount(userAccount);
                    userAccountService.save(userAccountBean, Util.getRandomUUID(), result.getSystemRequestUserId());
                    // 保存用户密码
                    UserPassword userPasswordbean = new UserPassword();
                    userPasswordbean.setAppId(result.getAppId());
                    userPasswordbean.setUserId(result.getUserId());
                    userPasswordbean.setUserPassword(Util.generatePassword(userPassword));
                    userPasswordService.save(userPasswordbean, Util.getRandomUUID(), result.getSystemRequestUserId());
                    // 保存用户手机号码
                    UserMobile userMobile = new UserMobile();
                    userMobile.setAppId(result.getAppId());
                    userMobile.setUserId(result.getUserId());
                    userMobile.setUserMobile(userAccount);
                    userMobileService.save(userMobile, Util.getRandomUUID(), result.getSystemRequestUserId());

                    // 保存用户视图信息到mongodb
                    UserView userView = JSON.parseObject(result.toJSONString(), UserView.class);
                    userView.setUserAccount(userAccount);
                    userView.setUserPassword(Util.generatePassword(userPassword));
                    userView.setUserMobile(userAccount);
                    
                    userService.save(userView);
                }
                
            }
        };
    }

}
