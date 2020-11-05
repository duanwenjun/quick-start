package com.duan.customer.service;

import com.duan.customer.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author duanwj
 */
//@FeignClient(name = "user", fallback = CustomerFeignClientFallback.class, fallbackFactory = CustomerFeignClientFallbackFactory.class)
//@FeignClient(name = "user", fallbackFactory = CustomerFeignClientFallbackFactory.class)
//@FeignClient(name = "user")
@FeignClient(name = "user", fallback = CustomerFeignClientFallback.class)
public interface CustomerService {

  /**
   * 获取用户信息
   *
   * @return 用户信息
   */
  @GetMapping("/user")
  UserDTO getUser ();

  /**
   * 添加一用户
   *
   * @return 用户信息
   */
  @PostMapping("/user")
  UserDTO addUser ();
}
