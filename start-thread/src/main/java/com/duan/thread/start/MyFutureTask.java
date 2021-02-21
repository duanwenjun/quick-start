package com.duan.thread.start;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用 FutureTask
 * <p>
 * FutureTask能够在高并发环境下确保任务只执行一次
 * <p>
 * state表示任务的运行状态，初始状态为NEW。运行状态只会在set、setException、cancel方法中终止。COMPLETING、INTERRUPTING是任务完成后的瞬时状态
 * <p>
 * state可能的状态转变路径如下： NEW -> COMPLETING -> NORMAL
 * <p>
 * NEW -> COMPLETING -> EXCEPTIONAL
 * <p>
 * NEW -> CANCELLED
 * <p>
 * NEW -> INTERRUPTING -> INTERRUPTED
 *
 * @author duanwj
 */
@Slf4j
public class MyFutureTask implements Callable<Integer> {

  @Override
  public Integer call () throws Exception {
    ThreadUtil.sleep(1, TimeUnit.SECONDS);
    return 2;
  }

  @SneakyThrows
  public static void main (String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();
    FutureTask<Integer> futureTask = new FutureTask<>(new MyFutureTask());
    executorService.submit(futureTask);
    log.info("使用 futureTask 执行任务返回结果:{}", futureTask.get());
  }
}
