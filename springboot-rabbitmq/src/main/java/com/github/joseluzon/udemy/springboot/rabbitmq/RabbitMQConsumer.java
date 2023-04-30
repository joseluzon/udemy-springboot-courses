package com.github.joseluzon.udemy.springboot.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RabbitMQConsumer {

    public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException, TimeoutException {
        final var connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://guest:guest@rabbitmq:5672");
        final var connection = connectionFactory.newConnection();
        final var channel = connection.createChannel();

        final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            final var message = new String(delivery.getBody());
            log.info(String.format("consumed: %s", message));
        };
        channel.basicConsume("queue-1", true, deliverCallback, consumerTag -> { log.info(String.format("consumer tag: %s", consumerTag)); });
    }
}
