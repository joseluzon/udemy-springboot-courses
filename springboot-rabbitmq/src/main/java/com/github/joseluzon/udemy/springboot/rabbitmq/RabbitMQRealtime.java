package com.github.joseluzon.udemy.springboot.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;

import com.github.javafaker.Faker;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQRealtime {
    
    public static void main(String[] args) throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
        final var connectionFactory = new ConnectionFactory();
        connectionFactory.setUri("amqp://guest:guest@rabbitmq:5672");
        final var connection = connectionFactory.newConnection();
        final var channel = connection.createChannel();

        final var faker = new Faker();
        final var json = new JSONObject();
        json.put("from_date", "01-Jan-2022");
        json.put("to_date", "31-Dec-2022");
        json.put("email", faker.internet().emailAddress());
        json.put("query", "select * from data");

        channel.basicPublish("", "queue-1", null, json.toString().getBytes());

        channel.close();
        connection.close();        
    }
}
