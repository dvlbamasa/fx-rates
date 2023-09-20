package com.martrust.employee.amqp;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 19/09/2023
 * Time: 4:45 pm
 */

@Service
@RequiredArgsConstructor
public class RabbitMQSender {
    
    private final AmqpTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQSender.class);

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.exchange.new}")
    private String exchangeNew;

    @Value("${rabbitmq.routing.key.new}")
    private String routingKeyNew;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    @Value("${rabbitmq.routingkey-2}")
    private String routingkey2;

    public void send(EmployeeDetail employeeDetail) {
        rabbitTemplate.convertAndSend(exchange, routingkey, employeeDetail);
        LOGGER.info("Sending message to RabbitMQ queue1");

    }

    public void send2(EmployeeDetail employeeDetail) {
        rabbitTemplate.convertAndSend(exchange, routingkey2, employeeDetail);
        LOGGER.info("Sending message to RabbitMQ queue2");
    }

    public void sendDeadLetter(EmployeeDetail employeeDetail) {
        rabbitTemplate.convertAndSend(exchangeNew, routingKeyNew, employeeDetail);
        LOGGER.info("Sending message to RabbitMQ exchangeNew to test DLQ");
    }
}
