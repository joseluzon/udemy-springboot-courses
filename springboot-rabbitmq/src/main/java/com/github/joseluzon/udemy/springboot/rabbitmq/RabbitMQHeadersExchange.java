package com.github.joseluzon.udemy.springboot.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RabbitMQHeadersExchange {
    
    public static void main(String[] args) throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        final var connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://guest:guest@rabbitmq:5672");
        final var connection = connectionFactory.newConnection();
        final var channel = connection.createChannel();

        // to mobile & television
        final var headersMap = new HashMap<String, Object>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "television");

        var basicProperties = new BasicProperties();
        basicProperties = basicProperties.builder().headers(headersMap).build();

        channel.basicPublish("headers-exchange", "", basicProperties, "This is headers message!".getBytes());

        channel.close();
        connection.close();
    }
}
