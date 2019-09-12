package ssp.common.application.event.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssp.common.application.event.Event;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentCompleted implements Event
{
    private String orderId;
}
