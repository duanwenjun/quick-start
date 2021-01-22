package com.duan.knife4j;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@Api
public class IndexController {

  @PostMapping
  String test (String name, Integer age) {
    return "name:" + name + "age:" + age;
  }
}
