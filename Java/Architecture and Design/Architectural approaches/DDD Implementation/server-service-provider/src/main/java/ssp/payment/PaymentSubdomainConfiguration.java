package ssp.payment;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.ImmutableMap;

import ssp.payment.domain.PaymentSystem;
import ssp.payment.domain.PaymentSystemType;
import ssp.payment.domain.stubpaymentsystem.StubPaymentSystem;

@Configuration
public class PaymentSubdomainConfiguration
{
    @Bean
    public Map<PaymentSystemType, PaymentSystem> paymentSystems()
    {
        return ImmutableMap.of(
                    PaymentSystemType.STUBPAYMENTSYSTEM, new StubPaymentSystem()
               );
    }
}
