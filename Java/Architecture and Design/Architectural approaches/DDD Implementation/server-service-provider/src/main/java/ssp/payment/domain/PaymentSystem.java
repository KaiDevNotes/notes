package ssp.payment.domain;

public interface PaymentSystem
{
    PaymentSettings getPaymentSettings();
    
    boolean isPaymentCompleted(PaymentNotification notification);
}
