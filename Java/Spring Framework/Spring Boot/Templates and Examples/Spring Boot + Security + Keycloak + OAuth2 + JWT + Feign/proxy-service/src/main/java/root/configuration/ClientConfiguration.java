package root.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import root.infrastructure.client.BackendServiceClient;
import root.infrastructure.client.FeignClientBuilder;

@Configuration
@RequiredArgsConstructor
public class ClientConfiguration
{
    private final ObjectMapper objectMapper;
    private final KeycloakProperties properties;

    @Bean
    public BackendServiceClient backendServiceClient(@Value("${backend-service-url}") String backendServiceUrl)
    {
        return new FeignClientBuilder<>(BackendServiceClient.class, objectMapper, "http://localhost:8881")
            .requestInterceptor(oAuth2FeignRequestInterceptor())
            .build();
    }

    private OAuth2FeignRequestInterceptor oAuth2FeignRequestInterceptor()
    {
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri(properties.getTokentUrl());
        details.setClientId(properties.getBackendClientId());
        details.setClientSecret(properties.getBackendClientSecret());
        
        OAuth2FeignRequestInterceptor interceptor = 
            new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), details);
        interceptor.setAccessTokenProvider(new ClientCredentialsAccessTokenProvider());
        
        return interceptor;
    }
}
