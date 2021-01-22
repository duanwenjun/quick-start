package com.duan.es;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @author duanwj
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

  @Resource
  private PersonRepository personRepository;
  @Resource
  private ElasticsearchRestTemplate restTemplate;

  @Override
  public List<Person> getList (Person person) {
    NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
    builder.withQuery(QueryBuilders.matchAllQuery());
    SearchHits<Person> search = restTemplate.search(builder.build(), Person.class);
    log.info("查询总数：{}", search.getTotalHits());
    return search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
  }

  @Override
  public void add (Person person) {
    Person p = new Person();
    p.setAge(18);
    p.setBirthday(DateUtil.date());
    p.setCountry("中国");
    p.setName("中国人");
    p.setRemark("备注");
    p.setCreateTime(DateUtil.date());
    Policy policy = new Policy();
    policy.setDate(DateUtil.date());
    policy.setName("政策一");
    p.setPolicy(policy);
    personRepository.save(p);
  }
}
