package com.obamax.service;

import com.obamax.payload.ProductRequest;
import com.obamax.model.Product;
import com.obamax.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getQuantity())
                .build();

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getQuantity());

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().filter(product -> product.getStock() > 0).toList();
    }

}
