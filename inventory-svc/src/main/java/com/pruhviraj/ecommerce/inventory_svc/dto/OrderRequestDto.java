package com.pruhviraj.ecommerce.inventory_svc.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDto {
    private List<OrderRequestItemDto> items;
}
