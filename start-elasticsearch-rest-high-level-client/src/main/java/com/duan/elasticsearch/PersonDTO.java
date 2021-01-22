package com.duan.elasticsearch;

import java.io.Serializable;
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
public class PersonDTO implements Serializable {


  /**
   * 分页
   */
  private Integer page;
  /**
   * 分页大小
   */
  private Integer size;

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
  private String birthday;

  /**
   * 介绍
   */
  private String remark;

  /**
   * 创建时间
   */
  private String createTime;


  /**
   * 全文搜索内容
   */
  private String content;
}
