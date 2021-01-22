package com.duan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.StopWatch;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duan.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
class UserServiceTest {

  @Resource
  private UserService userService;

  @Test
  public void bachInsert () {
    List<User> list = new ArrayList<>(1000);
    for (int i = 0; i < 50000; i++) {
      User user = new User();
      user.setName("name" + i);
      user.setAge(i);
      user.setCreateTime(new Date());
      user.setUpdateTime(new Date());
      list.add(user);
    }
    StopWatch stopWatch = DateUtil.createStopWatch();
    stopWatch.start();
    Integer line = userService.batchSave(list);
    stopWatch.stop();
    double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
    log.info("批量插入执行行数:{}耗时:{}", line, totalTimeSeconds);
  }

  @Test
  public void list () {
    Page<User> page = userService.page(new Page<>());
    log.info("分页查询总数[{}]当前页数[{}]页面元素个数[{}]", page.getTotal(), page.getCurrent(), page.getSize());
  }

}