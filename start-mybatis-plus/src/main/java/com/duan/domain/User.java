package com.duan.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author duanwj
 */
@Data
public class User implements Serializable {

  @TableId
  private Long id;

  private String name;

  private Integer age;

  private Date createTime;
  private Date updateTime;

}
