package com.joker.rabbit.quickstart;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Console;
import com.joker.rabbit.RabbitMQConfig;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/11/28 10:11
 */
@RestController
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;// 用来创建Exchange,Queue,routing key,以及对应的绑定管

    @GetMapping("/sendMessage")
    public Object sendMessage() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                String value = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
                Console.log("send message {}", value);
                rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, value);
            }
        }).start();
        return "ok";
    }

    @GetMapping("/sendMessageByWrite")
    public String sendMessageByWrite(@RequestParam("writeStr") String writeStr) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, writeStr);
        return "ok";
    }

    @GetMapping("/createExchange")
    public String createExchange(@RequestParam("exchange") String exchange) {
        //amqpAdmin.deleteExchange(exchange);
        //Exchange接口有四个实现子类。DirectExchange,FanoutExchange,TopicExchange,CustomExchange
        amqpAdmin.declareExchange(new DirectExchange(exchange));
        return "创建Exchange成功";
    }

    @GetMapping("/createQueue")
    public String createQueue(@RequestParam("queue") String queue) {
        amqpAdmin.declareQueue(new Queue(queue, true));
        return "创建Queue成功";
    }

    @GetMapping("/createBinding")
    public String createBinding(@RequestParam("exchange") String exchange, @RequestParam("routingKey") String routingKey) {
        amqpAdmin.declareBinding(new Binding(routingKey, Binding.DestinationType.QUEUE, exchange, routingKey, null));
        return "Binding成功";
    }

}