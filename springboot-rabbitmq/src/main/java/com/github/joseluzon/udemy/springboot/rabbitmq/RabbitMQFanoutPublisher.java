package com.github.joseluzon.udemy.springboot.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQFanoutPublisher {
    
    public static void main(String[] args) throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        final var connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://guest:guest@rabbitmq:5672");
        final var connection = connectionFactory.newConnection();
        final var channel = connection.createChannel();

        channel.basicPublish("fanout-exchange", "", null, "This is fanedout message!".getBytes());    

        channel.close();
        connection.close();
    }
}
