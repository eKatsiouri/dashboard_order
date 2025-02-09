package com.example.userordersproject.mapper;

import com.example.userordersproject.dto.*;
import com.example.userordersproject.model.Order;
import com.example.userordersproject.model.OrderProduct;
import com.example.userordersproject.model.Product;
import com.example.userordersproject.model.User;
import com.example.userordersproject.repository.OrderProductRepository;
import com.example.userordersproject.repository.OrderRepository;
import com.example.userordersproject.repository.ProductRepository;
import com.example.userordersproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mapper {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        User user = new User();
        user.setFirstName(userInsertDTO.getFirstName());
        user.setLastName(userInsertDTO.getLastName());
        user.setEmail(userInsertDTO.getEmail());
        user.setPassword(userInsertDTO.getPassword());
        user.setPhoneNumber(userInsertDTO.getPhoneNumber());
        return user;
    }

    public UserReadOnlyDTO mapToUserReadOnlyDTO(User user) {
        UserReadOnlyDTO userReadOnlyDTO = new UserReadOnlyDTO();
        userReadOnlyDTO.setUsername(user.getUsername());
        userReadOnlyDTO.setFirstName(user.getFirstName());
        userReadOnlyDTO.setLastName(user.getLastName());
        userReadOnlyDTO.setEmail(user.getEmail());
        userReadOnlyDTO.setPhoneNumber(user.getPhoneNumber());
        return userReadOnlyDTO;
    }


    public ProductReadOnlyDTO mapToProductReadOnlyDTO(Product product) {
        ProductReadOnlyDTO productReadOnlyDTO = new ProductReadOnlyDTO();
        productReadOnlyDTO.setName(product.getName());
        productReadOnlyDTO.setPrice(product.getPrice());
        productReadOnlyDTO.setDescription(product.getDescription());
        return productReadOnlyDTO;
    }


    public OrderReadOnlyDTO mapToOrderReadOnlyDTO(Order order) {
        OrderReadOnlyDTO orderReadOnlyDTO = new OrderReadOnlyDTO();
        orderReadOnlyDTO.setId(order.getId());
        orderReadOnlyDTO.setOrderDate(order.getOrderDate());
        orderReadOnlyDTO.setStatus(order.getStatus());
        orderReadOnlyDTO.setTotalPrice(order.getTotalPrice());

        List<OrderProductReadOnlyDTO> orderProductDTOs = order.getOrderProducts().stream()
                .map(this::mapToOrderProductReadOnlyDTO)
                .collect(Collectors.toList());

        orderReadOnlyDTO.setOrderProducts(orderProductDTOs);
        return orderReadOnlyDTO;
    }


    public OrderProductReadOnlyDTO mapToOrderProductReadOnlyDTO(OrderProduct orderProduct) {
        OrderProductReadOnlyDTO orderProductReadOnlyDTO = new OrderProductReadOnlyDTO();
        orderProductReadOnlyDTO.setId(orderProduct.getId());
        orderProductReadOnlyDTO.setOrderId(orderProduct.getOrder().getId());
        orderProductReadOnlyDTO.setProductId(orderProduct.getProduct().getId());
        orderProductReadOnlyDTO.setProductName(orderProduct.getProduct().getName());
        orderProductReadOnlyDTO.setQuantity(orderProduct.getQuantity());
        orderProductReadOnlyDTO.setPriceAtOrderTime(orderProduct.getPriceAtOrderTime());
        return orderProductReadOnlyDTO;

    }
}
