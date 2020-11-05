package com.duan.customer.service;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Component
@Slf4j
public class CustomerFeignClientFallbackFactory implements FallbackFactory<CustomerService> {

  @Override
  public CustomerService create (Throwable throwable) {
    log.info("回退原因:{}", throwable.getMessage());
    return null;
  }
}
