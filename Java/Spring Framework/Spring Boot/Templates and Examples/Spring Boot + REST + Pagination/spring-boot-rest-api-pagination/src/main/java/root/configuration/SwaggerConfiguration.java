package root.configuration;

import static java.util.Collections.singletonList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.any;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.ImmutableMap;

import io.swagger.annotations.ApiParam;
import root.paging.PageRequest;
import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRuleConvention;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration
{
    @Bean
    public Docket api(@Value("${swagger.title}") String title,
                      @Value("${swagger.version}") String version)
    { 
        return new Docket(DocumentationType.SWAGGER_2) 
                    .apiInfo(new ApiInfoBuilder()
                        .title(title)
                        .version(version)
                        .build())
                    .select()
                    .apis(any())
                    .paths(regex("/api.*"))
                    .build();
    }
    
    @Bean
    public AlternateTypeRuleConvention pageableConvention(TypeResolver resolver)
    {
        return new AlternateTypeRuleConvention()
        {
            @Override
            public int getOrder()
            {
                return Ordered.HIGHEST_PRECEDENCE;
            }

            @Override
            public List<AlternateTypeRule> rules()
            {
                return singletonList(newRule(resolver.resolve(PageRequest.class), resolver.resolve(pageableMixin())));
            }
        };
    }

    private static Type pageableMixin() 
    {
        return new AlternateTypeBuilder()
                .fullyQualifiedClassName(String.format("%s.generated.%s",
                        PageRequest.class.getPackage().getName(),
                        PageRequest.class.getSimpleName())
                )
                .property(property(Integer.class, "limit", ImmutableMap.of("value", "Maximum number of elements to be returned.")))
                .property(property(Integer.class, "offset", ImmutableMap.of("value", "Number of elements to be skipped.")))
                .property(property(String.class, "sort", ImmutableMap.of("value", "Order of elements. Syntax: `fieldName,(asc|desc)`.")))
                .build();
    }

    private static AlternateTypePropertyBuilder property(Class<?> type, String name, Map<String, Object> parameters) 
    {
        return new AlternateTypePropertyBuilder()
                .withName(name)
                .withType(type)
                .withCanRead(true)
                .withCanWrite(true)
                .withAnnotations(singletonList(AnnotationProxy.of(ApiParam.class, parameters)));
    }
}
