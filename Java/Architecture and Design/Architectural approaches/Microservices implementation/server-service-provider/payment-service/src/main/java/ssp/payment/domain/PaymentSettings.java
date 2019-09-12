package ssp.payment.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class PaymentSettings
{
    @NonNull
    private final String paymentUrl;
    @NonNull
    private final String notificationUrl;
}
