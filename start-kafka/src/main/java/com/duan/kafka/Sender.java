package com.duan.kafka;

import javax.annotation.Resource;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Component
public class Sender {

  @Resource
  private KafkaTemplate<String, String> kafkaTemplate;

  void send () {
    kafkaTemplate.send("test-collect", "kafka messages send....");
  }
}
