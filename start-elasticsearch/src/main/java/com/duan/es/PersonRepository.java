package com.duan.es;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author duanwj
 */
public interface PersonRepository extends ElasticsearchRepository<Person, String> {


  /**
   * 更具姓名查询
   *
   * @param name 查询参数
   * @return 查询结果
   */
  List<Person> findByName (String name);



}
