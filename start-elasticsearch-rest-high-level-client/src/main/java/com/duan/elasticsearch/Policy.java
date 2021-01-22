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
public class Policy implements Serializable {

  /**
   * 政策姓名
   */
  private String name;

  /**
   * 政策时间
   */
  private Date date;
}
