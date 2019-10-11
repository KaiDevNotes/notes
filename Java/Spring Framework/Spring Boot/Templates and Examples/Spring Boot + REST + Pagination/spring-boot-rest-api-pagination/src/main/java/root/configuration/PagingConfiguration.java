package root.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import root.paging.PageRequestHandlerMethodArgumentResolver;

@Configuration
@RequiredArgsConstructor
public class PagingConfiguration implements WebMvcConfigurer
{
    private final SortHandlerMethodArgumentResolver sortHandlerMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(pageRequestResolver(sortHandlerMethodArgumentResolver));
    }

    @Bean
    public PageRequestHandlerMethodArgumentResolver pageRequestResolver(SortHandlerMethodArgumentResolver sortResolver)
    {
        return new PageRequestHandlerMethodArgumentResolver(sortResolver);
    }
}
