package com.pruhviraj.ecommerce.order_svc.repository;

import com.pruhviraj.ecommerce.order_svc.entites.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
}
