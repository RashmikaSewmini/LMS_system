package com.CO527.LMS.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.CO527.LMS.dto.LoginRequestDTO;
import com.CO527.LMS.dto.UserRequestDTO;
import com.CO527.LMS.dto.UserResponseDTO;
import com.CO527.LMS.model.User;
import com.CO527.LMS.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "User API", description = "User management")
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/profile")
    public String profile() {
        return "User Profile";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return service.getUserByEmail(email);
    }
    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        UserResponseDTO res = service.create(dto);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = service.updateUser(id, user);
        if (updatedUser != null) {
            return "User with ID: " + id + " updated to: " + updatedUser;
        } else {
            return "User with ID: " + id + " not found.";
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) { 
        // This is a placeholder. In a real application, you would delete the user from the database.
        service.delete(id);
        return ResponseEntity.ok("User with ID: " + id + " deleted.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(service.login(dto));
    }
}
