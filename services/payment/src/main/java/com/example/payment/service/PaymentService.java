// package com.example.payment.service;

// import com.example.payment.client.CustomerClient;
// import com.example.payment.dto.CustomerResponse;
// import com.example.payment.dto.PaymentRequest;
// import com.example.payment.dto.PaymentResponse;
// import com.example.payment.entity.Payment;
// import com.example.payment.mapper.PaymentMapper;
// import com.example.payment.repository.PaymentRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;

// @Service
// @RequiredArgsConstructor
// public class PaymentService {

//     private final PaymentRepository repository;
//     private final PaymentMapper mapper;
//     private final CustomerClient customerClient;

//     public PaymentResponse createPayment(PaymentRequest request) {
//         Payment payment = mapper.toEntity(request);
//         repository.save(payment);
//         CustomerResponse customer = customerClient.getCustomerById(request.getCustomerId());
//         return mapper.toResponse(payment, customer);
//     }

   
// }





package com.example.payment.service;

import com.example.payment.client.CustomerClient;
import com.example.payment.dto.CustomerResponse;
import com.example.payment.dto.PaymentRequest;
import com.example.payment.dto.PaymentResponse;
import com.example.payment.entity.Payment;
import com.example.payment.kafka.PaymentNotificationProducer; // <-- ADDED IMPORT
import com.example.payment.kafka.PaymentNotificationRequest;  // <-- ADDED IMPORT
import com.example.payment.mapper.PaymentMapper;
import com.example.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final CustomerClient customerClient;
    private final PaymentNotificationProducer notificationProducer; // <-- ADDED FINAL FIELD

    public PaymentResponse createPayment(PaymentRequest request) {
        // 1. Save the payment to the database
        Payment payment = mapper.toEntity(request);
        repository.save(payment);

        // 2. Fetch customer details from the customer-service
        // NOTE: For this test, make sure your customer-service is running
        // and has a customer with the ID you provide in the request.
        CustomerResponse customer = customerClient.getCustomerById(request.getCustomerId());

        // 3. Send notification to Kafka -- THIS IS THE NEW LOGIC --
        notificationProducer.sendPaymentNotification(
            PaymentNotificationRequest.builder()
                    .orderReference(request.getOrderReference())
                    .amount(request.getAmount())
                    .paymentMethod(request.getPaymentMethod())
                    .customerFirstName(customer.getFirstName())
                    .customerLastName(customer.getLastName())
                    .customerEmail(customer.getEmail())
                    .build()
        );

        // 4. Return the response to the original caller
        return mapper.toResponse(payment, customer);
    }
}