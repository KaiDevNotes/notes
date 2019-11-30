package root.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Component
@ConfigurationProperties("destination")
public class DestinationProperties
{
    private String host;
    private int port;
    private ConnectionPool connectionPool;

    @Getter
    @Setter
    public static class ConnectionPool
    {
        private int maxIdle;
        private int maxTotal;
    }
}
