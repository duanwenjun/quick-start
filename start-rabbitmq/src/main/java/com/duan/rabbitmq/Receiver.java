package com.duan.rabbitmq;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Component
@RabbitListener(queues = "hello-queue")
@Slf4j
public class Receiver {

  @RabbitHandler
  private void process (String hello) {
    Transaction transaction = ElasticApm.currentTransaction();
    Transaction transaction1 = ElasticApm.startTransaction();
    transaction1.setName("Receiver#process");
    String traceId = transaction.getTraceId();
    String traceId1 = transaction1.getTraceId();
    log.info("本类上个事务traceId:{}，本次开启:{}接收到消息:{}", traceId, traceId1, hello);
  }
}
