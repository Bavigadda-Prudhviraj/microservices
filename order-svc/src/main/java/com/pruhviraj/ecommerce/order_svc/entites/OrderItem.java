package com.pruhviraj.ecommerce.order_svc.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long productId;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

}

