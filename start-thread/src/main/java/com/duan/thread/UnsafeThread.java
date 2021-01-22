package com.duan.thread;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author duanwj
 */
@Slf4j
public class UnsafeThread {

  private int count;

  public void calc () {
    count++;
  }

  public int getCount () {
    return count;
  }

  @SneakyThrows
  public static void main (String[] args) {
    UnsafeThread unsafeThread = new UnsafeThread();
    for (int i = 0; i < 40; i++) {
      ThreadA t = new ThreadA(i, unsafeThread);
      t.start();
    }
    ThreadUtil.sleep(2000);
    log.info("relCount:{}", unsafeThread.count);
  }

}
