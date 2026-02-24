package com.example.Order.controller;

import com.example.Order.dto.OrderRequest;
import com.example.Order.dto.OrderResponse;
import com.example.Order.entity.OrderStatus;
import com.example.Order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponse createOrder(
           @RequestBody OrderRequest orderRequest
    )
    {
        return orderService.createOrder(orderRequest);
    }

    //getById
    @GetMapping("/{id}")
    public OrderResponse getOrder(
            @PathVariable Long id
    )
    {
        OrderResponse order=orderService.getOrderById(id);
        return order;
    }

    //getAll
    @GetMapping
    public List<OrderResponse> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    //delete
    @DeleteMapping("/{id}")
    public void deleteOrder(
            @PathVariable Long id
    )
    {
        orderService.deleteOrder(id);
    }
    //updateStatus
    @PutMapping("/{id}/status")
    public OrderResponse updateOrder(
            @PathVariable Long id,
            @RequestParam OrderStatus status
            )
    {
        return orderService.updateStatus(id, status);
    }
}
