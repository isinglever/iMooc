package i.mooc.config;

import io.swagger.annotations.Api;
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

@Configuration
@EnableSwagger2
public class Swagger2 {
    
//    http://localhost:8088/swagger-ui.html
    @Bean
    public Docket creatRestApi() {
        
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage("i.mooc.controller"))
                .paths(PathSelectors.any())
                .build();
        
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("iMooc api info")
                .contact(new Contact("ori","orikuo.ml","hi@orikuo.ml"))
                .description("api info")
                .version("1.0")
                .termsOfServiceUrl("orikuo.ml")
                .build();
    }
}
