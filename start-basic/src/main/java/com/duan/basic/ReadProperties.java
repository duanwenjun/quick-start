package com.duan.basic;

import java.util.List;
import java.util.Map;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author duanwj
 */
@Data
@ConfigurationProperties(prefix = "demo")
@Component
public class ReadProperties {

  private String name;
  private Map<String, Object> map;
  private List<Object> stringList;
}
