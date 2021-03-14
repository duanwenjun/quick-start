package com.duan.thread;

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
    int a = 0;
    new Thread(() -> print("A", a)).start();
    new Thread(() -> print("B", a)).start();
    new Thread(() -> print("C", a)).start();
    new Thread(() -> print("D", a)).start();
    new Thread(() -> print("E", a)).start();
    new Thread(() -> print("F", a)).start();

  }

  private static void print (String s, int a) {
    for (int i = 0; i < 10; i++) {
      a = a + 1;
    }
    log.info("线程{} 值:{}", s, a);
  }
}
