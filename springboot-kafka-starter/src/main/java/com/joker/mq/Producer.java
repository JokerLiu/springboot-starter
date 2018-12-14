package com.joker.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/12/13 19:12
 */
@Component
public class Producer {

    ObjectMapper mapper = new ObjectMapper();

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    Producer(KafkaTemplate<Object, Object> kafkaTemplate) {
        //kafkaTemplate.setMessageConverter(new StringJsonMessageConverter());
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        this.kafkaTemplate.send("helloTopic", message);
        System.out.println("Sent sample message :" + message);
    }

    public void sendPerson(Person person) throws JsonProcessingException {
        this.kafkaTemplate.send("personTopic", mapper.writeValueAsString(person));
        System.out.println("Sent sample message :" + mapper.writeValueAsString(person));
    }

}