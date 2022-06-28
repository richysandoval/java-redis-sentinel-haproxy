package com.example.redisdemo.controller;

import com.example.redisdemo.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.atomic.AtomicLong;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class GreetingController {

    Logger log = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private RedisCommands<String,String> commands;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        log.info("Greetings");
        commands.lpush(Long.toString(greeting.getId()), greeting.getContent());

        return greeting;
    }
}
