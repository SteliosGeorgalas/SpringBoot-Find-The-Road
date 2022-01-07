package gr.mindthecode.findtheroad;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class swaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("api/*"))
                .apis(RequestHandlerSelectors.basePackage("gr.mindthecode.findtheroad"))
                .build();
        //.apiInfo(apiDetails())
    }

    /*
    private ApiInfo apiDetails() {
        String title = "Springboot - Find the Road API";
        String description = "Here is the documentation for the Springboot - Find the Road project";
        String version = "1.0";
        String termsOfServiceURL = "Free to use";
        String contact = "Springboot - Find the Road team";
        String license = "Apache 2.0";
        String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0";

        return new ApiInfo(
                title,
                description,
                version,
                termsOfServiceURL,
                contact,
                license,
                licenseUrl
        );

    }
    */
}
