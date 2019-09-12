package ssp.common.application.event.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ssp.common.application.event.Event;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderFulfilled implements Event
{
    private String orderId;
    private String serviceUserId;
    private String accountId;
}
