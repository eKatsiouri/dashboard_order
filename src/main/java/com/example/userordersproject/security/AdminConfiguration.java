package com.example.userordersproject.security;

import com.example.userordersproject.model.User;
import com.example.userordersproject.model.enums.Role;
import com.example.userordersproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class AdminConfiguration implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("adminpassword"));
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setPhoneNumber("1234567890");
            admin.setEmail("admin@gmail.com");
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }



        if (userRepository.findByUsername("user") == null) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("userpassword"));
            user.setFirstName("John");
            user.setLastName("Doe");
            user.setPhoneNumber("0987654321");
            user.setEmail("user@gmail.com");
            user.setRole(Role.USER);
            userRepository.save(user);
        }
    }
}
