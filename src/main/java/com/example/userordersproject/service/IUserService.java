package com.example.userordersproject.service;

import com.example.userordersproject.dto.UserInsertDTO;
import com.example.userordersproject.exceptions.UserAlreadyExistsException;
import com.example.userordersproject.model.User;

public interface IUserService {
    User createUser(UserInsertDTO userInsertDTO) throws UserAlreadyExistsException;

    User getUserByUsername(String username);

}
