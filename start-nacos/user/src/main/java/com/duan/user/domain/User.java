package com.duan.user.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author duanwj
 */
@Data
public class User implements Serializable {

  private static final long serialVersionUID = 4872270475832416542L;
  @TableId(type = IdType.AUTO)
  private Long id;

  private String name;

  private String phone;

}
