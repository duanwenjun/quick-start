package com.duan.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author duanwj
 */
@Configuration
@MapperScan("com.duan.mapper")
public class MybatisPlusConfig {

  /**
   * 分页插件
   *
   * @return PaginationInterceptor
   */
  @Bean
  public PaginationInnerInterceptor paginationInnerInterceptor () {
    return new PaginationInnerInterceptor();
  }

  @Bean
  public EasySqlInjector easySqlInjector () {
    return new EasySqlInjector();
  }
}
