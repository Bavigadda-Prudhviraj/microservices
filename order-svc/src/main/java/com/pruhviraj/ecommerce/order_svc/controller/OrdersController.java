package com.pruhviraj.ecommerce.order_svc.controller;


import com.pruhviraj.ecommerce.order_svc.dto.OrderRequestDto;
import com.pruhviraj.ecommerce.order_svc.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/core")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;
    private final ModelMapper modelMapper;

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){

        OrderRequestDto orderRequestDto1 = ordersService.createorder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);

    }

    @GetMapping("/helloOrders")
    public ResponseEntity<String> helloOrders(){
        return ResponseEntity.ok("orders from Order-Service");
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<List<OrderRequestDto>> getAllOrder() {
        log.info("Fetching all the Orders");
        List<OrderRequestDto> allOrder = ordersService.getAllOrder();
        List<OrderRequestDto> response = allOrder.
                stream().
                map(orders -> modelMapper.map(orders, OrderRequestDto.class)).
                toList();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/getOrderbyId/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable long id) {
        log.info("Fetching the oder with Id : {}", id);
        OrderRequestDto orders = ordersService.getOrderById(id);
        return ResponseEntity.ok(modelMapper.map(orders, OrderRequestDto.class));
    }
}
