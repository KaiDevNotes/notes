package ssp.order.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ssp.order.application.OrderService;
import ssp.order.application.command.CreateOrder;
import ssp.order.domain.serviceorder.ServiceOrder;
import ssp.order.domain.serviceorder.ServiceOrderRepository;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;
    private final ServiceOrderRepository serviceOrderRepository;
    
    @GetMapping
    public List<ServiceOrder> getAll()
    {
        return serviceOrderRepository.findAll();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@Valid @RequestBody CreateOrder command)
    {
        log.info("[Order Subdomain]: API endpoint [POST /api/v1/orders] was called");
        
        orderService.createOrder(command);
    }
}
