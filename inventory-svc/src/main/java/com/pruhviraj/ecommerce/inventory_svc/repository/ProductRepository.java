package com.pruhviraj.ecommerce.inventory_svc.repository;

import com.pruhviraj.ecommerce.inventory_svc.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
