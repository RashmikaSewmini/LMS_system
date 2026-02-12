package com.CO527.LMS.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.CO527.LMS.model.Course;


public interface CourseRepository extends MongoRepository<Course, String> {
    Optional<Course> findByCourseCode(String courseCode);

    
}
