package com.duan.customer.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author duanwj
 */
@Data
@Accessors(chain = true)
public class UserDTO implements Serializable {

  private static final long serialVersionUID = 369971401757007164L;

  private String name;
  private String phone;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date updateTime;

}
