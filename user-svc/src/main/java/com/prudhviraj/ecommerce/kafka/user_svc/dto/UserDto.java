package com.prudhviraj.ecommerce.kafka.user_svc.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
