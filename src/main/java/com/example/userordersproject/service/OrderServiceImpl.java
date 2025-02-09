package com.example.userordersproject.service;


import com.example.userordersproject.dto.OrderProductReadOnlyDTO;
import com.example.userordersproject.dto.OrderReadOnlyDTO;
import com.example.userordersproject.exceptions.UserNotFoundException;
import com.example.userordersproject.mapper.Mapper;
import com.example.userordersproject.model.Order;
import com.example.userordersproject.model.OrderProduct;
import com.example.userordersproject.model.User;
import com.example.userordersproject.repository.OrderProductRepository;
import com.example.userordersproject.repository.OrderRepository;
import com.example.userordersproject.repository.ProductRepository;
import com.example.userordersproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService{

    private OrderRepository orderRepository;
    private OrderProductRepository orderProductRepository;
    private UserRepository userRepository;
    private Mapper mapper;



    @Override
    public List<OrderProductReadOnlyDTO> getOrderDetails(Long orderId) {
        List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderId);
        return orderProducts.stream()
                .map(mapper::mapToOrderProductReadOnlyDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Page<OrderReadOnlyDTO> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(mapper::mapToOrderReadOnlyDTO);
    }

    @Transactional
    @Override
    public List<OrderReadOnlyDTO> getOrdersByUser(Long userId) throws UserNotFoundException {

        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream()
                .map(mapper::mapToOrderReadOnlyDTO)
                .collect(Collectors.toList());
    }
}

