package com.duan.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duanwj
 */
@Slf4j
public class SafeThread {

  private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
  private int count;

  public void calc () {
    threadLocal.set(count + 1);
  }

  public int getCount () {
    return threadLocal.get();
  }

  @SneakyThrows
  public static void main (String[] args) {
    SafeThread safeThread = new SafeThread();
    for (int i = 0; i < 40; i++) {
      ThreadB t = new ThreadB(i, safeThread);
      t.start();
    }
    ThreadUtil.sleep(2000);
    log.info("relCount:{}", safeThread.count);
  }

}
