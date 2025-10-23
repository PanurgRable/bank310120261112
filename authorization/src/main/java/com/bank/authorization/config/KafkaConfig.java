package com.bank.authorization.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic userCreateTopic() {
        return TopicBuilder.name("user.create")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userGetTopic() {
        return TopicBuilder.name("user.get")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userDeleteTopic() {
        return TopicBuilder.name("user.delete")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userUpdateTopic() {
        return TopicBuilder.name("user.update")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic userResponseTopic() {
        return TopicBuilder.name("user.response")
                .partitions(3).replicas(1)
                .build();
    }

    @Bean
    public NewTopic authLoginTopic() {
        return TopicBuilder.name("auth.login")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic authValidateTopic() {
        return TopicBuilder.name("auth.validate")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic authResponseTopic() {
        return TopicBuilder.name("auth.response")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
