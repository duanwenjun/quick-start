package com.duan.nginx;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RequestMapping("/")
@RestController
public class HomeController {

  @RequestMapping
  String home () {
    return "home";
  }
}
