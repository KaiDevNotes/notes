package ssp.payment.domain;

public interface PaymentNotification
{    
    String getOrderId();
    PaymentSystemType getPaymentSystemType();
}
