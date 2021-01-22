package com.duan.elasticsearch.config;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author duanwj
 */
@Configuration
@Slf4j
public class ElasticSearchConfig {

  @Resource
  private ElasticsearchProperties properties;

  @Bean(destroyMethod = "close")
  @Qualifier("restClient")
  public RestHighLevelClient restClient () {
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY,
        new UsernamePasswordCredentials(properties.getUsername(), properties.getPassword()));

    HttpHost[] hosts = properties.getClusterNodes().stream().map(this::createHttpHost).toArray(HttpHost[]::new);
    RestClientBuilder builder = RestClient.builder(hosts)
        .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider))
        .setRequestConfigCallback(requestConfigBuilder -> {
          requestConfigBuilder.setConnectTimeout(properties.getConnectTimeout());
          requestConfigBuilder.setSocketTimeout(properties.getSocketTimeout());
          requestConfigBuilder.setConnectionRequestTimeout(properties.getConnectionRequestTimeout());
          return requestConfigBuilder;
        });
    return new RestHighLevelClient(builder);
  }

  @Bean
  public BulkProcessor bulkProcessor (@Qualifier("restClient") RestHighLevelClient client) {
    Listener listener = new Listener() {
      @Override
      public void beforeBulk (long executionId, BulkRequest request) {
        log.info("序号:[{}]开始执行:[{}]条数据批量操作", executionId, request.numberOfActions());
      }

      @Override
      public void afterBulk (long executionId, BulkRequest request,
          BulkResponse response) {
        log.info("序号:[{}]成功执行[{}]条批量数据耗时:[{}]毫秒", executionId, request.numberOfActions(), response.getIngestTook().seconds());
      }

      @Override
      public void afterBulk (long executionId, BulkRequest request,
          Throwable failure) {
        log.info("afterBulk fail message:{}",failure.getMessage());
      }
    };
    BulkProcessor.Builder builder = BulkProcessor.builder(
        (request, bulkListener) ->
            client.bulkAsync(request, RequestOptions.DEFAULT, bulkListener),
        listener);
    // 根据当前添加的操作数设置何时刷新新的批量请求（默认为1000，使用-1禁用它）
    builder.setBulkActions(500);
    // 根据当前添加的操作大小设置何时刷新新的批量请求（默认为5Mb，使用-1禁用它
    builder.setBulkSize(new ByteSizeValue(1L, ByteSizeUnit.MB));
    // 设置允许执行的并发请求数（默认为1，使用0仅允许执行单个请求）
    builder.setConcurrentRequests(2);
    // 设置刷新间隔，BulkRequest如果间隔超过，则刷新所有未决（默认值未设置）
    builder.setFlushInterval(TimeValue.timeValueSeconds(50L));
    // 设置一个恒定的退避策略，该策略最初等待1秒，然后重试3次
    builder.setBackoffPolicy(BackoffPolicy
        .constantBackoff(TimeValue.timeValueSeconds(1L), 3));
    return builder.build();
  }

  private HttpHost createHttpHost (String uri) {
    try {
      return createHttpHost(URI.create(uri));
    } catch (IllegalArgumentException ex) {
      return HttpHost.create(uri);
    }
  }

  private HttpHost createHttpHost (URI uri) {
    if (!StringUtils.hasLength(uri.getUserInfo())) {
      return HttpHost.create(uri.toString());
    }
    try {
      return HttpHost.create(new URI(uri.getScheme(), null, uri.getHost(), uri.getPort(), uri.getPath(),
          uri.getQuery(), uri.getFragment()).toString());
    } catch (URISyntaxException ex) {
      throw new IllegalStateException(ex);
    }
  }
}
