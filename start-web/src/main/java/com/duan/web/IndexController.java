package com.duan.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@RequestMapping("test")
public class IndexController {

  @PostMapping(produces = "application/x-www-form-urlencoded")
  String test (String name, Integer age) {
    return "name:" + name + "age:" + age;
  }
}
