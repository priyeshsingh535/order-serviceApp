package com.example.Order.service;

import com.example.Order.dto.OrderResponse;
import com.example.Order.dto.OrderRequest;
import com.example.Order.entity.Order;
import com.example.Order.entity.OrderStatus;
import com.example.Order.repository.OrderRepository;
import com.example.Order.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    //create
    public OrderResponse createOrder(OrderRequest orderRequest)
    {
        Order order=new Order(
                orderRequest.getUserId(),
                orderRequest.getTotalAmount(),
                OrderStatus.CREATED
        );
        Order savedOrder=orderRepository.save(order);
        return mapToResponse(savedOrder);
    }

    //getByid
    public OrderResponse getOrderById(Long id)
    {
        Order order=orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        return mapToResponse(order);
    }

    //getAll
    public List<OrderResponse> getAllOrders()
    {
        List<Order> orders=orderRepository.findAll();
        return orders.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // UPDATE status
    public OrderResponse updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        order.setStatus(status);
        Order updated = orderRepository.save(order);
        return mapToResponse(updated);
    }

    //delete
    public void deleteOrder(Long id)
    {
        orderRepository.deleteById(id);
    }

    // PRIVATE HELPER: Entity → DTO
    private OrderResponse mapToResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
