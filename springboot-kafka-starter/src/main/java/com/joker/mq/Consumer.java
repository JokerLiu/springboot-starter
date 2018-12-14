package com.joker.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author: Joker
 * @Description:
 * @Date: Created in 2018/12/13 19:12
 */
@Component
public class Consumer {

    ObjectMapper mapper = new ObjectMapper();

    private final List<SampleMessage> messages = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = "testTopic")
    public void processMessage(SampleMessage message) {
        this.messages.add(message);
        System.out.println("Received sample message :" + message);
    }

    @KafkaListener(topics = "personTopic")
    public void helloTopic(String message) throws IOException {
        Person person = mapper.readValue(message, Person.class);
        System.out.println("Received sample message :" + person.toString());
    }

    List<SampleMessage> getMessages() {
        return this.messages;
    }

}