package com.CO527.LMS.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.CO527.LMS.model.*;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email); // internally db.users.find({ email: "abc@gmail.com" })

 
}
