package ssp.order.application.event;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.common.application.event.Event;
import ssp.common.application.event.EventBus;
import ssp.common.application.event.payment.PaymentCompleted;
import ssp.common.application.event.serviceuser.ServiceUserCreated;
import ssp.order.application.OrderService;
import ssp.order.application.command.MarkOrderAsFulfilled;
import ssp.order.application.command.MarkOrderAsPayed;

@Component("orderSubdomainEventListener")
@Slf4j
@RequiredArgsConstructor
public class EventListener
{
    private final EventBus eventBus;
    private final OrderService orderService;
    
    public void init()
    {
        eventBus.subscribe(PaymentCompleted.class, this::handlePaymentCompletedEvent);
        eventBus.subscribe(ServiceUserCreated.class, this::handleServiceUserCreatedEvent);
    }
    
    private void handlePaymentCompletedEvent(Event event)
    {
        log.info("[Order Subdomain]: PaymentCompleted event was received");
        
        PaymentCompleted paymentCompletedEvent = (PaymentCompleted) event;
        MarkOrderAsPayed command = new MarkOrderAsPayed(paymentCompletedEvent.getOrderId());
        orderService.markOrderAsPayed(command);
    }
    
    private void handleServiceUserCreatedEvent(Event event)
    {
        log.info("[Order Subdomain]: ServiceUserCreated event was received");
        
        ServiceUserCreated serviceUserCreatedEvent = (ServiceUserCreated) event;
        MarkOrderAsFulfilled command = MarkOrderAsFulfilled.builder()
                                                           .orderId(serviceUserCreatedEvent.getOrderId())
                                                           .serviceUserId(serviceUserCreatedEvent.getServiceUserId())
                                                           .build();
        orderService.markOrderAsFulfilled(command);
    }
}
