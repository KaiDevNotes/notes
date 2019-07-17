package ssp.payment.application;

import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.common.application.event.EventBus;
import ssp.common.application.event.payment.PaymentCompleted;
import ssp.payment.domain.PaymentNotification;
import ssp.payment.domain.PaymentSettings;
import ssp.payment.domain.PaymentSystem;
import ssp.payment.domain.PaymentSystemType;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService
{
    private static final String PAYMENT_SYSTEM_NOT_SUPPORTED_MSG = "Payment system [%s] is not supported.";
    
    private final Map<PaymentSystemType, PaymentSystem> paymentSystems;
    private final EventBus eventBus;
    
    public PaymentSettings getPaymentSettings(String paymentSystemName)
    {
        PaymentSystemType paymentSystemType = Enum.valueOf(PaymentSystemType.class, paymentSystemName.toUpperCase());
        return getPaymentSystem(paymentSystemType).getPaymentSettings();
    }
    
    public void processNotification(PaymentNotification notification)
    {
        log.info("[Payment Subdomain]: PaymentNotification was received from [{}]", notification.getPaymentSystemType());
        
        if (getPaymentSystem(notification.getPaymentSystemType()).isPaymentCompleted(notification))
        {
            log.info("[Payment Subdomain]: PaymentCompleted event will be published");
            
            eventBus.publish(
                new PaymentCompleted(notification.getOrderId()));            
        }
        else 
        {
            log.info("[Payment Subdomain]: Payment has not completed yet");
        }
    }
    
    private PaymentSystem getPaymentSystem(PaymentSystemType type)
    {
        PaymentSystem paymentSystem = paymentSystems.get(type);
        if (paymentSystem == null)
        {
            throw new RuntimeException(String.format(PAYMENT_SYSTEM_NOT_SUPPORTED_MSG, type.name()));
        }
        return paymentSystem;
    }
}
