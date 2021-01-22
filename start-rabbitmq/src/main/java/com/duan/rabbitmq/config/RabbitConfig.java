package com.duan.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Component
public class RabbitConfig {

  @Bean
  public Queue queue () {
    return new Queue("hello-queue");
  }
}
