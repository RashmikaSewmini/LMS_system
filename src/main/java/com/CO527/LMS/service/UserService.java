package com.CO527.LMS.service;

import java.util.List;

import com.CO527.LMS.dto.LoginRequestDTO;
import com.CO527.LMS.dto.UserRequestDTO;
import com.CO527.LMS.dto.UserResponseDTO;
import com.CO527.LMS.model.User;

public interface UserService {
    List<User> getAllUsers();
    User getUserByEmail(String email);
    User updateUser(String id,User user);
    void delete(String id);
    UserResponseDTO create(UserRequestDTO dto);
    String login(LoginRequestDTO dto);


}
