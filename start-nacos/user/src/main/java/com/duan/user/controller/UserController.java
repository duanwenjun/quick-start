package com.duan.user.controller;

import java.util.List;

import javax.annotation.Resource;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import com.duan.user.domain.User;
import com.duan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RestController
@RequestMapping("user")
@RefreshScope
@Slf4j
public class UserController {

  @Value("${user.name:defaultName}")
  private String name;
  @Value("${user.age:19}")
  private String age;

  @Resource
  private UserService userService;

  @GetMapping
  //@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = "请求参数异常!")
  public User getUser () throws RuntimeException {
    log.info("user服务被请求了");
    ThreadUtil.sleep(3000);
    //throw new RuntimeException();

    //log.info("user服务被调用了");
    return userService.getById(1);
  }

  @PostMapping
  public User add () {
    log.info("新增请求");
    ThreadUtil.sleep(3000);
    User user = new User();
    user.setName(IdUtil.fastUUID());
    userService.save(user);
    return user;
  }

  @PutMapping
  public User update () {
    User user = new User();
    user.setId(1L);
    user.setPhone("157");
    userService.updateById(user);
    return user;
  }

  @GetMapping("/name")
  public String getName () {
    return name + age;
  }

  @GetMapping("/list")
  public List<User> getList () {
    return userService.list();
  }

}
