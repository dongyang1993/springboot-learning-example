package org.springboot.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SendMessage implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendMessage.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("t1", UUID.randomUUID());
        }
        LOGGER.info("发送消息完成");
    }
}
