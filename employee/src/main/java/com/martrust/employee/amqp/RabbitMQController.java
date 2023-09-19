package com.martrust.employee.amqp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 19/09/2023
 * Time: 4:47 pm
 */

@RestController
@RequestMapping(value = "/api/rabbit-mq")
@RequiredArgsConstructor
public class RabbitMQController {

    private final RabbitMQSender rabbitMQSender;

    @GetMapping
    public String producer(@RequestParam("employeeId") String employeeId,
                           @RequestParam("employeeNickName") String employeeNickName) {
        EmployeeDetail employeeDetail = EmployeeDetail.builder()
                .employeeId(employeeId)
                .employeeNickName(employeeNickName)
                .build();
        rabbitMQSender.send(employeeDetail);
        return "Message sent successfully to the RabbitMQ server";
    }

    @PostMapping
    public String producer2(@RequestBody EmployeeDetail employeeDetail) {
        rabbitMQSender.send2(employeeDetail);
        return "Message sent successfully to the RabbitMQ server";
    }}
