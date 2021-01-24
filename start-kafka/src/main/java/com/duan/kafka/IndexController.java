package com.duan.kafka;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@Slf4j
public class IndexController {

  @Resource
  private Sender sender;

  @GetMapping("/send")
  ResponseEntity<String> send () {
    sender.send();
    log.info("开始发送kafka消息");
    return ResponseEntity.ok("send success");
  }
}
