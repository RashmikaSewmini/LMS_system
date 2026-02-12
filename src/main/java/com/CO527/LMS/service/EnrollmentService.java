package com.CO527.LMS.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CO527.LMS.repository.*;
import com.CO527.LMS.model.*;

@Service
public class EnrollmentService {

    @Autowired // Dependency injection for repositories mean that Spring will automatically provide instances of these repositories when the service is created.
    private UserRepository userRepo;

    @Autowired
    private CourseRepository courseRepo;

    @Autowired
    private EnrollmentRepository enrollRepo;

    public Enrollment enrollUser(String email, String courseCode) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepo.findByCourseCode(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment();

        enrollment.setUserId(user.getId());
        enrollment.setCourseId(course.getId());

        enrollment.setEnrollmentDate(LocalDate.now().toString());
        enrollment.setStatus("ACTIVE");

        return enrollRepo.save(enrollment);
    }
}
