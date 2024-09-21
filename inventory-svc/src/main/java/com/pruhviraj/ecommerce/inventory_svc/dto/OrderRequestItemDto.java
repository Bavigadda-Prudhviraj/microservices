package com.pruhviraj.ecommerce.inventory_svc.dto;

import lombok.Data;

@Data
public class OrderRequestItemDto {
    private long productId;
    private Integer quantity;
}
