package com.example.Order.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderRequest {

    @NonNull
    private Long userId;

    @NonNull
    private BigDecimal totalAmount;

    // getters & setters


    public OrderRequest(@NonNull Long userId, @NonNull BigDecimal totalAmount) {
        this.userId = userId;
        this.totalAmount = totalAmount;
    }
}