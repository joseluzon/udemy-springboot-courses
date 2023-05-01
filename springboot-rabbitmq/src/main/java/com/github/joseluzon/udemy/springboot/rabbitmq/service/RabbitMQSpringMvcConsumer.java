package com.github.joseluzon.udemy.springboot.rabbitmq.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.github.joseluzon.udemy.springboot.rabbitmq.model.Person;
import lombok.extern.slf4j.Slf4j;

@Profile("development-mvc")
@Service
@EnableRabbit
@Slf4j
public class RabbitMQSpringMvcConsumer {
    
    // @RabbitListener(queues = "mobile")
    // public void getMessage(final Person person) {
    //     log.info("received person: {}", person);
    // }

    @RabbitListener(queues = "mobile")
    public void getMessage(final byte[] message) throws IOException, ClassNotFoundException {
        final var bis = new ByteArrayInputStream(message);
        final var in = new ObjectInputStream(bis);
        final var person = (Person) in.readObject();
        in.close();
        bis.close();
        
        log.info("received person: {}", person);
    }
}
