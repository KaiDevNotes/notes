package ssp.order.infrastructure.persistence.serviceorder;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ssp.order.domain.serviceorder.ServiceOrder;

@Entity
@Table(name = "service_order")
@Access(AccessType.FIELD)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class ServiceOrderDbEntry
{
    @Id
    @NonNull
    private UUID id;
    
    @NonNull
    private String accountId;
    
    @NonNull
    private String serverId;
    
    private String serviceUserId;
    
    @NonNull
    private BigDecimal priceInUsd;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private ServiceOrder.Status status;
}
