package com.ustc.kafkamessagedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class KafkaMessageDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMessageDemoApplication.class, args);
    }

}
