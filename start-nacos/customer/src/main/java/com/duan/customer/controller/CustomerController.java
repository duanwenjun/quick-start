package com.duan.customer.controller;

import javax.annotation.Resource;

import com.duan.customer.domain.UserDTO;
import com.duan.customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

  @Resource
  private CustomerService customerService;

  @GetMapping
  public UserDTO getUser () {
    return customerService.getUser();
  }

  @PostMapping
  public UserDTO addUser () {
    return customerService.addUser();
  }
}
