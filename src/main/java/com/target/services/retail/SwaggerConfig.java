package com.target.services.retail;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${info.build.version}")
	private String buildVersion;

	@Bean
	public Docket api() {
		//@formatter:off
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(new ApiInfoBuilder()
				.version(buildVersion)
				.build())
			.select()
				.apis(RequestHandlerSelectors.basePackage("com.target.services.retail"))
				.paths(PathSelectors.any())
				.build();


		//@formatter:on
	}

}
