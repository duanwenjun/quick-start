package com.duan.apm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


/**
 * @author duanwj
 */
@Component
@Slf4j
public class Index implements ApplicationRunner {

  @Override
  public void run (ApplicationArguments args) throws Exception {
    log.info("启动了");
  }

}
