package com.example.notification.email.service;

import com.example.notification.kafka.model.PaymentConfirmation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    // We will add the order confirmation method later
    // public void sendOrderConfirmationEmail(...) {}

    public void sendPaymentSuccessEmail(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info("Sending payment confirmation email for order {}", paymentConfirmation.orderReference());

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
            mimeMessage,
            MimeMessageHelper.MULTIPART_MODE_RELATED,
            StandardCharsets.UTF_8.name()
        );

        messageHelper.setFrom("contact@your-company.com"); // You can change this
        messageHelper.setTo(paymentConfirmation.customerEmail());
        messageHelper.setSubject("Payment Successful for order - " + paymentConfirmation.orderReference());

        // Simple text-based email body
        final String text = buildPaymentEmailBody(
            paymentConfirmation.customerFirstName(),
            paymentConfirmation.amount(),
            paymentConfirmation.orderReference()
        );
        messageHelper.setText(text, false); // 'false' means the text is not HTML

        mailSender.send(mimeMessage);
        log.info("Payment confirmation email successfully sent for order {}", paymentConfirmation.orderReference());
    }

    private String buildPaymentEmailBody(String customerName, BigDecimal amount, String orderReference) {
        return String.format(
            "Dear %s,\n\n" +
            "We are pleased to confirm that your payment of %.2f has been successfully processed for your order with reference number %s.\n\n" +
            "Thank you for your purchase!\n\n" +
            "Best regards,\n" +
            "Your E-commerce Team",
            customerName,
            amount,
            orderReference
        );
    }
}