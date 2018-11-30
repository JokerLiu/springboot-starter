package com.joker.rabbit.quickstart;

import cn.hutool.core.lang.Console;
import com.joker.rabbit.RabbitMQConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/11/28 10:09
 */
@Component
public class Consumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeMessage(Message message) {
        System.out.println(message.getMessageProperties().getHeaders().getClass());
        Console.log("consume message {}", message);
    }

}