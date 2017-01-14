package org.application.list.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.apiInfo(apiInfo())// apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
				.select()// select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
				.apis(RequestHandlerSelectors.basePackage("org.application.list"))//
				.paths(PathSelectors.any())//
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Cloud中使用Swagger2构建RESTful APIs")
				.description("Spring Cloud官网请关注：http://projects.spring.io/spring-cloud/")//
				.termsOfServiceUrl("http://projects.spring.io/spring-cloud/")//
				.contact("Vincent Chen")//
				.version("1.0")//
				.build();
	}

}
