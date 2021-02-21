package com.duan.thread.start;

import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试线程优先级
 * <p>
 * 优先级不能代表执行顺序
 * <p>
 * Java程序中对线程所设置的优先级只是给操作系统一个建议，操作系统不一定会采纳。而真正的调用顺序，是由操作系统的线程调度算法决定的
 * <p>
 * Java提供一个线程调度器来监视和控制处于RUNNABLE状态的线程。线程的调度策略采用抢占式，优先级高的线程比优先级低的线程会有更大的几率优先执行。在优先级相同的情况下，按照“先到先得”的原则。每个Java程序都有一个默认的主线程，就是通过JVM启动的第一个线程main线程
 * <p>
 * 如果某个线程优先级大于线程所在线程组的最大优先级，那么该线程的优先级将会失效，取而代之的是线程组的最大优先级
 *
 * @author duanwj
 */
@Slf4j
public class T1 extends Thread {

  @Override
  public void run () {
    super.run();
    log.info("当前执行线程:[{}]优先:[{}]", Thread.currentThread().getName(), Thread.currentThread().getPriority());
  }

  public static void main (String[] args) {
    IntStream.range(1, 10).forEach(i -> {
      T1 t1 = new T1();
      t1.setPriority(i);
      t1.start();
    });
    ThreadGroup threadGroup = new ThreadGroup("t1");
    threadGroup.setMaxPriority(6);
    Thread thread = new Thread(threadGroup, "thread");
    thread.setPriority(9);
    log.info("线程优先级:[{}]，线程名称:{}", thread.getPriority(), thread.getName());
    log.info("线程组优先级:[{}]", threadGroup.getMaxPriority());
  }
}
