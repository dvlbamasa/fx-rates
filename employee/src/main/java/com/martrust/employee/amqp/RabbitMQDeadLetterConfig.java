package com.martrust.employee.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 20/09/2023
 * Time: 12:43 pm
 */
@Configuration
public class RabbitMQDeadLetterConfig {

    @Value("${rabbitmq.dead-letter.exchange}")
    private String deadLetterExchange;

    @Value("${rabbitmq.dead-letter.queue}")
    private String deadLetterQueue;

    @Value("${rabbitmq.exchange.new}")
    private String exchangeNew;

    @Value("${rabbitmq.queue.new}")
    private String queueNew;

    @Value("${rabbitmq.dlx.routing.key}")
    private String deadLetterRoutingKey;

    @Value("${rabbitmq.routing.key.new}")
    private String routingKeyNew;

    @Bean
    TopicExchange deadLetterTopicExchange() {
        return new TopicExchange(deadLetterExchange);
    }

    @Bean
    TopicExchange topicExhangeNew() {
        return new TopicExchange(exchangeNew);
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(deadLetterQueue).build();
    }

    @Bean
    Queue queueNew() {
        return QueueBuilder.durable(queueNew)
                .withArgument("x-dead-letter-exchange", deadLetterExchange)
                .withArgument("x-dead-letter-routing-key", deadLetterRoutingKey)
                .build();
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder
                .bind(deadLetterQueue())
                .to(deadLetterTopicExchange())
                .with(deadLetterRoutingKey);
    }

    @Bean
    Binding bindingNew() {
        return BindingBuilder
                .bind(queueNew())
                .to(topicExhangeNew())
                .with(routingKeyNew);
    }
}
