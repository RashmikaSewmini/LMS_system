package com.CO527.LMS;


import com.CO527.LMS.model.User;
import com.CO527.LMS.repository.UserRepository;
import com.CO527.LMS.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repo; // Mocking the UserRepository

    @InjectMocks
    private UserServiceImpl service; // Injecting the mocked UserRepository into the UserServiceImpl

    @Test
    void testFindUserByEmail() {

        User u = new User(); // creating a new User object
        u.setEmail("alex@gmail.com"); // setting the email of the User object

        // Mocking the behavior of the UserRepository to return the User object when findByEmail is called with "
        when(repo.findByEmail("alex@gmail.com")).thenReturn(Optional.of(u));

        Optional<User> result = repo.findByEmail("alex@gmail.com");

        assertTrue(result.isPresent());
        assertEquals("alex@gmail.com", result.get().getEmail());
        
    }
    @Test
    void testEmail() {

        User u = new User();
        u.setEmail("alex@gmail.com");

        assertEquals("alex@gmail.com", u.getEmail());
    }
    @Test
    void testLogin(){
        User u = new User();
        u.setEmail("rash@gmail.com");
        u.setPassword("123456");

        assertEquals("rash@gmail.com", u.getEmail());
        assertEquals("123456", u.getPassword());
    }



    
}
