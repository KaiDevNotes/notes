package root.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@ComponentScan("root")
@EnableWebMvc
public class SpringWebAppConfig extends WebMvcConfigurerAdapter 
{
    // This Resource Handlers is needed in order to be able to open Swagger UI 
    // which is provided via "swagger-ui.html" and scripts from "webjars" folder
    // which are placed in springfox-swagger-ui-2.7.0.jar:
    // [app-name].war\WEB-INF\lib\springfox-swagger-ui-2.7.0.jar\META-INF\resources\
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}