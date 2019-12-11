package root.configuration;

import static java.util.Collections.singletonList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.any;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{
    private static final String OAUTH2_SCHEMA = "oauth2schema";

    @Bean
    public Docket api(@Value("${swagger.title}") String title,
                      @Value("${swagger.version}") String version,
                      @Value("${keycloak.tokent-url}") String tokenUrl)
    { 
        return new Docket(DocumentationType.SWAGGER_2) 
                    .apiInfo(new ApiInfoBuilder()
                        .title(title)
                        .version(version)
                        .build())
                    .select()
                    .apis(any())
                    .paths(regex("/api.*"))
                    .build()
                    .securitySchemes(singletonList(securitySchema(tokenUrl)))
                    .securityContexts(singletonList(securityContext()));
    }

    private OAuth securitySchema(String tokenUrl) 
    {
        List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        List<GrantType> grantTypes = singletonList(
            new ResourceOwnerPasswordCredentialsGrant(tokenUrl));
        return new OAuth(OAUTH2_SCHEMA, authorizationScopeList, grantTypes);
    }

    private SecurityContext securityContext()
    {
        return SecurityContext.builder()
            .securityReferences(singletonList(new SecurityReference(OAUTH2_SCHEMA, new AuthorizationScope[0])))
            .forPaths(PathSelectors.ant("/**"))
            .build();
    }

    @Bean
    public SecurityConfiguration securityInfo(@Value("${keycloak.frontend-client-id}") String clientId)
    {
        return SecurityConfigurationBuilder.builder().clientId(clientId).build();
    }
}
