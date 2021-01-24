package com.duan.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@KafkaListener(topics = "test-collect")
@Slf4j
@Component
public class Receiver {

  @KafkaHandler
  void listen (String message) {
    log.info("接受到消息:{}", message);
  }
}
