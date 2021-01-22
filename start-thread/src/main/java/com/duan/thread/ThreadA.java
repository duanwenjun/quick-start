package com.duan.thread;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duanwj
 */
@Slf4j
public class ThreadA extends Thread {


  private int i;
  private UnsafeThread unsafeThread;

  ThreadA (int i, UnsafeThread unsafeThread) {
    this.i = i;
    this.unsafeThread = unsafeThread;
  }

  @Override
  public void run () {
    ThreadUtil.sleep(RandomUtil.randomInt(50));
    unsafeThread.calc();
    log.info("线程{}执行第[{}]次,调用计算结果:[{}]", Thread.currentThread().getName(), i, unsafeThread.getCount());
  }
}
