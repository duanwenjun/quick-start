package com.duan.es;

import java.util.List;

/**
 * @author duanwj
 */
public interface PersonService {

  /**
   * 查询列表
   *
   * @param person 查询参数
   * @return 查询列表
   */
  List<Person> getList (Person person);


  /**
   * 保存
   *
   * @param person 保存参数
   */
  void add (Person person);

}
