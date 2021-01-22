package com.duan.rabbitmq;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@Slf4j
public class HomeController {

  @Resource
  private Sender sender;

  @GetMapping("/send")
  public void send () {
    sender.send();
  }
}
