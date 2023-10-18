package com.example.transaction.utils;

import com.example.transaction.service.ITransactionService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @Autowired
    ITransactionService inventoryService;

    @Autowired
    MessageSender messageSender;

    @RabbitListener(queues = {"${queue.name.debitCredit}"})
    public void receive(@Payload String message) {
        try {
            System.out.println("Message " + message);
//            Order orderDetails = UtilityMapper.responseToModel(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}