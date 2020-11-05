package com.duan.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author duanwj
 */
public interface PersonRepository extends ElasticsearchRepository<Person, String> {

}
