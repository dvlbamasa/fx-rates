package com.martrust.employee.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 19/09/2023
 * Time: 5:46 pm
 */
@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue}"})
    public void consumeMessage(EmployeeDetail employeeDetail) {
        LOGGER.info(String.format("Received message1 -> %s", employeeDetail.toString()));
    }

    @RabbitListener(queues = {"${rabbitmq.queue-2}"})
    public void consumeMessage2(EmployeeDetail employeeDetail) {
        LOGGER.info(String.format("Received message2 -> %s", employeeDetail.toString()));
    }
}
