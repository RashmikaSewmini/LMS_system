package com.CO527.LMS.JWT;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal( // this method is called for every incoming HTTP request. It checks the Authorization header for a Bearer token, validates it, and if valid, allows the request to proceed. If the token is invalid or missing, it simply continues without setting any authentication context.
        HttpServletRequest req, // this is the incoming HTTP request that contains headers, parameters, etc.
        HttpServletResponse res, // this is the HTTP response that will be sent back to the client after processing the request.
        FilterChain filterChain) // this is the filter chain that allows the request to proceed to the next filter or ultimately to the controller if all filters pass.
        throws ServletException, IOException, java.io.IOException { // this method can throw exceptions related to servlet processing or I/O operations.

        String header = req.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7); //remove word Bearer and space to get the actual token string.
            
            try{
                String email = jwtUtil.extractEmail(token); // validate
                String role = jwtUtil.extractRole(token); // validate

                var auth = new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + role))
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // If the token is invalid, we can choose to log the error or ignore it.    
                 res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        
        filterChain.doFilter(req, res);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();

        return path.startsWith("/users/login")
                || path.startsWith("/users/register")

                // ✅ Swagger UI (handles /swagger-ui and /swagger-ui/**)
                || path.startsWith("/swagger-ui")

                // ✅ OpenAPI docs (handles /v3/api-docs and /v3/api-docs/**)
                || path.startsWith("/v3/api-docs")

                // ✅ also allow swagger html if used
                || path.equals("/swagger-ui.html");
    }


}
