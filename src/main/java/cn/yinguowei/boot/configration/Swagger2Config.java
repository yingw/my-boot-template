package cn.yinguowei.boot.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 创建 by 殷国伟 于 2017/8/5.
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    //http://localhost:8080/v2/api-docs
    //http://localhost:8080/swagger-ui.html
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.yinguowei.boot"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MyBoot")
                .description("基于 Spring Boot 及 AdminLTE 的 Web 应用开发模板。")
                .termsOfServiceUrl("https://github.com/yingw/my-boot-template")
                .contact(new Contact("yinguowei", "https://github.com/yingw/", "yinguowei@gmail.com"))
                .version("1.0")
                .build();
    }
}
