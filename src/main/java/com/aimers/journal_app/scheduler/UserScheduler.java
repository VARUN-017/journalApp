package com.aimers.journal_app.scheduler;

import com.aimers.journal_app.cache.AppCache;
import com.aimers.journal_app.constants.Sentiment;
import com.aimers.journal_app.entity.JournalEntry;
import com.aimers.journal_app.entity.User;
import com.aimers.journal_app.model.SentimentData;
import com.aimers.journal_app.repository.UserRepositoryImpl;
import com.aimers.journal_app.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = true)
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @Autowired
    private KafkaTemplate<String,SentimentData> kafkaTemplate;


    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if (mostFrequentSentiment != null) {
                SentimentData sentimentData = SentimentData.builder()
                        .email(user.getEmail())
                        .sentiment("Sentiment for last 7 days: " + mostFrequentSentiment)
                        .build();
                kafkaTemplate.send("weekly-sentiments", sentimentData.getEmail(), sentimentData)
                        .whenComplete((result, ex) -> {
                            if (ex == null) {
                                log.info("Sent message={} with offset={}", sentimentData,
                                        result.getRecordMetadata().offset());
                            } else {
                                log.error("Failed to send Kafka message, sending email fallback", ex);
                                emailService.sendEmail(
                                        sentimentData.getEmail(),
                                        "Sentiment for previous week",
                                        sentimentData.getSentiment()
                                );
                            }
                        });
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }

}


