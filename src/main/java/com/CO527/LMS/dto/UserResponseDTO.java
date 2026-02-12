package com.CO527.LMS.dto;

public class UserResponseDTO {

    private String name;
    private String email;
    private String id;
    private String role;

    public UserResponseDTO(String id, String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.role = role;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getId() {
        return id;
    }
    public String getRole() {
        return role;
    }
    
}
