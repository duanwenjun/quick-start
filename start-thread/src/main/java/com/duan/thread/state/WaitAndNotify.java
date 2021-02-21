package com.duan.thread.state;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 等待和通知用法
 * <p>
 * 线程 AB 交替完成任务
 *
 * @author duanwj
 */
@Slf4j
public class WaitAndNotify {

  static final Object lock = new Object();

  public static void main (String[] args) {
    new Thread(() -> test("A"), "A").start();
    new Thread(() -> test("B"), "B").start();
  }

  @SneakyThrows
  private static void test (String a) {
    synchronized (lock) {
      for (int i = 0; i < 100; i++) {
        log.info("执行线程{} i={}", a, i);
        lock.notify();
        lock.wait();
      }
      lock.notify();
    }
  }

}
