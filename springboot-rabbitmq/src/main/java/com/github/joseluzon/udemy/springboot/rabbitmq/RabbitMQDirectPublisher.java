package com.github.joseluzon.udemy.springboot.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQDirectPublisher {

    public static void main(String[] args) throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        final var connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://guest:guest@rabbitmq:5672");
        final var connection = connectionFactory.newConnection();
        final var channel = connection.createChannel();

        channel.basicPublish("direct-exchange", "mobile", null, "This is mobile!".getBytes());
        channel.basicPublish("direct-exchange", "ac", null, "This is ac!".getBytes());
        channel.basicPublish("direct-exchange", "tv", null, "This is tv!".getBytes());       

        channel.close();
        connection.close();
    }
}
