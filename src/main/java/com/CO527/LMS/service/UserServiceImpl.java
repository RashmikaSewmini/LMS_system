package com.CO527.LMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CO527.LMS.JWT.JwtUtil;
import com.CO527.LMS.dto.LoginRequestDTO;
import com.CO527.LMS.dto.UserRequestDTO;
import com.CO527.LMS.dto.UserResponseDTO;
import com.CO527.LMS.exception.UserAlreadyExistsException;
import com.CO527.LMS.model.User;
import com.CO527.LMS.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    @Override
    public User updateUser(String id, User user) {
        User old = repo.findById(id).orElse(null);
        old.setEmail(user.getEmail());
        old.setName(user.getName());
        return repo.save(old);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
        
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {

        if (repo.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                "Email already registered"
            );
        }
        else{
            User s = new User();
            s.setName(dto.getName());
            s.setEmail(dto.getEmail());
            s.setPassword(encoder.encode(dto.getPassword()));

            s.setRole("USER");

            User saved = repo.save(s);

            return new UserResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole()
            );

        }
        
    }

    public String login(LoginRequestDTO dto) {

        User user = repo.findByEmail(dto.getEmail())
            .orElseThrow(() ->
                new RuntimeException("User not found")
            );

        if (!encoder.matches(
                dto.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Wrong password");
        }

        // Create token
        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }



    
}
