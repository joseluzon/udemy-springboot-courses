package com.github.joseluzon.udemy.springboot.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQPublisher {
    
    public static void main(String[] args) throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        final var connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://guest:guest@rabbitmq:5672");
        final var connection = connectionFactory.newConnection();
        final var channel = connection.createChannel();

        // One Publisher - One Consumer
        // final var message = "Hello RabbitMQ!";
        // channel.basicPublish("", "queue-1", null, message.getBytes());

        // One Publisher - N Consumer (Round robin)
        final var messages = new String[] { "one", "two", "three", "four" };
        for (final var m: messages) {
            channel.basicPublish("", "queue-1", null, m.getBytes());
        }        

        channel.close();
        connection.close();
    }
}
