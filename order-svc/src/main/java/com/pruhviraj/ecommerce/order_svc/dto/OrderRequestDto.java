package com.pruhviraj.ecommerce.order_svc.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private List<OrderRequestDto> items;
    private BigDecimal totalPrice;
}
