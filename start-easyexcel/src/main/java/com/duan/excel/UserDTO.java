package com.duan.excel;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author duanwj
 */
@Data
public class UserDTO implements Serializable {

  private static final long serialVersionUID = -8128886693521296887L;
  @ExcelProperty(value = "id")
  private Long id;
  @ExcelProperty(value = "用户姓名")
  private String name;
  @ExcelProperty(value = "手机号")
  private String phone;

}
