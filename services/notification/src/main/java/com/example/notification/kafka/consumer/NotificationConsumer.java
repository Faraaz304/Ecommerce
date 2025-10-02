package com.example.notification.kafka.consumer;

import com.example.notification.email.service.EmailService;
import com.example.notification.kafka.model.PaymentConfirmation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation) {
        log.info(
            "Consumed payment confirmation event from Kafka: {}",
            paymentConfirmation
        );

        try {
            emailService.sendPaymentSuccessEmail(paymentConfirmation);
        } catch (MessagingException e) {
            log.error(
                "Failed to send payment confirmation email for order {}: {}",
                paymentConfirmation.orderReference(),
                e.getMessage()
            );
            // Here you could add logic to retry or send the failed event to another topic
        }
    }

    // We can add the listener for Order Confirmation later
    /*
    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) {
        // ... logic to send order confirmation email
    }
    */
}