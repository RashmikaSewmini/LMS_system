package com.CO527.LMS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.CO527.LMS.JWT.JwtFilter;

@Configuration
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection since we're using JWT for stateless authentication.
            .authorizeHttpRequests(auth -> auth
                // Admin only
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                // User only
                .requestMatchers("/user/**")
                .hasRole("USER")

                // ✅ allow swagger
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()

                // Public
                .requestMatchers("/users/login","/users/register")
                .permitAll()


                // Others: any logged user
                .anyRequest()
                .authenticated()
            );
            http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
