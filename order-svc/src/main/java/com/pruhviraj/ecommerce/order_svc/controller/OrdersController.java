package com.pruhviraj.ecommerce.order_svc.controller;


import com.pruhviraj.ecommerce.order_svc.config.FeatureEnableConfig;
import com.pruhviraj.ecommerce.order_svc.dto.OrderRequestDto;
import com.pruhviraj.ecommerce.order_svc.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/core")
@RequiredArgsConstructor
@RefreshScope
public class OrdersController {
    @Value("${my.variable}")
    private String myVariable;

    private final FeatureEnableConfig featureEnableConfig;
    private final OrdersService ordersService;
    private final ModelMapper modelMapper;

    /**
     * Endpoint to greet the user and provide information about user tracking status and active profile.
     *
     * @param userId the ID of the user, retrieved from the request header "X-User-Id"
     * @return a ResponseEntity containing a message about user tracking status, user ID, and the active profile
     */
    /**
     * Endpoint to greet the user and provide information about user tracking status and active profile.
     *
     * @param userId the ID of the user, retrieved from the request header "X-User-Id"
     * @return a ResponseEntity containing a message about user tracking status, user ID, and the active profile
     */
    @GetMapping("/helloOrders")
    public ResponseEntity<String> helloOrders(@RequestHeader("X-User-Id") Long userId) {
        log.info("Received request to /helloOrders with User ID: {}", userId);

        String trackingStatus = featureEnableConfig.isUserTrackingEnabled() ? "enabled" : "disabled";
        String responseMessage = String.format(
                "User tracking is %s. Order-Service response for User ID: %d with active profile: %s",
                trackingStatus, userId, myVariable
        );

        log.info("Returning response: {}", responseMessage);
        return ResponseEntity.ok(responseMessage);
    }



    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){

        OrderRequestDto orderRequestDto1 = ordersService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);

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
