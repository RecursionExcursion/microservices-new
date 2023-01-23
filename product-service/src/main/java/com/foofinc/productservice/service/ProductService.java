package com.foofinc.productservice.service;

import com.foofinc.productservice.dto.ProductRequest;
import com.foofinc.productservice.dto.ProductResponse;
import com.foofinc.productservice.model.Product;
import com.foofinc.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                                 .name(productRequest.getName())
                                 .description(productRequest.getDescription())
                                 .price(productRequest.getPrice())
                                 .build();

        productRepository.save(product);
        log.info(String.format("Product %s is saved", product.getId()));
//        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                       .map(this::mapToProductResponse)
                       .toList();
    }

    private ProductResponse mapToProductResponse(Product prod) {
        return ProductResponse.builder()
                              .id(prod.getId())
                              .name(prod.getName())
                              .description(prod.getDescription())
                              .price(prod.getPrice())
                              .build();
    }
}
