package com.duan.es;

import javax.annotation.Resource;

import cn.hutool.core.date.DateUtil;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duanwj
 */
@RequestMapping("person")
@RestController
public class IndexController {

  @Resource
  private PersonRepository personRepository;

  @GetMapping
  Page<Person> list () {
    NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
    builder.withQuery(QueryBuilders.matchAllQuery());
    return personRepository.search(builder.build());
  }

  @PostMapping
  void add () {
    Person person = new Person();
    person.setAge(18);
    person.setBirthday(DateUtil.date());
    person.setCountry("中国");
    person.setName("中国人");
    person.setRemark("备注");
    Policy policy = new Policy();
    policy.setDate(DateUtil.date());
    policy.setName("政策一");
    person.setPolicy(policy);
    personRepository.save(person);
  }
}
