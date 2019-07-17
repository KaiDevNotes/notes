package ssp.common.application.event.payment;

import lombok.Value;
import ssp.common.application.event.Event;

@Value
public class PaymentCompleted implements Event
{
    private final String orderId;
}
