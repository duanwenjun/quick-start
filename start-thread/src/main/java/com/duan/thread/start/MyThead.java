package com.duan.thread.start;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建一个线程
 *
 * @author duanwj
 */
@Slf4j
public class MyThead extends Thread {

  @Override
  public void run () {
    log.info("执行继承 Thread 线程方法");
  }

  public static void main (String[] args) {
    MyThead myThead = new MyThead();
    myThead.start();
  }
}
