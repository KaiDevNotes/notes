package root.configuration;

import java.net.DatagramSocket;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import root.application.RetransmissionService;
import root.infrastructure.UdpSocketFactory;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration
{
    private final DestinationProperties properties;

    @Bean
    public RetransmissionService retransmissionService()
    {
        GenericObjectPoolConfig<DatagramSocket> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(properties.getConnectionPool().getMaxIdle());
        poolConfig.setMaxTotal(properties.getConnectionPool().getMaxTotal());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestOnCreate(true);

        GenericObjectPool<DatagramSocket> udpSocketPool = 
            new GenericObjectPool<>(new UdpSocketFactory(), poolConfig);

        return new RetransmissionService(udpSocketPool, properties);
    }
}
