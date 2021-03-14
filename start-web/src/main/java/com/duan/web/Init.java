package com.duan.web;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Component
@Slf4j
public class Init {

  @PostConstruct
  void init1 () {
    log.info("项目初始化");
  }

  @PostConstruct
  void init2 () {
    for (; ; ) {
      log.info("无限循环！");
    }
  }

}
