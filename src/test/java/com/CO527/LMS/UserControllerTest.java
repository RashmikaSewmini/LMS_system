package com.CO527.LMS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegister() throws Exception {

        String email = "john" + System.currentTimeMillis() + "@example.com";

        mockMvc.perform(post("/users/register")
                .contentType("application/json")
                .content("{\"name\":\"John Doe\",\"email\":\"" + email + "\",\"password\":\"password123\"}"))
                .andExpect(status().isCreated());


    }
    // @Test
    // void testSecurity() throws Exception{
    //     mockMvc.perform(get("/admin/dashboard")
    //     .header("Authorization", "Bearer " + token))
    //     .andExpect(status().isOk());

    // }
    
}
