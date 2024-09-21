package com.pruhviraj.ecommerce.inventory_svc.service;

import com.pruhviraj.ecommerce.inventory_svc.dto.ProductDto;
import com.pruhviraj.ecommerce.inventory_svc.entites.Product;
import com.pruhviraj.ecommerce.inventory_svc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventories(){
        log.info("Fetching all the inventories");
        List<Product> allProducts = productRepository.findAll();
        return allProducts.
                stream().
                map(product -> modelMapper.map(product, ProductDto.class)).toList();
    }
    public ProductDto findProductById(Long id){
        log.info("Fetching product with id : {}",id);
        Optional<Product> product = productRepository.findById(id);
        return product.map(item -> modelMapper.map(product, ProductDto.class)).orElseThrow(() -> new RuntimeException("Inventory not found with given id " + id));

    }

}
