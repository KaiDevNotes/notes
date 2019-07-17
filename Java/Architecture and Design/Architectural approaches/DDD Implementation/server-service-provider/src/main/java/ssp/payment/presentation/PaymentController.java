package ssp.payment.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.payment.application.PaymentService;
import ssp.payment.domain.PaymentNotification;
import ssp.payment.domain.PaymentSettings;
import ssp.payment.domain.stubpaymentsystem.StubPaymentNotification;

@RestController
@RequestMapping("/api/v1/payments/system")
@Slf4j
@RequiredArgsConstructor
public class PaymentController
{
    private final PaymentService paymentService;
    
    @GetMapping("/{paymentSystemName}/settings")
    public PaymentSettings getPaymentSettings(@PathVariable String paymentSystemName)
    {
        return paymentService.getPaymentSettings(paymentSystemName);
    }
    
    @PostMapping("/stubpaymentsystem/notification")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void processNotification(@RequestParam("orderId") String orderId)
    {
        log.info("[Payment Subdomain]: API endpoint [POST /api/v1/payments/system/stubpaymentsystem/notification] was called");
        
        PaymentNotification notification = new StubPaymentNotification(orderId);
        paymentService.processNotification(notification);
    }
}
