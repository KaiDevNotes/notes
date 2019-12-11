package root.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import root.infrastructure.security.KeycloakRealmRoleConverter;
import root.infrastructure.security.UsernameSubClaimAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception 
    {
        httpSecurity
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                // Necessary for access to Swagger UI
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                // Necessary roles provided via @PreAuthorize on Controller methods
                .antMatchers("/api/**").authenticated()
            .and()
            .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
    }

    JwtAuthenticationConverter jwtAuthenticationConverter()
    {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtAuthenticationConverter;
    }

    @Bean
    public JwtDecoder jwtDecoderByIssuerUri(@Value("${keycloak.certs-url}") String certsUrl)
    {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(certsUrl).build();
        // Use preferred_username from claims as authentication name, instead of UUID subject
        jwtDecoder.setClaimSetConverter(new UsernameSubClaimAdapter());
        return jwtDecoder;
    }
}