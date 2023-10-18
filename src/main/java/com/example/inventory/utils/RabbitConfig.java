package com.example.inventory.utils;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${queue.name.inventory}")
    private String queueInventory;

    @Bean(name = "queueInventory")
    public Queue queue() {
        return new Queue(queueInventory, true);
    }

    @Value("${queue.name.payment}")
    private String queuePayment;

    @Bean
    public Queue queuePayment() {
        return new Queue(queuePayment, true);
    }

}