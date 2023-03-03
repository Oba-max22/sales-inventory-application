package com.obamax.controller;

import com.obamax.payload.OrderRequest;
import com.obamax.model.Order;
import com.obamax.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@RestController
public class OrderController {

    private  final OrderService orderService;
    private final KafkaTemplate<String, Order> kafkaTemplate;

    @PostMapping("/")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.placeOrder(orderRequest);
        kafkaTemplate.send("order", order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
