package ssp.order.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ssp.order.domain.subscriptionplan.SubscriptionPlan;
import ssp.order.domain.subscriptionplan.SubscriptionPlanRepository;

@RestController
@RequestMapping("/api/v1/subscription-plans")
@RequiredArgsConstructor
public class SubscriptionPlanController
{
    private final SubscriptionPlanRepository subscriptionPlanRepository;
    
    @GetMapping
    public List<SubscriptionPlan> getAll()
    {
        return subscriptionPlanRepository.findAll();
    }
}
