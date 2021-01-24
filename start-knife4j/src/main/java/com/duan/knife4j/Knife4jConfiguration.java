package com.duan.knife4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author duanwj
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

  @Bean
  public Docket defaultApi () {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(groupApiInfo())
        //分组名称
        .groupName("1.0")
        .select()
        //这里指定Controller扫描包路径
        .apis(RequestHandlerSelectors.basePackage("com.duan.knife4j"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo groupApiInfo () {
    return new ApiInfoBuilder()
        .title("接口文档 title")
        .description("接口文档 description")
        .termsOfServiceUrl("https://github.com/duanwenjun")
        .contact(new Contact("duanwenjun", "https://github.com/duanwenjun", "duanwj@139.com"))
        .version("1.0")
        .build();
  }
}
