package com.duan.elasticsearch;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.completion.CompletionSuggestion;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.stereotype.Service;

/**
 * @author duanwj
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

  @Resource
  private RestHighLevelClient restHighLevelClient;
  @Resource
  private BulkProcessor bulkProcessor;

  @SneakyThrows
  @Override
  public List<Person> getList (PersonDTO personDTO) {
    // 搜索请求对象
    SearchRequest searchRequest = new SearchRequest("person");
    // 搜索源构建对象
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    // 高亮显示
    HighlightBuilder highlightBuilder = new HighlightBuilder();
    // 高亮前缀
    highlightBuilder.preTags("<mark>");
    // 高亮前缀
    highlightBuilder.postTags("</mark>");
    // 高亮字段
    List<Field> fields = highlightBuilder.fields();
    fields.add(new HighlightBuilder.Field("name"));
    fields.add(new HighlightBuilder.Field("country"));
    fields.add(new HighlightBuilder.Field("age"));
    searchSourceBuilder.highlighter(highlightBuilder);
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    //姓名 模糊查询
    if (StrUtil.isNotBlank(personDTO.getName())) {
      boolQueryBuilder.must(QueryBuilders.fuzzyQuery("name", personDTO.getName()));
    }
    //年龄
    if (ObjectUtil.isNotEmpty(personDTO.getAge())) {
      boolQueryBuilder.filter(QueryBuilders.termQuery("age", personDTO.getAge()));
    }
    //时间范围 gte大于等于，lte小于等于
    if (StrUtil.isNotBlank(personDTO.getCreateTime())) {
      boolQueryBuilder.filter(QueryBuilders.rangeQuery("createTime").gte(personDTO.getCreateTime()));
    }
    // 多字段查询
    if (StrUtil.isNotBlank(personDTO.getContent())) {
      boolQueryBuilder.must(QueryBuilders.multiMatchQuery(personDTO.getContent(), "name", "age", "country")
          .minimumShouldMatch("60%"));
    }
    // 将查询条件封装给查询对象
    searchSourceBuilder.query(boolQueryBuilder);
    // 分页参数
    int page = ObjectUtil.isEmpty(personDTO.getPage()) ? 0 : personDTO.getPage() - 1;
    int size = ObjectUtil.isEmpty(personDTO.getSize()) ? 10 : personDTO.getSize();
    searchSourceBuilder.from(page * size);
    searchSourceBuilder.size(size);
    // 排序
    searchSourceBuilder.sort("createTime", SortOrder.DESC);
    searchRequest.source(searchSourceBuilder);
    log.info("查询列表DSL:{}", searchSourceBuilder.toString());
    SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    SearchHits searchHits = searchResponse.getHits();
    SearchHit[] hits = searchHits.getHits();
    List<Person> personList = new ArrayList<>();
    for (SearchHit searchHit : hits) {
      // 源文档内容
      Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
      // 高亮替换
      Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
      if (MapUtil.isNotEmpty(highlightFields)) {
        highlightFields.keySet().forEach(e -> {
          if (ObjectUtil.isNotEmpty(highlightFields.get(e))) {
            Text[] fragments = highlightFields.get(e).getFragments();
            StringBuilder stringBuffer = new StringBuilder();
            for (Text str : fragments) {
              stringBuffer.append(str.string());
            }
            sourceAsMap.put(e, stringBuffer.toString());
          }
        });
      }
      Person p = BeanUtil.toBeanIgnoreCase(sourceAsMap, Person.class, true);
      personList.add(p);
    }
    log.info("分页查询总数:{}", searchHits.getTotalHits().value);
    return personList;
  }

  @SneakyThrows
  @Override
  public String add (Person person) {
    IndexRequest indexRequest = new IndexRequest("person");
    person.setCreateTime(new Date());
    person.setBirthday(new Date());
    indexRequest.source(BeanUtil.beanToMap(person));
    IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    log.info("添加数据返回值;{}", JSONUtil.parse(indexResponse));
    return indexResponse.getResult().name();
  }

  @SneakyThrows
  @Override
  public List<String> getAggregationList (String field) {
    SearchRequest searchRequest = new SearchRequest("person");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("sum").field(field + ".keyword");
    searchSourceBuilder.aggregation(termsAggregationBuilder);
    searchSourceBuilder.size(0);
    searchSourceBuilder.fetchSource("name", null);
    log.info("分组DSL:{}", searchSourceBuilder.toString());
    searchRequest.source(searchSourceBuilder);
    SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    Terms terms = search.getAggregations().get("sum");
    return terms.getBuckets().stream().map(MultiBucketsAggregation.Bucket::getKeyAsString).collect(Collectors.toList());
  }

  @SneakyThrows
  @Override
  public List<String> getSuggestions (String queryContent) {
    SearchRequest searchRequest = new SearchRequest("person");
    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    CompletionSuggestionBuilder termSuggestionBuilder = SuggestBuilders.completionSuggestion("title.suggest")
        .prefix(queryContent)
        .skipDuplicates(true);
    SuggestBuilder suggestBuilder = new SuggestBuilder();
    suggestBuilder.addSuggestion("suggest_title", termSuggestionBuilder);
    searchSourceBuilder.fetchSource("title", "");
    searchSourceBuilder.suggest(suggestBuilder);
    searchRequest.source(searchSourceBuilder);
    log.info("搜索建议DSL:{}", searchSourceBuilder.toString());
    SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    Suggest suggest = search.getSuggest();
    CompletionSuggestion termSuggestion = suggest.getSuggestion("suggest_title");
    List<String> list = new ArrayList<>();
    for (CompletionSuggestion.Entry entry : termSuggestion.getEntries()) {
      for (CompletionSuggestion.Entry.Option option : entry) {
        String suggestText = option.getText().string();
        list.add(suggestText);
      }
    }
    return list;
  }

  @Override
  public String bulk (Person person) {
    log.info("开始批量提交");
    IndexRequest indexRequest = new IndexRequest("person");
    person.setCreateTime(new Date());
    person.setBirthday(new Date());
    indexRequest.source(BeanUtil.beanToMap(person));
    BulkProcessor bulkProcessor = this.bulkProcessor.add(indexRequest);
    return "created";
  }
}
