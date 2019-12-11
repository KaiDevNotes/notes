package root.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("keycloak")
public class KeycloakProperties
{
    private String authUrl;
    private String tokentUrl;
    private String certsUrl;
    private String realm;
    private String frontendClientId;
    private String backendClientId;
    private String backendClientSecret;
}
