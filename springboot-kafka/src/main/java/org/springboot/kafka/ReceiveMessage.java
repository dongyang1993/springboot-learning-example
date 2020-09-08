package org.springboot.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveMessage {

    @KafkaListener(id = "defaultGroupId", topics = "t1")
    public void listen(String data) {
        System.out.println(data);
    }
}
