package com.ustc.kafkamessagedemo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author :Yangyang Miao
 * @date :2023-07-30 17:41:00
 */

@Configuration
public class AppKafkaConfiguration {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("thing1")
                .partitions(1)
                .compact()
                .build();
    }
}
