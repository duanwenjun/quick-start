package com.duan.elasticsearch;

import java.util.List;

/**
 * @author duanwj
 */
public interface PersonService {

  /**
   * 获取列表
   *
   * @param personDTO 查询参数
   * @return 列表
   */
  List<Person> getList (PersonDTO personDTO);

  /**
   * 保存
   *
   * @param person 人员信息
   * @return 状态
   */
  String add (Person person);

  /**
   * 查询聚合结果
   *
   * @param field 字段
   * @return 聚合去重后结果
   */
  List<String> getAggregationList (String field);

  /**
   * 搜索建议
   *
   * @param queryContent 搜索内容
   * @return 搜索列表
   */
  List<String> getSuggestions (String queryContent);

  /**
   * 批量提交
   *
   * @param person 人员信息
   * @return 创建状态
   */
  String bulk (Person person);
}
