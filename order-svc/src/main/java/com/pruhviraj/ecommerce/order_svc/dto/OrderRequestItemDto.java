package com.pruhviraj.ecommerce.order_svc.dto;

import lombok.Data;

@Data
public class OrderRequestItemDto {
    private Long id;
    private Long productId;
    private Integer quantity;
}
