package com.duan.apm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author duanwj
 */
@SpringBootApplication
@EnableScheduling
public class ElasticApmApplication {

  public static void main (String[] args) {
    SpringApplication.run(ElasticApmApplication.class, args);
  }
}
