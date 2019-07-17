package ssp.order.infrastructure.persistence.subscriptionplan;

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
import ssp.order.domain.subscriptionplan.SubscriptionPlan;

@Entity
@Table(name = "subscription_plan")
@Access(AccessType.FIELD)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class SubscriptionPlanDbEntry
{
    @Id
    @NonNull
    private UUID id;
    
    @NonNull
    private String name;
    
    @NonNull
    private BigDecimal priceInUsd;
    
    @NonNull
    @Enumerated(EnumType.STRING)
    private SubscriptionPlan.Status status;
}
