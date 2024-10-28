package com.pruhviraj.ecommerce.product_svc.product_svc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker/products")
@Slf4j
public class ProductController {
    @GetMapping("/hello")
    public String hello(){
        return "from product Controller ";
    }
}
