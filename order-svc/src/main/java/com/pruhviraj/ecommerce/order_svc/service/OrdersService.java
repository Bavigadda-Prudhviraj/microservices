package com.pruhviraj.ecommerce.order_svc.service;

import com.pruhviraj.ecommerce.order_svc.dto.OrderRequestDto;
import com.pruhviraj.ecommerce.order_svc.entites.Orders;
import com.pruhviraj.ecommerce.order_svc.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;

    public List<OrderRequestDto> getAllOrder() {
        log.info("Fetching all the Orders");
        List<Orders> allOrder = ordersRepository.findAll();
        return allOrder.
                stream().
                map(orders -> modelMapper.map(orders, OrderRequestDto.class)).
                toList();

    }

    public OrderRequestDto getOrderById(@PathVariable long id) {
        log.info("Fetching the oder with Id : {}", id);
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new RuntimeException("Order doesn't exist with given id :" + id));
        return modelMapper.map(orders, OrderRequestDto.class);
    }
}
