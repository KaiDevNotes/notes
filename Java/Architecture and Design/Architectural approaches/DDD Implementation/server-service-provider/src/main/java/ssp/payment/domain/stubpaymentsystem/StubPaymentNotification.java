package ssp.payment.domain.stubpaymentsystem;

import lombok.Value;
import ssp.payment.domain.PaymentNotification;
import ssp.payment.domain.PaymentSystemType;

@Value
public class StubPaymentNotification implements PaymentNotification
{    
    private final PaymentSystemType paymentSystemType = PaymentSystemType.STUBPAYMENTSYSTEM;
    private final String orderId;
}
