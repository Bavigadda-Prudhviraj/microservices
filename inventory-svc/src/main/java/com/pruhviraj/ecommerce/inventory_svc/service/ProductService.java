package com.pruhviraj.ecommerce.inventory_svc.service;

import com.pruhviraj.ecommerce.inventory_svc.dto.OrderRequestDto;
import com.pruhviraj.ecommerce.inventory_svc.dto.OrderRequestItemDto;
import com.pruhviraj.ecommerce.inventory_svc.dto.ProductDto;
import com.pruhviraj.ecommerce.inventory_svc.entites.Product;
import com.pruhviraj.ecommerce.inventory_svc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    @Transactional
    public Double reduceStock(OrderRequestDto orderRequestDto){
        log.info("Reducing the stock");
        double totalPrice = 0.0;
        for (OrderRequestItemDto item : orderRequestDto.getItems()){
            Long productId = item.getProductId();
            Integer quantity = item.getQuantity();

            Product product = productRepository.findById(productId).orElseThrow(
                    ()-> new RuntimeException("Product doesnt exist with given ID : "+ productId)
            );

            if(product.getStock() < item.getQuantity()){
                throw new RuntimeException("Insufficient stock for product ID " + productId + ". Available quantity: " + product.getStock() + ", requested quantity: " + item.getQuantity() + ".");
            }
            product.setStock(product.getStock()-item.getQuantity());
            totalPrice += (product.getPrice()*quantity);

        }
        return totalPrice;
    }

}
