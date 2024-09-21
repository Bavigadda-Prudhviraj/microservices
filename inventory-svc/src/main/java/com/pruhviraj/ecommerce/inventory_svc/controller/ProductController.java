package com.pruhviraj.ecommerce.inventory_svc.controller;

import com.pruhviraj.ecommerce.inventory_svc.dto.ProductDto;
import com.pruhviraj.ecommerce.inventory_svc.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAllInventories")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllInventories();
        return  ResponseEntity.ok(products);
    }

    @GetMapping("getinventoryById/{id}")
    public  ResponseEntity<ProductDto> getInventoryById(@PathVariable Long id){
        ProductDto productDto = productService.findProductById(id);
        return ResponseEntity.ok(productDto);
    }
}
