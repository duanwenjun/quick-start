package com.duan.weixin;

import java.io.Serializable;

import lombok.Data;

/**
 * @author duanwj
 */
@Data
public class CallBack implements Serializable {

  private String signature;
  private String timestamp;
  private String nonce;
  private String echostr;

}
