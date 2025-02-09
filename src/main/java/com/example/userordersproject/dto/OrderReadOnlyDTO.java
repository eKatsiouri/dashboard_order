package com.example.userordersproject.dto;

import com.example.userordersproject.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReadOnlyDTO {
    private Long id;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Double totalPrice;
    private Long userId;
    private  UserReadOnlyDTO user;
    private List<OrderProductReadOnlyDTO> orderProducts;
}
