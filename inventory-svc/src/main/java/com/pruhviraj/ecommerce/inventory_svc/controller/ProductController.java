package com.pruhviraj.ecommerce.inventory_svc.controller;

import com.pruhviraj.ecommerce.inventory_svc.client.OrderFeignClient;
import com.pruhviraj.ecommerce.inventory_svc.dto.OrderRequestDto;
import com.pruhviraj.ecommerce.inventory_svc.dto.ProductDto;
import com.pruhviraj.ecommerce.inventory_svc.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final DiscoveryClient discoveryClient;
    //private final RestClient restClient;
    private final OrderFeignClient orderFeignClient;

    @GetMapping("/callToOrderSvcAPI")
    public ResponseEntity<String> callToOrderSvcAPI(){
       // ServiceInstance instance = discoveryClient.getInstances("order-svc").getFirst();
       // String response = restClient
        // .get()
        // .uri(instance.getUri()+"/orders/core/helloOrders")
        // .retrieve()
        // .body(String.class);
        String response = orderFeignClient.callToOrderSvcAPI();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/getAllInventories")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllInventories();
        return  ResponseEntity.ok(products);
    }

    @GetMapping("/getinventoryById/{id}")
    public  ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
        ProductDto productDto = productService.findProductById(id);
        return ResponseEntity.ok(productDto);
    }
    @PutMapping("/reduce-stock")
    public ResponseEntity<Double> reduceStock(@RequestBody OrderRequestDto orderRequestDto){
     Double totalPrice = productService.reduceStock(orderRequestDto);
        return ResponseEntity.ok(totalPrice);

    }
}
