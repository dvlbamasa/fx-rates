package com.martrust.fxrates.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 20/09/2023
 * Time: 1:09 pm
 */
@Component
public class RabbitMQDeadLetterConsumer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQDeadLetterConsumer.class);

    @RabbitListener(queues = "queue.new.nats")
    public void receiveMessage(EmployeeDetail employeeDetail) throws CustomException {
        logger.info("Recieved Message From RabbitMQ: " + employeeDetail);
        if (!employeeDetail.getEmployeeId().equals("1")) {
            throw new CustomException();
        }
    }
}
