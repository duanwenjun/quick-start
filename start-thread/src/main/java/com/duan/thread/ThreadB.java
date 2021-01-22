package com.duan.thread;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duanwj
 */
@Slf4j
public class ThreadB extends Thread {


  private int i;
  private SafeThread safeThread;

  ThreadB (int i, SafeThread safeThread) {
    this.i = i;
    this.safeThread = safeThread;
  }

  @Override
  public void run () {
    ThreadUtil.sleep(RandomUtil.randomInt(50));
    safeThread.calc();
    log.info("线程{}执行第[{}]次,调用计算结果:[{}]", Thread.currentThread().getName(), i, safeThread.getCount());
  }
}
