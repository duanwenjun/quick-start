package com.duan.thread.state;

import lombok.extern.slf4j.Slf4j;

/**
 * 类似信号量实现线程 A 输出 0 B 输出 1 A 输出 2 B输出 3...
 * <p>
 * 多个线程（超过2个）需要相互合作，我们用简单的“锁”和“等待通知机制”就不那么方便了。这个时候就可以用到信号量
 *
 * @author duanwj
 */
@Slf4j
public class Signal {

  private static volatile int signal = 0;

  static class ThreadA implements Runnable {

    @Override
    public void run () {
      while (signal < 100) {
        if (signal % 2 == 0) {
          log.info("ThreadA:{}", signal);
          signal = signal + 1;
        }
      }
    }
  }

  static class ThreadB implements Runnable {

    @Override
    public void run () {
      while (signal < 100) {
        if (signal % 2 == 1) {
          log.info("ThreadB:{}", signal);
          signal = signal + 1;
        }
      }
    }
  }

  public static void main (String[] args) {
    Thread threadA = new Thread(new ThreadA());
    Thread threadB = new Thread(new ThreadB());
    threadA.start();
    threadB.start();
  }
}
