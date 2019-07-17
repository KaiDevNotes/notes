package ssp.order.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.common.application.event.EventBus;
import ssp.common.application.event.order.OrderCreated;
import ssp.common.application.event.order.OrderFulfilled;
import ssp.common.application.event.order.OrderPayed;
import ssp.order.application.command.CreateOrder;
import ssp.order.application.command.MarkOrderAsFulfilled;
import ssp.order.application.command.MarkOrderAsPayed;
import ssp.order.domain.serviceorder.AccountId;
import ssp.order.domain.serviceorder.ServerId;
import ssp.order.domain.serviceorder.ServiceOrder;
import ssp.order.domain.serviceorder.ServiceOrderId;
import ssp.order.domain.serviceorder.ServiceOrderRepository;
import ssp.order.domain.serviceorder.ServiceUserId;
import ssp.order.domain.subscriptionplan.SubscriptionPlan;
import ssp.order.domain.subscriptionplan.SubscriptionPlanId;
import ssp.order.domain.subscriptionplan.SubscriptionPlanRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService
{
    private static final String ORDER_NOT_FOUND_MSG_MSG_FORMAT = "ServiceOrder with id [%s] does not exist.";
    private static final String SUBSCRIPTION_PLAN_NOT_FOUND_MSG_FORMAT = "SubscriptionPlan with id [%s] does not exist.";
    private static final String SUBSCRIPTION_INACTIVE_MSG_FORMAT = "ServiceOrder cannot be created based on INACTIVE SubscriptionPlan [%s].";
    
    private final ServiceOrderRepository serviceOrderRepository;
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final EventBus eventBus;
    
    public void createOrder(CreateOrder command)
    {
        log.info("[Order Subdomain]: CreateOrder command was received");
        
        SubscriptionPlan subscriptionPlan = subscriptionPlanRepository.findById(new SubscriptionPlanId(command.getSubscriptionPlanId()))
            .orElseThrow(() -> new RuntimeException(String.format(SUBSCRIPTION_PLAN_NOT_FOUND_MSG_FORMAT, command.getSubscriptionPlanId())));
        
        if (subscriptionPlan.isNotActive())
        {
            throw new RuntimeException(String.format(SUBSCRIPTION_INACTIVE_MSG_FORMAT, subscriptionPlan.getId().getValue()));
        }
        
        ServiceOrder order = ServiceOrder.builder()
                                         .accountId(new AccountId(command.getAccountId()))
                                         .serverId(new ServerId(command.getServerId()))
                                         .priceInUsd(subscriptionPlan.getPriceInUsd())
                                         .build();
        
        serviceOrderRepository.save(order);
        
        log.info("[Order Subdomain]: OrderCreated event will be published");
        
        eventBus.publish(
            OrderCreated.builder()
                .orderId(order.getId().getValue())
                .accountId(order.getAccountId().getValue())
                .serverId(order.getServerId().getValue())
                .priceInUsd(order.getPriceInUsd())
                .build());
    }
    
    public void markOrderAsPayed(MarkOrderAsPayed command)
    {
        log.info("[Order Subdomain]: MarkOrderAsPayed command was received");
        
        ServiceOrder order = serviceOrderRepository.findById(new ServiceOrderId(command.getOrderId()))
            .orElseThrow(() -> new RuntimeException(String.format(ORDER_NOT_FOUND_MSG_MSG_FORMAT, command.getOrderId())));
        
        order.markAsPayed();
        
        serviceOrderRepository.save(order);
        
        log.info("[Order Subdomain]: OrderPayed event will be published");
        
        eventBus.publish(
            OrderPayed.builder()
                .orderId(order.getId().getValue())
                .serverId(order.getServerId().getValue())
                .build());
    }
    
    public void markOrderAsFulfilled(MarkOrderAsFulfilled command)
    {
        log.info("[Order Subdomain]: MarkOrderAsFulfilled command was received");
        
        ServiceOrder order = serviceOrderRepository.findById(new ServiceOrderId(command.getOrderId()))
            .orElseThrow(() -> new RuntimeException(String.format(ORDER_NOT_FOUND_MSG_MSG_FORMAT, command.getOrderId())));
        
        order.markAsFulfilled(new ServiceUserId(command.getServiceUserId()));
        
        serviceOrderRepository.save(order);
        
        log.info("[Order Subdomain]: OrderFulfilled event will be published");
        
        eventBus.publish(
            OrderFulfilled.builder()
                .orderId(order.getId().getValue())
                .serviceUserId(order.getServiceUserId().getValue())
                .accountId(order.getAccountId().getValue())
                .build());
    }
}
