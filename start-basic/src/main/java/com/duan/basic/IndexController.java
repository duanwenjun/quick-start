package com.duan.basic;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@RequestMapping("index")
public class IndexController {

  @Resource
  private ReadProperties readProperties;

  @GetMapping
  ReadProperties index () {
    return readProperties;
  }

}
