package com.duan.apm;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Component
@Slf4j
public class ScheduleTask {

  @Scheduled(fixedRate = 3000)
  public void scheduledTask () {
    log.info("任务执行时间：{}", LocalDateTime.now());
  }

}
