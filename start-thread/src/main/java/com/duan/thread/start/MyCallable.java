package com.duan.thread.start;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用 Callable 调用任务
 *
 * @author duanwj
 */
@Slf4j
public class MyCallable implements Callable<Integer> {

  @Override
  public Integer call () throws Exception {
    //模拟休眠1秒钟
    ThreadUtil.sleep(1, TimeUnit.SECONDS);
    return 2;
  }

  @SneakyThrows
  public static void main (String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Future<Integer> submit = executorService.submit(new MyCallable());
    //Integer result = submit.get(1, TimeUnit.SECONDS);
    Integer result = submit.get();
    log.info("使用 callable 执行任务返回值:{}", result);
  }
}
