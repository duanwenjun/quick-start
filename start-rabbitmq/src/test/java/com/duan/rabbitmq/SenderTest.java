package com.duan.rabbitmq;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
class SenderTest {

  @Resource
  private Sender sender;

  @Test
  public void test () {
    sender.send();
  }
}