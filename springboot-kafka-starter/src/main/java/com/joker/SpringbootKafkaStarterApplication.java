package com.joker;

import com.joker.mq.Person;
import com.joker.mq.Producer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootKafkaStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaStarterApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(Producer producer) {
        // return (args) -> producer.send("hello kafka");
        return (args) -> producer.sendPerson(new Person("张三",18,"男"));
    }

}