package com.duan.thread.state;

import lombok.extern.slf4j.Slf4j;

/**
 * ThreadLocal用法
 * <p>
 * ThreadLocal是一个本地线程副本变量工具类。内部是一个弱引用的Map来维护
 * <p>
 * TODO InheritableThreadLocal
 * <p>
 * InheritableThreadLocal类与ThreadLocal类稍有不同，Inheritable是继承的意思。它不仅仅是当前线程可以存取副本值，而且它的子线程也可以存取这个副本值
 *
 * @author duanwj
 */
@Slf4j
public class ThreadLocalDemo {

  static class ThreadA implements Runnable {

    private final ThreadLocal<String> threadLocal;

    public ThreadA (ThreadLocal<String> threadLocal) {
      this.threadLocal = threadLocal;
    }

    @Override
    public void run () {
      threadLocal.set("ThreadA");
      log.info("线程 A threadLocal:{}", threadLocal.get());
      threadLocal.remove();
    }
  }

  static class ThreadB implements Runnable {

    private final ThreadLocal<String> threadLocal;

    public ThreadB (ThreadLocal<String> threadLocal) {
      this.threadLocal = threadLocal;
    }

    @Override
    public void run () {
      threadLocal.set("ThreadB");
      log.info("线程 B threadLocal:{}", threadLocal.get());
      threadLocal.remove();
    }
  }

  public static void main (String[] args) {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    Thread threadA = new Thread(new ThreadA(threadLocal));
    Thread threadB = new Thread(new ThreadB(threadLocal));
    threadA.start();
    threadB.start();
  }
}
