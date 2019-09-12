package ssp.serviceprovider.application.event;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.common.application.event.Event;
import ssp.common.application.event.EventBus;
import ssp.common.application.event.order.OrderPayed;
import ssp.serviceprovider.application.ServiceProviderService;
import ssp.serviceprovider.application.command.CreateServiceUser;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventListener
{
    private final EventBus eventBus;
    private final ServiceProviderService serviceProviderService;
    
    public void init()
    {
        eventBus.subscribe(OrderPayed.class, this::handleOrderPayedEvent);
    }
    
    private void handleOrderPayedEvent(Event event)
    {
        log.info("[ServiceProvider Core Domain]: OrderPayed event was received");
        
        OrderPayed orderPayedEvent = (OrderPayed) event;
        CreateServiceUser command = CreateServiceUser.builder()
                                                     .orderId(orderPayedEvent.getOrderId())
                                                     .serverId(orderPayedEvent.getServerId())
                                                     .build();
        serviceProviderService.createNewUser(command);
    }
}
