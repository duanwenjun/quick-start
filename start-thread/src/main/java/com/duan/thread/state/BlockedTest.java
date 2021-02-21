package com.duan.thread.state;

import java.util.concurrent.TimeUnit;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 线程状态装换
 * <p>
 * a的状态转换过程：RUNNABLE（a.start()） -> TIMED_WATING（Thread.sleep()）->RUNABLE（sleep()时间到）->BLOCKED(未抢到锁) -> TERMINATED
 * <p>
 * b的状态转换过程：RUNNABLE（b.start()) -> BLOCKED(未抢到锁) ->TERMINATED
 *
 * @author duanwj
 */
@Slf4j
public class BlockedTest {

  public static void main (String[] args) {
    Thread a = new Thread(() -> test(), "a");
    a.start();
    ThreadUtil.sleep(1, TimeUnit.SECONDS);
    Thread b = new Thread(() -> test(), "b");
    b.start();
    log.info("线程{} 状态:{}", a.getName(), a.getState());
    log.info("线程{} 状态:{}", b.getName(), b.getState());
  }

  private synchronized static void test () {
    ThreadUtil.sleep(2, TimeUnit.SECONDS);
  }
}
