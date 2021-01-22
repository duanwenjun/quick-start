package com.duan.rabbitmq;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Component
@Slf4j
public class Sender {

  @Resource
  private AmqpTemplate amqpTemplate;

  public void send () {
    String text = "hello" + LocalDateTime.now().toString();
    log.info("send:{}", text);
    amqpTemplate.convertAndSend("hello-queue", text);
  }
}
