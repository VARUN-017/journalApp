package com.Aimers.journalApp.service;

import com.Aimers.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true")
public class SentimentProducerService {

    @Autowired
    private KafkaTemplate<String, SentimentData> kafkaTemplate;

    public void sendSentiment(SentimentData sentimentData){
        kafkaTemplate.send("weekly-sentiments",sentimentData);
    }

}
