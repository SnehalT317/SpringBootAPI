 package com.sample.config;

import java.util.Arrays;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


//@Configuration
//public class SwaggerConfig extends WebMvcConfigurationSupport{
	//@Value("${enable.swagger.plugin:true}")
    //private boolean enableSwaggerPlugin;
  
    /*ApiInfo apiInfo() {

        return new ApiInfoBuilder()
            .title("Swagger Car Inventory Service")
            .description("Car Inventory Service")
            .license("MIT")
            .licenseUrl("https://opensource.org/licenses/MIT")
            .version("1.0.0")
            .build();
    }

    @Bean
    public Docket customImplementation() {

      return new Docket(DocumentationType.SWAGGER_2)
          .useDefaultResponseMessages(false)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.sample.application"))
          .paths(PathSelectors.any())
          .build()
          .enable(enableSwaggerPlugin)
          .apiInfo(apiInfo());
    }*/
	 
	
	
//}
	
	

