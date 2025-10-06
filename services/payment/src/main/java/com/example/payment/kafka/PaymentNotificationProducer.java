package com.example.payment.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentNotificationProducer {

    private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;
    private static final String TOPIC_NAME = "payment-topic";

    public void sendPaymentNotification(PaymentNotificationRequest notificationRequest) {
        log.info("Sending payment notification to Kafka topic \"{}\"", TOPIC_NAME);
        kafkaTemplate.send(TOPIC_NAME, notificationRequest);
    }
}