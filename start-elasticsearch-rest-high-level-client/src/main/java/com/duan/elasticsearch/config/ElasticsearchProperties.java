package com.duan.elasticsearch.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {

  /**
   * 集群节点
   */
  private List<String> clusterNodes = new ArrayList<>(Collections.singletonList("http://localhost:9200"));

  /**
   * 连接超时时间(毫秒)
   */
  private Integer connectTimeout = 1000;

  /**
   * socket 超时时间
   */
  private Integer socketTimeout = 30000;

  /**
   * 连接请求超时时间
   */
  private Integer connectionRequestTimeout = 500;

  /**
   * 每个路由的最大连接数量
   */
  private Integer maxConnectPerRoute = 10;

  /**
   * 最大连接总数量
   */
  private Integer maxConnectTotal = 30;

  /**
   * true 写入数据到db中
   */
  private Boolean db;


  /**
   * 用户名
   */
  private String username;

  /**
   * 密码
   */
  private String password;

}
