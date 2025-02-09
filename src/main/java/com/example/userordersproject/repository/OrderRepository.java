package com.example.userordersproject.repository;

import com.example.userordersproject.model.Order;
import com.example.userordersproject.model.User;
import com.example.userordersproject.model.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> , JpaSpecificationExecutor<Order> {
    List<Order> findByUserAndStatus(User user, OrderStatus status);

    Page<Order> findByStatus(OrderStatus status, Pageable pageable);
    List<Order> findByStatus(OrderStatus status);

    List<Order> findByUser(User user);

    Page<Order> findAll(Pageable pageable);
    Page<Order> findByUser_UsernameOrUser_Email(String username, String email, Pageable pageable);

    Page<Order> findById(Long orderId, Pageable pageable);

    Page<Order> findByOrderProducts_Product_NameContainingIgnoreCase(String productName, Pageable pageable);
}
