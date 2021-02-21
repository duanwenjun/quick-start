package com.duan.thread.start;

import lombok.extern.slf4j.Slf4j;

/**
 * 实现 runnable 接口 启动一个线程
 *
 * @author duanwj
 */
@Slf4j
public class MyRunnable implements Runnable {

  @Override
  public void run () {
    log.info("启动实现 runnable 一个线程");
  }

  public static void main (String[] args) {
    new Thread(new MyRunnable()).start();
    // 匿名函数
    new Thread(() -> {
      log.info("使用匿名函数创建一个线程");
    }).start();
  }
}
