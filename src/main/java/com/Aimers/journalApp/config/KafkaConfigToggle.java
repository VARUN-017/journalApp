package com.Aimers.journalApp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@ConditionalOnProperty(
        name = "kafka.enabled",
        havingValue = "true"
)
@Import(KafkaAutoConfiguration.class)
@EnableKafka
public class KafkaConfigToggle {


}
