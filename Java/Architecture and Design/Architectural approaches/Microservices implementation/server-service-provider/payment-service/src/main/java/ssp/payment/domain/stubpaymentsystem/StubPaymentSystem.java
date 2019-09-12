package ssp.payment.domain.stubpaymentsystem;

import ssp.payment.domain.PaymentNotification;
import ssp.payment.domain.PaymentSettings;
import ssp.payment.domain.PaymentSystem;

public class StubPaymentSystem implements PaymentSystem
{
    @Override
    public PaymentSettings getPaymentSettings()
    {
        return PaymentSettings.builder()
                              .paymentUrl("https://stubpayments.com/payments")
                              .notificationUrl("/api/v1/payments/system/stubpaymentsystem/notification")
                              .build();
    }
    
    @Override
    public boolean isPaymentCompleted(PaymentNotification notification)
    {
        return true;
    }
}
