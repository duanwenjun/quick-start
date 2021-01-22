package com.duan.es;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author duanwj
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "person-#{T(java.time.LocalDate).now().toString()}", shards = 1)
public class Person implements Serializable {

  /**
   * 主键
   */
  @Id
  private String id;
  /**
   * 名字
   */
  @Field(type = FieldType.Keyword)
  private String name;

  /**
   * 国家
   */
  @Field(type = FieldType.Keyword)
  private String country;

  /**
   * 年龄
   */
  @Field(type = FieldType.Integer)
  private Integer age;

  /**
   * 生日
   */
  @Field(type = FieldType.Date, format = DateFormat.date)
  private Date birthday;

  /**
   * 介绍
   */
  @Field(type = FieldType.Keyword)
  private String remark;

  /**
   * 对象嵌套
   */
  @Field(type = FieldType.Object)
  private Policy policy;

  /**
   * 创建时间
   */
  @Field(type = FieldType.Date, format = DateFormat.date_time)
  private Date createTime;

  public static void main (String[] args) {
    System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM")));
  }
}
