package com.example.transaction.utils;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("queueDebitCredit")
    private Queue queueDebitCredit;

    @Autowired
    @Qualifier("queueReverseDebit")
    private Queue queueReverseDebit;

    public void sendDebitCreditDetails(String message) {
        rabbitTemplate.convertAndSend(queueDebitCredit.getName(), message);
    }

    public void sendToReverseDebit(String message) {
        rabbitTemplate.convertAndSend(queueReverseDebit.getName(), message);
    }
}
