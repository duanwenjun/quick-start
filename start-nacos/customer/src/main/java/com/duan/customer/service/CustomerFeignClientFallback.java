package com.duan.customer.service;

import com.duan.customer.domain.UserDTO;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Component
public class CustomerFeignClientFallback implements CustomerService {

  @Override
  public UserDTO getUser () {
    return new UserDTO().setName("服务降级");
  }

  @Override
  public UserDTO addUser () {
    return new UserDTO().setName("系统繁忙");
  }
}
