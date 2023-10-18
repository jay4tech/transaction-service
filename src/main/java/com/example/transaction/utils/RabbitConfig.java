package com.example.transaction.utils;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${queue.name.debitCredit}")
    private String queueNameDebitCredit;

    @Bean(name = "queueDebitCredit")
    public Queue queueDebitCredit() {
        return new Queue(queueNameDebitCredit, true);
    }


    @Qualifier("${queue.name.reverseDebit}")
    private String queueNameReverseDebit;

    @Bean(name = "queueReverseDebit")
    public Queue queueReverseDebit() {
        return new Queue(queueNameReverseDebit, true);
    }



}