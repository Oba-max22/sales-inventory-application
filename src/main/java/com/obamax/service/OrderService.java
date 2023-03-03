package com.obamax.service;

import com.obamax.payload.OrderRequest;
import com.obamax.model.Order;
import com.obamax.model.Product;
import com.obamax.repository.OrderRepository;
import com.obamax.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order placeOrder(OrderRequest orderRequest) {
        Product product = productRepository.findById(orderRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() == 0) {
            throw new RuntimeException("Product not available");
        }
        if (product.getStock() < orderRequest.getQuantity()) {
            throw new RuntimeException("Product stock not enough");
        }

        Order order = Order.builder()
                .productId(orderRequest.getProductId())
                .quantity(orderRequest.getQuantity())
                .customerName(orderRequest.getCustomerName())
                .customerPhoneNumber(orderRequest.getCustomerPhoneNumber())
                .totalAmount(product.getPrice() * Double.valueOf(orderRequest.getQuantity()))
                .build();

        product.setStock(product.getStock() - orderRequest.getQuantity());
        productRepository.save(product);

        return orderRepository.save(order);
    }
}
