package gr.mindthecode.findtheroad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class swaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("gr.mindthecode.findtheroad"))
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        String title = "Springboot - Find the Road API";
        String description = "Here is the documentation for the Springboot - Find the Road project";
        String version = "1.0";
        String termsOfServiceURL = "Free to use";
        String contactName = "Springboot - Find the Road team";
        String contactURL = "https://github.com/SteliosGeorgalas/SpringBoot-Find-The-Road";
        String contactEmail = "todo@email.com";
        Contact contact = new Contact(contactName, contactURL, contactEmail);
        String license = "Apache 2.0";
        String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0";

        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .termsOfServiceUrl(termsOfServiceURL)
                .contact(contact).license(license)
                .licenseUrl(licenseUrl)
                .extensions(Collections.emptyList())
                .build();

        /*
        return new ApiInfo(title,
                description,
                version,
                termsOfServiceURL,
                contact,
                license,
                licenseUrl,
                Collections.emptyList()
        );
        */
    }
}
