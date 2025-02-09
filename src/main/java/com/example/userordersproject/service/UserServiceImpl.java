package com.example.userordersproject.service;

import com.example.userordersproject.dto.UserInsertDTO;
import com.example.userordersproject.exceptions.UserAlreadyExistsException;
import com.example.userordersproject.mapper.Mapper;
import com.example.userordersproject.model.User;
import com.example.userordersproject.model.enums.Role;
import com.example.userordersproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;


    @Override
    public User createUser(UserInsertDTO userInsertDTO) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(userInsertDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException("Username already exists.");
        }

        if (userRepository.findByEmail(userInsertDTO.getEmail()) != null) {
            throw new UserAlreadyExistsException("Email already exists.");
        }
        String encodedPassword = passwordEncoder.encode(userInsertDTO.getPassword());


        User user = new User();
        user.setUsername(userInsertDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setFirstName(userInsertDTO.getFirstName());
        user.setLastName(userInsertDTO.getLastName());
        user.setEmail(userInsertDTO.getEmail());
        user.setPhoneNumber(userInsertDTO.getPhoneNumber());
        user.setRole(Role.USER);


        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
}
