package com.duan.thread.state;

import lombok.extern.slf4j.Slf4j;

/**
 * 执行完线程 A 或者 B 之后再执行其他的线程任务
 * <p>
 * 如果想要顺序执行任务 A B  需要保证线程获得 cpu 顺序
 * <p>
 * print 方法增加 synchronized 方法后保证线程 AB 每个线程执行完在执行下一个线程,不然线程无序执行
 *
 * @author duanwj
 */
@Slf4j
public class NoneLock {

  public static void main (String[] args) {

    new Thread(() -> print("A"), "A").start();
    new Thread(() -> print("B"), "B").start();
  }

  private static void print (String s) {
    for (int i = 0; i < 100; i++) {
      log.info("线程{} 执行:{}", s, i);
    }
  }
}
