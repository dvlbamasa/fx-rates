package com.martrust.employee.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 19/09/2023
 * Time: 4:41 pm
 */
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue}")
    String queueName;

    @Value("${rabbitmq.queue-2}")
    String queueName2;

    @Value("${rabbitmq.exchange}")
    String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    @Value("${rabbitmq.routingkey-2}")
    private String routingkey2;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    Queue queue2() {
        return new Queue(queueName2, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingkey);
    }

    @Bean
    Binding binding2() {
        return BindingBuilder
                .bind(queue2())
                .to(exchange())
                .with(routingkey2);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
