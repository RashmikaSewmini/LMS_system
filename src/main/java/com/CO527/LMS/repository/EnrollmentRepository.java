package com.CO527.LMS.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.CO527.LMS.model.Enrollment;


public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    
}
