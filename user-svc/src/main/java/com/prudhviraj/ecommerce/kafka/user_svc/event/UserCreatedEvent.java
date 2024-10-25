package com.prudhviraj.ecommerce.kafka.user_svc.event;


import lombok.Data;

@Data
public class UserCreatedEvent {
    private Long id;
    private String name;
    private String email;
}
