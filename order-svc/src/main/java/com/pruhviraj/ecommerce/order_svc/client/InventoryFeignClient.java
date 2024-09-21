package com.pruhviraj.ecommerce.order_svc.client;

import com.pruhviraj.ecommerce.order_svc.dto.OrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-svc", path = "/inventory")
public interface InventoryFeignClient {

    @PutMapping("/product/reduce-stock")
    public Double redcuceStock(@RequestBody OrderRequestDto orderRequestDto);
}
