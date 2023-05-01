package com.github.joseluzon.udemy.springboot.rabbitmq.controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.joseluzon.udemy.springboot.rabbitmq.model.Person;
import lombok.RequiredArgsConstructor;

@Profile("development-nomvc")
@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor // Ctor. is auto @Autowired
public class TestController {

    private final RabbitTemplate rabbitTemplate;
    
    // @GetMapping("/{name}")
    // public ResponseEntity<String> testAPI(@PathVariable("name") final String name) {
    //     final var person = Person.builder().id(1L).name(name).build();
    //     rabbitTemplate.convertAndSend("mobile", person);
    //     rabbitTemplate.convertAndSend("direct-exchange", "mobile", person);
    //     rabbitTemplate.convertAndSend("fanout-exchange", "", person);
    //     rabbitTemplate.convertAndSend("topic-exchange", "tv.mobile.ac", person);
    //     return new ResponseEntity<String>("null", HttpStatus.OK);
    // }

    @GetMapping("/{name}")
    public ResponseEntity<String> testAPI(@PathVariable("name") final String name) throws IOException {
        final var person = Person.builder().id(1L).name(name).build();

        final var bos = new ByteArrayOutputStream();
        final var out = new ObjectOutputStream(bos);
        out.writeObject(person);
        out.flush();
        out.close();

        final var byteMessage = bos.toByteArray();
        bos.close();

        final var message = MessageBuilder.withBody(byteMessage)
            .setHeader("item1", "mob")
            .setHeader("item2", "television").build();
        rabbitTemplate.send("headers-exchange", "", message);
        
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

}
