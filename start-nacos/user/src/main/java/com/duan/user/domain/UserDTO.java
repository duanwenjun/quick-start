package com.duan.user.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * @author duanwj
 */
@Data
public class UserDTO implements Serializable {

  private static final long serialVersionUID = -8128886693521296887L;
  private Long id;
  private String name;
  private String phone;

}
