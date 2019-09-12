package ssp.common.application.event.order;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssp.common.application.event.Event;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreated implements Event
{
    private String orderId;
    private String accountId;
    private String serverId;
    private BigDecimal priceInUsd;
}
