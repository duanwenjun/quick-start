package com.duan.weixin;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@RequestMapping("test")
@Slf4j
public class IndexController {

  @GetMapping
  String index () {
    return "hello";
  }

  @GetMapping("/token")
  String token (CallBack request) {
    log.info("{}", JSONUtil.parse(request));
    return request.getEchostr();
  }

}
