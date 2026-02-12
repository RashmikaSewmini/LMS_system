package com.CO527.LMS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration // mean this class is a configuration class, and it will be used to define beans and other configurations for the application.
public class PasswordConfig {

    @Bean // indicates that this method produces a bean to be managed by the Spring container.
    public PasswordEncoder encoder(){ // defines a bean of type PasswordEncoder.
        return new BCryptPasswordEncoder(); // returns an instance of BCryptPasswordEncoder, which is a specific implementation of PasswordEncoder that uses the BCrypt hashing function to encode passwords.
    }

    
}
