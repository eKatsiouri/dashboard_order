package com.example.userordersproject.service;

import com.example.userordersproject.dto.OrderProductReadOnlyDTO;
import com.example.userordersproject.dto.OrderReadOnlyDTO;
import com.example.userordersproject.exceptions.UserNotFoundException;
import com.example.userordersproject.model.Order;
import com.example.userordersproject.model.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {

    public List<OrderProductReadOnlyDTO> getOrderDetails(Long orderId);
   Page<OrderReadOnlyDTO> getAllOrders(Pageable pageable);
    public List<OrderReadOnlyDTO> getOrdersByUser(Long userId) throws UserNotFoundException;
}
