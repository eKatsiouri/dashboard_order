package com.example.userordersproject.contoller;

import com.example.userordersproject.dto.OrderReadOnlyDTO;
import com.example.userordersproject.dto.ProductReadOnlyDTO;
import com.example.userordersproject.dto.UserReadOnlyDTO;
import com.example.userordersproject.exceptions.UserNotFoundException;
import com.example.userordersproject.mapper.Mapper;
import com.example.userordersproject.model.Order;
import com.example.userordersproject.model.Product;
import com.example.userordersproject.model.User;
import com.example.userordersproject.model.enums.OrderStatus;
import com.example.userordersproject.repository.OrderRepository;
import com.example.userordersproject.service.OrderServiceImpl;
import com.example.userordersproject.service.ProductServiceImpl;
import com.example.userordersproject.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
@AllArgsConstructor
public class DashboardController {

    private final UserServiceImpl userService;
    private final OrderServiceImpl orderService;
    private final ProductServiceImpl productService;
    private final OrderRepository orderRepository;
    private final Mapper mapper;

    @GetMapping("/dashboard")
    public String dashboardPage(Model model) throws UserNotFoundException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        User user = userService.getUserByUsername(username);
        UserReadOnlyDTO currentUser = mapper.mapToUserReadOnlyDTO(user);
        model.addAttribute("currentUser", currentUser);

        List<OrderReadOnlyDTO> orders = orderService.getOrdersByUser(user.getId());
        model.addAttribute("orders", orders);

        List<ProductReadOnlyDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "dashboard";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard( @RequestParam(required = false) String username,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) Long orderId  ,
                                  @RequestParam(required = false) String productName,
                                  @RequestParam(defaultValue = "0") int page, Model model) throws UserNotFoundException {



        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(currentUsername);
        UserReadOnlyDTO currentUser = mapper.mapToUserReadOnlyDTO(user);
        if (currentUser == null) {
            throw new UserNotFoundException("User not found for username: " + currentUsername);
        }
        model.addAttribute("currentUser", currentUser);

        Pageable pageable = PageRequest.of(page, 10);
        Page<Order> orders = orderRepository.findAll(pageable);



       if (username != null && !username.isEmpty()) {
            orders = orderRepository.findByUser_UsernameOrUser_Email(username, email, pageable);
        }

        if (orderId != null) {
            orders = orderRepository.findById(orderId, pageable);
        }

        if (productName != null && !productName.isEmpty()) {
            orders = orderRepository.findByOrderProducts_Product_NameContainingIgnoreCase(productName, pageable);
        }
        Page<OrderReadOnlyDTO> ordersDTOPage = orders.map(mapper::mapToOrderReadOnlyDTO);


        List<ProductReadOnlyDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("orders", orders);
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("orderId", orderId);
        model.addAttribute("productName", productName);

        return "admin-dashboard";
    }
}