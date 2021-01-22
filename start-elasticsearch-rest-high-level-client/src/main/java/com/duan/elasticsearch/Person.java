package com.duan.elasticsearch;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author duanwj
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

  /**
   * 主键
   */
  private String id;
  /**
   * 名字
   */
  private String name;

  /**
   * 国家
   */
  private String country;

  /**
   * 年龄
   */
  private Integer age;

  /**
   * 生日
   */
  private Date birthday;

  /**
   * 介绍
   */
  private String remark;

  /**
   * 创建时间
   */
  private Date createTime;
}
