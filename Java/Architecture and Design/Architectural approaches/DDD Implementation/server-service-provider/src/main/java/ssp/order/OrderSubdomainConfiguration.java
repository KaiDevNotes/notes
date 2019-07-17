package ssp.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ssp.order.application.event.EventListener;

@Configuration
public class OrderSubdomainConfiguration
{
    @Bean("orderSubdomainEventListenerInitializer")
    public CommandLineRunner eventListenerInitializer(@Autowired EventListener eventLisener)
    {
        return new CommandLineRunner()
        {
            @Override
            public void run(String... args)
            {
                eventLisener.init();
            }
        };
    }
}
