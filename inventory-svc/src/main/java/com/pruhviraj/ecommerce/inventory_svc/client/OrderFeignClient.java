package com.pruhviraj.ecommerce.inventory_svc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-svc", path = "/orders")
public interface OrderFeignClient {
    @GetMapping("/core/helloOrders")
    public String callToOrderSvcAPI();
}
